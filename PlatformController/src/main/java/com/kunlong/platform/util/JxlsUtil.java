package com.kunlong.platform.util;


import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JxlsUtil{
	
	
	public static InputStream getTemplateStream(String resName) throws IOException {
		InputStream in = null;
		if(resName.startsWith("classpath:")) {
			ClassPathResource classPathResource = new ClassPathResource(resName.replace("classpath:", ""));
			in = classPathResource.getInputStream();
		} else if(resName.startsWith("file:")) {
			in = new FileInputStream(new File(resName.replace("file:", "")));
		}
		return in;
	}
	
    private JxlsUtil() {}
    
    /**
     * 导出excel
     * @param templateName
     * @param params
     * @param out
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static void exportExcel(String templateName,Map<String,Object> params,OutputStream out) throws IOException {
		InputStream in = getTemplateStream(templateName);
		if(in == null) {
			throw new RuntimeException("资源不存在:"+templateName);
		}
		try {
			JxlsUtil.exportExcel(in, out, params);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				
			}
		}
		
	}
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException{
        Context context = PoiTransformer.createInitialContext();
        context.putVar("now", new Date());
        context.putVar("math", MathUtil.getInstance());
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.createTransformer(is, os);
        //获得配置
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        //设置静默模式，不报警告
        //evaluator.getJexlEngine().setSilent(true);
        //函数强制，自定义功能
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("util", new JxlsUtil());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        
        //必须要这个，否者表格函数统计会错乱
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
            exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }
    
    public static void exportExcel(File template,Map<String, Object> model, OutputStream os) throws IOException {
        if(template != null){
            exportExcel(new FileInputStream(template), os, model);    
        } else {
            throw new RuntimeException("Excel 模板未找到");
        }
    }
    // 日期格式化
    public String dateFormat(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
           
        }
        return "";
    }
    
    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }
    
    private Map<String,Integer> countMap = new HashMap<>();
    public Integer getIndex(String var){
	  if (var == null) return null;
	  if(countMap.containsKey(var)){
	    Integer t = countMap.get(var);
	    t += 1;
	    countMap.replace(var,t);
	    return t;
	  }else{
	    countMap.put(var,1);
	  }
	  return 1;
	}
}