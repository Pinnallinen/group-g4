package com.weathertraffic.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

public class JsonReadAndWrite implements iReadAndWriteToFile {
    
    private Gson gson;
    
    public JsonReadAndWrite() {
        this.gson = new Gson();
    }
    
    @Override
    public String readFromFile(String fileName) throws Exception {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            return stringBuilder.toString();
        }
        catch (IOException e) {
            throw new Exception("Error reading from file: " + e.getMessage());
        }
    }
    
    @Override
    public boolean writeToFile(String fileName, String content) throws Exception {
        try (Writer writer = new FileWriter("src/main/resources/" + fileName)) {
            JsonElement jsonElement = JsonParser.parseString(content);
            gson.toJson(jsonElement, writer);
            return true;
        }
        catch (IOException e) {
            throw new Exception("Error writing to file: " + e.getMessage());
        }
    }
}