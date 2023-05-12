package com.mc.iiitd_mess;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuAdminFragment extends Fragment {
    private static String TAG = "MenuAdminFragment";
    TextView breakfastText, lunchText, snacksText, dinnerText;
    RequestQueue queue;
    String url = "http://52.66.209.45/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_user, container, false);
        breakfastText = (TextView) view.findViewById(R.id.breakfastText);
        lunchText = (TextView) view.findViewById(R.id.lunchText);
        snacksText = (TextView) view.findViewById(R.id.snacksText);
        dinnerText = (TextView) view.findViewById(R.id.dinnerText);
        queue = Volley.newRequestQueue(view.getContext());
        Spinner daySpinner = view.findViewById(R.id.day_spinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.days_of_week, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String day = adapterView.getItemAtPosition(i).toString();
                StringRequest request = new StringRequest(Request.Method.GET, url + "menu?day=" + day, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String breakfast = jsonObject.getString("breakfast");
                            String lunch = jsonObject.getString("lunch");
                            String snacks = jsonObject.getString("snacks");
                            String dinner = jsonObject.getString("dinner");
                            breakfastText.setText(breakfast);
                            lunchText.setText(lunch);
                            snacksText.setText(snacks);
                            dinnerText.setText(dinner);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                });
                queue.add(request);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
}