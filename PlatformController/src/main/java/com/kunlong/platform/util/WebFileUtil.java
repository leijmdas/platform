package com.kunlong.platform.util;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class WebFileUtil  {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private static String dir;
	public static void setTemplateDir(String dir) {
		WebFileUtil.dir = dir;
	}

	public WebFileUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static String getFileExtByFilename(String filename) {
		if (StringUtil.trimToEmpty(filename).indexOf(".") < 0)
			return "";

		String fileExt = filename.substring(filename.lastIndexOf("."));
		return fileExt;
	}

	public static String getFileExt(File file) {
		return getFileExtByFilename(file.getName());
	}
	
	public String getOutputAttachementFilename(String filename) throws UnsupportedEncodingException {
		String userAgent = this.request.getHeader("User-Agent");
		String new_filename = URLEncoder.encode(filename, "UTF8");
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		String rtn = "filename=\"" + new_filename + "\"";
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				rtn = "filename=\"" + new_filename + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				rtn = "filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
		}
		return rtn;
	}
	
	/**
	 * 导出至Excel
	 * @param templateName
	 * @param params
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void export2Excel(String templateName,Map<String,Object> params,String fileName) throws FileNotFoundException, IOException {
		setExcelHeader(fileName);
		
		JxlsUtil.exportExcel(resovePath(dir+"/"+templateName),  params,this.response.getOutputStream());
		
	}


	//easyExcel
	public void export2EasyExcel(String fileName, List<String> titleNames, List<List<String>> records) throws IOException {
		setExcelHeader(fileName);
		OutputStream out = response.getOutputStream();

		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

		// 设置SHEET
		Sheet sheet = new Sheet(1, 0);
		sheet.setSheetName("明细单");

		// 设置标题
		Table table = new Table(1);
		List<List<String>> titles = new ArrayList<List<String>>();
		for(String name:titleNames){
			titles.add(Arrays.asList(name));

		}
		table.setHead(titles);

		writer.write0(records, sheet, table);
		writer.finish();

	}

	private static String resovePath(String path) {
		return StringUtil.resolveUrl(path);
	}
	private void setExcelHeader(String fileName) throws UnsupportedEncodingException {
		response.setHeader("content-disposition", "attachment;  filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("attachment-name", URLEncoder.encode(fileName,"utf-8"));
		response.setContentType("application/msexcel");
	}
	public void saveWorkbook(org.apache.poi.ss.usermodel.Workbook resultWorkbook, String fileName) throws IOException {
		OutputStream os = response.getOutputStream();
		try {
			response.setHeader("content-disposition", "attachment;  filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setContentType("application/msexcel");
			resultWorkbook.write(os);
			os.flush();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void write2Response(File file) throws IOException{
		this.write2Response(new FileInputStream(file));
	}
	public void write2Response(InputStream is) throws IOException{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		// 获取输入流
		bis = new BufferedInputStream(bis);
		// 输出流
		bos = new BufferedOutputStream(response.getOutputStream());
		try {
			byte[] buff = new byte[2048];
			int bytesRead;
			long len = 0;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
				len += bytesRead;
			}
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(len));
			
		} finally {
			// 关闭流
			try {
				bis.close();
			} catch (Exception bisEx) {

			}
			try {
				bos.close();
			} catch (Exception bosEx) {

			}
		}
	}
	public void writeAttachment2Response(String outputFilename,InputStream is) throws IOException{
		
		// 设置文件输出类型
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;" + this.getOutputAttachementFilename(outputFilename));
		// 设置输出长度
		//response.setHeader("Content-Length", String.valueOf(fileLength));
		write2Response(is);
	}
	public void writeAttachment2Response(String outputFilename,File file) throws IOException{
		// 设置文件输出类型
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;" + this.getOutputAttachementFilename(outputFilename));
		write2Response(file);
	}

//	public void exportExcelBatchInviteDetailByBatchNo(String fileName, List<String> titleNames, List<List<String>> records) throws IOException {
//		setExcelHeader(fileName);
//		OutputStream out = response.getOutputStream();
//		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//		Sheet sheet = new Sheet(1, 0);
//		sheet.setSheetName("批次号邀请码明细");
//		Table table = new Table(1);
//		List<List<String>> titles = new ArrayList<List<String>>();
//		for(String name:titleNames){
//			titles.add(Arrays.asList(name));
//		}
//		table.setHead(titles);
//		writer.write0(records, sheet, table);
//		writer.finish();
//
//	}
}
