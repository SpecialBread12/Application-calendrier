package ca.qc.cgmatane.gestionnairedefte.vue.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;

public class FeteDAO {
    private static FeteDAO instance = null;
    //private List<HashMap<String, String>> listeFete;
    private List<Fete> listeFete;
    private BaseDeDonnees baseDeDonnees;
    private FeteDAO(){
        //listeFete = new ArrayList<HashMap<String, String>>();
        listeFete = new ArrayList<Fete>();
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        //preparerListeFete();
    }

    private void preparerListeFete() {
/*
        HashMap<String,String> fete;

        fete = new HashMap<String,String>();
        fete.put("Nom", "Jorn");
        fete.put("Date", "2000/02/08");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Cola");
        fete.put("Date", "2030/11/20");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Carole");
        fete.put("Date", "2013/12/31");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Épic Sax Guy");
        fete.put("Date", "2024/04/10");
        listeFete.add(fete);

*/
        listeFete.add(new Fete("Tom", "2001/12/31", 1));
        listeFete.add(new Fete("Jom", "2021/12/23", 2));
        listeFete.add(new Fete("Eom", "2001/09/11", 3));

    }

    public static FeteDAO getInstance(){
        if(null == instance){
            instance = new FeteDAO();
        }
        return instance;
    }
    /*
    public List<Fete> listerFete(){
        return listeFete;
    }

     */

    public List<Fete> listerFete(){
        String LISTER_FETE = "SELECT * FROM fete";
        Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_FETE, null);
        this.listeFete.clear();
        Fete fete;

        int indexId = curseur.getColumnIndex("int"); //Y'a eu une erreur de frappe lors de la création des valeurs, je suis donc coincé avec ça
        int indexDate = curseur.getColumnIndex("date");
        int indexNom = curseur.getColumnIndex("nom");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()){
            int id = curseur.getInt(indexId);
            String date = curseur.getString(indexDate);
            String nom = curseur.getString(indexNom);
            fete = new Fete(nom, date, id);
            this.listeFete.add(fete);
        }
        return listeFete;

    }
    /*
    public void ajouterFete(HashMap<String, String> fete){
        //listeFete.add(fete);
    }

     */
    public void ajouterFete(Fete fete){
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues feteEnCleValeur = new ContentValues();
            feteEnCleValeur.put("date", fete.getDate());
            feteEnCleValeur.put("nom", fete.getNom());

            baseDeDonneesEcriture.insertOrThrow("fete", null, feteEnCleValeur);
            baseDeDonneesEcriture.setTransactionSuccessful();
        }
        catch(Exception e){
            Log.d("FeteDAO", "ERREUR : problème dans l'ajout d'une fête dans la base de données");
        }
        finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public Fete chercherFeteParId(int id){
        listerFete();
        for(Fete feteRecherche : this.listeFete){
            if(feteRecherche.getId() == id) return feteRecherche;
        }
        return null;
    }
    public void modifierFete(Fete fete) {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues feteEnCleValeur = new ContentValues();
            feteEnCleValeur.put("date", fete.getDate());
            feteEnCleValeur.put("nom", fete.getNom());

            // La clause WHERE pour spécifier quelle ligne mettre à jour
            String selection = "int = ?";
            String[] selectionArgs = { String.valueOf(fete.getId()) };

            // Met à jour la ligne qui correspond à la clause WHERE
            int rowsAffected = baseDeDonneesEcriture.update("fete", feteEnCleValeur, selection, selectionArgs);

            if (rowsAffected == 0) {
                // Aucune ligne mise à jour signifie que l'ID n'existe pas
                Log.d("FeteDAO", "Aucune fête trouvée avec l'ID : " + fete.getId());
            }

            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("FeteDAO", "ERREUR : problème dans la modification de la fête dans la base de données", e);
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

}
