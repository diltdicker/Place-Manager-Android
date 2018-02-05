package edu.asu.bsse.dtdicker.assign3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
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

public class Edit_Activity extends AppCompatActivity {

    private Place_Description plDesc;
    private String name;
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent prev = getIntent();

        String json = prev.getExtras().getString("Place_Description");

        name = prev.getExtras().getString("name");
        plDesc = new Place_Description(json, name);
        updateActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_activity_bar, menu);
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
                return true;
            case R.id.action_edit:
                Log.d("EDIT", "onOptionsItemSelected: EDIT");
                return true;
            case R.id.action_done:
                Log.d("DONE", "onOptionsItemSelected: DONE");
                grabEdits();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("action", "edit");
                returnIntent.putExtra("name", name);
                returnIntent.putExtra("data", plDesc.toJSON());
                setResult(0, returnIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        grabEdits();
        Log.d("EDIT", plDesc.toJSON());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("action", "edit");
        returnIntent.putExtra("name", name);
        returnIntent.putExtra("data", plDesc.toJSON());
        setResult(0, returnIntent);
        finish();
    }

    public void updateActivity() {
        tView = new TextView(this);

        TextView mod = (TextView)findViewById(R.id.place);
        mod.setText(name);
        mod = (TextView)findViewById(R.id.add_title_edit);
        mod.setText(plDesc.getAddTitle());
        mod = (TextView)findViewById(R.id.add_street_edit);
        mod.setText(plDesc.getAddStreet());
        mod = (TextView)findViewById(R.id.elevation_edit);
        mod.setText(plDesc.getElevation());
        mod = (TextView)findViewById(R.id.latitude_edit);
        mod.setText(plDesc.getLatitude());
        mod = (TextView)findViewById(R.id.longitude_edit);
        mod.setText(plDesc.getLongitude());
        mod = (TextView)findViewById(R.id.name_edit);
        mod.setText(name);
        mod = (TextView)findViewById(R.id.image_edit);
        mod.setText(plDesc.getImage());
        mod = (TextView)findViewById(R.id.description_edit);
        mod.setText(plDesc.getDescription());
        mod = (TextView)findViewById(R.id.category_edit);
        mod.setText(plDesc.getCategory());
    }

    public void grabEdits() {
        TextView mod = (TextView)findViewById(R.id.place);

        mod = (TextView)findViewById(R.id.add_title_edit);
        Log.d("TEST", mod.getText().toString());
        plDesc.setAddTitle(mod.getText().toString());
        mod = (TextView)findViewById(R.id.add_street_edit);
        plDesc.setAddStreet(mod.getText().toString());
        mod = (TextView)findViewById(R.id.elevation_edit);
        plDesc.setElevation(mod.getText().toString());
        mod = (TextView)findViewById(R.id.latitude_edit);
        plDesc.setLatitude(mod.getText().toString());
        mod = (TextView)findViewById(R.id.longitude_edit);
        plDesc.setLongitude(mod.getText().toString());
        mod = (TextView)findViewById(R.id.name_edit);
        plDesc.setName(mod.getText().toString());
        mod = (TextView)findViewById(R.id.image_edit);
        plDesc.setImage(mod.getText().toString());
        mod = (TextView)findViewById(R.id.description_edit);
        plDesc.setDescription(mod.getText().toString());
        mod = (TextView)findViewById(R.id.category_edit);
        plDesc.setCategory(mod.getText().toString());
    }
}
