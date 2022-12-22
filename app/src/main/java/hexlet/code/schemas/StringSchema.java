package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {

        addToChecks(str -> str instanceof String);
    }

    public final StringSchema required() {
        setRequired();
        addToChecks(str -> !str.toString().isEmpty());
        return this;
    }

    public final StringSchema minLength(int length) {
        addToChecks(str -> str.toString().length() > length);
        return this;
    }

    public final StringSchema contains(String text) {
        addToChecks(str -> str.toString().contains(text));
        return this;
    }

}
