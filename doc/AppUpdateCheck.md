## 接口协议  
**请求样例**  

{"cmd":"AppUpdateCheck"  
,"request_id": 12345  
,"body":{  
    "version_name": "dana+5.2.0"  
     ,"version_code":3  
   ,"client_id":"103"
   ,"client_type":2   
   ,"patch_versions":[
   1
   ,2
   ,4
   ]  
   ,"channel_code":4  
   ,"current_apk_md5":"51266f5d84f0d03beefe0670bd5c86fb"  
   ,"update_time":1456666666  
   ,"terminal_did":"gfgq234yg34rh354521q4yh35rh"
   ,"terminal_vender":"HUAWEI"  
    ,"terminal_model":"HUAWEI MT7-TL10"  
     ,"terminal_os_byte":"32"  
    ,"terminal_os_ver":"5.0.1"  
}  
}

---

**参数说明**   

|字段名称 | 类型 | 必须 | 描述 |    
|:--------|:-----:|:----:|:------|    
|cmd|string|是|AppUpdateCheck|    
|request_id|int|是|标示该请求的唯一ID，在返回中将包含该ID号码,用来匹配请求和应答的成对出现|     
|body|map|是|见后续描述|  

---

**body参数说明**

|字段名称|　类型|　必须|描述|
|:-------------|---------|---------|:----------|
|version_name|string|否|当前app版本名(5.2.0)|
|version_code|int|是|当前app的版本号|
|client_id|string|是|同一系列的app的唯一标识字符串|
|client_type|int|是|app所在终端类型，1,IOS phone;2,安卓 phone;3,Win Phone;4, Windows;5 Android TV; 6 IOS TV; 7 Android  PAD; 8 iOS PAD|
|patch_versions|int_array|是|整型数组，表示当前app已具有的补丁版本号|
|channel_code|int|是|发放apk的渠道代码|
|current_apk_md5|string|否|当前apk的md5值，用于判断当前apk是否被更改|
|update_time|int64|否|当前app的安装时间,UTC时间戳，秒|
|terminal_did|string|否|app所在终端的唯一识别码|
|terminal_vender|string|否|终端生产商|
|terminal_model|string|否|终端型号|
|terminal_os_byte|string|否|终端操作系统位数|
|terminal_os_ver|string|否|终端操作系统版本号|


---

**接口返回样例**

{"code":0  
,"request_id": 12345  
,"code_msg":""  
,"body": {  
            "has_new":1  
	        ,"new_version_name": "5.2.6"  
	        ,"new_version_code": ４  
	        ,"client_id":"103"  
	        ,"client_type": 2  
	        ,"channel_code": 4           
	        ,"is_force_update":1  
	        ,"update_method":  0  
	        ,"release_time": "2016-07-22"  
	         ,"old_md5":"79a041326bfdeb84503806d0f23d6872"  
             ,"file_size": 567   
	        ,"full_url": "https://oss.aliyun.com/xxxx"  
	        ,"full_md5": "51266f5d84f0d03beefe0670bd5c86fb"  
	        ,"diff_url": "https://xxxxxxxxx"  
	        ,"diff_md5": "d19c9603343a077e1ea6ca3cdb99503c"  
	         ,"package_name": "xxx"  
	        	 ,"change_log": "新增公共视频直播功能"  
	        ,"patchs":[  
	        {  
	               "patch_version":3  
	               ,"patch_package_name":"xxxx.patch"  
	              , "patch_url"="https:xxxxxxx.down3"  
	               ,"patch_size":45  
	               ,"patch_md5":"dasgjaadsfbiisjnj34nboisnk848ln346235"  
	               ,"patch_log":"修复视频卡顿问题"   
	               ,"patch_time":"2016-07-23"   
	        }  
	        {  
	               "patch_version":5  
	               ,"patch_package_name":"xxxx.patch"  
	              , "patch_url"="https:xxxxxxx.down5"  
	               ,"patch_size":23  
	               ,"patch_md5":"5asd4gqwr4e521g61aewry3as5d4g6q34e"  
	               ,"patch_log":"修复头像加载错误问题"   
	               ,"patch_time":"2016-07-24"  
	        }
	        ]   
}  
, "count":0}

---

**返回参数说明**

|字段名称|类型|描述|
|:------------|----------|:----------|
|code|int|0,正确; 其它返回码说明参见错误代码描述|
|code_msg|string|返回码对应的信息|
|request_id|int|与请求中的request_id一致|
|counts|int|返回body个数|
|has_new|int|是否存在更新,0 不存在,　１ 存在|
|new_version_name|string|新版ap版本名(如5.2.6)|
|new_version_code|int|新版apk版本号|
|client_id|string|该系列app的标识符|
|client_type|int|该系列app所属平台类型|
|channel_code|int|发布该app的渠道代码|
|is_force_update|int|0:非强制更新　1:强制更新　2:不需要更新|
|update_method|int|0:完整更新　1:增量更新    2:热更新|
|release_time|string|新版app发布时间|
|old_md5|string|老版本app的md5值|
|file_size|int|全量安装包大小|
|full_url|string|全量安装包下载地址|
|full_md5|string|全量安装包md5值|
|diff_url|string|差分安装包下载地址|
|diff_md5|string|差分安装包md5值|
|package_name|string|新版app包名|
|change_log|string|新版特性|
|patchs|array|需更新的补丁包详细信息|
|patch_version|int|补丁版本号|
|patch_package_name|string|补丁包名|
|patch_url|string|补丁包下载地址|
|patch_size|int|补丁包大小|
|patch_md5|string|补丁包md5值|
|patch_log|string|补丁特性|   
|patch_time|string|发布时间|

---

## DB设计

## 策略表  client_update_policy

|id|client_id|client_type|version_code|latest_version_code|version_name|chanel_code|is_default|is_force_update|update_method|version_md5|insert_time|  
|--|:----------:|:------------------:|:---------------:|:--------:|:-------------:|:------------------------:|:-----------:|:------------:|:-----------------:|------|---------|
|23|103|2|4|7|5.2.6|4|0|1|0|dasgjaadsfbiisjnj34nboisnk848ln346235|16854626163|
|25|103|2|5|7|5.2.6|4|0|1|0|hadh8f7awgt6by35tgkbe86g9w3457tgb7|16854626164|
|26|103|2|6|7|5.2.6|4|0|0|0|6t9q784gt6aidre7fh82347rtyq28974t88g|16854626166|
|27|103|2|7|7|5.2.6|4|0|0|0|6742q6grt82q6yrtga9078t8273y8273y5r2|16854626168|
|28|103|2|0|0|5.2.6|4|1|1|0|6742q6grt82q6yrtga9078t8273y8273y5r2|16854626168|

---

## 全量表  client_full

|id|client_id|client_type|new_version_code|chanel_code|is_new|is_check|package_name|url|file_size|full_md5|change_log|release_time|insert_time|  
|--|:-----------:|:----:|:----------------:|:----------------:|:--:|:---------:|:-----------:|-----------------|----------------|------------|----|-----|------------|
|3|101|2|4|4|1|1|xxx|https://xxxxxxxxxxxxxxx|723|uqy389478t69qt7uh0q74etylaudh|新增xx功能|2016-06-19|142689572525|
|4|103|2|7|3|0|1|xxx|https://xxxxxxxxxxxxxxx|439|adtyg5664ju4w6j524qw6t2572wg|新增xx功能|2016-07-20|159846572346|
|5|103|2|7|4|1|1|xxx|https://xxxxxxxxxxxxxxx|427|7qu2h4e89r7q24ght7234872t3y27|新增xx功能|2016-07-20|159846572525|

---
## 差分表  client_diff

|id|client_id|client_type|old_version_code|new_version_code|chanel_code|is_check|package_name|url|file_size|diff_md5|full_md5|change_log|release_time|insert_time|  
|--|:----------:|:-----:|:-----------------------:|:------------------------:|:---------------:|:---:|:---------:|------------|------------------|---------------|--------------|-----------------|----|------|
|3|103|2|5|7|4|1|xxx|https://xxxxxxxxxxxxxxx|98|81q0347gt7i874g93485t73f346t34h|q246t134h56ty56ytdrtffuyh3w54jt|新增xx功能|2016-07-19|142689573543|
|4|103|2|6|7|4|1|xxx|https://xxxxxxxxxxxxxxx|89|q28467t9gf379oq3t742o9283ghi9as|234yh3q5qjhq2nr3463h5jtesu345|新增xx功能|2016-07-23|142689562677|


---
## 补丁表  client_patch

|id|client_id|client_type|full_version_code|patch_version_code|chanel_code|is_check|package_name|url|file_size|patch_md5|change_log|release_time|insert_time|  
|--|:------------:|:-----:|:---------------------:|:------------------------:|:-----------------:|:---:|:----------:|--------------|--------------|------------------|----------------|---------|------------|
|1|103|2|7|1|4|1|xxx|https://xxxxxxxxxxxxxxx|34|28745tr8927aw89e7r9237rtg82q73t|完善xx功能|2016-07-01|142689562677|
|2|103|2|7|2|4|1|xxx|https://xxxxxxxxxxxxxxx|31|q384t9gqh3849gq9834t0238fj2982y|完善xx功能|2016-07-11|142588963214|
|3|103|2|7|3|4|1|xxx|https://xxxxxxxxxxxxxxx|40|34y26ie57i6sw4u5uyh3wg5n67i7je4|完善xx功能|2016-07-21|142789514268|
