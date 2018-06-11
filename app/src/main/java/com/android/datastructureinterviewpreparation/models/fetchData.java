package com.android.datastructureinterviewpreparation.models;

import android.os.AsyncTask;
import android.widget.Toast;

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

public class fetchData extends AsyncTask<Void, Void, Void> {
    public String data = "";
    public String single = "";
    public String parsed = "";


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
                single = "Question : " +jsonObject.get("question")+"\n"+
                         "Answer : "+jsonObject.get("Answer")+"\n";
                parsed = parsed+single+"\n";
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        HomeActivity.text.setText(this.parsed);
    }
}