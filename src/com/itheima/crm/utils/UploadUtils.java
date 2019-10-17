package com.itheima.crm.utils;

import java.util.UUID;

public class UploadUtils {
    /**
     * 解决文件名重复问题
     * @param fileName
     * @return
     */
    public static String getUuidFileName(String fileName) {
        // 找出符号"."的位置
        int idx = fileName.lastIndexOf("."); // aa.txt
        // 获取后缀
        String lastName = fileName.substring(idx); //.txt
        // 返回随机生成的uuid+后缀 去横杠
        return UUID.randomUUID().toString().replace("-", "") + lastName;
    }
    /**
     * 使用目录分离的方法
     * @return
     */
    public static String getPath(String uuidFileName) {
        // 获取文件名的哈希值
        int hashCode1 = uuidFileName.hashCode();
        int hashCode2 = hashCode1 >>> 4;
        // 作为一级目录
        int d1 = hashCode1 & 0xf;
        // 作为二级目录
        int d2 = hashCode2 & 0xf;
        return "/" + d1 + "/" + d2;
    }
}
