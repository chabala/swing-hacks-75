package org.chabala.swinghacks._75;

class MinMaxStats {
    private short min;
    private short max;
    private boolean empty = true;

    void accept(short value) {
        if (!empty) {
            if (min > value) {
                min = value;
            } else if (max < value) {
                max = value;
            }
        } else {
            min = value;
            max = value;
            empty = false;
        }
    }

    final short getMin() {
        return min;
    }

    final short getMax() {
        return max;
    }

    @Override
    public String toString() {
        if (empty) {
            return "MinMaxStats{empty}";
        } else {
            return "MinMaxStats{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }
}
