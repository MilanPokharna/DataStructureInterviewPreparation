package com.android.datastructureinterviewpreparation.models;

import android.content.Intent;
import android.os.AsyncTask;

import com.android.datastructureinterviewpreparation.activities.HomeActivity;

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

public class fetchData extends AsyncTask<Void, Void, Void> {
    public String data = "";
    public static ArrayList<String> ques = new ArrayList<>();
    public static ArrayList<String> ans = new ArrayList<>();
    public String string;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://learncodeonline.in/api/android/datastructure.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null)
            {
                line = bufferedReader.readLine();
                data = data+line;
            }

            JSONObject object = new JSONObject(data);
            JSONArray jsonArray = object.getJSONArray("questions");
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                string = (String)jsonObject.get("question");
                ques.add(string);
                string = (String)jsonObject.get("Answer");
                ans.add(string);
            }

            }
            catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);

    }
}