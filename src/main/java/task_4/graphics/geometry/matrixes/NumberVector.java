package task_4.graphics.geometry.matrixes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class NumberVector extends NumberMatrix {
    protected NumberVector(List<List<Double>> values) {
        super(values);
    }

    public static NumberVector createZeroVector(int size) {
        List<List<Double>> values = new ArrayList<>();
        for (int row = 0; row < size; ++row) {
            List<Double> column = new LinkedList<>();
            column.add(0.0);
            values.add(column);
        }
        return new NumberVector(values);
    }

    public static NumberVector fromMatrix(NumberMatrix numberMatrix) {
        List<List<Double>> values = new ArrayList<>();
        for (int row = 0; row < numberMatrix.getNumberRows(); ++row) {
            List<Double> column = new LinkedList<>();
            column.add(numberMatrix.get(row, 0));
            values.add(column);
        }
        return new NumberVector(values);
    }

    public double get(int index) {
        return get(index, 0);
    }

    public void set(int index, double value) {
        set(index, 0, value);
    }
}
