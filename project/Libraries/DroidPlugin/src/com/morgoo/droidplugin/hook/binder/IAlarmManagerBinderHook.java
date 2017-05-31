package com.morgoo.droidplugin.hook.binder;

import android.content.Context;
import android.os.IBinder;

import com.morgoo.droidplugin.hook.BaseHookHandle;
import com.morgoo.droidplugin.hook.handle.IAlarmManagerHookHandle;
import com.morgoo.helper.compat.IAlarmManagerCompat;

/**
 * Created by jc on 17-5-23.
 */

public class IAlarmManagerBinderHook extends BinderHook {

    private final static String SERVICE_NAME = Context.ALARM_SERVICE;

    public IAlarmManagerBinderHook(Context hostContext) {
        super(hostContext);
    }

    @Override
    protected BaseHookHandle createHookHandle() {
        return new IAlarmManagerHookHandle(mHostContext);
    }

    @Override
    Object getOldObj() throws Exception {
        IBinder iBinder = MyServiceManager.getOriginService(SERVICE_NAME);
        return IAlarmManagerCompat.asInterface(iBinder);
    }

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }
}
