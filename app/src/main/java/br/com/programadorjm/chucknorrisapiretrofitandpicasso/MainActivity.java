package br.com.programadorjm.chucknorrisapiretrofitandpicasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.programadorjm.chucknorrisapiretrofitandpicasso.RemoteDataSource.JokeTask;

public class MainActivity extends AppCompatActivity implements MainAdapter.onClickListener, JokeTask.resultFindAllCategories {
    MainAdapter mainAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recycleview);

        mainAdapter = new MainAdapter(new ArrayList<>(), this);

        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCategory();
    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.CATEGORY_NAME, mainAdapter.getCategoryList().get(position));
        startActivity(intent);

    }

    private void loadCategory(){
        progressDialogShow();
        JokeTask jokeTask = new JokeTask();
        jokeTask.findAllCategires(this);
    }

    public void progressDialogShow(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Load list");
            progressDialog.setMessage("Wait load list...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void progressDialogHide(){
        if(progressDialog != null){progressDialog.hide();}
    }

    @Override
    public void onSuccess(List<String> categoryList) {
        mainAdapter.getCategoryList().clear();
        mainAdapter.getCategoryList().addAll(categoryList);
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String messege) {
        progressDialog.hide();
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        progressDialogHide();
    }
}