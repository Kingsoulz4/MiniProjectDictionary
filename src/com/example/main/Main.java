package com.example.main;

import com.example.model.sql.SQLSeverDatabaseConnector;
import com.example.model.word.Word;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQLSeverDatabaseConnector connector = new SQLSeverDatabaseConnector();

        Scanner sc = new Scanner(System.in);
        String s ;
        System.out.print("Nhap word ban muon sua: ");
        s = sc.nextLine();
        /*
        String pho;
        System.out.print("Nhap Phonetic ban muon them: ");
        pho = sc.nextLine();
        Word word = new Word();
        word.setWord_target(s);
        word.setPhonetic(pho);
        connector.AddWord(word);
        */

         //sua

        String column;
        System.out.print("Nhập trường bạn muốn sửa: ");
        column = sc.nextLine();
        connector.UpdateW(s,column);

        /* // xoa word
        connector.DeleteWord(s);
         */
        /*
        //find word
        Word word = connector.findWord(s);
        System.out.println("Word: "+s);
        System.out.println(word.getPhonetic());
        for (Map.Entry<String, ArrayList<Meaning>> e: word.getPartOfSpeech_Meaning().entrySet()) {
            System.out.println("Part Of Speech: "+ e.getKey());
            for (Meaning m: e.getValue()) {
                System.out.println(m.getDefinition());
                System.out.println("Example: "+m.getExample());
            }
        }*/

    }
}
