package com.cid.jquick_test;

import com.cid.jquick.transpilation.JAst.JArr;
import com.cid.jquick.transpilation.JAst.JExp;
import com.cid.jquick_test.Models.Name;

import java.nio.ByteBuffer;

import static com.cid.jquick.transpilation.JAst.JE_Type.JARR;

public class AOO extends Test {
    @Override
    public ByteBuffer get_buffer_to_parse() {
        return R.file_aoo_json;
    }


    // @todo(RJ): Create a JSON comparator I guess
    private static final Name[] COMPARE =
    {
      new Name("Dayan",    "Rodriguez",     null),
      new Name("Steve",    "Jobs",          null),
      new Name("Bill",     "Gates",         null),
      new Name("Jonathan", "Blow",          null),
    };

    @Override
    public void test_parsed_expression(JExp from) {
        ensure(from.type == JARR, "Root Expression Is JARR");
        final JArr cast = (JArr) from;
        ensure(cast.scope.length == 4, "Root Expression Scope Count == 4");
        final Name[] mapped = from.to_object(Name[].class);
        for (int i = 0; i < mapped.length; i++) {
            mapped[i].ensure_equal(this, COMPARE[i]);
        }
    }
}
