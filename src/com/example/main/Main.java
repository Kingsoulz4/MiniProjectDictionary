package com.example.main;

import com.example.model.Dictionary;
import com.example.model.DictionaryCommandline;
import com.example.model.DictionaryManagement;
import com.example.model.Word;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Word> words = new ArrayList<>();
        Dictionary dictionary = new Dictionary(words);
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline dcmd = new DictionaryCommandline(dictionary, dictionaryManagement);
        dcmd.dictionaryBasic();
        dcmd.getDictionaryManagement().dictionaryExportToFile(dictionary,"F:\\UET\\OOP\\MiniProjectDictionary\\src\\com\\example\\data\\dictionaries.txt");
        dcmd.dictionaryAdvanced();
    }
}
