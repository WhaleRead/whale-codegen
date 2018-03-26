package com.whaleread.codegen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by Dolphin on 2018/1/17
 */
public class IOUtility {
    public static void silentClose(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }

    public static void silentClose(Reader in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }
}
