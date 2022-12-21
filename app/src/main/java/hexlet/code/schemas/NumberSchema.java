package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {

        addToChecklist(num -> num instanceof Number);
    }

    public final NumberSchema required() {
        setRequired();
        return this;
    }

    public final NumberSchema positive() {
        addToChecklist(num -> (int) num > 0);
        return this;
    }

    public final NumberSchema range(int number1, int number2) {
        int minValue = Math.min(number1, number2);
        int maxValue = Math.max(number1, number2);
        addToChecklist(num -> ((int) num >= minValue) && ((int) num <= maxValue));
        return this;
    }
}
