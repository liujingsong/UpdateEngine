package com.bd.update.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/18 15:11
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/18 15:11
 * @ModifyDescription : <Content>
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getNewApk(String filename) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename;
    }

    public static String getPatch(String patchName){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + patchName;
    }

    public static File findOldApk(Context context) {
        String mRootPath = FileUtils.getExternalStorageDirectory();
        File oldApk = new File(mRootPath + File.separator + context.getPackageName() + ".apk");
        Log.e("TEST_UPDATE", "oldApk exists: "+oldApk.exists());
        return oldApk.exists() ? oldApk : backupApk(context, oldApk);
    }

    public static File backupApk(Context context, File destFile) {
        String oldPath = "";
        try {
            oldPath = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileUtil.copyFile(new File(oldPath), destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }

    public static String getFileMD5(File updateFile) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		InputStream is = null;
		try {
			is = new FileInputStream(updateFile);
		} catch (FileNotFoundException e) {
			return null;
		}
		byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			return md5SumToString(digest.digest());
		} catch (IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
			}
		}
	}
    
	private static String md5SumToString(byte[] md5Sum){
		 StringBuilder hexValue = new StringBuilder();
       for (int i = 0; i < md5Sum.length; i++) {
           int val = ((int) md5Sum[i]) & 0xff;
           if (val < 16){
               hexValue.append("0");
           }
           hexValue.append(Integer.toHexString(val));
       }
       return hexValue.toString();
	}

    /**
     * 通过 ‘？’ 和 ‘/’ 判断文件名
     */
    public static String getUrlFileName(String url) {
        int index = url.lastIndexOf('?');
        String filename;
        if (index > 1) {
            filename = url.substring(url.lastIndexOf('/') + 1, index);
        } else {
            filename = url.substring(url.lastIndexOf('/') + 1);
        }
        return filename;
    }
}
