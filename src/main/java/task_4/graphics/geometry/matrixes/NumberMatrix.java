package task_4.graphics.geometry.matrixes;

import java.util.ArrayList;
import java.util.List;


public class NumberMatrix
    extends Matrix<Double>
{
    protected NumberMatrix(List<List<Double>> values) {
        super(values);
    }

    public static NumberMatrix createIdentityMatrix(
        int numberRows,
        int numberColumns
    ) {
        List<List<Double>> values = new ArrayList<>();
        for (int row = 0; row < numberRows; ++row) {
            List<Double> rowList = new ArrayList<>();
            values.add(rowList);
            for (int column = 0; column < numberColumns; ++column) {
                rowList.add(
                    row == column ? 1.0 : 0.0
                );
            }
        }
        return new NumberMatrix(values);
    }

    public static NumberMatrix createZeroMatrix(
        int numberRows,
        int numberColumns
    ) {
        List<List<Double>> values = new ArrayList<>();
        for (int row = 0; row < numberRows; ++row) {
            List<Double> rowList = new ArrayList<>();
            values.add(rowList);
            for (int column = 0; column < numberColumns; ++column) {
                rowList.add(0.0);
            }
        }
        return new NumberMatrix(values);
    }

    public NumberMatrix multiply(NumberMatrix matrix)
    throws MatrixException {
        if (this.getNumberColumns() != matrix.getNumberRows()) {
            throw new MatrixException();
        }

        NumberMatrix zeroMatrix = createZeroMatrix(
            getNumberRows(),
            matrix.getNumberColumns()
        );

        for (int i = 0; i < getNumberRows(); i++) {
            for (int j = 0; j < matrix.getNumberColumns(); j++) {
                for (int k = 0; k < matrix.getNumberRows(); k++) {
                    zeroMatrix.set(
                        i, j,
                        zeroMatrix.get(i, j) + this.get(i, k) * matrix.get(k, j)
                    );
                }
            }
        }

        return zeroMatrix;
    }
}
