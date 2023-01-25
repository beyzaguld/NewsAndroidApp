package edu.sabanciuniv.beyzagul_demir_hw2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    Context context;
    List<NewsItem> data;

    public NewsAdapter(Context context, List<NewsItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.news_row_layout,parent,false);

        NewsItemViewHolder holder = new NewsItemViewHolder(root);
        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.rowDate.setText(data.get(position).getDate());
        holder.rowTitle.setText(data.get(position).getTitle());

        NewsApp app = (NewsApp) ((AppCompatActivity)context).getApplication();

        holder.downloadImage(app.srv, data.get(holder.getAdapterPosition()).getImg());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NewsDetailsActivity.class);
                i.putExtra("selectedNewsId", data.get(position).getId());
                i.putExtra("newsCategory", data.get(position).getCatName());
                ((AppCompatActivity)context).startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder {

        ImageView rowImg;
        TextView rowDate;
        TextView rowTitle;
        CardView row;

        ProgressBar progressBar_LoadRowImg;

        boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Bitmap img = (Bitmap)msg.obj;
                rowImg.setImageBitmap(img);
                imageDownloaded = true;
                progressBar_LoadRowImg.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        public NewsItemViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImg = itemView.findViewById(R.id.row_img);
            rowDate = itemView.findViewById(R.id.row_date);
            rowTitle = itemView.findViewById(R.id.row_title);
            progressBar_LoadRowImg = itemView.findViewById(R.id.prgBar_row);
            row = itemView.findViewById(R.id.catRow);
        }

        public void downloadImage(ExecutorService srv, String path){

            if (!imageDownloaded){
                NewsRepo repo = new NewsRepo();
                repo.downloadImage(srv,imgHandler,path);
            }

        }
    }
}

