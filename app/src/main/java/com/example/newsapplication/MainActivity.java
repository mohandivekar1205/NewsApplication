package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.newsapplication.Models.CustomAdapter;
import com.example.newsapplication.Models.NewsApiResponse;
import com.example.newsapplication.Models.NewsHeadlines;
import com.example.newsapplication.Models.OnFetchDataListener;
import com.example.newsapplication.Models.RequestManager;
import com.example.newsapplication.Models.SelectListener;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog=new ProgressDialog(this);
        dialog.setTitle("downloading...");
        dialog.show();
        setContentView(R.layout.activity_main);


        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,"technology",null);
    }


    private final OnFetchDataListener<NewsApiResponse> listener =new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void OnNewsClick(NewsHeadlines headlines) {
            startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data", (Serializable) headlines));
    }
}