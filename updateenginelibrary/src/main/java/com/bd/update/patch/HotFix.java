package com.bd.update.patch;

/**
 * Description : <Content><br>
 * CreateTime : 2016/7/27 17:46
 *
 * @author KevinLiu
 * @version <v1.0>
 * @Editor : KevinLiu
 * @ModifyTime : 2016/7/27 17:46
 * @ModifyDescription : <Content>
 */
public class HotFix {

    int patch_version;/*补丁版本号*/
    String patch_package_name;/*补丁包名*/
    String patch_url;/*补丁包下载地址*/
    int patch_size;/*补丁包大小*/
    String patch_md5;/*补丁包md5值*/
    String patch_log;/*补丁特性*/
    String patch_time;/*发布时间*/

    public int getPatch_version() {
        return patch_version;
    }

    public void setPatch_version(int patch_version) {
        this.patch_version = patch_version;
    }

    public String getPatch_package_name() {
        return patch_package_name;
    }

    public void setPatch_package_name(String patch_package_name) {
        this.patch_package_name = patch_package_name;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    public int getPatch_size() {
        return patch_size;
    }

    public void setPatch_size(int patch_size) {
        this.patch_size = patch_size;
    }

    public String getPatch_md5() {
        return patch_md5;
    }

    public void setPatch_md5(String patch_md5) {
        this.patch_md5 = patch_md5;
    }

    public String getPatch_log() {
        return patch_log;
    }

    public void setPatch_log(String patch_log) {
        this.patch_log = patch_log;
    }

    public String getPatch_time() {
        return patch_time;
    }

    public void setPatch_time(String patch_time) {
        this.patch_time = patch_time;
    }
}
