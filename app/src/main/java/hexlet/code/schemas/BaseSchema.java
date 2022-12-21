package hexlet.code.schemas;

import hexlet.code.Check;

import java.util.ArrayList;
import java.util.List;

public class BaseSchema {

    private boolean required;
    private final List<Check> checklist = new ArrayList<>();

    public final void setRequired() {
        this.required = true;
    }

    public final void addToChecklist(Check condition) {
        checklist.add(condition);
    }

    public final boolean isValid(Object request) {
        if (request == null) {
            return !required;
        }
        for (Check item : this.checklist) {
            if (!item.check(request)) {
                return false;
            }
        }
        return true;
    }
}
