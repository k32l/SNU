using Uno;
using Uno.Collections;
using Uno.Compiler.ExportTargetInterop;
using Uno.Collections;
using Fuse.Scripting;
using Fuse.Reactive;

namespace ForeignBeaconDetector
{
	[Require("Gradle.Dependency","compile('com.android.support:appcompat-v7:24.2.0')")]
	[Require("Gradle.Dependency","compile('org.altbeacon:android-beacon-library:2+')")]
	[Require("Gradle.Dependency","compile('io.syncano:library:4.1.0')")]
	[Require("AndroidManifest.ApplicationElement", "<activity android:name=\"com.android.beacondetector.BluetoothAlert\" />")]
	[Require("AndroidManifest.ApplicationElement", "<service android:name=\"com.android.beacondetector.BeaconDetectorService\" />")]	
	[ForeignInclude(Language.Java, "android.util.Log")]
	[ForeignInclude(Language.Java, "java.util.ArrayList")]
	[ForeignInclude(Language.Java, "android.content.Intent")]
	[ForeignInclude(Language.Java, "android.os.Bundle")]
	[ForeignInclude(Language.Java, "android.app.Activity")]
	extern(Android)
	internal class BeaconDetector_Android	
	{
		Java.Object _takingCareOfAReferenceToTheJavaClass;	// 'CallBack' class

		// Call 'CallCheckAttendance' method in 'BeaconDetectorModule.uno'
		// This method is triggered by 'callBackToUnoCheck' in 'CallBack.java' class 
	    public void MyCallbackFunction()
	    {
	        debug_log("We got a callback from Java");
			debug_log(GetInfo());
	     	
	     	string[] info = GetInfo().Split('#');

	        BeaconDetectorModule module = new BeaconDetectorModule();
     	 	module.CallCheckAttendance(info[0], info[1], info[2], info[3], info[4]);
	    }

		// Constructor
	    public BeaconDetector_Android() 
	    {
	        _takingCareOfAReferenceToTheJavaClass = CreateJavaClass(MyCallbackFunction);
	        debug_log("created CallBack object");
	    }

	    // Create 'CallBack' class in which run 'MyCallBackFunction' 
	    [Foreign(Language.Java)]
	    public static Java.Object CreateJavaClass(Action unoAction)
	    @{
	        com.android.beacondetector.CallBack myClass = 
	        			new com.android.beacondetector.CallBack(unoAction);

	        return myClass;
	    @}

	    // Get the monitoring information and change it into a string
	    // Each information is separated by '#'
		[Foreign(Language.Java)]
		public extern(Android) string GetInfo()
		@{
			String[] info = com.android.beacondetector.CallBack.getInfo();
			String infoString = info[0] + "#" + info[1] + "#" + info[2] + "#" + 
								info[3] + "#" + info[4];
			android.util.Log.d("BeaconDetector_Android", infoString);

			return infoString;
		@}

	    // Get the list of courses and start 'MainActivity'
		[Foreign(Language.Java)]
		public extern(Android) void Start(string[] courses)
		@{
			// Get argument and create java string array with it
			String[] stringArray = courses.copyArray();

			// Create ArrayList<Course> with 'stringArray'
			ArrayList<com.android.beacondetector.Course> courseList = 
				new ArrayList<com.android.beacondetector.Course>();

			for(String courseInfo: stringArray) {
				String[] infos = courseInfo.split("#");
				courseList.add(new com.android.beacondetector
					.Course(infos[0], infos[1], infos[2], infos[3]));
			}
			for(com.android.beacondetector.Course course: courseList) {
				android.util.Log.d("BeaconDetector_Android", course.courseName);
			}

			// Start activity passing the course list
			android.content.Intent intent = 
				new android.content.Intent(com.fuse.Activity.getRootActivity(), 
					com.android.beacondetector.BeaconDetectorService.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	android.os.Bundle bundle = new android.os.Bundle();
       	 	bundle.putParcelableArrayList("CourseList",courseList);
        	intent.putExtras(bundle);
        	com.fuse.Activity.getRootActivity().startService(intent);		
		@}
	}
}