package hexlet.code.schemas;

import hexlet.code.Check;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BasicSchema {

    private boolean required;
    private final List<Check> checklist = new ArrayList<>();

    private final Check strInstanceCheck = e -> e instanceof String;
    public final void setStrRequired() {
        this.required = true;
        checklist.add(strInstanceCheck);
        checklist.add(str -> !str.toString().isEmpty());
    }

    public final void setContains(String text) {
        checklist.add(strInstanceCheck);
        checklist.add(str -> str.toString().contains(text));
    }

    public final void setMinLength(Integer minLength) {
        checklist.add(strInstanceCheck);
        checklist.add(str -> str.toString().length() > minLength);
    }

    private final Check numInstanceCheck = e -> e instanceof Number;
    public final void setNumRequired() {
        this.required = true;
        checklist.add(numInstanceCheck);
    }

    public final void setPositive() {
        checklist.add(numInstanceCheck);
        checklist.add(num -> (int) num > 0);
    }

    public final void setRange(int number1, int number2) {
        int minValue = Math.min(number1, number2);
        int maxValue = Math.max(number1, number2);
        checklist.add(numInstanceCheck);
        checklist.add(num -> ((int) num >= minValue) && ((int) num <= maxValue));
    }

    private final Check mapInstanceCheck = e -> e instanceof Map;
    public final void setMapRequired() {
        this.required = true;
        checklist.add(mapInstanceCheck);
    }

    public final void setSizeof(int number) {
        checklist.add(mapInstanceCheck);
        checklist.add(m -> ((Map<Object, Object>) m).size() == number);
    }

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
