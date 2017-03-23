package com.example.a0lambj41.networkcommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button addSongButton = (Button) findViewById(R.id.addSongButton);
        addSongButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText songTitleEditText = (EditText) findViewById(R.id.songTitleEditText);
        String songTitle = songTitleEditText.getText().toString();

        EditText artistEditText = (EditText) findViewById(R.id.artistEditText);
        String artist = songTitleEditText.getText().toString();

        EditText yearEditText = (EditText) findViewById(R.id.yearEditText);
        String year = songTitleEditText.getText().toString();

        (new AddSongAsyncTask()).execute(songTitle, artist, year);
    }

    class AddSongAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String postData = "song="+params[0] + "&artist=" + params[1] + "&year=" + params[2];

            try{
                URL urlObject = new URL("http://www.free-map.org.uk/course/mad/ws/addhit.php");
            }
            catch (IOException e) {
                return "error";
            }

            return null;
        }
    }
}

