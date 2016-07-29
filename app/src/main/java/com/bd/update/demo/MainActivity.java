package com.bd.update.demo;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bd.update.Update;
import com.bd.update.UpdateManager;
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
        showProgress();
        Update update = new Update();
        update.setChange_log("修改更新方式为：差分升级！"); /*替换成后台返回*/

        update.setHas_new(Update.EXIST);/*存在新版本，替换成后台返回*/
        update.setIs_force_update(Update.NONE_FORCE);/*非强制更新，替换成后台返回*/
        update.setUpdate_method(Update.DELTA);/*全量更新，替换成后台返回*/

        update.setDiff_md5("c3d491a431e88cb2efde056b71c6696f");
        update.setDiff_url("http://liujingsong.tunnel.qydev.com/video_1.0.0-1.0.1.patch");
        update.setFull_md5("0995727a6fcf7cab9df0be7a61da6b7d");  /*替换成后台返回*/
        update.setFull_url("http://liujingsong.tunnel.qydev.com/video_10001_1.0.1_5.apk"); /*替换成后台返回*/
        update.setOld_md5("f5487194a3c7e84b6d86acfdb2d23736");

        update.setChannel_code(5);/*替换成后台返回*/
        update.setClient_id("103");/*替换成后台返回*/


        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            update.setNew_version_code(info.versionCode); /*替换成后台返回*/
            update.setNew_version_name(info.versionName); /*替换成后台返回*/
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        update.setPackage_name("com.danale.video"); /*替换成后台返回*/
        update.setRelease_time("2016-07-29"); /*替换成后台返回*/

        Log.e("TEST_UPDATE", new Gson().toJson(update));

        return update;
    }


    public void update(View v) {
        new UpdateManager(this).update(fillTestData());
        hideProgress();
    }


    protected ViewGroup mCover;

    public ViewGroup getCover() {
        return this.mCover;
    }

    public void setCover(ViewGroup mCover) {
        this.mCover = mCover;
    }

    public ViewGroup showProgress(ProgressBar pb) {
        if (null == getCover()) {
            LinearLayout mCover = new LinearLayout(this);
            mCover.setGravity(Gravity.CENTER);
            mCover.addView(pb);
            addContentView(mCover, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT));

            setCover(mCover);
        }
        getCover().setVisibility(View.VISIBLE);
        return getCover();
    }

    public ViewGroup showProgress() {

        if (null == getCover()) {
            LinearLayout mCover = new LinearLayout(this);
            mCover.setGravity(Gravity.CENTER);
            ProgressBar pb = new ProgressBar(this);
            mCover.addView(pb);
            addContentView(mCover, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT));
            setCover(mCover);
        }
        getCover().setVisibility(View.VISIBLE);
        getCover().setClickable(true);
        return getCover();
    }

    public void hideProgress() {
        if (null == mCover)
            return;

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCover.getVisibility() == View.VISIBLE) {
                    mCover.setVisibility(View.GONE);
                }
            }
        }, 200l);


    }


}
