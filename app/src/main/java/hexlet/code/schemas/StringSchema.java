package hexlet.code.schemas;

public class StringSchema extends BasicSchema {

      public final StringSchema required() {
        setStrRequired();
        return this;
    }

    public final StringSchema minLength(Integer number) {
        setMinLength(number);
        return this;
    }

    public final StringSchema contains(String text) {
        setContains(text);
        return this;
    }

}
