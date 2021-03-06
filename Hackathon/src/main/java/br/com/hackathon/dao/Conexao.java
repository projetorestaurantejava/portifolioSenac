package br.com.hackathon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexao {
    public static Connection openConnection() throws SQLException{
            Connection driver = null;
        try {    
            Class.forName("com.mysql.cj.jdbc.Driver");
            driver = DriverManager.getConnection("jdbc:mysql://localhost:3306/jovemprogramador"
                    +"?userTimezone=true&serverTimezone=America/Sao_Paulo&zeroDateTimeBehavior=convertToNull",
                    "root", "admin");
            return driver;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);;
        }
        return driver;
    }
    public static void fecharConexao(Connection conn, Statement psmt, ResultSet rs) throws SQLException{
        rs.close();
        conn.close();
        psmt.close();
    }
    public static void fecharConexao(Connection conn, Statement psmt) throws SQLException{
        conn.close();
        psmt.close();
    }
}
