package edu.sabanciuniv.beyzagul_demir_hw2;

import android.app.Application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsApp extends Application {

    ExecutorService srv = Executors.newCachedThreadPool();

}
