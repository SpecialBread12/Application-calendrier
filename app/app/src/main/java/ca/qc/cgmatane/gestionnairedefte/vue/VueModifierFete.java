package ca.qc.cgmatane.gestionnairedefte.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ca.qc.cgmatane.gestionnairedefte.R;
import ca.qc.cgmatane.gestionnairedefte.vue.donnee.FeteDAO;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;

public class VueModifierFete extends AppCompatActivity {

    protected EditText vueModifierChampNom;
    protected EditText vueModifierChampDate;
    protected FeteDAO feteDAO;
    protected Fete fete;

    protected Intent intentionNaviguerListeFete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vue_modifier_fete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button vueActionModifierFeteAnnuler = (Button)findViewById(R.id.vueActionModifierFeteAnnuler);


        vueActionModifierFeteAnnuler.setOnClickListener(
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
        Bundle parametres = this.getIntent().getExtras();
        String idPrarametre = (String) parametres.get("id");
        int id = Integer.parseInt(idPrarametre);
        feteDAO = FeteDAO.getInstance();
        fete = feteDAO.chercherFeteParId(id);

        vueModifierChampNom = (EditText)findViewById(R.id.vueModifierFeteChampNom);
        vueModifierChampDate = (EditText)findViewById(R.id.vueModifierFeteChampDate);
        vueModifierChampNom.setText(fete.getNom());
        vueModifierChampDate.setText(fete.getDate());
        Button vueModifierFeteActionModifier = (Button)findViewById(R.id.vueModifierFeteActionModifier);

        vueModifierFeteActionModifier.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enregistrerFete();
                        naviguerRetourFete();
                    }
                }

        );
    }
    private void enregistrerFete(){
        fete.setNom(vueModifierChampNom.getText().toString());
        fete.setDate(vueModifierChampDate.getText().toString());

        feteDAO = FeteDAO.getInstance();
        feteDAO.modifierFete(fete);
    }
    public void naviguerRetourFete()
    {
        this.finish();
    }
}