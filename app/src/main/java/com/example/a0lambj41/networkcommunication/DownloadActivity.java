package com.example.a0lambj41.networkcommunication;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button go = (Button)findViewById(R.id.go);
        go.setOnClickListener(this);
    }


    public void onClick(View view) {
        EditText urlEditText = (EditText) findViewById(R.id.urlEditText);
       String url = urlEditText.getText().toString();

        EditText artistEditText = (EditText) findViewById(R.id.artistEditText);
        String artist = artistEditText.getText().toString();

        new DownloadSongsAsyncTask().execute(url, artist);
    }

    class DownloadSongsAsyncTask extends AsyncTask<String,Void,String> {

        private static final int HTTP_OK = 200;

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String artist = params[1];

            HttpURLConnection connection;

            try {
                URL urlObject = new URL(url + "?artist=" + artist);
                connection = (HttpURLConnection) urlObject.openConnection();

                if(connection.getResponseCode() == HTTP_OK) {
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    String lines = "";
                    String line = br.readLine();
                    while(line != null){
                        lines += line;
                        line = br.readLine();
                    }

                    return lines;
                }
            }
            catch (IOException e) {
                return "Error: " + e.getMessage();
            }
            return "Error Something Went Wrong";
        }

        @Override
        protected void onPostExecute(String songs) {
            TextView tv1 = (TextView) findViewById(R.id.tv1);
            tv1.setText(songs);
        }
    }
}









