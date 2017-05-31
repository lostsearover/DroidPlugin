package com.morgoo.droidplugin.hook.handle;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.TextUtils;

import com.morgoo.droidplugin.hook.BaseHookHandle;
import com.morgoo.droidplugin.hook.HookedMethodHandler;
import com.morgoo.droidplugin.pm.PluginManager;

import java.lang.reflect.Method;

public class IAlarmManagerHookHandle extends BaseHookHandle {

    public IAlarmManagerHookHandle(Context hostContext) {
        super(hostContext);
    }

    @Override
    protected void init() {
        sHookedMethodHandlers.put("set", new set(mHostContext));
    }

    private static class set extends HookedMethodHandler {

        public set(Context context) {
            super(context);
        }

        @Override
        protected boolean beforeInvoke(Object receiver, Method method, Object[] args) throws Throwable {
            if (VERSION.SDK_INT > VERSION_CODES.M) {
                if (args != null && args.length > 0) {
                    for (int index = 0; index < args.length; index++) {
                        if (args[index] instanceof String) {
                            String callingPkg = (String) args[index];
                            if (!TextUtils.equals(callingPkg, mHostContext.getPackageName()) && PluginManager.getInstance().isPluginPackage(callingPkg)) {
                                args[index] = mHostContext.getPackageName();
                            }
                        }
                    }
                }

            }
            return super.beforeInvoke(receiver, method, args);
        }
    }
}
