package hexlet.code.schemas;

public class MapSchema extends BasicSchema {

    public final MapSchema required() {
        setMapRequired();
        return this;
    }

    public final MapSchema sizeof(int number) {
        setSizeof(number);
        return this;
    }
}
