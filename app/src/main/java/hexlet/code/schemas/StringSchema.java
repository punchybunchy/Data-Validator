package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {

        addToChecklist(str -> str instanceof String);
    }

    public final StringSchema required() {
        setRequired();
        addToChecklist(str -> !str.toString().isEmpty());
        return this;
    }

    public final StringSchema minLength(int length) {
        addToChecklist(str -> str.toString().length() > length);
        return this;
    }

    public final StringSchema contains(String text) {
        addToChecklist(str -> str.toString().replaceAll(" ", "").contains(text));
        return this;
    }

}
