package task_4.graphics.geometry.matrixes;

import java.util.List;


public class Matrix<TypeValue> {
    private final List<List<TypeValue>> values;

    protected Matrix(List<List<TypeValue>> values) {
        this.values = values;
    }

    public final int getNumberRows() {
        return values.size();
    }

    public final int getNumberColumns() {
        try {
            return values.get(0).size();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public final TypeValue get(int row, int column) throws IndexOutOfBoundsException {
        return values.get(row).get(column);
    }

    public final void set(int row, int column, TypeValue value)
    throws IndexOutOfBoundsException {
        values.get(row).set(column, value);
    }
}
