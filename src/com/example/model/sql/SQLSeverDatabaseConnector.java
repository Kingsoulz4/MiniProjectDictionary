package com.example.model.sql;


import com.example.model.word.Meaning;
import com.example.model.word.Word;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.*;

public class SQLSeverDatabaseConnector {

    Connection connection;

    public SQLSeverDatabaseConnector() {
    }

    public Connection getConnection() {
        SQLServerDataSource a = new SQLServerDataSource();
        a.setUser("sa");
        a.setPassword("113008");
        a.setServerName("WIN10PRO\\VIET");
        a.setPortNumber(1433);
        a.setDatabaseName("Dictionary");

        Connection b = null;
        try {
            b= a.getConnection();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return b;

    }

    // get ID
    public int GetID(String s) {
        int Id = 0;
        try {

            Connection connection = this.getConnection();
            String sql = "{Call GetId(?,?)}";
            CallableStatement cal = connection.prepareCall(sql);

            cal.setString(1,s);
            cal.registerOutParameter(2,Types.INTEGER);

            cal.execute();
            Id = cal.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Id;
    }

    public Word findWord(String s) {
        Word word = new Word();
        Connection connection = this.getConnection();
        //String selectQuery = "SELECT * FROM WORDS W INNER JOIN WORD_MEANING WM ON W.ID = WM.ID WHERE word='"+s+"'" ;
        String selectQuery = "SELECT * FROM WORDS W WHERE word='" + s + "'";
        String selectPOS = "SELECT wp.PartOfSpeech FROM dbo.Words w INNER JOIN dbo.Word_POS wp ON w.ID=wp.ID WHERE w.Word = '" + s + "'";
        //String selectQuery2 = "SELECT wm.Meaning  FROM dbo.Word_Meaning wm INNER JOIN dbo.Words w ON w.ID = wm.ID WHERE w.Word = '" + s + "'";
        ArrayList<Meaning> meanings = new ArrayList<>();
        ArrayList<String> example = new ArrayList<String>();
        Map<String, ArrayList<Meaning>> map = new HashMap<>();
        //String selectQuery = "SELECT * FROM WORDS";
        try {

            //Get word
            Statement stWord = connection.createStatement();
            ResultSet rsWord = stWord.executeQuery(selectQuery);
            while (rsWord.next()) {

                String Phonetic = rsWord.getString("Phonetic");
                String Synonym = rsWord.getString("Synonym");
                String Antonym = rsWord.getString("Antonym");
                word.setPhonetic(Phonetic);
            }
        word.setWord_target(s);
            //Get POS
            ArrayList<String> POS = new ArrayList<>();
            Statement stPOS = connection.createStatement();
            ResultSet rsPOS = stPOS.executeQuery(selectPOS);
            while (rsPOS.next()) {
                POS.add(rsPOS.getString("PartOfSpeech"));
            }
            //Get meanings and examples each POS
            for (String p: POS) {
                ArrayList<Meaning> mes = new ArrayList<>();
                String selectMeaningsAndExamples = "SELECT wm.Meaning, wm.Example  " +
                        "FROM dbo.Word_Meaning wm INNER JOIN dbo.Words w " + "ON w.ID = wm.ID " +
                        "WHERE w.Word = '"+s+ "' \n" + " AND wm.PartOfSpeech = '"+p+"'";
                Statement stME = connection.createStatement();
                ResultSet rsME = stME.executeQuery(selectMeaningsAndExamples);
                while (rsME.next()) {
                    Meaning me = new Meaning();
                    me.setDefinition(rsME.getString("Meaning"));
                    me.setExample(rsME.getString("Example"));
                    mes.add(me);
                }
                map.put(p, mes);
            }
            word.setPartOfSpeech_Meaning(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }


    // nhập vào word -> id
/*
    public Word findWord(String word) {
        Word word1 = new Word();
        // query trên bảng
        ...
        // ví dụ lấy được cột meaning rồi
        word1.setWord_explain(meaning);
        // thuộc tính example
        word1.setExample(example);
        nghĩa,syn,ant = aaaaaa;
        reutnr về ??

        return word1;
    }
/*
    //Thêm từ
    public void add() {
        insert into
    }


    //Tìm từ
    public void search(String s) {
        select * from
    }

    //Xóa từ

    public void Delete(String word) {
        delete ..
    }
 */
    // add w
    public void AddWord(Word word) {
        Connection connection = this.getConnection();
        try {
            Statement addWord = connection.createStatement();

            String ant = "ayz";
            String syn = "qbz";

            for (Map.Entry<String,ArrayList<Meaning>> entry: word.getPartOfSpeech_Meaning().entrySet()) {
                String POS = entry.getKey(); //POS
                ArrayList<Meaning> meanings = entry.getValue(); //Array list meaning
                for (Meaning m:meanings) {
                    ArrayList<String> synonyms = m.getSynonyms();
                    ArrayList<String> antonyms = m.getAntonyms();
                    for (String a: synonyms) {
                        syn += a +", ";
                    }
                    for (String b: antonyms) {
                        ant += b +", ";
                    }
                }
            }
            String addW = "INSERT INTO dbo.Words(Word, Phonetic, Synonym, Antonym )\n" +
                    "VALUES ('" + word.getWord_target() + "', N'" +word.getPhonetic()+ "', N'" + syn + "',N'" + ant + "')";
                    addWord.executeUpdate(addW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//delete w
    public void DeleteWord(String s) {

        Connection connection = this.getConnection();
        int i = GetID(s);
        try {
            //xoa meaning
            String dMeanings = "DELETE FROM dbo.Word_Meaning WHERE ID =" + i;
            Statement rmMeanings = connection.createStatement();
            rmMeanings.executeUpdate(dMeanings);

            // xoa pos
            String dPos = "DELETE FROM dbo.Word_POS WHERE ID =" + i;
            Statement rmPos = connection.createStatement();
            rmPos.executeUpdate(dPos);

            // xoa word
            String dWord = "DELETE FROM dbo.Words WHERE Word ='" + s + "'";
            Statement rmWord = connection.createStatement();
            rmWord.executeUpdate(dWord);

        }catch (Exception e) {
            System.out.println("Can not delete this word!");
        }
    }

    //update word
    public void UpdateW(String s , String column) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap du lieu ban muon them");
        String data = sc.nextLine();
        Connection connection = this.getConnection();
        int i = GetID(s);
        try {
            // sua Phonetic
            switch (column) {
                case "Phonetic" : {
                    String checkNULL = "SELECT dbo.Words.Phonetic FROM dbo.Words WHERE dbo.Words.Word = '" +s+ "'";
                    Statement stWord = connection.createStatement();
                    ResultSet rsWord = stWord.executeQuery(checkNULL);
                    while (rsWord.next()) {
                        String Phonetic = rsWord.getString("Phonetic");
                        if (Phonetic != null) {
                            String upPhonetic = "UPDATE dbo.Words SET Phonetic = '" + data +"'" +" WHERE Word = '" + s +"'";
                            Statement st = connection.createStatement();
                            st.executeUpdate(upPhonetic);
                        }
                    }
                }   break;
                //Synonym
                case "Synonym" : {
                    String checkNULL = "SELECT dbo.Words.Synonym FROM dbo.Words WHERE dbo.Words.Word = '" +s+ "'";
                    Statement stWord = connection.createStatement();
                    ResultSet rsWord = stWord.executeQuery(checkNULL);
                    while (rsWord.next()) {
                        String Synonym = rsWord.getString("Synonym");
                        if (Synonym != null) {
                            String upSynonym = "UPDATE dbo.Words SET Synonym = '" + data + "'" + " WHERE Word = '" + s + "'";
                            Statement st = connection.createStatement();
                            st.executeUpdate(upSynonym);
                        }
                    }
                } break;
                //Antonym
                case "Antonym" : {
                    String checkNULL = "SELECT dbo.Words.Antonym FROM dbo.Words WHERE dbo.Words.Word = '" +s+ "'";
                    Statement stWord = connection.createStatement();
                    ResultSet rsWord = stWord.executeQuery(checkNULL);
                    while (rsWord.next()) {
                        String Antonym = rsWord.getString("Antonym");
                        if (Antonym != null) {
                            String upAntonym = "UPDATE dbo.Words SET Antonym = '" + data + "'" + " WHERE Word = '" + s + "'";
                            Statement st = connection.createStatement();
                            st.executeUpdate(upAntonym);
                        }
                    }
                } break;
                //POS
                default:
                    System.out.println("Can not find column!");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
