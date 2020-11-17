package task_4.transforms;

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

    public void setScaleX(double scaleX) {
        scale.set(
            0, 0,
            scale.get(0, 0) * scaleX
        );
    }

    public void setScaleY(double scaleY) {
        scale.set(
            1, 1,
            scale.get(1, 1) * scaleY
        );
    }

    public void setScaleZ(double scaleZ) {
        scale.set(
            2, 2,
            scale.get(2, 2) * scaleZ
        );
    }

    public void setScale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
        setScaleZ(scale);
    }

    public Pixel convert(Point point) {
        NumberVector vector = NumberVector.createZeroVector(4);
        vector.set(0, point.getX());
        vector.set(1, point.getY());
        vector.set(2, point.getZ());
        vector.set(3, 1);
        NumberMatrix pixelMatrix = projection.multiply(
            translate.multiply(
                rotate.multiply(
                    scale.multiply(
                        vector
                    )
                )
            )
        );
        return new Pixel(
            (int) Math.round(
                pixelMatrix.get(0, 0) / pixelMatrix.get(0, 3)
            ),
            (int) Math.round(
                pixelMatrix.get(0, 1) / pixelMatrix.get(0, 3)
            ),
            (int) Math.round(
                pixelMatrix.get(0, 2) / pixelMatrix.get(0, 3)
            )
        );
    }
}
