package com.android.datastructureinterviewpreparation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.datastructureinterviewpreparation.R;
import com.android.datastructureinterviewpreparation.models.fetchData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    fetchData fetchData = new fetchData();
    @BindView(R.id.button)
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (isNetworkConnected()) {

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
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

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(HomeActivity.this,ques1.class);
        startActivity(intent);
    }
}
