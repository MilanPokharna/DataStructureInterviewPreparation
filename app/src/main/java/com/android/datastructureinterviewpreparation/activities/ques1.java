package com.android.datastructureinterviewpreparation.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.datastructureinterviewpreparation.R;
import com.android.datastructureinterviewpreparation.interfaces.asyncResponse;
import com.android.datastructureinterviewpreparation.models.fetchData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ques1 extends AppCompatActivity implements asyncResponse {

    @BindView(R.id.ques)
    TextView ques;
    @SuppressLint("StaticFieldLeak")
    public static TextView question;
    @BindView(R.id.ans)
    TextView ans;

    @SuppressLint("StaticFieldLeak")
    public static TextView answer;

    fetchData fetchData = new fetchData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques1);
        ButterKnife.bind(this);
        question = (TextView)findViewById(R.id.question);
        answer = (TextView)findViewById(R.id.answer);
        if (isNetworkConnected()) {

            fetchData.execute();

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ques1.this);
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

    @Override
    public void processFinish(String output) {
        question.setText("hello "+output);

    }
}
