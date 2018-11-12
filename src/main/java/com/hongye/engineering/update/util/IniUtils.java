/**
 * 
 */
package com.hongye.engineering.update.util;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
/**
 * @author zhangliping
 * 2015年1月31日
 */
public class IniUtils {
	String configpath = "D:\\dev\\installanywhere\\BMTMv1.1\\resourse\\BMTM.ini";
	private static Properties properties =new Properties(); 
	FileInputStream fis = null; // 读
	OutputStream fos ; 
	
	
	/**
	 * 
	 */
	public IniUtils() {
		try {
			fis = new FileInputStream(configpath);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	
	public String getProperty(String key)
	{
		Object object = properties.get(key);
		return object.toString();
	}
	
	public void setProperty(String key, String value)
	{
		try {
			fos = new FileOutputStream(configpath);// 加载读取文件流
			properties.setProperty(key, value);
			properties.store(fos, null);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
 
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IniUtils ini = new IniUtils();
		System.out.println("當前軟件版本為：" + ini.getProperty("version"));
		ini.setProperty("reportFile", "D://aa");
		System.out.println(ini.getProperty("reportFile"));
		ini.setProperty("keyStoreFile", "C://ee");
		System.out.println(ini.getProperty("keyStoreFile"));
	}
}