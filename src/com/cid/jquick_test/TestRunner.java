package com.cid.jquick_test;

import com.cid.dbot.discord.models.gateway.frames.FrameReady;
import com.cid.jquick.JQuick;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.cid.jquick.Utils.log;
import static java.nio.ByteBuffer.allocateDirect;


public class TestRunner {

    public static final boolean LOGS = true;

    public static final Test[] TESTS =
    {
        new AOF(),
        new AOO(),
        new Objects(),
    };
    // @formatter:on


    public static void run() {


        FrameReady frame_ready = new JQuick(R.file_frame_ready).map(FrameReady.class);













        for (Test test : TESTS)
        {
            final ByteBuffer buffer = test.get_buffer_to_parse();

            if (buffer != null)
            {
                final JQuick j = new JQuick(buffer);
                test.test_parsed_expression(j.root);
            }
            else
            {
                log("There was an error getting the test's ByteBuffer");
            }
        }
    }


    public static void main(String[] args) {

        log("Running Tests...");

        long start = System.nanoTime();
        run();
        long end = System.nanoTime();

        log("All Tests Ran: " + ((end - start) / 1000000) + "ms");

    }

    // @todo:: allocate one buffer only
    private static ByteBuffer read(File json_file) {
        try {
            final FileInputStream is = new FileInputStream(json_file);
            final ByteBuffer bb = allocateDirect(is.available());
            is.getChannel().read(bb);
            return bb;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

