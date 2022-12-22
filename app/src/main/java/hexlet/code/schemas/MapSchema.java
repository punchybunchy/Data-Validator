package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        addToChecks(m -> m instanceof Map);
    }

    public final MapSchema required() {
        setRequired();
        return this;
    }

    public final MapSchema sizeof(int number) {
        addToChecks(m -> ((Map) m).size() == number);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        addToChecks(value -> {
            return schemas.entrySet().stream()
                    .allMatch(e -> {
                        Object v = ((Map) value).get(e.getKey());
                        return e.getValue().isValid(v);
                    });
            }
        );
        return this;
    }

}
