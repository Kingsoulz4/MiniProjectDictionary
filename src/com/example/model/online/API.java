package com.example.model.online;

import com.example.model.word.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    public Word getWordData(String word) {
        String data = "";
        Word wordParsed = new Word();
        try {
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/ "+word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode()==200) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line=bufferedReader.readLine())!=null) {
                    data += line ;
                }
                JSONParser jsonParser = new JSONParser();
                wordParsed = jsonParser.ParseWordData(data);
            } else {
                System.out.println("Connection error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordParsed;
    }
}
