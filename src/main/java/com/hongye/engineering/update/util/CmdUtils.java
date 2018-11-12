package com.hongye.engineering.update.util;

import java.io.IOException;

public class CmdUtils {


	/**
	 * 执行批处理命令
	 * @param strcmd
	 */
	public static void run_cmd(String strcmd) {
		//
		Runtime rt = Runtime.getRuntime(); // Runtime.getRuntime()返回当前应用程序的Runtime对象
		Process ps = null; // Process可以控制该子进程的执行或获取该子进程的信息。
		try {
			ps = rt.exec(strcmd); // 该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
			ps.waitFor(); // 等待子进程完成再往下执行。
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = ps.exitValue(); // 接收执行完毕的返回值
		if (i == 0) {
			System.out.println(strcmd + "执行完成.");
		} else {
			System.out.println(strcmd + "执行失败.");
		}

		ps.destroy(); // 销毁子进程
		ps = null;
	}
	
	
	public static void main(String[] args) {
//		String cmdStr = "D:\\dev\\mysql-5.5\\bin\\mysql -uroot -proot<\"D:\\Program Files (x86)\\BMTM\"\\updateData\\engineering_project.sql";
//		String cmdStr = "net start mysql6";
		String cmdStr = "cmd /c call D:\\dev\\mysql-5.5\\bin\\mysql -uroot -proot<\"D:\\Program Files (x86)\\BMTM\"\\updateData\\BMTM\\update.sql";
		run_cmd(cmdStr);
	}
}
