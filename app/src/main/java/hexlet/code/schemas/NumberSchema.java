package hexlet.code.schemas;

public class NumberSchema extends BasicSchema {

    public final NumberSchema required() {
        setNumRequired();
        return this;
    }

    public final NumberSchema positive() {
        setPositive();
        return this;
    }

    public final NumberSchema range(int number1, int number2) {
        setRange(number1, number2);
        return this;
    }
}
