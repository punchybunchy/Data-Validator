package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

        boolean expected2 = true;
        boolean actual2 = schema.required().contains("abcd").isValid("Deabcdg");
        assertThat(actual2).isEqualTo(expected2);

        boolean expected3 = false;
        boolean actual3 = schema.contains("abc").isValid("abdcd efg");
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

    @Test
    void testMapRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        boolean actual1 = schema.isValid(null);
        assertThat(actual1).isTrue();

        boolean actual2 = schema.required().isValid(null);
        assertThat(actual2).isFalse();

        boolean actual3 = schema.isValid(new HashMap());
        assertThat(actual3).isTrue();
    }

    @Test
    void testMapSize() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        boolean actual1 = schema.required().isValid(data); // true
        assertThat(actual1).isTrue();

        schema.sizeof(2);

        boolean actual2 = schema.isValid(data);  // false
        assertThat(actual2).isFalse();

        data.put("key2", "value2");
        boolean actual3 = schema.isValid(data); // true
        assertThat(actual3).isTrue();
    }

    @Test
    void testMapShape() {
        final int positiveNumber = 10;
        final int negativeNumber = -5;
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", positiveNumber);

        boolean actual1 = schema.isValid(human1); // true
        assertThat(actual1).isTrue();
//------------------------------------------------------
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);

        boolean actual2 = schema.isValid(human2); // true
        assertThat(actual2).isTrue();
//------------------------------------------------------

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        boolean actual3 = schema.isValid(human3); // false
        assertThat(actual3).isFalse();
//------------------------------------------------------

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", negativeNumber);
        boolean actual4 = schema.isValid(human4); // false
        assertThat(actual4).isFalse();
//------------------------------------------------------
        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Valya");
        human5.put("email", positiveNumber);
        boolean actual5 = schema.isValid(human5); // true
        assertThat(actual5).isTrue();
//------------------------------------------------------
        Map<String, Object> human6 = new HashMap<>();
        human6.put("lastname", "Messi");
        human6.put("email", "messi@gmail.com");
        boolean actual6 = schema.isValid(human6); // false
        assertThat(actual6).isFalse();
    }
}
