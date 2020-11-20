package task_4.graphics.scene.camera;

import task_4.graphics.geometry.matrixes.NumberMatrix;
import task_4.graphics.geometry.matrixes.NumberVector;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;


public class Transform {
    private NumberMatrix translate = NumberMatrix.createIdentityMatrix(
        4, 4
    );
    private NumberMatrix rotate = NumberMatrix.createIdentityMatrix(
        4, 4
    );
    private NumberMatrix scale = NumberMatrix.createIdentityMatrix(
        4, 4
    );
    private NumberMatrix projection = NumberMatrix.createIdentityMatrix(
        4, 4
    );

    public void modifyTranslateX(double translateX) {
        modifyTranslate(translateX, 0);
    }

    public void modifyTranslateY(double translateY) {
        modifyTranslate(translateY, 1);
    }

    public void modifyTranslateZ(double translateZ) {
        modifyTranslate(translateZ, 2);
    }

    private void modifyTranslate(double translate, int axis) {
        NumberMatrix modifier = NumberMatrix.createIdentityMatrix(
            4, 4
        );
        modifier.set(axis, 3, translate);
        this.translate = modifier.multiply(this.translate);
    }

    public void modifyRotateX(double angleX) {
        modifyRotate(angleX, 0);
    }

    public void modifyRotateY(double angleY) {
        modifyRotate(angleY, 1);
    }

    public void modifyRotateZ(double angleZ) {
        modifyRotate(angleZ, 2);
    }

    private void modifyRotate(double angle, int axis) {
        NumberMatrix modifier = NumberMatrix.createIdentityMatrix(
            4, 4
        );
        int a1 = (axis + 1) % 3;
        int a2 = (axis + 2) % 3;

        modifier.set(a1, a1, Math.cos(angle));
        modifier.set(a1, a2, Math.sin(angle));
        modifier.set(a2, a1, -Math.sin(angle));
        modifier.set(a2, a2, Math.cos(angle));

        this.rotate = modifier.multiply(this.rotate);
    }

    public void modifyScaleX(double scaleX) {
        modifyScale(scaleX, 0);
    }

    public void modifyScaleY(double scaleY) {
        modifyScale(scaleY, 1);
    }

    public void modifyScaleZ(double scaleZ) {
        modifyScale(scaleZ, 2);
    }

    public void modifyScale(double scale) {
        NumberMatrix modifier = NumberMatrix.createIdentityMatrix(
            4, 4
        );
        modifier.set(0, 0, scale);
        modifier.set(1, 1, scale);
        modifier.set(2, 2, scale);
        this.scale = modifier.multiply(this.scale);
    }

    private void modifyScale(double scale, int axis) {
        NumberMatrix modifier = NumberMatrix.createIdentityMatrix(
            4, 4
        );
        modifier.set(axis, axis, scale);
        this.scale = modifier.multiply(this.scale);
    }

    public Transform modify(Transform transform) {
        Transform copiedTransform = copy();
        this.translate = this.translate.multiply(transform.translate);
        this.rotate = this.rotate.multiply(transform.rotate);
        this.scale = this.scale.multiply(transform.scale);
        this.projection = this.projection.multiply(transform.projection);
        return copiedTransform;
    }

    private Transform copy() {
        Transform copiedTransform = new Transform();
        copiedTransform.copyOf(this);
        return copiedTransform;
    }

    public void copyOf(Transform transform) {
        this.translate = copyMatrix(transform.translate);
        this.rotate = copyMatrix(transform.rotate);
        this.scale = copyMatrix(transform.scale);
        this.projection = copyMatrix(transform.projection);
    }

    private NumberMatrix copyMatrix(NumberMatrix matrix) {
        NumberMatrix copiedMatrix = NumberMatrix.createZeroMatrix(
            4, 4
        );
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                copiedMatrix.set(
                    i, j,
                    matrix.get(i, j)
                );
            }
        }
        return copiedMatrix;
    }

    public Pixel convert(Point point) {
        NumberVector vector = NumberVector.createZeroVector(4);
        vector.set(0, point.getX());
        vector.set(1, point.getY());
        vector.set(2, point.getZ());
        vector.set(3, 1);
        NumberVector pixelVector = NumberVector.fromMatrix(projection.multiply(
            translate.multiply(
                rotate.multiply(
                    scale.multiply(
                        vector
                    )
                )
            )
        ));
        return new Pixel(
            (int) Math.round(pixelVector.get(0) / pixelVector.get(3)),
            (int) Math.round(pixelVector.get(1) / pixelVector.get(3)),
            (int) Math.round(pixelVector.get(2) / pixelVector.get(3))
        );
    }
}
