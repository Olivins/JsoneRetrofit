
package alunoangoti.com.jsoneretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import alunoangoti.com.jsoneretrofit.dominio.Example;
import alunoangoti.com.jsoneretrofit.endpoint.ApiEndPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button botao = (Button) findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int x = Integer.parseInt(findViewById(R.id.entradanumero).toString());
                Gson gson = new GsonBuilder()

                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()

                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                ApiEndPoint apiService = retrofit.create(ApiEndPoint.class);
                final Call<Example> call = apiService.obterpost(x);
                call.enqueue(new Callback<Example>() {//chamada assíncrona
                    public void onResponse(Call<Example> call,

                                           Response<Example> response) {

                        int statusCode = response.code();
                        Example example = response.body();
                        String title = example.getTitle().toString();
                        TextView mensagem = findViewById(R.id.mensagem);
                        mensagem.setText(title);
                        ;}



                    public void onFailure(Call<Example> call, Throwable t) {
                        // se a porra fuder vai aparecer isso aki
                        Log.i("teste",t.toString());
                    }});


            }
        });


    }
}
