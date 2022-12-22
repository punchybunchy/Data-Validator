package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private boolean required;
    private final List<Predicate> checks = new ArrayList<>();

    public final void setRequired() {
        this.required = true;
    }

    public final void addToChecks(Predicate condition) {
        checks.add(condition);
    }

    public final boolean isValid(Object request) {
        if (request == null) {
            return !required;
        }
        for (Predicate item : this.checks) {
            if (!item.test(request)) {
                return false;
            }
        }
        return true;
    }
}
