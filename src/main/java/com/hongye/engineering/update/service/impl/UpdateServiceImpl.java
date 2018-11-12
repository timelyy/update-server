package com.hongye.engineering.update.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hongye.engineering.update.common.BizCodeEnum;
import com.hongye.engineering.update.constants.Const;
import com.hongye.engineering.update.exception.EngineeringException;
import com.hongye.engineering.update.feign.EngineeringManageFeignClient;
import com.hongye.engineering.update.service.UpdateService;
import com.hongye.engineering.update.util.CmdUtils;
import com.hongye.engineering.update.util.DownloadUtil;
import com.hongye.engineering.update.util.FileUtil;
import com.hongye.engineering.update.util.ZipUtil;


@Service
public class UpdateServiceImpl implements UpdateService {

	@Value("${user.install.dir}")
	private String userInstallDir;
	
	@Autowired
	private EngineeringManageFeignClient manageClient;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void doUpdate(String url) {
		synchronized (this) {
			try {
				long start = System.currentTimeMillis();
				//判断是否有新版本
				
				//下载最新更新包.解压到当前目录
//				downUpdateFile("http://model-light-result-files-4-bmtm.oss-cn-beijing.aliyuncs.com/file/9fff2e33-8d85-487d-be8e-8aadb0ab2ca9.zip");
				downUpdateFile(url);
				
				//关闭所有需要更新的服务
				stopServices();
				//替换所有四个项目
				replaceFile();
				//执行sql
				execSql();
				//启动所有服务
				startServices();
				//清空更新包
				cleanUpdateFile();
				long end = System.currentTimeMillis();
				logger.info("总耗时：" + (end - start));
			} catch (Exception e) {
				throw new EngineeringException(BizCodeEnum.B2B4068);
			}
		}
	}
	
	/**
	 * 启动所有服务
	 */
	private void startServices() {
		CmdUtils.run_cmd(Const.START_JAVA_SERVICE);
		CmdUtils.run_cmd(Const.START_LIGHTWEIGHT_SERVICE);
		CmdUtils.run_cmd(Const.START_NGINX_SERVICE);
		CmdUtils.run_cmd(Const.START_NODE_SERVICE);
	}

	/**
	 * 执行更新sql
	 */
	private void execSql() {
		String execSqlStr = "cmd /c call D:\\dev\\mysql-5.5\\bin\\mysql -uroot -proot<\"" + userInstallDir + "\"\\updateData\\BMTM\\update.sql";
//		String execSqlStr = "cmd /c call \"C:\\Program Files\"\\mysql-5.5\\bin\\mysql -uroot -pbamutumo<\"" + userInstallDir + "\"\\updateData\\BMTM\\update.sql";
		CmdUtils.run_cmd(execSqlStr);
	}

	/**
	 * 清空更新包
	 */
	private void cleanUpdateFile() {
		//清空更新包数据
		String updateDataPath = userInstallDir + "\\updateData";
		try {
			FileUtils.cleanDirectory(new File(updateDataPath));
			logger.info("清空成功");
		} catch (IOException e) {
			logger.info("清空失败");
			e.printStackTrace();
		}
	}

	/**
	 * 替换旧文件
	 */
	private void replaceFile() {
		//替换jar包
		String newFile = userInstallDir + "\\updateData\\" + "BMTM\\javaProject\\" + Const.JAR_NAME;
		String oldFile = userInstallDir + "\\javaProject\\" + Const.JAR_NAME;
		FileUtil.copySingleFile(newFile, oldFile);
		
		//替换前端文件
		String oldDistPath = userInstallDir + "\\dist\\";
		String newDistPath = userInstallDir + "\\updateData\\" + "BMTM\\dist\\";
		FileUtil.cleanAndCopy(oldDistPath, newDistPath);
		
		//替换node文件
		String oldNodePath = userInstallDir + "\\node-api\\";
		String newNodePath = userInstallDir + "\\updateData\\" + "BMTM\\node-api\\";
		FileUtil.cleanAndCopy(oldNodePath, newNodePath);
		
		//替换轻量化文件
		String oldLightPath = userInstallDir + "\\TaskWorkerCorePublish\\";
		String newLightPath = userInstallDir + "\\updateData\\" + "BMTM\\TaskWorkerCorePublish\\";
		FileUtil.cleanAndCopy(oldLightPath, newLightPath);
	}

	/**
	 * 停止所有服务
	 */
	private void stopServices() {
		CmdUtils.run_cmd(Const.STOP_JAVA_SERVICE);
		CmdUtils.run_cmd(Const.STOP_LIGHTWEIGHT_SERVICE);
		CmdUtils.run_cmd(Const.STOP_NGINX_SERVICE);
		CmdUtils.run_cmd(Const.STOP_NODE_SERVICE);
	}

	/**
	 * 下载并解压更新包
	 * @param url
	 */
	public void downUpdateFile(String url) {
		String savePath = userInstallDir + "\\updateData\\";
		String zipPath = savePath + Const.UPDATE_DATA_NAME;
		try {
			logger.info("开始下载");
			DownloadUtil.downLoadFromUrl(url, Const.UPDATE_DATA_NAME, savePath);
			logger.info("文件下载成功");
			logger.info("文件开始解压");
			ZipUtil.unZipFiles(new File(zipPath), savePath);
			logger.info("文件解压成功");
		} catch (IOException e) {
			logger.info("文件下载异常:",e.getMessage());
			e.printStackTrace();
		}
	}
	
}
