package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {
    public Validator() {
    }

    public final StringSchema string() {
        return new StringSchema();
    }
}
