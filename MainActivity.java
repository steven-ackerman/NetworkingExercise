/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;


import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {
    //Class Variables and Constants.
    private TextView mWeatherTextView;
    private static final String DEFAULT_WEATHER_LOCATION = "97401";
    private static final String PREFERRED_LOCATION = String.valueOf(97401);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        //Done TODO (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.
        //Done TODO (3) Delete the for loop that populates the TextView with dummy data
        //Done: Method is not completed. TODO (9) Call loadWeatherData to perform the network request to get the weather
        loadWeatherData();
    }

    //Done TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
    public void loadWeatherData() {
        String preferredLocation = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherTask().execute(preferredLocation);
    }
        //Done TODO (5) Create a class that extends AsyncTas to perform network requests

        public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

            //Done TODO (6) Override the doInBackground method to perform your network requests
            @Override
            protected String[] doInBackground(String... params) {
                if (params.length == 0) {
                    return null;
                }
                String location = params[0];
                URL weatherRequestUrl = NetworkUtils.buildUrl(location);

                try {
                    String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
                    String[] simpleJsonWeatherData = OpenWeatherJsonUtils
                            .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
                    return simpleJsonWeatherData;

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

                return null;
            }
            //Completed: TODO (7) Override the onPostExecute method to display the results of the network request
            @Override
            protected void onPostExecute(String[] weatherData) {
//                if (networkRequests != null & !networkRequests.equals("")) {
//                    mWeatherTextView.setText(networkRequests);
                if (weatherData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                    for (String weatherString : weatherData) {
                        mWeatherTextView.append((weatherString) + "\n\n\n");
                    }

                }
            }


        }



}