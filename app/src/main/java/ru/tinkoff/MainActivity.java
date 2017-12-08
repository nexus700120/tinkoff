package ru.tinkoff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.tinkoff.newslist.view.NewsListFragmnet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NewsListFragmnet())
                .commitNowAllowingStateLoss();
    }
}
