package edu.sabanciuniv.beyzagul_demir_hw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    int newsID;

    RecyclerView recView;

    TextView txtDialog;
    ProgressBar prgBar;

    Handler commentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            boolean flag = true;
            List<CommentItem> data = (List<CommentItem>)msg.obj;
            CommentAdapter adp = new CommentAdapter(CommentsActivity.this, data);
            recView.setAdapter(adp);
            prgBar.setVisibility(View.INVISIBLE);
            txtDialog.setVisibility(View.INVISIBLE);
            return flag;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_layout);

        newsID = (int) getIntent().getSerializableExtra("selectedNewsId");
        recView = findViewById(R.id.recView_comments);
        recView.setLayoutManager(new LinearLayoutManager(this));

        txtDialog = findViewById(R.id.txt_load_comment);
        prgBar = findViewById(R.id.prgBar_load_comments);

        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        NewsRepo repo = new NewsRepo();
        repo.getCommentById(((NewsApp) getApplication()).srv, commentHandler, newsID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.comment_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_post_comment)
        {
            Intent i = new Intent(CommentsActivity.this, PostCommentActivity.class);
            i.putExtra("selectedNewsId", newsID);
            startActivity(i);
        }
        else if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return true;
    }

}
