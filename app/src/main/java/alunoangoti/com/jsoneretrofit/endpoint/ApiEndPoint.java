package alunoangoti.com.jsoneretrofit.endpoint;
import alunoangoti.com.jsoneretrofit.dominio.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndPoint {
    @GET("posts/{x}")
    Call<Example> obterpost(@Path("x") int a);
}
