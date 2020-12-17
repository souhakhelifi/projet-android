package com.example.clockon2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.clockon2.Med.Ligne;
import com.example.clockon2.Med.Medicament;
import com.example.clockon2.Prise.Prise;
import com.example.clockon2.Prog.Programme;

import java.util.ArrayList;

public class DAO {
    // The database.
    private SQLiteDatabase db;

    // The database creator and updater helper.
    DBOpenHelper dbOpenHelper;


    public void openDB(Context ctx){
        dbOpenHelper = new DBOpenHelper(ctx, DBOpenHelper.Constants.DATABASE_NAME, null,
                DBOpenHelper.Constants.DATABASE_VERSION);
        db = dbOpenHelper.getWritableDatabase();

    }
    public void close(){
    db.close();
    }
    //Gestion des utilisateurs
   public long insertUser(ContentValues ct){
        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_user, null, ct);
        return rowId;
    }

   public long updateUser(ContentValues contentValues, String login){

       long rowId = db.update(DBOpenHelper.Constants.MY_TABLE_user, contentValues, "login=" + login, null);
        return rowId;
    }

    public long deleteUser(String login){
       long rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_user,  "login =" + login, null);
        return rowId;
    }
    //verifier existence user
    public boolean checkUser(String login, String pwd){
        String[] columns = new String[]{"login", "mdp"};

        String selection = "login =? and mdp= ?";
        String[] selectionArgs = { login, pwd };
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_user,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        if(count>0)
            return  true;
        else
            return  false;
    }

    public String getProfilUser(String login){
        String[] columns = new String[]{"login","profil"};

        String selection = "login =?";
        String[] selectionArgs = { login};
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_user,columns,selection,selectionArgs,null,null,null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public ArrayList<User> getUsers(Context ctx) {
        String[] projections=new String[] {"login", "mdp", "nom","age","profil" };
        String login,mdp,nom,age,profil;
        int num_p;
// To add a Where clause:
        String selection=null;
        String[] selectionArg=null;
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        ArrayList<User> values=new ArrayList<>();
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_user, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
        if(cursor.moveToFirst()){
        do{
            login=cursor.getString(0);
            mdp=cursor.getString(1);
            nom=cursor.getString(2);
            age=cursor.getString(3);
            profil=cursor.getString(4);
            User u=new User(login,mdp,nom,age,profil);
            values.add(u);

        }while(cursor.moveToNext());}
        return values;
    }


    //Gestion des programmes
   public long insertProg(ContentValues ct){

        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_prog, null, ct);
        return rowId;
    }
    // Update the database
    public long updateProg(ContentValues contentValues, int rowId){

        rowId = db.update(DBOpenHelper.Constants.MY_TABLE_prog, contentValues, "num_p =" + rowId, null);
        return rowId;
    }

    public long deleteProg(int rowId){
        rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_prog,  "num_p =" + rowId, null);
        return rowId;
    }

    public ArrayList<Programme> getPrograms(Context ctx, String idPatient) {
        String[] projections=new String[] {"num_p", "dateDeb", "duree","maladie","login" };
        String dateDeb,duree,maladie;
        int num_p;
// To add a Where clause:
        String selection="login =?";
        String[] selectionArg=new String[] {idPatient};
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_prog, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
        cursor.moveToFirst();
        int nb= cursor.getCount();
        Toast.makeText(ctx, "The number of elements retrieved is "+nb, Toast.LENGTH_LONG).show();
        Programme p;
        ArrayList<Programme> values= new ArrayList<>();
        if(cursor.moveToFirst()){
        do{
            num_p=cursor.getInt(0);
            dateDeb=cursor.getString(1);
            duree=cursor.getString(2);
            maladie=cursor.getString(3);
            p=new Programme(num_p,dateDeb,duree,maladie);
            values.add(p);
        }while(cursor.moveToNext());}
        return values;
    }

    //Gestion des medicaments
   public long insertMed(ContentValues ct){

        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_med, null, ct);
        return rowId;
    }


    public long deleteMed(String Refmed){
       long rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_med,  "ref_med like '" + Refmed+"'", null);
        return rowId;
    }

    public ArrayList<Medicament> getMed(Context ctx) {
        String[] projections=new String[] {"ref_med" };
        String ref_med;
        ArrayList<Medicament>lst=new ArrayList<>();
// To add a Where clause:
        String selection=null;
        String[] selectionArg=null;
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_med, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
        if(cursor.moveToFirst())
        {do{
            ref_med=cursor.getString(0);
            Medicament m=new Medicament(ref_med);
            lst.add(m);
        }while(cursor.moveToNext());}
        return lst ;
    }

    //Gestion des prises
    public long insertPrise(ContentValues ct){

        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_prise, null, ct);
        return rowId;
    }

    public long deletePrise(int rowId){
        rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_prise,  "num_prise =" + rowId, null);
        return rowId;
    }

    public ArrayList<Prise> getPrises(Context ctx, String idMed) {
        String[] projections=new String[] {"num_prise", "descr", "date","heure","qte","ref_med" };
        String date,heure,descr,ref_med;
        String qte;
        int num_prise;
// To add a Where clause:
        String selection="ref_med =?";
        String[] selectionArg=new String[] {""+idMed+""};
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_prise, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
ArrayList<Prise> values=new ArrayList<>();
Prise p ;
        if(cursor.moveToFirst()){
        do{
            num_prise=cursor.getInt(0);
            descr=cursor.getString(1);
            date=cursor.getString(2);
            heure=cursor.getString(3);
            qte=cursor.getString(4);
            ref_med=cursor.getString(5);
            p=new Prise(num_prise,date,heure,descr,qte,ref_med);
            values.add(p);
        }while(cursor.moveToNext());}
        return values ;
    }

    //Gestion des températures
    public long insertTemp(ContentValues ct){

        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_temp, null, ct);
        return rowId;
    }

    public long deleteTemp(int rowId){
        rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_temp,  "num_p =" + rowId, null);
        return rowId;
    }

    public ArrayList<Temperature> getTemp(Context ctx, String idProg) {
        String[] projections=new String[] {"num_temp", "degree", "login","date" };
        String date,num_p;
        int num_temp,degree;
        ArrayList<Temperature>values=new ArrayList<>();
// To add a Where clause:
        String selection="login =?";
        String[] selectionArg=new String[] {idProg};
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_temp, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);

        if(cursor.moveToFirst()){
        do{
            num_temp=cursor.getInt(0);
            degree=cursor.getInt(1);
            num_p=cursor.getString(2);
            date=cursor.getString(3);
            Temperature t=new Temperature(num_temp,degree,num_p,date);
            values.add(t);
        }while(cursor.moveToNext());}
        return values;
    }

    //Gestion des lignes medicaments
   public long insertLigne(ContentValues ct){

        long rowId = db.insert(DBOpenHelper.Constants.MY_TABLE_ligne, null, ct);
        return rowId;
    }

    public long deleteLigne(int rowId,String refMed){
        rowId = db.delete(DBOpenHelper.Constants.MY_TABLE_ligne,  "num_p =" + rowId+" and ref_med like '"+refMed+"'", null);
        return rowId;
    }

    public ArrayList<Ligne> getLigne(Context ctx, int idProg) {
        String[] projections=new String[] {"num_p", "ref_med","dateDeb","duree"};
        int num_p;
        String ref,date,duree ;
// To add a Where clause:
        String selection="num_p =?";
        String[] selectionArg=new String[] {""+idProg+""};
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_ligne, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
        int count=0;

        int nb= cursor.getCount();
        Medicament m;
        ArrayList<Ligne>values=new ArrayList<>();
        if(cursor.moveToFirst())
        {do{
            num_p=cursor.getInt(0);
            ref=cursor.getString(1);
            date=cursor.getString(2);
            duree=cursor.getString(3);
            Ligne l =new Ligne(ref,num_p,date,duree);
            values.add(l);
        }while(cursor.moveToNext());}
        return values ;
    }

    public Medicament getMed(String ref_med)
    {
        String[] projections=new String[] {"ref_med" };
        String date,duree;
        int num_p,num_temp;
// To add a Where clause:
        String selection="ref_med =?";
        String[] selectionArg=new String[] {""+ref_med+""};
// The groupBy clause:
        String groupBy=null;
// The having clause
        String having=null;
// The order by clause (column name followed by Asc or DESC)
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.Constants.MY_TABLE_med, projections, selection, selectionArg, groupBy, having, orderBy, maxResultsListSize);
        Medicament m=null;
        if(cursor.moveToFirst())
        {
        do{
            //num_p=cursor.getInt(0);
            date=cursor.getString(1);
            duree=cursor.getString(2);
            m=new Medicament(ref_med);
        }while(cursor.moveToNext());}
        return m ;
    }
}
