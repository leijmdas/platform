package com.kunlong.platform.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.api.dto.FileInfoDTO;
import com.kunlong.dubbo.api.service.FileApiService;
import com.kunlong.platform.service.impl.FastDFSService;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service(version = "${dubbo.service.version}")
public class FileApiProvider implements FileApiService {

	@Value("${file.server}")
	private String fileServer;
	@Override
	public FileInfoDTO upload(String name, String ext, byte[] bytes, Map<String, String> props) {
		FileInfoDTO r = new FileInfoDTO();
		try {
			String path = FastDFSService.upload(name, ext, bytes, null);
			r.setExt(ext);
			r.setName(name);
			r.setPath(fileServer + "/" + path);
			r.setSize((long) bytes.length);
			return r;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] download(String groupName, String remoteFileName) {
		try {
			byte[] content = FastDFSService.download(groupName, remoteFileName);
			return content;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
