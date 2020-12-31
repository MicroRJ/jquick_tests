package com.cid.jquick_test;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class R {

    public static ByteBuffer file_aoo_json;
    public static ByteBuffer file_fao_json;
    public static ByteBuffer file_objects_json;

    public static ByteBuffer load_file(String name_in_jar) {
        ByteBuffer result = null;
        try {
            InputStream is = R.class.getResourceAsStream(name_in_jar);
            result = ByteBuffer.allocateDirect(is.available());
            int read;
            while ((read = is.read()) != -1) result.put((byte) read);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static {
        file_aoo_json = load_file("aoo.json");
        file_fao_json = load_file("fao.json");
        file_objects_json = load_file("objects.json");
    }
}
