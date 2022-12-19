package hexlet.code.schemas;

public class StringSchema extends BasicSchema {

    public final StringSchema required() {
        super.setRequired();
        return this;
    }

    public final StringSchema minLength(Integer number) {
        super.setRequired();
        super.setMinLength(number);
        return this;
    }

    public final StringSchema contains(String text) {
        super.setRequired();
        super.setTextContent(text);
        return this;
    }

}
