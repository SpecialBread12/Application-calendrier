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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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


    private void enregistrerFete() {
        Fete fete = new Fete(
                vueAjouterFeteChampNom.getText().toString(),
                vueAjouterFeteChampDate.getText().toString()
        );

        // Enregistrement local (pas modifié)
        feteDAO = FeteDAO.getInstance();
        feteDAO.ajouterFete(fete);

        // Envoi au serveur avec Retrofit
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.ajouterFete(fete);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Fête envoyée avec succès !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur d'envoi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problème de connexion : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void naviguerRetourFete()
    {
        this.finish();
    }


}