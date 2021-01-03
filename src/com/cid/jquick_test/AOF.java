package com.cid.jquick_test;

import com.cid.jquick.JQuick;
import com.cid.jquick.mappers.ToJson;
import com.cid.jquick.mappers.ToObject;
import com.cid.jquick.transpilation.JAst.JArr;
import com.cid.jquick.transpilation.JAst.JExp;
import com.cid.jquick.transpilation.JAst.JObj;
import com.cid.jquick_test.Models.Person;

import java.nio.ByteBuffer;

import static com.cid.jquick.transpilation.JAst.JE_Type.JOBJ;

// # Array Of Fields, Or Array _of_ Object Fields
public class AOF extends Test {

    @Override
    public ByteBuffer get_buffer_to_parse() {
        return R.file_fao_json;
    }

    // @formatter:off
    private static final Person[] COMPARE =
    {
        new Person("Dayan Rodriguez",   17, new Person[]
            {
                new Person("Dayan Rodriguez",   17, null),
                new Person("Steve Jobs",        56, null),
                new Person("Bill Gates",        64, null),
                new Person("Jonathan Blow",     49, null),
            }
        ),
        new Person("Steve Jobs",        56, null),
        new Person("Bill Gates",        64, null),
        new Person("Jonathan Blow",     49, null),
    };
    // @formatter:on


    @Override
    public void test_parsed_expression(JExp from) {
        ensure(from.type == JOBJ, "Root Expression Is JOBJ");
        final JObj cast = (JObj) from;
        ensure(cast.scope.length == 4, "Root Expression Scope Count == 4");

        final Person[] mapped = from.to_object(Person[].class);

        final JArr what = (JArr) ToJson.to_json(mapped);

        {
            final JObj a = ToJson.to_json(mapped[0]);       // expected a object Person
            final JArr b = ToJson.to_json(mapped);          // expected an array of Person
        }

        for (int i = 0; i < mapped.length; i++) {
            mapped[i].ensure_equal(this, COMPARE[i]);
        }
    }
}
