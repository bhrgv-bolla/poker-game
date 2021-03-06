package org.bbolla.pokergame.fivecard.etl;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;

@Slf4j
public class FileUtils {

    /**
     * Reads arbitrary number of lines from the file. Keeps the head pointer at the last read byte.
     * @param file
     * @param maxDataSizeInBytes
     */
    public static String[] readLines(RandomAccessFile file, int maxDataSizeInBytes) throws IOException {
        long currentOffset = file.getFilePointer();
        long totalBytes = file.length();
        log.info("Current {}, total {}", currentOffset, totalBytes);
        if(currentOffset == totalBytes) return null;
        else {
            long remaining = totalBytes - currentOffset;
            int bytesToRead = maxDataSizeInBytes > remaining ? (int) remaining : maxDataSizeInBytes;
            log.info("Reading {} bytes", bytesToRead);
            byte[] buffer = new byte[bytesToRead];
            file.readFully(buffer, 0, bytesToRead);
            String allText = new String(buffer);
            if(!(allText.lastIndexOf(System.lineSeparator()) == (allText.length() - System.lineSeparator().length()))) {
                String lastLine = file.readLine();
                if (lastLine != null) allText = allText + lastLine;
            } else {
                if(allText.length() > 15) //prints the last 15 characters
                    log.warn("Because this is end of a line; no new line is read: {}", allText.substring(allText.length() - 15));
            }
            log.info("read {} current {}, total {}", file.getFilePointer() - currentOffset, file.getFilePointer(), totalBytes);
            return allText.split(System.lineSeparator());
        }
    }
}
