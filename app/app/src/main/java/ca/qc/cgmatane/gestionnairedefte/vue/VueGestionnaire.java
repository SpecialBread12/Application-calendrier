package ca.qc.cgmatane.gestionnairedefte.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.gestionnairedefte.R;
import ca.qc.cgmatane.gestionnairedefte.vue.donnee.BaseDeDonnees;
import ca.qc.cgmatane.gestionnairedefte.vue.donnee.FeteDAO;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;

public class VueGestionnaire extends AppCompatActivity {

    protected ListView vuelisteGestionnaire;
    //protected List<HashMap<String, String>> listeFete;
    protected List<Fete> listeFete;
    protected Intent intentionNaviguerAjouterFete;
    protected Intent intentionNaviguerModifierFete;
    protected FeteDAO feteDAO;
    static final public int ACTIVITE_AJOUTER_FETE = 1;
    static final public int ACTIVITE_MODIFIER_FETE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vue_gestionnaire);
        vuelisteGestionnaire = (ListView)findViewById(R.id.vueListeGestionnaire);
        BaseDeDonnees.getInstance(getApplicationContext());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        feteDAO = FeteDAO.getInstance();
        /*
        listeFete = feteDAO.listerFete();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,listeFete,android.R.layout.two_line_list_item,
                new String[] {"Nom", "Date"},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        vuelisteGestionnaire.setAdapter(adapteur);
        */
        afficherListeFete();
        Button vueGestionActionAjouterFete = (Button)findViewById(R.id.vueGestionActionAjouterFete);

        intentionNaviguerAjouterFete = new Intent(this, VueAjouterFete.class);

        vueGestionActionAjouterFete.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View arg0) {
                    // Placer l'action du bouton
                    /*
                    Toast message = Toast.makeText(getApplicationContext(),"Action ajouter fete",Toast.LENGTH_SHORT);
                   message.show();
                   */
                    // startActivity(intentionNaviguerAjouterFete);
                    startActivityForResult(intentionNaviguerAjouterFete, ACTIVITE_AJOUTER_FETE);
                }
            }
        );
        intentionNaviguerModifierFete = new Intent(this, VueModifierFete.class);

        vuelisteGestionnaire.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View vue, int positionDansAdapteur, long positionItem) {
                        ListView vueListeFete = (ListView)vue.getParent();


                        @SuppressWarnings("unchecked")
                        HashMap<String,String> fete = (HashMap<String, String>) vueListeFete.getItemAtPosition((int)positionItem);
                        /*
                        Toast message = Toast.makeText(getApplicationContext(),"Position "+positionItem + " Nom " + Fete.get("Nom"),Toast.LENGTH_SHORT);
                        message.show();
                        */
                        //startActivity(intentionNaviguerModifierFete);
                        intentionNaviguerModifierFete.putExtra("id", fete.get("id"));
                        startActivityForResult(intentionNaviguerModifierFete, ACTIVITE_MODIFIER_FETE);
                    }
                }
        );
    }
    protected void onActivityResult(int activite, int resultat, Intent donnees) {

        super.onActivityResult(activite, resultat, donnees);
        switch (activite){
            case ACTIVITE_AJOUTER_FETE:
                afficherListeFete();
                break;

            case ACTIVITE_MODIFIER_FETE:
                afficherListeFete();
                break;

        }
    }
    public void afficherListeFete(){
        listeFete = feteDAO.listerFete();

        List<HashMap<String, String>> listeFetePourAfficher =
                new ArrayList<>();

        for(Fete fete:listeFete){
            listeFetePourAfficher.add(fete.obtenirFetePourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeFetePourAfficher,
                android.R.layout.two_line_list_item,
                new String[] {"nom", "date", "id"},
                new int[] {android.R.id.text1, android.R.id.text2});
        vuelisteGestionnaire.setAdapter(adapteur);
    }
/*
    public List<HashMap<String, String>> preparerListeFete() {
        List<HashMap<String, String>> listeFete = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> fete;

        fete = new HashMap<String,String>();
        fete.put("Nom", "Jorn");
        fete.put("Date", "8 Février");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Cola");
        fete.put("Date", "23 Mars");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Carole");
        fete.put("Date", "31 Décembre");
        listeFete.add(fete);

        fete = new HashMap<String,String>();
        fete.put("Nom", "Épic Sax Guy");
        fete.put("Date", "14 Janvier");
        listeFete.add(fete);
        return listeFete;
    }

 */
}