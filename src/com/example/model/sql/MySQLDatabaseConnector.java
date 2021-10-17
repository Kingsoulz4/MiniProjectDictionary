package com.example.model.sql;

import com.example.model.word.Word;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class MySQLDatabaseConnector {
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/edict?characterEncoding=utf8", "root", "113008");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<String> findWordPattern(String wordPattern) {
        ArrayList<String> list = new ArrayList<>();
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where word like '"+wordPattern+"%' limit 20");
            //System.out.println(rs.toString());

            if (rs.next()==false) {
                System.out.println("No result");
                return null;
            }
            else {
                rs.beforeFirst();
            }
            while (rs.next()) {
                list.add(rs.getString("word"));

            }

            connection.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Word findWord(String s) {
        Word word = new Word();
        String res = "";
        try {
            getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where word = '"+s+"'");
            //System.out.println(rs.toString());

            if (rs.next()==false) {
                System.out.println("No result");
                return null;
            }
            else {
                rs.beforeFirst();
            }
            while (rs.next()) {

                word.setWord_target(rs.getString("word"));
                word.setWord_explain(rs.getString("detail"));

            }

            connection.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return word;
    }

    public void addWord(Word word) {
        String insertQuery = "insert into tbl_edict(word, detail) "+ "values(?,?)";

        try {
            getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, word.getWord_target());
            statement.setString(2, "<Q>" +word.getWord_explain() +"</Q>");
            int rowAffected = statement.executeUpdate();
            if (rowAffected>=1) {
                System.out.println("Add new word successfully");
            }
            else {
                System.out.println("Add new word failed");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(String word) {

        String deleteQuery = "delete from tbl_edict where word = ?";
        Word word1 = findWord(word);
        if (word1==null) {
            System.out.println("Database not contain this word");
            return;
        }

        try {
            getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, word);
            int rowAffected = statement.executeUpdate();
            if (rowAffected>=1) {
                System.out.println("Delete word successfully");
            }
            else {
                System.out.println("Delete word failed");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modifyWord(Word word) {
        String updateQuery = "update tbl_edict "+"set detail = ? "+ "where word = ? ";
        Word word1 = findWord(word.getWord_target());
        if (word1==null) {
            System.out.println("Database not contain this word");
            return;
        }
        try {
            getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, "<Q>" +word.getWord_explain() +"</Q>");
            statement.setString(2, word.getWord_target());
            int rowAffected = statement.executeUpdate();
            if (rowAffected>=1) {
                System.out.println("Update word successfully");
            }
            else {
                System.out.println("Update word failed");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWordToFavoriteList(Word word) {
        getConnection();
        String updateQuery = "update tbl_edict "+"set isFavorite = ? "+ "where word = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, 1);
            statement.setString(2, word.getWord_target());
            int rowAffected = statement.executeUpdate();
            if (rowAffected>=1) {
                System.out.println("Add word to favorite list successfully");
            }
            else {
                System.out.println("Add word to favorite list failed");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Word> getListFavoriteWord() {
        ArrayList<Word> listWord = new ArrayList<>();
        try {
            getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where isFavorite = '"+1+"'");
            //System.out.println(rs.toString());
            if (rs.next()==false) {
                System.out.println("No result");
                return null;
            }
            while (rs.next()) {
                Word word = new Word();
                word.setWord_target(rs.getString("word"));
                word.setWord_explain(rs.getString("detail"));
                listWord.add(word);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWord;
    }


}
