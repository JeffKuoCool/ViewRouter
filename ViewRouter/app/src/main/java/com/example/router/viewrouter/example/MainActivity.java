package com.example.router.viewrouter.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.router.viewrouter.R;
import com.example.router.viewrouter.Router;
import com.example.router.viewrouter.example.model.Bezier;
import com.example.router.viewrouter.example.view.BezierView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityRouter router;
    private BezierView bezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bezierView = findViewById(R.id.bezierView);

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
        setBezierView();
    }

    private void setBezierView(){
        //TODO mock数据
        List<Bezier> mock = new ArrayList<>();
        for (int i=0;i<12;i++){
            mock.add(new Bezier(new Random().nextInt(10)+1, i+1));
        }
        bezierView.setData(mock);
    }

}
