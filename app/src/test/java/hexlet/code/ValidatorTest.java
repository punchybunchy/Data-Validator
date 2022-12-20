package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testStringRequired() {
        final int number = 4;

        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean expected1 = true;
        boolean actual1 = schema.isValid(null);
        assertThat(actual1).isEqualTo(expected1);

        boolean expected2 = false;
        boolean actual2 = schema.required().isValid(null);
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = true;
        boolean actual3 = schema.isValid("abc");
        assertThat(actual3).isEqualTo(expected3);

        boolean expected4 = false;
        boolean actual4 = schema.isValid(number);
        assertThat(expected4).isEqualTo(actual4);
    }

    @Test
    void testContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean expected1 = true;
        boolean actual1 = schema.contains("abc").isValid(null);
        assertThat(actual1).isEqualTo(expected1);

        boolean expected2 = false;
        boolean actual2 = schema.required().contains("abcdefg").isValid("abc");
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = true;
        boolean actual3 = schema.contains("abc").isValid("abcdefg");
        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void testMinLength() {
        final int length = 3;

        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean expected1 = true;
        boolean actual1 = schema.minLength(length).isValid(null);
        assertThat(actual1).isEqualTo(expected1);

        boolean expected2 = false;
        boolean actual2 = schema.required().minLength(length).isValid("ab");
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = true;
        boolean actual3 = schema.minLength(length).isValid("abcdefg");
        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void testNumRequired() {
        final int positiveNumber = 10;

        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.isValid(null); // true
        assertThat(actual1).isTrue();

        boolean actual2 = schema.positive().isValid(null); // true
        assertThat(actual2).isTrue();

        schema.required();

        boolean actual3 = schema.isValid(null); // false
        assertThat(actual3).isFalse();

        boolean actual4 = schema.isValid(positiveNumber); // true
        assertThat(actual4).isTrue();

        boolean actual5 = schema.isValid("5"); // false
        assertThat(actual5).isFalse();
    }

    @Test
    void testPositive() {
        final int positiveNumber = 10;
        final int negativeNumber = -10;
        final int zero = 0;

        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.required().positive().isValid(negativeNumber);
        assertThat(actual1).isFalse();

        boolean actual2 = schema.isValid(zero);
        assertThat(actual2).isFalse();

        boolean actual3 = schema.isValid(positiveNumber);
        assertThat(actual3).isTrue();
    }

    @Test
    void testRange() {
        final int positiveNumber = 10;
        final int negativeNumber = -10;
        final int zero = 0;

        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.required().range(positiveNumber, negativeNumber).isValid(zero);
        assertThat(actual1).isTrue();

        boolean actual2 = schema.positive().range(positiveNumber, negativeNumber).isValid(zero);
        assertThat(actual2).isFalse();
    }
}
