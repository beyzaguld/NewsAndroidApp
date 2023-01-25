package edu.sabanciuniv.beyzagul_demir_hw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class NewsDetailsActivity extends AppCompatActivity {

    int currentNewsId;

    ImageView detailed_img;
    TextView detailTitle;
    TextView detailDate;
    TextView detailText;

    ProgressBar prgBarDetails;

    Handler newsHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            List<NewsItem> objData = (List<NewsItem>) message.obj;

            NewsRepo repo = new NewsRepo();
            repo.downloadImage(((NewsApp) getApplication()).srv, imgHandler, objData.get(0).getImg());

            detailTitle.setText(objData.get(0).getTitle());
            detailDate.setText(objData.get(0).getDate());
            detailText.setText(objData.get(0).getText());


            return true;
        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bitmap img = (Bitmap) message.obj;
            detailed_img.setImageBitmap(img);
            prgBarDetails.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_layout);

        detailed_img = findViewById(R.id.img_detail);
        detailTitle = findViewById(R.id.txt_detail_title);
        detailDate = findViewById(R.id.txt_detail_date);
        detailText = findViewById(R.id.txt_detail_text);
        prgBarDetails = findViewById(R.id.prgBar_details);

        currentNewsId = (int) getIntent().getSerializableExtra("selectedNewsId");

        getSupportActionBar().setTitle((String) getIntent().getSerializableExtra("newsCategory"));

        NewsRepo repo = new NewsRepo();
        repo.getNewsById(((NewsApp) getApplication()).srv, newsHandler, currentNewsId);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.news_details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_comments)
        {
            Intent i = new Intent(NewsDetailsActivity.this, CommentsActivity.class);
            i.putExtra("selectedNewsId", currentNewsId);
            startActivity(i);
        }
        else if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return true;
    }
}
