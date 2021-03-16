package br.com.programadorjm.chucknorrisapiretrofitandpicasso;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import br.com.programadorjm.chucknorrisapiretrofitandpicasso.Model.JokeModel;
import br.com.programadorjm.chucknorrisapiretrofitandpicasso.RemoteDataSource.JokeTask;


public class JokeActivity extends AppCompatActivity implements JokeTask.resultFindCategoryBy {
    public static final String CATEGORY_NAME = "category_name";
    ProgressDialog progressDialog;
    ImageView imgJoke;
    TextView txtJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        imgJoke = findViewById(R.id.img_joke);
        txtJoke = findViewById(R.id.txt_joke);

        FloatingActionButton floatingActionButton = findViewById(R.id.btn_float_joke);
        floatingActionButton.setOnClickListener(v -> jokeLoad());

        jokeLoad();
    }

    private void jokeLoad() {
        progressDialogShow();
        JokeTask jokeTask = new JokeTask();
        String category = getIntent().getExtras().getString(CATEGORY_NAME);
        jokeTask.findJokeBy(category, this);
    }

    @Override
    public void onSuccess(JokeModel jokeModel) {
        Picasso.get().load(jokeModel.getUrlIcon()).into(imgJoke);
        txtJoke.setText(jokeModel.getJoke());
    }

    @Override
    public void onError(String messege) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        progressDialogHide();
    }

    private void progressDialogShow(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading Joke");
            progressDialog.setMessage("Loading joke Wait....!");
        }
        progressDialog.show();
    }

    private void progressDialogHide(){
        if (progressDialog != null){progressDialog.hide();}
    }
}