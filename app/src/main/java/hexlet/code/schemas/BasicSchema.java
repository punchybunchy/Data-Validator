package hexlet.code.schemas;

import hexlet.code.Check;

import java.util.ArrayList;
import java.util.List;


public class BasicSchema {

    private boolean required;
    private String textContent;
    private Integer minLength;
    private final List<Check> checklist = new ArrayList<>();

    protected BasicSchema() {

    }

    public final void setRequired() {
        this.required = true;
        checklist.add(isRequired);
    }

    public final void setTextContent(String text) {
        this.textContent = text;
        checklist.add(doesContain);
    }

    public final void setMinLength(Integer length) {
        this.minLength = length;
        checklist.add(isMinLength);
    }

    private final Check isRequired = str -> str instanceof String && !str.toString().isEmpty();
    private final Check doesContain = str -> str.toString().contains(textContent);
    private final Check isMinLength = str -> str.toString().length() > minLength;

    public final boolean isValid(Object request) {
        if (this.required) {
            for (Check item : this.checklist) {
                if (!item.check(request)) {
                    return false;
                }
            }
        }
        return true;
    }

}
