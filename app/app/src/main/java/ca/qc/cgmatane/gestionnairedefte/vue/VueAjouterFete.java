package ca.qc.cgmatane.gestionnairedefte.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ca.qc.cgmatane.gestionnairedefte.R;
import ca.qc.cgmatane.gestionnairedefte.vue.donnee.FeteDAO;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;




public class VueAjouterFete extends AppCompatActivity {

    protected Intent intentionNaviguerListeFete;
    protected EditText vueAjouterFeteChampNom;
    protected EditText vueAjouterFeteChampDate;
    protected FeteDAO feteDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vue_ajouter_fete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button vueActionAjouterFeteAnnuler = (Button)findViewById(R.id.vueActionAjouterFeteAnnuler);


        vueActionAjouterFeteAnnuler.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // Placer l'action du bouton
                    /*
                    Toast message = Toast.makeText(
                            getApplicationContext()
                            ,"Action annuler",
                            Toast.LENGTH_SHORT);
                   message.show();
                    */
                        naviguerRetourFete();
                        //startActivity(intentionNaviguerListeFete);
                    }
                }
        );

        vueAjouterFeteChampNom = (EditText)findViewById(R.id.vueAjouterFeteChampNom);
        vueAjouterFeteChampDate = (EditText)findViewById(R.id.vueAjouterFeteChampDate);
        Button vueAjouterFeteActionAjouter = (Button)findViewById(R.id.vueAjouterFeteActionAjouter);

        vueAjouterFeteActionAjouter.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // Placer l'action du bouton

                    Toast message = Toast.makeText(
                            getApplicationContext()
                            ,"Nom : " + vueAjouterFeteChampNom.getText().toString() + " Date : " + vueAjouterFeteChampDate.getText().toString(),
                            Toast.LENGTH_SHORT);
                   message.show();

                        enregistrerFete();
                        naviguerRetourFete();

                    }
                }
        );
    }


    private void enregistrerFete(){
        //vueAjouterFeteChampNom.getText().toString();
        //vueAjouterFeteChampDate.getText().toString();
/*
        HashMap<String,String> fete;

        fete = new HashMap<String,String>();
        fete.put("Nom", vueAjouterFeteChampNom.getText().toString());
        fete.put("Date", vueAjouterFeteChampDate.getText().toString());
*/
        Fete fete = new Fete(vueAjouterFeteChampNom.getText().toString(),
                vueAjouterFeteChampDate.getText().toString(), 0);


        feteDAO = FeteDAO.getInstance();
        feteDAO.ajouterFete(fete);
    }
    public void naviguerRetourFete()
    {
        this.finish();
    }

    public static class ApiService {
    }
}