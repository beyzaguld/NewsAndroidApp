package edu.sabanciuniv.beyzagul_demir_hw2;

import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.os.Handler;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TabLayout tabs;
    ProgressBar prgBar;
    ViewPager2 viewPager;

    Handler categoryHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            List<String> data = (List<String>) message.obj;
            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        prgBar = findViewById(R.id.prgBar_main);
        viewPager = findViewById(R.id.viewPager);
        prgBar.setVisibility(View.VISIBLE);

        NewsRepo repo = new NewsRepo();

        repo.getAllCategories(((NewsApp) getApplication()).srv, categoryHandler);

        ViewPagerAdapter adp = new ViewPagerAdapter(this);
        this.viewPager.setAdapter(adp);


        viewPager.setVisibility(View.VISIBLE);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabs.getTabAt(position).select();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
    }
}