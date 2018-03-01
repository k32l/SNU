package com.example.android.beacondetector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class MainActivity extends Activity implements BeaconConsumer {
    private static final String TAG = "MainActivity";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private final static int REQUEST_ENABLE_BT = 1;

    private BluetoothAdapter mBluetoothAdapter;
    private BeaconManager beaconManager;

    ArrayList<String> beaconList = new ArrayList<>();
    ArrayList<Course> courseList = new ArrayList<>();
    Region regionAll = new Region("AllBeacons", null, null, null);

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyBluetooth();
        accessLocation();

        Log.d(TAG, "app launched");
        logToDisplay("App launched");
        // initialize BeaconManager
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);

        // get today's course list and create Course objects for each one
        courseList.add(new Course("Operating System", "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/2", "11:00:00", "12:15:00"));
        courseList.add(new Course("Programming Principles", "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/1", "09:00:00", "10:45:00"));
        courseList.add(new Course("Database", "DF7E1C79-43E9-44FF-886F-1D1F7DA6997A/1/2", "21:00:00", "22:15:00"));
        courseList.add(new Course("Chinese", "f5ad1b58-a659-4976-b4b1-f8a0aac7fe3b/0/0", "17:00:00", "18:15:00"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
    }

    // start ranging
    public void startScanning(View view) {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Iterator<Beacon> beaconIterator = beacons.iterator();
                    while (beaconIterator.hasNext()) {
                        Beacon beacon = beaconIterator.next();
                        // Debug - logging a beacon - checking background logging is working.
                        System.out.println("Logging another beacon.");
                        logBeaconData(beacon);
                    }
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(regionAll);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // stop ranging
    public void stopScanning(View view) {
        try {
            beaconManager.stopRangingBeaconsInRegion(regionAll);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startRanging(Course course) {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Iterator<Beacon> beaconIterator = beacons.iterator();
                    while (beaconIterator.hasNext()) {
                        Beacon beacon = beaconIterator.next();
                        // Debug - logging a beacon - checking background logging is working.
                        System.out.println("Logging another beacon.");
                        beacon.getDistance();
                    }
                }
                else {

                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(course.region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // start monitoring
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {

                if(findMonitoredRegion(region) < 0) {
                    // the given region is not in the student's course list
                    return;
                }

                Course monitoredCourse = courseList.get(findMonitoredRegion(region));
                String enterTime = getCurrTime();

                // when enter occurs before the class ends
                if(timeDiff(monitoredCourse.end, enterTime) > 0) {
                    if (!monitoredCourse.isAlreadyEntered() &&
                        (timeDiff(enterTime, monitoredCourse.start) > 0 ||
                            timeDiff(monitoredCourse.start, enterTime) < 900)) { // first entering
                        monitoredCourse.setAlreadyEntered(true);
                        monitoredCourse.setEnterTime(enterTime);
                            // TODO: trigger attendance checking
                    } else { // next enters : 20분 넘게 나갔다 들어올 때
                        if (timeDiff(enterTime, monitoredCourse.getExitTime()) > 1200) {
                            monitoredCourse.setEnterTime(enterTime);
                            // TODO: retrigger attendance checking, adjust time
                        }
                            // 잠깐 나갔다 들어오는 것은 무시
                    }
                }

                // debug logs
                Log.d(TAG, "I just saw a beacon for the first time!");
                logToDisplay("entered" + monitoredCourse.courseName + " at " + monitoredCourse.getEnterTime());
                beaconList.add(region.getUniqueId());
                logToDisplay(beaconList.toString() + "\n");

            }

            @Override
            public void didExitRegion(Region region) {
                if(findMonitoredRegion(region) < 0) {
                    return;
                }

                Course monitoredCourse = courseList.get(findMonitoredRegion(region));
                String exitTime = getCurrTime();

                // 현재 시간에 해당하는 course만을 대상으로 나간 시간을 저장한다.
                // 수업의 시작 시간부터 끝난 후 15분까지의 exit만 처리
                if(timeDiff(monitoredCourse.end + 900, exitTime) > 0 &&
                        timeDiff(exitTime, monitoredCourse.start) > 0) {
                    monitoredCourse.setExitTime(exitTime);
                    // TODO : trigger attendance checking
                }
                // debug logs
                Log.d(TAG, "I no longer see a beacon");
                logToDisplay("exited from " + monitoredCourse.courseName + " at " + monitoredCourse.getExitTime());
                beaconList.remove(region.getUniqueId());
                logToDisplay(beaconList.toString() + "\n");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.d(TAG, "I have just switched from seeing/not seeing beacons: " + state);
            }
        });

        try {
            // start monitoring for each course which a student takes
            for(Course course : courseList) {
                beaconManager.startMonitoringBeaconsInRegion(course.region);
            }
        } catch (RemoteException e) {
        }
    }

    // find the course whose region has the same identifier with 'region' and return the index of it
    public int findMonitoredRegion(Region region) {
        for(Course course : courseList) {
            if(course.region.hasSameIdentifiers(region)) {
                return courseList.indexOf(course);
            }
        }
        return -1;
    }

    // get current time in the form of hour:minute:second
    public String getCurrTime() {
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        String time = s.format(new Date());

        return time;
    }

    // get the difference between two times
    public long timeDiff(String time1, String time2) {
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        Date timeFirst = new Date();
        Date timeSecond = new Date();
        try {
            timeFirst = s.parse(time1);
            timeSecond = s.parse(time2);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return (timeFirst.getTime() - timeSecond.getTime()) / 1000;
    }

    // check bluetooth is on
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void verifyBluetooth() {
        try {
            // Use this check to determine whether BLE is supported on the device. Then
            // you can selectively disable BLE-related features.
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                Toast.makeText(this, "BLE not supported", Toast.LENGTH_SHORT).show();
                finish();
            }

            // Initializes Bluetooth adapter.
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBluetoothAdapter = bluetoothManager.getAdapter();

            // Ensures Bluetooth is available on the device and it is enabled. If not,
            // displays a dialog requesting user permission to enable Bluetooth.
            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        catch(RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }
            });
            builder.show();
        }
    }

    // access location permission
    public void accessLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSION_REQUEST_COARSE_LOCATION);
                    }

                });
                builder.show();
            }
        }
    }

    // location permission result
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }

    // show on mobile display
    public void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                TextView editText = (TextView) MainActivity.this
                        .findViewById(R.id.monitoringText);
                editText.append(line + "\n");
            }
        });
    }

    private void logBeaconData(Beacon beacon) {
        logToDisplay("The beacon " + beacon.toString() + " is about " + beacon.getDistance() + " meters away.");
    }

}