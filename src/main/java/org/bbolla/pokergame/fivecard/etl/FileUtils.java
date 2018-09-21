package org.bbolla.pokergame.fivecard.etl;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtils {

    /**
     * Reads arbitrary number of lines from the file. Keeps the head pointer at the last read byte.
     * @param file
     * @param maxDataSizeInBytes
     */
    public static String[] readLines(RandomAccessFile file, int maxDataSizeInBytes) throws IOException {
        long currentOffset = file.getFilePointer();
        long totalBytes = file.length();
        if(currentOffset == totalBytes) return null;
        else {
            long remaining = totalBytes - currentOffset;
            int bytesToRead = maxDataSizeInBytes > remaining ? (int) remaining : maxDataSizeInBytes;
            byte[] buffer = new byte[bytesToRead];
            file.readFully(buffer, 0, bytesToRead);
            String lastLine = file.readLine();
            String allText = new String(buffer);
            allText = allText + lastLine;
            return allText.split(System.lineSeparator());
        }
    }
}
