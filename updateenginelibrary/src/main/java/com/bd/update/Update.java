package com.bd.update;

import com.bd.update.patch.Patch;

import java.util.Collections;
import java.util.List;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/27 10:06
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/27 10:06
 * @ModifyDescription : <Content>
 */
public class Update {
    public static final int NONE = 0x00;
    public static final int EXIST = 0x01;
    int has_new;/*是否存在更新 0 不存在,　１ 存在*/
    String new_version_name;/*新版ap版本名(如5.2.6)*/
    int new_version_code;/*新版apk版本号*/
    String client_id;/*该系列app的标识符*/
    int client_type;/*该系列app所属平台类型*/
    int channel_code;/*发布该app的渠道代码*/
    public static final int NONE_FORCE = 0x00;
    public static final int FORCE = 0x01;
    int is_force_update; /*0:非强制更新　1:强制更新*/
    public static final int FULL = 0x00;
    public static final int DELTA = 0x01;
    public static final int HOT_FIX = 0x02;
    int update_method;/*0:完整更新　1:增量更新 2:热更新*/
    String release_time;/*新版app发布时间*/
    String old_md5;/*老版本app的md5值*/
    int file_size;/*全量安装包大小*/
    String full_url;/*全量安装包下载地址*/
    String full_md5;/*全量安装包md5值*/
    String diff_url;/*差分安装包下载地址*/
    String diff_md5;/*差分安装包md5值*/
    String package_name;/*新版app包名*/
    String change_log;/*新版特性*/

    List<Patch> patchs; /*array 需 热更新 的补丁包详细信息 列表*/

    public int getHas_new() {
        return has_new;
    }

    public void setHas_new(int has_new) {
        this.has_new = has_new;
    }

    public String getNew_version_name() {
        return new_version_name;
    }

    public void setNew_version_name(String new_version_name) {
        this.new_version_name = new_version_name;
    }

    public int getNew_version_code() {
        return new_version_code;
    }

    public void setNew_version_code(int new_version_code) {
        this.new_version_code = new_version_code;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public int getClient_type() {
        return client_type;
    }

    public void setClient_type(int client_type) {
        this.client_type = client_type;
    }

    public int getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(int channel_code) {
        this.channel_code = channel_code;
    }

    public int getIs_force_update() {
        return is_force_update;
    }

    public void setIs_force_update(int is_force_update) {
        this.is_force_update = is_force_update;
    }

    public int getUpdate_method() {
        return update_method;
    }

    public void setUpdate_method(int update_method) {
        this.update_method = update_method;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getOld_md5() {
        return old_md5;
    }

    public void setOld_md5(String old_md5) {
        this.old_md5 = old_md5;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }

    public String getFull_md5() {
        return full_md5;
    }

    public void setFull_md5(String full_md5) {
        this.full_md5 = full_md5;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getDiff_md5() {
        return diff_md5;
    }

    public void setDiff_md5(String diff_md5) {
        this.diff_md5 = diff_md5;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getChange_log() {
        return change_log;
    }

    public void setChange_log(String change_log) {
        this.change_log = change_log;
    }

    public List<Patch> getPatchs() {
        return Collections.unmodifiableList(patchs);
    }

    public void setPatchs(List<Patch> patchs) {
        this.patchs = patchs;
    }
}
