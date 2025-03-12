package ca.qc.cgmatane.gestionnairedefte.vue;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ton-serveur.com/api/") // Remplace par ton URL
                    .addConverterFactory(GsonConverterFactory.create()) // Convertit le JSON
                    .build();
        }
        return retrofit;
    }
}