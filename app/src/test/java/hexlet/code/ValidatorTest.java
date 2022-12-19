package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testRequired() {
        final int intExample = 4;

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
        boolean actual4 = schema.isValid(intExample);
        assertThat(expected4).isEqualTo(actual4);
    }

    @Test
    void testContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean expected1 = false;
        boolean actual1 = schema.contains("abc").isValid(null);
        assertThat(actual1).isEqualTo(expected1);

        boolean expected2 = false;
        boolean actual2 = schema.contains("abcdefg").isValid("abc");
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = true;
        boolean actual3 = schema.contains("abc").isValid("abcdefg");
        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void testMinLength() {
        final int lengthExample = 3;

        Validator validator = new Validator();
        StringSchema schema = validator.string();

        boolean expected1 = false;
        boolean actual1 = schema.minLength(lengthExample).isValid(null);
        assertThat(actual1).isEqualTo(expected1);

        boolean expected2 = false;
        boolean actual2 = schema.minLength(lengthExample).isValid("ab");
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = true;
        boolean actual3 = schema.minLength(lengthExample).isValid("abcdefg");
        assertThat(actual3).isEqualTo(expected3);
    }
}
