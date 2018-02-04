package edu.asu.bsse.dtdicker.assign3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        testJSONFile(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean testJSONFile(Context context) {
        try {
            InputStream jsonFile = context.getAssets().open("places.json");
            byte[] buf = new byte[jsonFile.available()];
            jsonFile.read(buf);
            jsonFile.close();
            String jsonStr = new String(buf, "UTF-8");
            Log.d("TESTJSONFILE()", "testJSONFile: " + jsonStr);
            return true;
        } catch (IOException e) {
            Log.i("ERROR", "testJSONFile: unable to load file");
            e.printStackTrace();
            return false;
        }
    }
}
