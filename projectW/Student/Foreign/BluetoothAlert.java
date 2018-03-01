package com.android.beacondetector;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;


public class BluetoothAlert extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Bluetooth not enabled");

        // set dialog message
        builder.setMessage("Please enable Bluetooth and restart this app.");
        builder.setPositiveButton("OK", null); 
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog){
                finish();
                System.exit(0);
            }
        });
        builder.show();
    }
}