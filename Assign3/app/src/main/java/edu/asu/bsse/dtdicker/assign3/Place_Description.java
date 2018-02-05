package edu.asu.bsse.dtdicker.assign3;

import org.json.JSONObject;

/**
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

public class Place_Description {

    private String place;

    private String addTitle;
    private String addStreet;
    private String elevation;
    private String latitude;
    private String longitude;
    private String name;
    private String image;
    private String description;
    private String category;

    public Place_Description(String json, String place) {

        this.place = place;
        try {
            JSONObject jObj = new JSONObject(json);
            addTitle = jObj.getString("address-title");
            addStreet = jObj.getString("address-street");
            elevation = jObj.getString("elevation");
            latitude = jObj.getString("latitude");
            longitude = jObj.getString("longitude");
            name = jObj.getString("name");
            image = jObj.getString("image");
            description = jObj.getString("description");
            category = jObj.getString("category");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Place_Description(JSONObject jObj, String place) {
        this.place = place;
        try{
        addTitle = jObj.getString("address-title");
        addStreet = jObj.getString("address-street");
        elevation = jObj.getString("elevation");
        latitude = jObj.getString("latitude");
        longitude = jObj.getString("longitude");
        name = jObj.getString("name");
        image = jObj.getString("image");
        description = jObj.getString("description");
        category = jObj.getString("category");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public String getAddTitle() {
        return addTitle;
    }

    public String getAddStreet() {
        return addStreet;
    }

    public String getElevation() {
        return elevation;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setAddTitle(String var) {
        addTitle = var;
    }

    public void setAddStreet(String var) {
        addStreet = var;
    }

    public void setElevation(String var) {
        elevation = var;
    }

    public void setLatitude(String var) {
        latitude = var;
    }

    public void setLongitude(String var) {
        longitude = var;
    }

    public void setName(String var) {
        name = var;
    }

    public void setImage(String var) {
        image = var;
    }

    public void setDescription(String var) {
        description = var;
    }

    public void setCategory(String var) {
        category = var;
    }

    public String toJSON() {
        JSONObject plDesc = new JSONObject();

        try {

            plDesc.put("address-title", addTitle);
            plDesc.put("address-street", addStreet);
            plDesc.put("elevation", elevation);
            plDesc.put("latitude", latitude);
            plDesc.put("longitude", longitude);
            plDesc.put("name", name);
            plDesc.put("image", image);
            plDesc.put("description", description);
            plDesc.put("category", category);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plDesc.toString();
    }

    public String getPlace() {
        return this.place;
    }
}
