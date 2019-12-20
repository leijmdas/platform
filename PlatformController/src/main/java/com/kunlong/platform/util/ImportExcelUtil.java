package com.kunlong.platform.util;

import org.apache.dubbo.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportExcelUtil {
	
	 /**
     * 工作薄对象
     */
    private Workbook wb;
    
    /**
     * 工作表对象
     */
    private Sheet sheet;
    
    /**
     * 标题行号
     */
    private int headerNum;
	
	/**
	 * 构造函数
	 * 
	 * @param path 导入文件,读取第一个工作表
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException
	 * @throws IOException
	 * */
	public ImportExcelUtil(String filName,int headerNum) throws InvalidFormatException, IOException{
		this(new File(filName), headerNum);
	}
	
	 /**
     * 构造函数
     * 
     * @param path 导入文件对象，读取第一个工作表
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcelUtil(File file, int headerNum)
        throws InvalidFormatException, IOException
    {
        this(file, headerNum, 0);
    }
    
    /**
     * 构造函数
     * 
     * @param path 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcelUtil(File file, int headerNum, int sheetIndex)
        throws InvalidFormatException, IOException
    {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }
    
    /**
     * 构造函数
     * 
     * @param path 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcelUtil(String fileName, InputStream is, int headerNum, int sheetIndex)
        throws InvalidFormatException, IOException
    {
        if (StringUtils.isBlank(fileName))
        {
            throw new RuntimeException("导入文档为空!");
        }
        else if (fileName.toLowerCase().endsWith("xls"))
        {
            this.wb = new HSSFWorkbook(is);
        }
        else if (fileName.toLowerCase().endsWith("xlsx"))
        {
            this.wb = new XSSFWorkbook(is);
        }
        else
        {
            throw new RuntimeException("文档格式不正确!");
        }
        if (this.wb.getNumberOfSheets() < sheetIndex)
        {
            throw new RuntimeException("文档中没有工作表!");
        }
        this.sheet = this.wb.getSheetAt(sheetIndex);
        this.headerNum = headerNum;
    }
    
    /**
      * 获取数据行号
     * 
     * @return
     */
    public int getDataRowNum()
    {
        return headerNum + 1;
    }
    
    /**
     * 获取最后一个数据行号
     * 
     * @return
     */
    public int getLastDataRowNum()
    {
        return this.sheet.getLastRowNum() + headerNum;
    }
    
    /**
     * 获取行对象
     * 
     * @param rownum
     * @return
     */
    public Row getRow(int rownum)
    {
        return this.sheet.getRow(rownum);
    }
    
    /**
     * 获取最后一个列号
     * 
     * @return
     */
    public int getLastCellNum()
    {
        return this.getRow(headerNum).getLastCellNum();
    }
    
    /**
     * 获取单元格值
     * 
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column)
    {
        Object val = "";
        try
        {
            Cell cell = row.getCell(column);
            if (cell != null)
            {	
            	switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                	if (HSSFDateUtil.isCellDateFormatted(cell)) {
                		SimpleDateFormat sdf = null;
                		if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                		.getBuiltinFormat("h:mm")) {
                		sdf = new SimpleDateFormat("HH:mm");
                		} else {// 日期
                		sdf = new SimpleDateFormat("yyyy-MM-dd");
                		} 
                		Date date = cell.getDateCellValue();
                		val = sdf.format(date); 
                	}else if (cell.getCellStyle().getDataFormat() == 58) {
                		// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58) 
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                		double value = cell.getNumericCellValue();
                		Date date = org.apache.poi.ss.usermodel.DateUtil
                		.getJavaDate(value);
                		val = sdf.format(date);
                	}else {
                		double v = cell.getNumericCellValue();
                		CellStyle style = cell.getCellStyle();
                		DecimalFormat format = new DecimalFormat();
                		String temp = style.getDataFormatString();
                		// 单元格设置成常规
                		if (temp.equals("General")) {
                		format.applyPattern("#");
                		} 
                		val = format.format(v);
                	}
                    break;
                case STRING:
                	val = cell.getStringCellValue();
                    break;
                case FORMULA:
                	val = cell.getCellFormula();
                    break;
                case BLANK:
                	val = null;
                    break;
                case BOOLEAN:
                	val = cell.getBooleanCellValue();
                    break;
                case ERROR:
                	val = cell.getErrorCellValue();
                    break;
                default:
                   break;
            }
				/*
				 * if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) { val =
				 * cell.getNumericCellValue(); } else if(cell.getCellType() ==
				 * cell.CELL_TYPE_BLANK) { val = "NULL"; } else if (cell.getCellType() ==
				 * Cell.CELL_TYPE_STRING) { val = cell.getStringCellValue(); } else if
				 * (cell.getCellType() == Cell.CELL_TYPE_FORMULA) { val = cell.getCellFormula();
				 * } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) { val =
				 * cell.getBooleanCellValue(); } else if (cell.getCellType() ==
				 * Cell.CELL_TYPE_ERROR) { val = cell.getErrorCellValue(); }
				 */
            }
        }
        catch (Exception e)
        {
            return val;
        }
        return val;
    }
    
    
}
