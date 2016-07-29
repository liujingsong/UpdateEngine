package com.bd.update;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.bd.update.callback.ProgressFileCallback;
import com.bd.update.patch.Patch;
import com.bd.update.utils.FileUtils;
import com.bd.update.utils.StringUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.utils.Platform;

import java.io.File;
import java.io.IOException;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/18 14:20
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/18 14:20
 * @ModifyDescription : <Content>
 */
public class UpdateManager {

    private Context mContext;

    public UpdateManager(Context context) {
        this.mContext = context;
    }


    public Update parseUpdate(String jsonBody) {
    	Update update = new Gson().fromJson(jsonBody, Update.class);
        return update;
    }

    public void update(String jsonBody) {
        update(parseUpdate(jsonBody));
    }


    /**
     * 1、根据接口数据判断升级类型
     * 2、选择升级方式
     */
    public void update(final Update update) {
        if (update.getHas_new() == Update.NONE) {
            /*无更新*/
            Log.e("TEST_UPDATE","  无更新");
            return;
        } else if (update.getHas_new() == Update.EXIST) {
                /*存在更新包*/
            Log.e("TEST_UPDATE","  存在更新包");
            if (update.is_force_update == Update.FORCE) {
                Log.e("TEST_UPDATE","  强制更新");
                /*强制更新*/
                autoUpdate(update);
            } else if (update.is_force_update == Update.NONE_FORCE) {
                Log.e("TEST_UPDATE"," 非强制更新");
                /*非强制更新*/
            	showUpdateGuideDialog(update);
            }
        }
    }


	private void autoUpdate(final Update update) {

		if (update.update_method == Update.FULL) {
            Log.e("TEST_UPDATE"," 全量更新");
		    /*全量更新*/
		    fullUpdate(update);
		} else if (update.update_method == Update.DELTA) {
            Log.e("TEST_UPDATE"," 增量更新");
		    /*增量更新*/
		    deltaUpdate(update);
		} else if (update.update_method == Update.HOT_FIX) {
            Log.e("TEST_UPDATE"," 热更新");
		    /*热更新*/
		}
	}
    
    public void showUpdateGuideDialog(final Update update){
    	Builder builder = new AlertDialog.Builder(mContext);
    	builder.setTitle("有更新");
    	builder.setMessage(update.getChange_log());
    	builder.setNegativeButton("暂不更新", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//TODO 
			}
		});
    	builder.setPositiveButton("立即更新", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				autoUpdate(update);
			}
		});
    	builder.show();
    	
    }

    /**
     * 增量更新
     */
    public void deltaUpdate(final Update update) {
        /*查找或备份旧的安装包到sd*/
        final File originOldApk = FileUtils.findOldApk(mContext);
        /*旧的安装包安全校验*/
        Log.e("TEST_UPDATE", "oldMd5: "+isValid(update.getOld_md5(), FileUtils.getFileMD5(originOldApk)));
        if (isValid(update.getOld_md5(), FileUtils.getFileMD5(originOldApk))) {
            downloadPatch(update, originOldApk.getAbsolutePath());
        } else {
            fullUpdate(update);
        }
    }

    public void startHotFix(String path) {

            //UEApplication.mContext.mPatchManager.addPatch(path);
    }

    public void downloadPatch(final Update update, final String originOldApkPath) {
           /*从url上截取patch文件名*/
        final String patchName = FileUtils.getUrlFileName(update.getDiff_url());
            /*下载patch文件，这里的Callback可以自定义*/
        downloadFile(update.getDiff_url(), new ProgressFileCallback(mContext, FileUtils.getExternalStorageDirectory(), patchName) {
            @Override
            public void onResponse(File response) {
                if (isValid(update.getDiff_md5(), FileUtils.getFileMD5(response))) {
                    /*校验通过后，重新打包，安装*/
                    patcher(update, originOldApkPath, FileUtils.getNewApk(getNewApkName(update)), FileUtils.getPatch(patchName));
                } else {
                    //TODO
                    return;
                }
            }
        });


    }

    /**
     * 全量更新
     */
    public void fullUpdate(final Update update) {
        downloadFile(update.getFull_url(), new ProgressFileCallback(mContext, FileUtils.getExternalStorageDirectory(), getNewApkName(update)) {
            @Override
            public void onResponse(File response) {
                /*文件安全校验*/
                if (isValid(update.getFull_md5(), FileUtils.getFileMD5(response))){
                    /*安装*/
                    installApk(mContext, response.getAbsolutePath());
                }else{
                	Toast.makeText(mContext, "下载文件MD5不匹配！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getNewApkName(Update update) {
        return update.getPackage_name() + "_" + update.getNew_version_name() + ".apk";
    }

    private void downloadFile(String url, FileCallBack callback) {
        OkHttpUtils.get().id(0x02).url(url).build().execute(callback);
    }

    /**
     * 打包新的apk
     *
     * @param update
     * @param oldApk
     * @param newApk
     * @param patch
     */
    private void patcher(final Update update, final String oldApk, final String newApk, final String patch) {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            private ProgressDialog progressDialog;

            @Override
            protected void onCancelled() {
                progressDialog = null;
                super.onCancelled();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                /*测试用*/
                progressDialog = ProgressDialog.show(mContext,
                        mContext.getString(R.string.update_patch), mContext.getString(R.string.update_wait), true, false);
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... arg0) {

                File file = new File(newApk);
                if (file.exists())
                    file.delete();/*如果newApk文件已经存在,先删除*/

                /* 调用.so库中的方法,把增量包和老的apk包合并成新的apk*/
                Patch.patcher(oldApk, newApk, patch);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                 /*测试用*/
                progressDialog.dismiss();
                if (isValid(update.getFull_md5(), FileUtils.getFileMD5(new File(newApk)))) {
                    Toast.makeText(mContext, R.string.patch_done_install, Toast.LENGTH_SHORT).show();
                    installApk(mContext, newApk);
                }
            }
        };
        task.execute();
    }

    public boolean isValid(String platformMD5, String localMD5) {
        return StringUtils.isEquals(platformMD5, localMD5);
    }

    /**
     * 安装指定地址(filePath)的apk
     */
    private void installApk(final Context context, final String filePath) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
                context.startActivity(i);
            }
        },200l);

    }


}
