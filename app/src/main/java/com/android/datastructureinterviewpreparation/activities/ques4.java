package com.android.datastructureinterviewpreparation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.datastructureinterviewpreparation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ques4 extends AppCompatActivity {
    @BindView(R.id.ques)
    TextView ques;
    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.ans)
    TextView ans;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.prev)
    Button prev;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.app)
    ImageView imageView;
    @BindView(R.id.applink)
    TextView applink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques1);
        ButterKnife.bind(this);
        if (isNetworkConnected()) {
            String q = ques1.myqueslist.get(4);
            String a = ques1.myanslist.get(4);
            question.setText(q);
            answer.setText(a);

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ques4.this);
            dialog.setTitle("Connectino Error ");
            dialog.setCancelable(false);
            dialog.setMessage("Unable to connect with the server.\n Check your Internet connection and try again.");
            dialog.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), ques4.class);
                    startActivity(intent);
                }
            }).show();
        }
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://courses.learncodeonline.in/"));
                startActivity(i);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callme();
            }
        });
        applink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callme();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @OnClick({R.id.prev, R.id.next, R.id.linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.prev:
                Intent i = new Intent(ques4.this,ques3.class);
                startActivity(i);
                break;
            case R.id.next:
                Intent i2 = new Intent(ques4.this,ques5.class);
                startActivity(i2);
                break;
            case R.id.linear:
                break;
        }
    }
    public void callme()
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=in.learncodeonline.lco")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=in.learncodeonline.lco")));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ques4.this,HomeActivity.class);
        startActivity(i);
    }
}
