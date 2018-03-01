using Fuse;
using Fuse.Scripting;
using Fuse.Reactive;
using Uno.UX;

[UXGlobalModule]
public class PermissionModule : NativeModule
{
    static readonly PermissionModule _instance;


    public PermissionModule()
    {
        // Make sure we're only initializing the module once
        if (_instance != null) return;

        _instance = this;
        Resource.SetGlobalKey(_instance, "PermissionModule");
       
        AddMember(new NativeFunction("getInternet", (NativeCallback)GetInternet));
    }

    object GetInternet(Context c, object[] args)
    {
        InternetPermission.GetInternet();
        return null;
    }
}