package ru.tinkoff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ru.tinkoff.newsdetail.view.NewsDetailFragment;

/**
 * Created by Vitaly on 08.12.2017.
 */
public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (savedInstanceState != null) {
            return;
        }

        Fragment fragment = new NewsDetailFragment();
        fragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNowAllowingStateLoss();
    }
}
