package edu.sabanciuniv.beyzagul_demir_hw2;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FragmentAll extends Fragment {

    RecyclerView recView;
    List<NewsItem> data;
    View v;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            data = (List<NewsItem>) message.obj;
            recView = v.findViewById(R.id.recView_news);
            recView.setLayoutManager(new LinearLayoutManager(getContext()));

            NewsAdapter adp = new NewsAdapter(getContext(), data);
            recView.setAdapter(adp);

            return true;
        }
    });

    public FragmentAll() {
        // Required empty public constructor
    }

    public static FragmentAll newInstance(int param1) {
        FragmentAll fragment = new FragmentAll();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewsRepo repo = new NewsRepo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            repo.getAll(((NewsApp) getActivity().getApplication()).srv, handler);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.news_fragment_layout, container, false);

        return v;
    }

}
