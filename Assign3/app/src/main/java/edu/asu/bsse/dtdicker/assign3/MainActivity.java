package edu.asu.bsse.dtdicker.assign3;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;

//  Created by diltdicker on 2/4/18.
//  Copyright © 2018 Dillon Dickerson. All rights reserved.
//
//---------------------------------------------------------------------------
//    Copyright (C) 2018 Dillon Dickerson
//	  email: diltdicker@gmail.com
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
//---------------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    private Recycle_Adapt plData;
    private int val = 3;
    private RecyclerView recycleView;
    private RecyclerView.Adapter recycleViewAdapter;
    private RecyclerView.LayoutManager recycleViewLayout;
    private JSONObject jsonObj;
    private JSONArray jsonArr;
    private ArrayList<String> places;
    private ArrayList<Place_Description> plDescList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        places = new ArrayList<String>();
        plDescList = new ArrayList<Place_Description>();
        loadJSON(this);

        recycleView = (RecyclerView) findViewById(R.id.main_recycle);
        recycleView.setHasFixedSize(false);
        recycleViewLayout = new LinearLayoutManager((this));
        recycleView.setLayoutManager(recycleViewLayout);

        plData = new Recycle_Adapt(places);
        recycleView.setAdapter(plData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Log.d("ADD", "onOptionsItemSelected: ADD");
                Intent viewIntent = new Intent(MainActivity.this, Add_Activity.class);
                this.startActivity(viewIntent);
                return true;
            case R.id.action_remove:
                Log.d("RM", "onOptionsItemSelected: REMOVE");
                return true;
            case R.id.action_edit:
                Log.d("EDIT", "onOptionsItemSelected: EDIT");
                return true;
            case R.id.action_done:
                Log.d("DONE", "onOptionsItemSelected: DONE");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean testJSONFile(Context context) {
        try {
            InputStream jsonFile = context.getAssets().open("places.json");
            byte[] buf = new byte[jsonFile.available()];
            jsonFile.read(buf);
            jsonFile.close();
            String jsonStr = new String(buf, "UTF-8");
            jsonObj = new JSONObject(jsonStr);
            Log.d("TESTJSONFILE()", "testJSONFile: " + jsonStr);
            for (int i = 0; i < jsonObj.length(); i++) {
                Log.d("List", "item:" + jsonObj.names().getString(i));
            }
            return true;
        } catch (Exception e) {
            Log.i("ERROR", "testJSONFile: unable to load file");
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadJSON(Context context) {
        try {
            InputStream jsonFile = context.getAssets().open("places.json");
            byte[] buf = new byte[jsonFile.available()];
            jsonFile.read(buf);
            jsonFile.close();
            String jsonStr = new String(buf, "UTF-8");
            jsonObj = new JSONObject(jsonStr);
            for (int i = 0; i < jsonObj.length(); i++) {
                Log.d("List", "item:" + jsonObj.names().getString(i));
                places.add(jsonObj.names().getString(i));
                jsonToObj(jsonObj.getJSONObject(jsonObj.names().getString(i)).toString(), jsonObj.names().getString(i));
            }
            Log.d("TESTJSONFILE()", "testJSONFile: " + jsonStr);
            return true;
        } catch (Exception e) {
            Log.i("ERROR", "testJSONFile: unable to load file");
            e.printStackTrace();
            return false;
        }
    }

    private boolean jsonToObj(JSONObject json, String place) {
        Place_Description pD = new Place_Description(json, place);

        plDescList.add(pD);

        return true;

    }

    private boolean jsonToObj(String json, String place) {
        Place_Description pD = new Place_Description(json, place);

        plDescList.add(pD);

        return true;

    }

    public  boolean testRecycleviewer(Context centext) {

        return  true;
    }

    public void onTap(View view) {
        TextView t = (TextView) view;
        Log.d("tap click", "onTap: list size: " + plDescList.size());
        Log.d("tap click", "onTap: Tapped: " + t.getText());
        Intent viewIntent = new Intent(MainActivity.this, View_Activity.class);
        viewIntent.putExtra("Place_Description", plDescList.get(places.indexOf(t.getText())).toJSON());
        Log.d("tap click", "onTap: Tapped: " + plDescList.get(places.indexOf(t.getText())).toJSON());
        viewIntent.putExtra("name", t.getText());

        this.startActivity(viewIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ret", "onActivityResult: back");
        Log.d("ret", "onActivityResult: " + requestCode);
        switch(requestCode) {
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        Log.d("ret", "onActivityResult: back");
        Log.d("ret", "onActivityResult: " + requestCode);
        /*String act = intent.getStringExtra("action");
        String name = intent.getStringExtra("name");
        String plDesc = intent.getStringExtra("data");
        if (act.equals("remove")){
            int i = places.indexOf(name);
            places.remove(i);
            plDescList.remove(i);

            plData.removeItem(i);
        }*/
    }
}
