using Uno;
using Uno.Permissions;

public class Permission
{
    public static void GetBluetooth()
    {
        if defined(Android)
        {
            var permissionPromise = Permissions.Request(Permissions.Android.BLUETOOTH);
            permissionPromise.Then(OnPermitted, OnRejected);
            var permissionAdminPromise = Permissions.Request(Permissions.Android.BLUETOOTH_ADMIN);
            permissionAdminPromise.Then(OnPermitted, OnRejected);
        }
    }

    static void OnPermitted(PlatformPermission permission)
    {
        debug_log "Woo, we can use bluetooth now";
    }

    static void OnRejected(Exception e)
    {
        debug_log "Damn: " + e.Message;
    }

    public static void AccessLocation()
    {
        if defined(Android)
        {
            var permissionPromise = Permissions.Request(Permissions.Android.ACCESS_COARSE_LOCATION);
            permissionPromise.Then(OnPermittedLoc, OnRejectedLoc);
        }
    }

    static void OnPermittedLoc(PlatformPermission permission)
    {
        debug_log "Woo, we can access location now";
    }

    static void OnRejectedLoc(Exception e)
    {
        debug_log "Damn: " + e.Message;
    }
}
