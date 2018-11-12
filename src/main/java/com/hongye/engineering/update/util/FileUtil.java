package com.hongye.engineering.update.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.tomcat.util.http.fileupload.FileUtils;

public class FileUtil {

	/**
	 * 复制文件夹到制定文件夹
	 * 
	 * @param path1
	 * @param path2
	 */
	public static void copy(String path1, String path2) {

		File file = new File(path1);

		if (file.isDirectory()) {

			File f2 = new File(path2);
			if (!f2.exists())
				f2.mkdir();
			File[] files = file.listFiles();

			for (File f1 : files) {
				copy(f1.toString(), path2 + "/" + f1.getName());
			}

		} else {
			copyFile(path1, path2);
		}

	}

	public static void copyFile(String path1, String path2) {
		DataInputStream in = null;
		DataOutputStream ot = null;
		try{ 
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(path1))));
			ot = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path2))));
			byte[] buff = new byte[in.available()];
			in.read(buff);
			ot.write(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				ot.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 清空path1,将path2复制到path1目录下
	 * 
	 * @param path1
	 * @param path2
	 */
	public static void cleanAndCopy(String path1, String path2) {
		File dest = new File(path1);
		try {
			FileUtils.cleanDirectory(dest);
			copy(path2, path1);
			System.out.println("替换成功：" + path1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copySingleFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时  
				inStream = new FileInputStream(oldPath); // 读入原文件  
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				System.out.println("复制单个文件操作成功" + oldPath);
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错" + oldPath);
			e.printStackTrace();
		} finally {
			try {
				inStream.close();
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// long start = System.currentTimeMillis();
		// cleanAndCopy("D:\\Program Files (x86)\\BMTM\\dist","D:\\Program Files
		// (x86)\\BMTM\\updateData\\dist");
		// cleanAndCopy("D:\\Program Files
		// (x86)\\BMTM\\javaProject","D:\\Program Files
		// (x86)\\BMTM\\updateData\\javaProject");
		// cleanAndCopy("D:\\Program Files (x86)\\BMTM\\node-api","D:\\Program
		// Files (x86)\\BMTM\\updateData\\node-api");
		// cleanAndCopy("D:\\Program Files
		// (x86)\\BMTM\\TaskWorkerCorePublish","D:\\Program Files
		// (x86)\\BMTM\\updateData\\TaskWorkerCorePublish");
		// long end = System.currentTimeMillis();
		// System.out.println("运行时间：" + (end - start));
		copySingleFile("D:\\Program Files (x86)\\BMTM\\updateData\\javaProject\\engineering_project-0.0.1-SNAPSHOT.jar",
				"D:\\Program Files (x86)\\BMTM\\javaProject\\engineering_project-0.0.1-SNAPSHOT.jar");
	}
}
