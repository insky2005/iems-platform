package com.iems.core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class AuthUtil {
	/**
	 * 加 密
	 * @param data 明文
	 * @param key 密钥
	 * @return 密文
	 */
	public static String HMACSHA1(String data, String key) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes("UTF-8"));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException ignore) {
			ignore.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (byteHMAC != null) {
			try {
				String hexMac = getHexString(byteHMAC);
				return hexMac;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}
	
	/**
	 * byte2HexString
	 * @param b
	 * @return 十六进制字符串
	 * @throws Exception
	 */
	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	
	/**
	 * 时间差
	 * @param timestamp
	 * @param seconds
	 * @return 时间戳与当前时间差在 seconds 秒内 返回true，否则返回false
	 */
	public static boolean checkIntervalTime(String timestamp, long seconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Calendar c = Calendar.getInstance();
			Date curTime = c.getTime();
			Date time = sdf.parse(timestamp);
			long interval = (time.getTime() - curTime.getTime()) / 1000;
			if(interval < seconds && interval > -seconds){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 时间格式转化
	 * @param time
	 * @return
	 */
	public static String timeFormat(String time){
		String year = time.substring(0, 4);
		String mon = time.substring(4, 6);
		String day = time.substring(6, 8);
		String hour = time.substring(8, 10);
		String min = time.substring(10, 12);
		String sec = time.substring(12, 14);
		return year+"-"+mon+"-"+day+" "+hour+":"+min+":"+sec;
	}
}
