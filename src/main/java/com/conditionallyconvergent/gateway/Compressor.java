package com.conditionallyconvergent.gateway;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

class Compressor {
    static byte[] zip(String string) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        OutputStream outputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(Deflater.BEST_COMPRESSION));
        outputStream.write(string.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        return byteArrayOutputStream.toByteArray();
    }
}
