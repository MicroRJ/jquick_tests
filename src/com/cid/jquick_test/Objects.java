package com.cid.jquick_test;


import com.cid.jquick.transpilation.JAst.JExp;
import com.cid.jquick_test.Models.Name;

import java.nio.ByteBuffer;

import static com.cid.jquick.transpilation.JAst.JE_Type.JOBJ;

public class Objects extends Test {

    @Override
    public ByteBuffer get_buffer_to_parse() {
        return R.file_objects_json;
    }

    private static final Name COMPARE = new Name("Roy", "Jacobs", new Name("Another Roy", "Another Jacobs", null));

    @Override
    public void test_parsed_expression(JExp from) {
        ensure(from.type == JOBJ, "Root Expression Is JOBJ");
        final Name mapped = from.to_object(Name.class);
        mapped.ensure_equal(this, COMPARE);
    }
}
