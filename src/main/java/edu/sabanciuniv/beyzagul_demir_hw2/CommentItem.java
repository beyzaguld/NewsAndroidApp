package edu.sabanciuniv.beyzagul_demir_hw2;
import java.io.Serializable;

public class CommentItem implements Serializable {

    private int commentID;
    private int newsID;
    private String username;
    private String commentText;

    public CommentItem(int commentID, int newsID, String username, String commentText) {
        this.commentID = commentID;
        this.newsID = newsID;
        this.username = username;
        this.commentText = commentText;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getNewsID() {
        return newsID;
    }

    public String getUsername() {
        return username;
    }

    public String getCommentText() {
        return commentText;
    }

}
