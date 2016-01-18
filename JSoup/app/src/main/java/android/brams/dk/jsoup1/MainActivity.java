package android.brams.dk.jsoup1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "JSoup MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fetcher fetcher = new Fetcher();
        fetcher.execute();

    }

    private class Fetcher extends AsyncTask<Void, Void, Document> {
        protected Document doInBackground(Void... param) {
            Document myDoc=null;
            try {
                myDoc= Jsoup.connect("http://en.wikipedia.org/").get();
            } catch (IOException e) {
                Log.e(TAG, "onCreate: Something went wrong on connect", e);
                e.printStackTrace();
            }
            return myDoc;
        }

        protected void onProgressUpdate(Void progress) {
        }

        protected void onPostExecute(Document myDoc) {
            Log.i(TAG, "onPostExecute: Done...");
            Element contentDiv = myDoc.select("div[id=content]").first();
            TextView mText = (TextView)findViewById(R.id.text_field);
            mText.setText(contentDiv.toString());
        }
    }
}
