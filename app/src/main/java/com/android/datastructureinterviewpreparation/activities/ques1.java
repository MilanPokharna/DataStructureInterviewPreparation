package com.android.datastructureinterviewpreparation.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.datastructureinterviewpreparation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ques1 extends AppCompatActivity {

    @BindView(R.id.ques)
    TextView ques;
    @SuppressLint("StaticFieldLeak")
    public static TextView question;
    @BindView(R.id.ans)
    TextView ans;
    List<String> myqueslist = new ArrayList<>();
    public List<List<String>> queslist = new ArrayList<List<String>>();
    @SuppressLint("StaticFieldLeak")
    public static TextView answer;

    @BindView(R.id.prev)
    Button prev;
    @BindView(R.id.next)
    Button next;

    @BindView(R.id.re)
    RelativeLayout re;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques1);
        ButterKnife.bind(this);
        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        if (isNetworkConnected()) {

            mytask mytask = new mytask();
            mytask.execute();

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

    @OnClick(R.id.next)
    public void onViewClicked() {
        Intent i = new Intent(ques1.this,ques2.class);

    }

    private class mytask extends AsyncTask<Void, Void, List<List<String>>> {
        public String data = "";
        public List<List<String>> list = new ArrayList<>();
        public List<String> ques = new ArrayList<>();
        public List<String> ans = new ArrayList<>();
        public String string;

        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            try {
                URL url = new URL("https://learncodeonline.in/api/android/datastructure.json");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONObject object = new JSONObject(data);
                JSONArray jsonArray = object.getJSONArray("questions");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    string = (String) jsonObject.get("question");
                    ques.add(string);
                    string = (String) jsonObject.get("Answer");
                    ans.add(string);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list.add(ques);
            list.add(ans);
            return list;
        }

        @Override
        protected void onPostExecute(List<List<String>> strings) {
            super.onPostExecute(strings);
            mymethod(strings);
        }
    }

    public void mymethod(List<List<String>> result) {
        queslist = result;
        myqueslist = queslist.get(0);
        question.setText(myqueslist.get(0));
    }
}
