package com.mc.iiitd_mess;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

public class HomeUserFragment extends Fragment {
    Button buyBtn;
    LinearLayout couponLayout;
    static Boolean hasBought = false;
    TextView textView4, textView5;
    static int s = 50;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        Toast.makeText(view.getContext(), "User Home", Toast.LENGTH_SHORT).show();
        textView4 = (TextView) view.findViewById(R.id.textView4);
        textView5 = (TextView) view.findViewById(R.id.textView5);
        if (hasBought) {
            buyBtn = (Button) view.findViewById(R.id.buyBtn);
            couponLayout = (LinearLayout) view.findViewById(R.id.couponsLayout);
            buyBtn.setVisibility(View.INVISIBLE);
            couponLayout.setVisibility(View.VISIBLE);
        } else {
            buyBtn = (Button) view.findViewById(R.id.buyBtn);
            View finalView = view;
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    couponLayout = (LinearLayout) finalView.findViewById(R.id.couponsLayout);
                    couponLayout.setVisibility(View.VISIBLE);
                    buyBtn.setVisibility(View.INVISIBLE);
                    hasBought = true;
                }
            });
        }
        handleWifi();
        return view;
    }

    public void handleWifi() {
        WifiManager wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        Log.i("hiii", "hiiii");
        for (ScanResult scanResult : scanResults) {
            String ssid = scanResult.SSID;
            String bssid = scanResult.BSSID; // this is the MAC address
            int level = scanResult.level;
            if (bssid == "2A:56:0F:28:69:3B") {
                Toast.makeText(getContext(), "Seating Registered", Toast.LENGTH_SHORT).show();
            }
            textView4.setText("Seating Left on 1st \n "+s);
            s--;
            Log.d("smoll", "mac: " + bssid);
            // do something with the MAC address
        }
    }
}