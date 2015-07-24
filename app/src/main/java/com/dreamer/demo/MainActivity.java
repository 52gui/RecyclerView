package com.dreamer.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener{

    private RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Article article = new Article();
            article.setTitle("标题" + i);
            article.setContent("内容" + i);

            articles.add(article);
        }

        adapter = new Adapter(this,articles);
        adapter.setItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Log.e("test","Activity收到了来自Adapter的点击事件，点击了"+position);
    }
}
