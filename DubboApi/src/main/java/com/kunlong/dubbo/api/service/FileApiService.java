package com.kunlong.dubbo.api.service;

import com.kunlong.dubbo.api.dto.FileInfoDTO;

import java.util.Map;

public interface FileApiService {

	/**
	 * 上传
	 * @param name
	 * @param ext
	 * @param bytes
	 * @param props
	 * @return
	 */
	FileInfoDTO upload(String name, String ext, byte[] bytes, Map<String, String> props);
	
	/**
	 * 下载
	 * @param groupName
	 * @param remoteFileName
	 * @return
	 */
	byte[] download(String groupName, String remoteFileName);
}
