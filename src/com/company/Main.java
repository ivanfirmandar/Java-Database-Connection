package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Database db = new Database("SELECT * FROM anggota");
        db.createConnection();
        //Show All Databases
        for (int i = 0; i < db.result.size();i++){
            System.out.println(
                    db.result.get(i).ID
                            + " " + db.result.get(i).nama
                            + " " + db.result.get(i).kelas
                            + " " + db.result.get(i).absen
            );
        }

    }
}
class Database{

    static final String DB_NAME = "firstdb";
    static final String DB_URL = "jdbc:mysql://localhost/" + DB_NAME;
    static final String USER = "root";
    static final String PASS = "Saranghae100";

    public String statement;
    public ArrayList<Result> result = new ArrayList(); //Used for storing database queries as array;

    public Database(String statement){
        this.statement = statement;
    }
    public void createConnection(){
        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Create Connection Object;
            Statement stmt = conn.createStatement(); //Create Statement Object from the Connection;
            ResultSet result = stmt.executeQuery(this.statement);  //Query ther Result;
            while (result.next()) {
                this.result.add(new Result(result.getString(1),result.getString(2),result.getString(3),result.getString(4)));
            }
        }catch (SQLException err){
            System.out.println(err);
        }
    }
}
class Result{
//    This class is instantiated for storing database result query as objects

    public String ID;
    public String nama;
    public String absen;
    public String kelas;

    public Result(String id, String nama,String absen, String kelas){
        this.ID = id;
        this.nama = nama;
        this.absen = absen;
        this.kelas = kelas;
    }
}
