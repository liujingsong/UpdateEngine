package com.bd.update.demo;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bd.update.Update;
import com.bd.update.UpdateManager;
import com.bd.update.utils.FileUtils;
import com.google.gson.Gson;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
    }

    /**
     * 模拟后台接口数据
     * 需要你的接口返回相应json
     */
    public Update fillTestData() {
        Update update = new Update();
        update.setChange_log("修改更新方式为：差分升级！"); /*替换成后台返回*/

        update.setHas_new(Update.EXIST);/*存在新版本，替换成后台返回*/
        update.setIs_force_update(Update.NONE_FORCE);/*非强制更新，替换成后台返回*/
        update.setUpdate_method(Update.FULL);/*全量更新，替换成后台返回*/

        update.setFull_md5("fa2f45203e83a272a7199881b09f8e50");  /*替换成后台返回*/
        update.setFull_url("http://liujingsong.tunnel.qydev.com/com.bd.update.demo.apk"); /*替换成后台返回*/
        update.setOld_md5(FileUtils.getFileMD5(FileUtils.findOldApk(this)));

        update.setChannel_code(5);/*替换成后台返回*/
        update.setClient_id("103");/*替换成后台返回*/


        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            update.setNew_version_code(info.versionCode); /*替换成后台返回*/
            update.setNew_version_name(info.versionName); /*替换成后台返回*/
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        update.setPackage_name("com.bd.update.demo"); /*替换成后台返回*/
        update.setRelease_time("2016-07-27"); /*替换成后台返回*/

        Log.e("TEST_UPDATE",new Gson().toJson(update));

        return update;
    }


    public void update(View v) {
        new UpdateManager(this).update(fillTestData());

    }

}
