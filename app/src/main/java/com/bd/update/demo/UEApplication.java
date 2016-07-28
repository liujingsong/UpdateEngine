package com.bd.update.demo;

import android.app.Application;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/13 14:26
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/13 14:26
 * @ModifyDescription : <Content>
 */
public class UEApplication extends Application{

    public static UEApplication mContext;
    public PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mPatchManager = new PatchManager(this);
        try {
            mPatchManager.init(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);//current version
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mPatchManager.loadPatch();
    }
}
