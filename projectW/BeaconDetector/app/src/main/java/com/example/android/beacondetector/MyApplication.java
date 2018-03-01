package com.example.android.beacondetector;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by 내 문서 on 2016-11-27.
 */
public class MyApplication extends Application implements BootstrapNotifier {
    private static final String TAG = "MyApplication";
    private BeaconManager beaconManager;
    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;

    Region regionAll = new Region("AllBeacons", null, null, null);

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "app launched");

        // initialize BeaconManager
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        Region region = new Region("my-beacon-region", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);

        backgroundPowerSaver = new BackgroundPowerSaver(this);
    }

    @Override
    public void didEnterRegion(Region region) {
        Log.d(TAG, "Got a didEnterRegion call");

        // This call to disable will make it so the activity below only gets launched
        // the first time a beacon is seen (until the next time the app is launched)
        // if you want the Activity to launch every single time beacons come into view, remove this call.
        regionBootstrap.disable();
        Intent intent = new Intent(this, MainActivity.class);

        // IMPORTANT: in the AndroidManifest.xml definition of this activity,
        // you must set android:launchMode="singleInstance" or you will get two instances
        // created when a user launches the activity manually and it gets launched from here.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    @Override
    public void didExitRegion(Region region) {
        // Ignore
    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {
        // Ignore
    }

}
