package com.android.beacondetector;

import android.util.Log;

public class CallBack {
    private static com.foreign.Uno.Action _myCallbackFunction;  // 'MyCallbackFunction()'
	private static String[] info;  // Monitored information

    // Constructor
    // 'MyCallbackFunction()' in 'BeaconDetector_Android' class will be passed
    public CallBack(com.foreign.Uno.Action callback) { 
        _myCallbackFunction = callback;
    }

    // This method is triggered every detected enter/exit
    // Calls 'MyCallbackFunction()' 
    public static void callBackToUnoCheck(Course course, String enterOrExit) {
        if(enterOrExit == "enter") {
            String[] s = {"enter", course.courseName, course.start, course.end, 
                        course.getEnterTime()};
            info = s;
        }
        else {
            String[] s = {"exit", course.courseName, course.start, course.end, 
                     course.getExitTime()};
            info = s;
        }

        Log.d("CallBack", info[0] + " " + info[1] + " " + info[2] + " " +
             info[3] + " " + info[4]);
        
        _myCallbackFunction.run();
    }

    // Return the monitored information
    public static String[] getInfo() {
    	return info;
    }

}
