package ca.qc.cgmatane.gestionnairedefte.vue.modele;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class Fete {
    @SerializedName("nom")
    private String nom;

    @SerializedName("date")
    private String date;
    protected int id;

    public Fete(String nom, String date, int id){
        this.nom = nom;
        this.date = date;
        this.id = id;
    }
    public Fete(String nom, String date){
        this.nom = nom;
        this.date = date;
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
