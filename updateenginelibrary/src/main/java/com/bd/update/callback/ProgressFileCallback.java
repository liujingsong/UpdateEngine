package com.bd.update.callback;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bd.update.R;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.utils.Platform;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/18 16:47
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/18 16:47
 * @ModifyDescription : <Content>
 */
public abstract class ProgressFileCallback extends FileCallBack {

    ProgressDialog mPd;
    Context mContext;

    public ProgressFileCallback(Context context, String destFileDir, String destFileName) {
        this(context, defaultProgressDialog(context), destFileDir, destFileName);
    }

    public ProgressFileCallback(Context context, ProgressDialog pd, String destFileDir, String destFileName) {
        super(destFileDir, destFileName);
        this.mContext = context;
        this.mPd = pd;
    }

    public static ProgressDialog defaultProgressDialog(Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle(R.string.update_pd_title);
        pd.setMessage(context.getString(R.string.update_pd_message));
        pd.setCancelable(false);
        return pd;
    }

    @Override
    public File parseNetworkResponse(Response response, int id) throws Exception {
        Platform.get().execute(new Runnable() {
            @Override
            public void run() {
                mPd.show();
            }
        });

        return super.parseNetworkResponse(response, id);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        mPd.dismiss();
        onDestroy();
    }

    @Override
    public void onResponse(File response, int id) {
        mPd.dismiss();
        onResponse(response);
        onDestroy();
    }


    @Override
    public void inProgress(float progress, long total, int id) {
        mPd.setMax((int) total);
        mPd.setProgress((int) (progress * total));
    }

    public void onDestroy() {
        mPd = null;
        mContext = null;
    }

    public abstract void onResponse(File response);
}
