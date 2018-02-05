package edu.asu.bsse.dtdicker.assign3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author Dillon Dickerson
 * Created by diltdicker on 2/4/18.
 */
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

public class View_Activity extends AppCompatActivity{

    private Place_Description plDesc;
    private String name;
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent prev = getIntent();

        String json = prev.getExtras().getString("Place_Description");

        name = prev.getExtras().getString("name");
        plDesc = new Place_Description(json, name);
        updateActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Log.d("ADD", "onOptionsItemSelected: ADD");
                return true;
            case R.id.action_remove:
                Log.d("RM", "onOptionsItemSelected: REMOVE");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("action", "remove");
                returnIntent.putExtra("name", plDesc.getPlace());
                returnIntent.putExtra("data", plDesc.toJSON());
                setResult(0, returnIntent);
                finish();
                return true;
            case R.id.action_edit:
                Log.d("EDIT", "onOptionsItemSelected: EDIT");
                Log.d("EDIT", plDesc.toJSON());
                Intent viewIntent = new Intent(View_Activity.this, Edit_Activity.class);
                viewIntent.putExtra("Place_Description", plDesc.toJSON());
                viewIntent.putExtra("name", name);
                startActivityForResult(viewIntent, 333);
                return true;
            case R.id.action_done:
                Log.d("DONE", "onOptionsItemSelected: DONE");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("Back", "onOptionsItemSelected: Go Back");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("action", "edit");
        returnIntent.putExtra("name", plDesc.getPlace());
        returnIntent.putExtra("data", plDesc.toJSON());
        setResult(0, returnIntent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Result", "onOptionsItemSelected: 333");
        if (requestCode == 333) {
            String act = data.getStringExtra("action");
            String dataName = data.getStringExtra("name");
            String dataDesc = data.getStringExtra("data");
            Log.d("UPDATE", dataDesc);
            if (act.equals("edit")) {
                plDesc = new Place_Description(dataDesc, dataName);
                updateActivity();
                Log.d("Result", "onOptionsItemSelected: DONE");
            }

        }
    }

    public void updateActivity() {
        tView = new TextView(this);

        TextView mod = (TextView)findViewById(R.id.place);
        mod.setText(name);
        mod = (TextView)findViewById(R.id.add_title_value);
        mod.setText(plDesc.getAddTitle());
        mod = (TextView)findViewById(R.id.add_street_value);
        mod.setText(plDesc.getAddStreet());
        mod = (TextView)findViewById(R.id.elevation_value);
        mod.setText(plDesc.getElevation());
        mod = (TextView)findViewById(R.id.latitude_value);
        mod.setText(plDesc.getLatitude());
        mod = (TextView)findViewById(R.id.longitude_value);
        mod.setText(plDesc.getLongitude());
        mod = (TextView)findViewById(R.id.name_value);
        mod.setText(name);
        mod = (TextView)findViewById(R.id.image_value);
        mod.setText(plDesc.getImage());
        mod = (TextView)findViewById(R.id.description_value);
        mod.setText(plDesc.getDescription());
        mod = (TextView)findViewById(R.id.category_value);
        mod.setText(plDesc.getCategory());
    }


}
