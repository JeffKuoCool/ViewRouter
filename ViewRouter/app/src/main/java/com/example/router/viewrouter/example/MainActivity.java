package com.example.router.viewrouter.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.router.viewrouter.R;
import com.example.router.viewrouter.Router;

public class MainActivity extends AppCompatActivity {

    ActivityRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = new Router().init(ActivityRouter.class);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                router.start(MainActivity.this, 1, "s");
            }
        });

        findViewById(R.id.schame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                router.schame( MainActivity.this,1, "s");
            }
        });
    }

}
