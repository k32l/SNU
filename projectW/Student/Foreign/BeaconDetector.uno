using Uno;
using Uno.Reflection;
using Uno.Compiler.ExportTargetInterop;
using Uno.Collections;

namespace ForeignBeaconDetector
{
	public class BeaconDetector
	{
		extern(Android) BeaconDetector_Android _androidImpl;
	//	extern(iOS) BeaconDetector_iOS _iosImpl;

		public BeaconDetector()
		{
			if defined(Android)
			{
				_androidImpl = new BeaconDetector_Android();
			}

		//	else if defined(iOS)
		//	{
		//		_iosImpl = new BeaconDetector_iOS();
		//	}
		}

		extern(Android)
		public void Start(string[] courses) 
		{ 
			Permission.GetBluetooth();
			Permission.AccessLocation();

			_androidImpl.Start(courses); 
			debug_log("start android");
		}
	
	//	extern(iOS)
	//	public void Start() { _iosImpl.Start(); }		

		extern(!MOBILE)
		public void Start(string[] courses) 
		{
			debug_log("start on computer");
		}
	
	}
}