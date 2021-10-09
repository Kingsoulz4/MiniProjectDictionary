package com.example.model.dictionary;

import com.example.model.word.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromCommandline(Dictionary dictionary) {
        System.out.print("Enter the number of word input:");
        int n = new Scanner(System.in).nextInt();
        for(int i=0; i<n; i++) {
            System.out.print("Word "+i +" target:");
            String wt = new Scanner(System.in).nextLine();
            System.out.print("Word "+i +" explain:");
            String we = new Scanner(System.in).nextLine();
            Word word = new Word(wt, we);
            addWordToDictionary(dictionary, word);
        }
    }
    public ArrayList<Word> insertFromFile(String path) {
        ArrayList<Word> words = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                String tok[] = line.split("\t");
                String target = tok[0];
                String explain = tok[1];
                Word word = new Word(target, explain);
                words.add(word);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    public Word dictionaryLookup(Dictionary dictionary, String w) {
        for (Word word: dictionary.getWords()) {
            if (word.getWord_target().equalsIgnoreCase(w)) {

                return word;
            }
        }
        System.out.println("Word not found");
        return null;
    }

    public void addWordToDictionary(Dictionary dictionary, Word newWord)
    {
        if(dictionary.getWords().indexOf(newWord)==-1) {
            dictionary.getWords().add(newWord);
        }
        else {
            System.out.println("This word has exist");
        }
    }

    public void removeWordFromDictionary(Dictionary dictionary, Word word) {
        if(dictionary.getWords().indexOf(word)==-1)
        {
            return;
        }
        else {
            dictionary.getWords().remove(word);
        }
    }

    public void modifyDictionary(Dictionary dictionary) {
        System.out.println("Enter your command: \n 1. add \n 2. remove \n 3. edit \n");
        int cmd = new Scanner(System.in) .nextInt();
        String wt = new Scanner(System.in).nextLine();
        if(cmd==1) {
            System.out.print("Enter explain:");
            String we = new Scanner(System.in).nextLine();
            Word word = new Word(wt, we);
            addWordToDictionary(dictionary, word);
        }
        else if (cmd==2) {
            Word word = dictionaryLookup(dictionary, wt);
            if(word!=null) {
                removeWordFromDictionary(dictionary, word);
            }
        }
        else if (cmd==3) {
            Word word = dictionaryLookup(dictionary, wt);
                if (word!=null) {
                    System.out.print("Enter explain:");
                    String we = new Scanner(System.in).nextLine();
                    word.setWord_explain(we);
                    return;
                }

            System.out.println("Word not found");
        }
    }

    public void dictionaryExportToFile(Dictionary dictionary, String path) {
        try {
            File outFile = new File(path);
            Writer writer = new FileWriter(outFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Word word: dictionary.getWords()) {
                bufferedWriter.write(word.getWord_target()+"\t"+word.getWord_explain()+"\n");
            }
            bufferedWriter.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
