package ca.qc.cgmatane.gestionnairedefte.vue.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {
    private static BaseDeDonnees instance = null;

    public static synchronized BaseDeDonnees getInstance(Context contexte){
        instance = new BaseDeDonnees(contexte);
        return instance;
    }
    public static BaseDeDonnees getInstance(){
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte){
        super(contexte, "Gestionnaire", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "create table fete(id INTEGER PRIMARY KEY, nom TEXT, date TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onOpen(SQLiteDatabase db){
        /*
        String DELETE = "delete from fete where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into fete(nom, date) VALUES('Jom', '2001')";
        String INSERT_2 = "insert into fete(nom, date) VALUES('Tom', '2011/09/11')";
        String INSERT_3 = "insert into fete(nom, date) VALUES('Rom', '2111/09/11')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        String CREER_TABLE = "create table fete(id INTEGER PRIMARY KEY, nom TEXT, date TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
