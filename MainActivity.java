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

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import java.net.URL;
import com.example.android.sunshine.utilities.NetworkUtils;




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
        LoadWeatherData();
    }

        // TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
        //MY COMMENTS: I created a final and set it to my own location. This doesn't seem to be what I need.
        //Seems like I am being asked to create a new Object the my NetworkRequests class that extends AsynchTask.
        //I am not sure what exactly my LoadWeatherData method is supposed to do. Please elaborate.

        public void LoadWeatherData(){
            String location = PREFERRED_LOCATION;


        }
    // TODO (5) Create a class that extends AsyncTask to perform network requests
    //This is my best guess. What exact network requests is this class supposed to perform?
    public class NetworkRequests extends AsyncTask<URL, Void, String>{

            // TODO (6) Override the doInBackground method to perform your network requests
            @Override
            protected String doInBackground(URL... params) {
                URL searchUrl = params[0];
                String networkRequests = null;
                try {
                    networkRequests = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return networkRequests;
            }
        // TODO (7) Override the onPostExecute method to display the results of the network request
        //I asuume we do not want our 'networkRequests' to be null or be a blank space.
        //I assume also that we are trying to set the text to display in the mWeatherTextView.
        //Is this correct. Please give me more instruction. Thank you.
        @Override
        protected void onPostExecute(String networkRequests) {
            if (networkRequests != null && !networkRequests.equals("")) {
                mWeatherTextView.setText(networkRequests);
            }
        }
    }


}