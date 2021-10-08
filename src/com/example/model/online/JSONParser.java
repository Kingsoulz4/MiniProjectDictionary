package com.example.model.online;

import com.example.model.word.Meaning;
import com.example.model.word.Word;
import javafx.scene.media.Media;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JSONParser {
    public Word ParseWordData(String data) {
        Word word = new Word();
        try {
            Object obj = JSONValue.parse(data);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject wordData = (JSONObject) jsonArray.get(0);
            JSONArray phonetics = (JSONArray) wordData.get("phonetics");
            JSONObject phoneticElement = (JSONObject) phonetics.get(0);
            JSONArray meanings = (JSONArray) wordData.get("meanings");

            Map<String, ArrayList<Meaning>> partOfSpeechMeaningMap = getMeaning(meanings);

            word.setWord_target(wordData.get("word").toString());
            word.setPhonetic(wordData.get("phonetic").toString());
            word.setPhoneticAudio(new Media("https:"+phoneticElement.get("audio")));
            word.setPartOfSpeech_Meaning(partOfSpeechMeaningMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }

    /** Get meanings from Json array */
    public Map<String, ArrayList<Meaning>> getMeaning(JSONArray meanings) {
        Map<String, ArrayList<Meaning>> partOfSpeechMeaningMap = new HashMap<>();
        for (int i=0; i<meanings.size(); i++) {
            JSONObject meaningElement = (JSONObject) meanings.get(i);
            String partOfSpeech = meaningElement.get("partOfSpeech").toString();
            JSONArray definitions = (JSONArray) meaningElement.get("definitions");
            ArrayList<Meaning> definitionListOfThisPart = new ArrayList<>();
            for(int j=0; j<definitions.size(); j++) {
                JSONObject definition = (JSONObject) definitions.get(j);
                Meaning meaning = new Meaning();
                meaning.setDefinition(definition.get("definition").toString());
                if (definition.get("example")!=null) {
                    meaning.setExample(definition.get("example").toString());
                }

                JSONArray synonyms = (JSONArray) definition.get("synonyms");
                JSONArray antonyms = (JSONArray) definition.get("antonyms");
                ArrayList<String> listSynonym = new ArrayList<>();
                for(int k=0; k<synonyms.size();k++) {
                    listSynonym.add(synonyms.get(k).toString());
                }
                meaning.setSynonyms(listSynonym);

                ArrayList<String> listAntonym = new ArrayList<>();
                for(int k=0; k<antonyms.size();k++) {
                    listAntonym.add(antonyms.get(k).toString());
                }
                meaning.setAntonyms(listAntonym);
                definitionListOfThisPart.add(meaning);
            }
            partOfSpeechMeaningMap.put(partOfSpeech, definitionListOfThisPart);


        }
        return partOfSpeechMeaningMap;
    }
}
