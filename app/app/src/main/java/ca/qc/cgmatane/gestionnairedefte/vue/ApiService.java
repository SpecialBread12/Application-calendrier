package ca.qc.cgmatane.gestionnairedefte.vue;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("process.php") // Chemin du fichier PHP sur ton serveur
    Call<ApiResponse> ajouterFete(@Body Fete fete);
}
