package com.spring.app.utils.ServiceMonitoring;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: IpUtils
 * @Date: 2023/11/2 11:03
 * @Description:
 **/
public class IpUtils {
    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }
}
