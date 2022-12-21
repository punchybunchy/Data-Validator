package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        addToChecklist(m -> m instanceof Map);
    }

    public final MapSchema required() {
        setRequired();
        return this;
    }

    public final MapSchema sizeof(int number) {
        addToChecklist(m -> ((Map) m).size() == number);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        for (Map.Entry<String, BaseSchema> schema : schemas.entrySet()) {
            String key = schema.getKey();
            BaseSchema value = schema.getValue();
            checkMapItems(key, value);
        }
        return this;
    }

    private void checkMapItems(String key, BaseSchema value) {
        addToChecklist(m -> {
            Map map = (Map) m;
            if (map.containsKey(key)) {
                Object request = map.get(key);
                return value.isValid(request);
            }
            return value.isValid(null);
        });
    }
}
