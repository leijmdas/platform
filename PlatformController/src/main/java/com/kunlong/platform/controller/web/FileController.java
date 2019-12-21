package com.kunlong.platform.controller.web;


import cn.integriti.center.api.dto.FileInfoDTO;
import cn.integriti.center.api.service.FileApiService;
import com.kunlong.platform.util.FileHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;


@SuppressWarnings("all")
@RequestMapping("/file")
@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private FileApiService fileApiService;
	// 处理文件上传
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
    FileInfoDTO upload(@RequestParam("file") MultipartFile uploadFile,
                       @RequestParam(value = "maxSize", defaultValue = "20480") Integer maxSize, HttpServletRequest request)
			throws Exception {
		
		String filename = uploadFile.getOriginalFilename();
		filename = URLDecoder.decode(filename,"UTF-8");
		
		String contentType = uploadFile.getContentType();
		logger.info("接收文件[contentType:" + contentType + ",name:" + filename + "]");
		long fileSize = uploadFile.getSize();
		if (fileSize > maxSize*1024) {
			throw new RuntimeException("文件过大。最大允许(" + maxSize + " KB)");
		}
		String extName = FileHelper.getFileExtByFilename(filename);

		byte[] bytes = FileHelper.stream2byte(uploadFile.getInputStream());

		FileInfoDTO fileInfo = fileApiService.upload(uploadFile.getName(), extName.length() > 0 ? extName.substring(1) : "",
				bytes, null);
		return fileInfo;
	}


	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(String path, @RequestParam(value = "name", required = false) String name,
                                           Model model, HttpServletResponse response) throws Exception {

		String[] paths = getFileNames(path);
		String group = paths[0];
		String remoteFileName = paths[1];
		String specFileName = remoteFileName.substring(remoteFileName.lastIndexOf("/") + 1);
		if (name != null) {
			specFileName = name + specFileName.substring(specFileName.indexOf("."));
		}
		return download(group, remoteFileName, specFileName);
	}

	private String[] getFileNames(String path) {
		String substr = path.substring(path.indexOf("group"));
		String group = substr.split("/")[0];
		String remoteFileName = substr.substring(substr.indexOf("/") + 1);
		return new String[] {group,remoteFileName};
	}

	private ResponseEntity<byte[]> download(String groupName, String remoteFileName, String specFileName)
			throws Exception {
		byte[] content = fileApiService.download(groupName, remoteFileName);
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setContentDispositionFormData("attachment",
					new String(specFileName.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		} catch (Exception e) {

		}
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
	}

}
