package com.mc.iiitd_mess;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class HomeAdminFragment extends Fragment {
    private static String TAG = "HomeAdminFragment";
    int CAMERA_REQUEST_CODE;
    Button scanBtn;
    RequestQueue queue;
    String url = "http://52.66.209.45/";
    EditText rollNo, days, updateRollNo;
    Button registerBtn, searchBtn;
    public static int checkSelfPermission(CameraActivity cameraActivity, String camera) {
        return 0;
    }

    public static void requestPermissions(CameraActivity cameraActivity, String[] strings, int requestCameraPermission) {
//        return 0;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        Toast.makeText(view.getContext(), "Admin Home", Toast.LENGTH_SHORT).show();
        scanBtn = (Button) view.findViewById(R.id.btnScanBarcode);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CameraActivity.class));
            }
        });
        rollNo = (EditText) view.findViewById(R.id.rollNo);
        days = (EditText) view.findViewById(R.id.days);
        registerBtn = (Button) view.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll = rollNo.getText().toString();
                String d = days.getText().toString();
                StringRequest request = new StringRequest(Request.Method.GET, url + "register?days=" + d+"&rollNo="+roll, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.i(TAG, response.toString());
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                });
                queue.add(request);
            }
        });
//        searchBtn = (Button) view.findViewById(R.id.searchBtn);
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Do something with the image captured by the camera
        }
    }
}