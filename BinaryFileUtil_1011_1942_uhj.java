// 代码生成时间: 2025-10-11 19:42:21
package com.example.micronaut.util;

import io.micronaut.core.io.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Singleton;

/**
 * Utility class for reading and writing binary files.
 */
@Singleton
public class BinaryFileUtil {

    /**
     * Writes the provided byte array to a file.
     *
     * @param content The byte array to write.
     * @param filePath The path where the file should be written.
     * @return The number of bytes written.
     * @throws IOException if an I/O error occurs.
     */
    public long writeFile(byte[] content, String filePath) throws IOException {
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(content);
            return content.length;
        }
    }

    /**
     * Reads a file and returns its content as a byte array.
     *
     * @param filePath The path of the file to read.
     * @return The byte array containing the file's content.
     * @throws IOException if an I/O error occurs.
     */
    public byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        try (InputStream is = new FileInputStream(file)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(is, baos);
            return baos.toByteArray();
        }
    }
}
