package com.example.model.word;

import com.sun.imageio.plugins.jpeg.JPEGImageReaderResources;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.Map;

public class Word {
    private String word_target;
    private String word_explain;
    private String phonetic;
    private ArrayList<String> meanings;
    private Map<String, ArrayList<Meaning>> partOfSpeech_Meaning;
    Media phoneticAudio;

    public Word(String word_target, String word_explain, Media phoneticAudio) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.phoneticAudio = phoneticAudio;
    }

    public ArrayList<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(ArrayList<String> meanings) {
        this.meanings = meanings;
    }

    public Map<String, ArrayList<Meaning>> getPartOfSpeech_Meaning() {
        return partOfSpeech_Meaning;
    }

    public void setPartOfSpeech_Meaning(Map<String, ArrayList<Meaning>> partOfSpeech_Meaning) {
        this.partOfSpeech_Meaning = partOfSpeech_Meaning;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public Media getPhoneticAudio() {
        return phoneticAudio;
    }

    public void setPhoneticAudio(Media phoneticAudio) {
        this.phoneticAudio = phoneticAudio;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public Word() {
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    @Override
    public String toString() {
        return this.word_target
                +"\t\t"+"|"+this.word_explain;
    }
}
