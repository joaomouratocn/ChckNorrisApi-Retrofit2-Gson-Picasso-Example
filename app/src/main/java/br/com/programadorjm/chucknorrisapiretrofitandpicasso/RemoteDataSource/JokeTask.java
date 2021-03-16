 package br.com.programadorjm.chucknorrisapiretrofitandpicasso.RemoteDataSource;

import java.util.List;

import br.com.programadorjm.chucknorrisapiretrofitandpicasso.Model.JokeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeTask {

//  Tarefa que busca todas categrias
    public void findAllCategires(resultFindAllCategories resultFindAllCategories){
        HttpClient.jokeRetrofit().create(ChuckNorrisApi.class)
                .loadCategories()
                .enqueue(new Callback<List<String>>() {//Callback evento chamado apos a realização da tarefa
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if (response.isSuccessful()){
                            resultFindAllCategories.onSuccess(response.body());
                        }
                        resultFindAllCategories.onComplete();

                    }
                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        resultFindAllCategories.onError(t.getMessage());
                        resultFindAllCategories.onComplete();

                    }
                });
    }

//  Tarefa que carrega categoria seleciona para apresenta na jokeActivity
    public void findJokeBy(String category, resultFindCategoryBy resultFindCategoryBy){
        HttpClient.jokeRetrofit().create(ChuckNorrisApi.class)
                .findBy(category)
                .enqueue(new Callback<JokeModel>() {
                    @Override
                    public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                        if (response.isSuccessful()){resultFindCategoryBy.onSuccess(response.body());}
                        resultFindCategoryBy.onComplete();
                    }

                    @Override
                    public void onFailure(Call<JokeModel> call, Throwable t) {
                        resultFindCategoryBy.onError(t.getMessage());
                        resultFindCategoryBy.onComplete();
                    }
                });
    }

//  Interface para resposta da tarefa de todas as categorias
    public interface resultFindAllCategories {
        void onSuccess(List<String> categoryList);
        void onError(String messege);
        void onComplete();
    }

//  Interface para resposta de uma categoris selecionada
    public interface resultFindCategoryBy{
        void onSuccess(JokeModel jokeModel);
        void onError(String messege);
        void onComplete();
    }
}
