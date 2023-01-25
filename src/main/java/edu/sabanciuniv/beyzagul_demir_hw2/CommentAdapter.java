package edu.sabanciuniv.beyzagul_demir_hw2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentItemViewHolder> {

    Context context;
    List<CommentItem> data;

    public CommentAdapter(Context context, List<CommentItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentItemViewHolder c = new CommentItemViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_row_layout,parent,false));
        return c;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {

        holder.commentName.setText(data.get(position).getUsername());
        holder.commentText.setText(data.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentItemViewHolder extends RecyclerView.ViewHolder {
        TextView commentName;
        TextView commentText;
        CardView rowLayout;

        public CommentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            commentName = itemView.findViewById(R.id.comment_name);
            commentText = itemView.findViewById(R.id.comment_text);
            rowLayout = itemView.findViewById(R.id.commentRow);
        }
    }

}

