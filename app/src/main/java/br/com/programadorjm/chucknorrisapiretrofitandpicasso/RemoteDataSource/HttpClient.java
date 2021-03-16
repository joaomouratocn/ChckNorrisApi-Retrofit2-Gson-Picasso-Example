package br.com.programadorjm.chucknorrisapiretrofitandpicasso.RemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    static Retrofit jokeRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ChuckNorrisApi.BASE_URL)
                .build();
    }
}
