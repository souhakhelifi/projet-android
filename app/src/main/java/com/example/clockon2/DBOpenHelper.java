package com.example.clockon2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
    /**
     * @goals This class aims to show the constant to use for the DBOpenHelper
     */
    public static class Constants implements BaseColumns {

        // The database name
        public static final String DATABASE_NAME = "clock_ON1";

        // The database version
        public static final int DATABASE_VERSION = 1;

        // The table Name
        public static final String MY_TABLE_user = "User";
        public static final String MY_TABLE_prog = "Programme";
        public static final String MY_TABLE_med = "Medicament";
        public static final String MY_TABLE_prise = "Prise";
        public static final String MY_TABLE_temp = "Temperature";
        public static final String MY_TABLE_ligne = "LigneMedicament";

    }

    /**
     * The static string to create the database.
     */
    private static final String Table_User_CREATE = "create table "
            + Constants.MY_TABLE_user + "(login TEXT, " +
            "mdp TEXT, nom TEXT,age INTEGER,profil TEXT); ";

    private static final String Table_PROG_CREATE = "create table "
            + Constants.MY_TABLE_prog + "(num_p integer primary key autoincrement, " +
            "dateDeb TEXT, duree TEXT,maladie TEXT,login TEXT,FOREIGN KEY (login) REFERENCES User(login)); ";

    private static final String Table_MED_CREATE = "create table "
            + Constants.MY_TABLE_med + "(ref_med TEXT primary key, " +
            "descr TEXT); ";

    private static final String Table_PRISE_CREATE = "create table "
            + Constants.MY_TABLE_prise + "(num_prise integer primary key autoincrement, " +
            "descr TEXT, date TEXT,heure TEXT, qte TEXT, ref_med TEXT," +
            "FOREIGN KEY (ref_med) REFERENCES Medicament(ref_med)); ";

    private static final String Table_Temp_CREATE = "create table "
            + Constants.MY_TABLE_temp + "(num_temp integer primary key autoincrement, " +
            "degree INTEGER, login TEXT, date TEXT ," +
            "FOREIGN KEY (login) REFERENCES User(login)); ";

    private static final String Table_Ligne_CREATE = "create table "
            + Constants.MY_TABLE_ligne + "(num_p integer, " +
            "ref_med TEXT, dateDeb TEXT, duree TEXT," +
            "FOREIGN KEY (ref_med) REFERENCES Medicament(ref_med)," +
            "FOREIGN KEY (num_p) REFERENCES Programme(num_p)," +
            "Primary key (ref_med,num_p)); ";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the new database using the SQL string Database_create
        db.execSQL(Table_User_CREATE);
        db.execSQL(Table_PROG_CREATE);
        db.execSQL(Table_MED_CREATE);
        db.execSQL(Table_PRISE_CREATE);
        db.execSQL(Table_Temp_CREATE);
        db.execSQL(Table_Ligne_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_ligne);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_prog);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_prise);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_med);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_temp);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE_user);
        // Create the new one
        onCreate(db);
        // or do a smartest stuff
    }
}