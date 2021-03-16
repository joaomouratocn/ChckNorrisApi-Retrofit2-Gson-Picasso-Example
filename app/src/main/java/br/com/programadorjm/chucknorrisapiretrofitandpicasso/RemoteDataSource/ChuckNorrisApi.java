package br.com.programadorjm.chucknorrisapiretrofitandpicasso.RemoteDataSource;

import java.util.List;

import br.com.programadorjm.chucknorrisapiretrofitandpicasso.Model.JokeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckNorrisApi {
    static final String BASE_URL = "https://api.chucknorris.io/";

    @GET("jokes/categories")
    Call<List<String>> loadCategories();

    @GET("jokes/random")
    Call<JokeModel> findBy(@Query("category") String category);
}
