package com.weathertraffic.util;

/**
 * Interface with methods to read from a file and write to a file.
 */
public interface iReadAndWriteToFile {
    
    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
     * @return the content of the file as a string.
     * @throws Exception if the method cannot find the file or read from it.
     */
    String readFromFile(String fileName) throws Exception; 
    
    /**
     * Writes the given content as JSON into the specified file.
     * @param fileName name of the file to write to.
     * @param content string to write into the file.
     * @return true if the write was successful, otherwise false.
     * @throws Exception if the method cannot write to the file.
     */
    boolean writeToFile(String fileName, String content) throws Exception;
}