package ca.qc.cgmatane.gestionnairedefte.vue;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.cgmatane.gestionnairedefte.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etEmail;
    private Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.etNom);
        etEmail = findViewById(R.id.etEmail);
        btnEnvoyer = findViewById(R.id.btnEnvoyer);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerDonnees();
            }
        });
    }

    private void envoyerDonnees() {
        String nom = etNom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nom.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Utilisateur utilisateur = new Utilisateur(nom, email);

        Call<ApiResponse> call = apiService.ajouterUtilisateur(utilisateur);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur serveur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Échec de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
