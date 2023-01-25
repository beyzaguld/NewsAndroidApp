package edu.sabanciuniv.beyzagul_demir_hw2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PostCommentActivity extends AppCompatActivity {

    int currentID;

    EditText name;
    EditText commentToPost;
    Button postButton;
    TextView warningMessage;
    ProgressBar prgBarPosting;

    Handler postCommentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            int retVal = (int) message.obj;

            if (retVal == 1) {
                Toast.makeText(PostCommentActivity.this, "Comment posted.", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                prgBarPosting.setVisibility(View.INVISIBLE);
                name.setEnabled(true);
                commentToPost.setEnabled(true);
                postButton.setEnabled(true);
            }

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_post_layout);

        name = findViewById(R.id.post_comment_name);
        commentToPost = findViewById(R.id.post_comment_text);
        postButton = findViewById(R.id.post_button);
        warningMessage = findViewById(R.id.post_comment_warning);
        prgBarPosting = findViewById(R.id.prgBar_post_comment);
        currentID = (int) getIntent().getSerializableExtra("selectedNewsId");

        getSupportActionBar().setTitle("Post comment");

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = name.getText().toString();
                String comment = commentToPost.getText().toString();

                if (username.isEmpty() || comment.isEmpty()) {
                    warningMessage.setVisibility(View.VISIBLE);
                }
                else {
                    NewsRepo repo = new NewsRepo();
                    prgBarPosting.setVisibility(View.VISIBLE);
                    name.setEnabled(false);
                    commentToPost.setEnabled(false);
                    postButton.setEnabled(false);
                    repo.postComment(((NewsApp) getApplication()).srv, postCommentHandler, currentID, username, comment);
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return true;
    }
}
