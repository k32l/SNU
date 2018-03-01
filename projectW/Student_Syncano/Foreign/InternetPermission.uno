using Uno;
using Uno.Permissions;

public class InternetPermission
{
    public static void GetInternet()
    {
        if defined(Android)
        {
            var permissionPromise = Permissions.Request(Permissions.Android.INTERNET);
            permissionPromise.Then(OnPermitted, OnRejected);
            var permissionNetworkPromise = Permissions.Request(Permissions.Android.ACCESS_NETWORK_STATE);
            permissionNetworkPromise.Then(OnPermitted, OnRejected);
        }
    }

    static void OnPermitted(PlatformPermission permission)
    {
        debug_log "Woo, we can use internet now";
    }

    static void OnRejected(Exception e)
    {
        debug_log "Damn: " + e.Message;
    }
}
