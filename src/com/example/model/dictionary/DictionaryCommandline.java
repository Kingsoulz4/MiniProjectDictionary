package com.example.model.dictionary;

import com.example.model.word.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    Dictionary dictionary;
    DictionaryManagement dictionaryManagement;


    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public DictionaryCommandline() {
    }

    public DictionaryCommandline(Dictionary dictionary, DictionaryManagement dictionaryManagement) {
        this.dictionary = dictionary;
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        ArrayList<Word> words = dictionary.getWords();
        System.out.println("No\t"+"|English\t\t"+"|Vietnamese");
        for (int i=1; i<=words.size(); i++) {
            System.out.println(i+"\t|"+words.get(i-1).getWord_target()
                    +"\t\t"+"|"+words.get(i-1).getWord_explain());

        }
        System.out.println();
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline(dictionary);
        this.showAllWords();
    }

    public void dictionaryAdvanced() {
        dictionary.setWords(dictionaryManagement.insertFromFile("F:\\UET\\OOP\\MiniProjectDictionary\\src\\com\\example\\data\\dictionaries.txt"));
        showAllWords();
        System.out.print("Enter word to lookup: ");
        String wt = new Scanner(System.in).nextLine();
        Word word = dictionaryManagement.dictionaryLookup(dictionary,wt);
        System.out.println(word);
    }
}
