package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapplication.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    TextView text_title,text_author,text_time,text_detail,text_content;
    ImageView img_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        text_title=findViewById(R.id.text_detail_title);
        text_author=findViewById(R.id.text_detail_author);
        text_time=findViewById(R.id.text_detail_time);
        text_detail=findViewById(R.id.text_detail_details);
        text_content=findViewById(R.id.text_detail_content);
        img_name=findViewById(R.id.img_detail_news);

    headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");
    text_title.setText(headlines.getTitle());
    text_author.setText(headlines.getAuthor());
    text_time.setText(headlines.getPublishedAt());
    text_detail.setText(headlines.getDescription());
    text_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_name);


    }
}