package com.example.model.sql;

import com.example.model.word.Word;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MySQLDatabaseConnector {
    Connection connection = null;

    public MySQLDatabaseConnector() {
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/edict?characterEncoding=utf8", "root", "55444664");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return this.connection;
    }

    public ArrayList<String> findWordPattern(String wordPattern) {
        ArrayList list = new ArrayList();

        try {
            this.getConnection();
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where word like '" + wordPattern + "%' limit 20");
            if (!rs.next()) {
                System.out.println("No result");
                return null;
            }

            rs.beforeFirst();

            while(rs.next()) {
                list.add(rs.getString("word"));
            }

            this.connection.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return list;
    }

    public Word findWord(String s) {
        Word word = new Word();
        String var3 = "";

        try {
            this.getConnection();
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where word = '" + s + "'");
            if (!rs.next()) {
                System.out.println("No result");
                return null;
            }

            rs.beforeFirst();

            while(rs.next()) {
                word.setWord_target(rs.getString("word"));
                word.setWord_explain(rs.getString("detail"));
            }

            this.connection.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return word;
    }

    public boolean addWord(Word word) {
        String insertQuery = "insert into tbl_edict(word, detail) values(?,?)";
        Word word1 = this.findWord(word.getWord_target());
        boolean flag = false;
        if (word1 != null) {
            System.out.println("Database contain this word");
            return flag;
        } else {
            try {
                this.getConnection();
                PreparedStatement statement = this.connection.prepareStatement(insertQuery);
                statement.setString(1, word.getWord_target());
                statement.setString(2, "<Q>" + word.getWord_explain() + "</Q>");
                int rowAffected = statement.executeUpdate();
                if (rowAffected >= 1) {
                    System.out.println("Add new word successfully");
                    flag = true;
                } else {
                    System.out.println("Add new word failed");
                }

                this.connection.close();
                return flag;
            } catch (Exception var7) {
                var7.printStackTrace();
                return false;
            }
        }
    }

    public boolean deleteWord(String word) {
        String deleteQuery = "delete from tbl_edict where word = ?";
        Word word1 = this.findWord(word);
        boolean flag = false;
        if (word1 == null) {
            System.out.println("Database not contain this word");
            return flag;
        } else {
            try {
                this.getConnection();
                PreparedStatement statement = this.connection.prepareStatement(deleteQuery);
                statement.setString(1, word);
                int rowAffected = statement.executeUpdate();
                if (rowAffected >= 1) {
                    System.out.println("Delete word successfully");
                    flag = true;
                } else {
                    System.out.println("Delete word failed");
                }

                this.connection.close();
                return flag;
            } catch (Exception var7) {
                var7.printStackTrace();
                return false;
            }
        }
    }

    public boolean editWord(Word word) {
        String updateQuery = "update tbl_edict set detail = ? where word = ? ";
        Word word1 = this.findWord(word.getWord_target());
        boolean flag = false;
        if (word1 == null) {
            System.out.println("Database not contain this word");
            return flag;
        } else {
            try {
                this.getConnection();
                PreparedStatement statement = this.connection.prepareStatement(updateQuery);
                statement.setString(1, "<Q>" + word.getWord_explain() + "</Q>");
                statement.setString(2, word.getWord_target());
                int rowAffected = statement.executeUpdate();
                if (rowAffected >= 1) {
                    System.out.println("Update word successfully");
                    flag = true;
                } else {
                    System.out.println("Update word failed");
                }

                this.connection.close();
                return flag;
            } catch (Exception var7) {
                var7.printStackTrace();
                return false;
            }
        }
    }

    public boolean addWordToFavoriteList(String word) {
        this.getConnection();
        boolean flag = false;
        String updateQuery = "update tbl_edict set isFavorite = ? where word = ? ";

        try {
            PreparedStatement statement = this.connection.prepareStatement(updateQuery);
            statement.setInt(1, 1);
            statement.setString(2, word);
            int rowAffected = statement.executeUpdate();
            if (rowAffected >= 1) {
                System.out.println("Add word to favorite list successfully");
                flag = true;
            } else {
                System.out.println("Add word to favorite list failed");
            }

            this.connection.close();
            return flag;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean removeWordFromFavoriteList(String word) {
        this.getConnection();
        boolean flag = false;
        String updateQuery = "update tbl_edict set isFavorite = ? where word = ? ";

        try {
            PreparedStatement statement = this.connection.prepareStatement(updateQuery);
            statement.setInt(1, 0);
            statement.setString(2, word);
            int rowAffected = statement.executeUpdate();
            if (rowAffected >= 1) {
                System.out.println("Remove word from favorite list successfully");
                flag = true;
            } else {
                System.out.println("Remove word to favorite list failed");
            }

            this.connection.close();
            return flag;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public ArrayList<Word> getListFavoriteWord() {
        ArrayList listWord = new ArrayList();

        try {
            this.getConnection();
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tbl_edict where isFavorite = '1'");
            if (!rs.next()) {
                System.out.println("No result");
                return null;
            }

            rs.beforeFirst();

            while(rs.next()) {
                Word word = new Word();
                word.setWord_target(rs.getString("word"));
                word.setWord_explain(rs.getString("detail"));
                listWord.add(word);
            }

            this.connection.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return listWord;
    }

    public String parseDataFromDatabase(String s) {
        String res = "";

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(s));
            Document document = builder.parse(source);
            NodeList nodeList = document.getElementsByTagName("Q").item(0).getChildNodes();

            for(int i = 0; i < nodeList.getLength(); ++i) {
                res = res + nodeList.item(i).getTextContent() + "\n";
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return res;
    }
}
