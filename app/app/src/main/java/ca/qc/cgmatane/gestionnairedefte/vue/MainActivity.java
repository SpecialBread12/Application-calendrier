package ca.qc.cgmatane.gestionnairedefte.vue;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import ca.qc.cgmatane.gestionnairedefte.R;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    public interface ApiService {
        @GET("getUser.php") // Correspond à l'URL de l'API côté serveur
        Call<Fete> getUser(@Query("id") int feteId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_ajouter_fete);

        // Initialisation de l'API
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Appel de l'API pour obtenir un utilisateur avec l'ID 1
        Call<Fete> call = apiService.getUser(1);
        call.enqueue(new Callback<Fete>() {
            @Override
            public void onResponse(Call<Fete> call, Response<Fete> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Fete fete = response.body();
                    Log.d("Retrofit", "Nom : " + fete.getNom() + ", Email : " + fete.getDate());
                } else {
                    Log.e("Retrofit", "Erreur de réponse");
                }
            }

            @Override
            public void onFailure(Call<Fete> call, Throwable t) {
                Log.e("Retrofit", "Erreur de connexion : " + t.getMessage());
            }
        });
    }
}
