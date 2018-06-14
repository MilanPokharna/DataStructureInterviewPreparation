package com.android.datastructureinterviewpreparation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }).show();
        }

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
}
