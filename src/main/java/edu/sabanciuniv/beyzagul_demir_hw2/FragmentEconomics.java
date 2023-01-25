package edu.sabanciuniv.beyzagul_demir_hw2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FragmentEconomics extends Fragment {

    RecyclerView recNews;
    List<NewsItem> data;
    View v;

    Handler newsHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            data = (List<NewsItem>) message.obj;


            recNews = v.findViewById(R.id.recView_news);
            recNews.setLayoutManager(new LinearLayoutManager(getContext()));

            NewsAdapter adp = new NewsAdapter(getContext(), data);

            recNews.setAdapter(adp);


            return true;
        }
    });

    public FragmentEconomics() {}

    public static FragmentEconomics newInstance(int param1) {
        FragmentEconomics fragment = new FragmentEconomics();
        Bundle args = new Bundle();
        args.putInt("3", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            NewsRepo repo = new NewsRepo();
            repo.getNewsByCatId(((NewsApp) getActivity().getApplication()).srv, newsHandler, 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.news_fragment_layout, container, false);

        return v;
    }

}

