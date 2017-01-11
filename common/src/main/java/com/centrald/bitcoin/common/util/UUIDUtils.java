package com.centrald.bitcoin.common.util;

import java.util.UUID;

/**
 * Created by central on 2017/1/11.
 */
public class UUIDUtils {

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        String user_code = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        return user_code;
    }
}
