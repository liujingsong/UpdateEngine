package com.bd.update.patch;

import com.bd.update.utils.StringUtils;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/12 15:15
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/12 15:15
 * @ModifyDescription : <Content>
 */
public class Patch {

    static {
        System.loadLibrary("Patcher");
    }



    /**
     * 调用.so库中的方法,合并apk
     *
     * @param oldApk 旧Apk地址
     * @param newApk 新apk地址(名字)
     * @param patch  增量包地址
     */
    public static native void patcher(String oldApk, String newApk, String patch);

}
