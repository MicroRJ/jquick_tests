package com.cid.jquick_test;

import com.cid.jquick.transpilation.JAst.JExp;

import java.nio.ByteBuffer;

import static com.cid.jquick.Utils.log;

public abstract class Test {

    public abstract ByteBuffer get_buffer_to_parse();

    public abstract void test_parsed_expression(JExp from);

    public void ensure(boolean expression, String description) throws RuntimeException
    {
        if (!expression)
        {
            throw new RuntimeException("runners.Test Failure, ENSURE: " + description);
        }

        if (TestRunner.LOGS)
        {
            log("ENSURE: " + description);
        }
    }
}
