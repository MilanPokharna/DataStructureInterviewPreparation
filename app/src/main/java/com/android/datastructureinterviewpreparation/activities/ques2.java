package com.android.datastructureinterviewpreparation.activities;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.datastructureinterviewpreparation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ques2 extends AppCompatActivity {
    @BindView(R.id.ques)
    TextView ques;
    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.ans)
    TextView ans;
    @BindView(R.id.answer)
    TextView answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques1);
        ButterKnife.bind(this);
        AssetManager am = getApplicationContext().getAssets();


    }
}
