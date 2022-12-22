package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {

        addToChecks(num -> num instanceof Number);
    }

    public final NumberSchema required() {
        setRequired();
        return this;
    }

    public final NumberSchema positive() {
        addToChecks(num -> (int) num > 0);
        return this;
    }

    public final NumberSchema range(int number1, int number2) {
        int minValue = Math.min(number1, number2);
        int maxValue = Math.max(number1, number2);
        addToChecks(num -> ((int) num >= minValue) && ((int) num <= maxValue));
        return this;
    }
}
