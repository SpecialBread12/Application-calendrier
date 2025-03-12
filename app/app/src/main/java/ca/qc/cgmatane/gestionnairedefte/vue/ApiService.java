package ca.qc.cgmatane.gestionnairedefte.vue;
import ca.qc.cgmatane.gestionnairedefte.vue.modele.Fete;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("http://54.39.97.222/process.php") // Chemin du fichier PHP sur ton serveur
    Call<ApiResponse> ajouterUtilisateur(@Body Fete fete);
}
