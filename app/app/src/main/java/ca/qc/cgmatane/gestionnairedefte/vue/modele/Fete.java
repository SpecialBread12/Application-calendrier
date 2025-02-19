package ca.qc.cgmatane.gestionnairedefte.vue.modele;

import java.util.HashMap;

public class Fete {
    protected String nom;
    protected String date;
    protected int id;

    public Fete(String nom, String date, int id){
        this.nom = nom;
        this.date = date;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public HashMap<String, String> obtenirFetePourAfficher(){
        HashMap<String, String> fetePourAfficher = new HashMap<String, String>();
        fetePourAfficher.put("nom", this.nom);
        fetePourAfficher.put("date", this.date);
        fetePourAfficher.put("id", "" + this.id);
        return fetePourAfficher;
    }
}
