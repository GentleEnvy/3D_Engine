package task_4.graphics.geometry.matrixes;

import java.util.ArrayList;
import java.util.List;


public class NumberVector extends NumberMatrix {
    protected NumberVector(List<List<Double>> values) {
        super(values);
    }

    public static NumberVector createZeroVector(int size) {
        List<List<Double>> values = new ArrayList<>();
        List<Double> rowList = new ArrayList<>();
        values.add(rowList);
        for (int column = 0; column < size; ++column) {
            rowList.add(0.0);
        }
        return new NumberVector(values);
    }

    public double get(int index) {
        return get(0, index);
    }

    public void set(int index, double value) {
        set(0, index, value);
    }
}
