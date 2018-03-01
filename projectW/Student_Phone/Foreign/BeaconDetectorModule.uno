using Fuse;
using Fuse.Scripting;
using Fuse.Reactive;
using Uno.UX;
using Uno.Collections;

namespace ForeignBeaconDetector
{
    [UXGlobalModule]
    public class BeaconDetectorModule : NativeModule
    {
        static readonly BeaconDetectorModule _instance;
        readonly BeaconDetector _beaconDetector = new BeaconDetector();
        static NativeEvent _nativeEvent;      
        List<string> courses = new List<string>();

        public BeaconDetectorModule()
        {
            // Make sure we're only initializing the module once
            if (_instance != null) return;

            _instance = this;
            Resource.SetGlobalKey(_instance, "BeaconDetectorModule");
            _nativeEvent = new NativeEvent("checkAttendance");

            AddMember(new NativeFunction("start", (NativeCallback)Start));
            AddMember(new NativeFunction("pushCourse", (NativeCallback)PushCourse));           
            AddMember(_nativeEvent);
        }

        // Start activity
        object Start(Context c, object[] args)
        {
            if(courses == null)
            {
                debug_log("Error: no courses");
                return null;
            }
            
            debug_log("start in module");
            string[] courseList = courses.ToArray();
            _beaconDetector.Start(courseList);

            return null;
        }

        // Push a JavaScript object into the list 'courses'
        object PushCourse(Context c, object[] args)
        {
            debug_log("PushCourse called in Module");
            if(args.Length != 4) 
            {
                debug_log("Error: should be four arguments");
                return null;
            }

            // Array of constructor parameters
            string[] s = new string[4]; 
            int index = 0;

            // Copy arguments into the string array 's[]'
            foreach (var arg in args) 
                s[index++] = arg.ToString();

            // Add a course into the course list 'courses'
            // Each course information is separated by '#'
            courses.Add(s[0] + "#" + s[1] + "#" + s[2] + "#" + s[3]);
            debug_log(courses[0]);

            return null;
        }

        // Call JavaScript 'checkAttendance' function
        public void CallCheckAttendance(string info1, string info2, string info3, 
                                        string info4, string info5)
        {
            debug_log("Module");
            _nativeEvent.RaiseAsync(info1, info2, info3, info4, info5);
        }
    }
}