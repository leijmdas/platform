package com.kunlong.platform.util;



import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class MathUtil {

	private static MathUtil instance = new MathUtil(new MathContext(14,RoundingMode.HALF_UP),2);
	private static DecimalFormat decimalFormat = new DecimalFormat("###,##0.0#");
	private int scale = 2;
	private MathContext mc;
	
	public static MathUtil getInstance() {
		return instance;
	}
	
	public static MathUtil getInstance(MathContext c,int scale) {
		return new MathUtil(c,scale);
	}
	
	private MathUtil(MathContext c,int scale) {
		this.mc = c;
		this.scale = scale;
	}
	
	public BigDecimal null2zero(Number n) {
		if(n == null) return BigDecimal.ZERO;
		return toBigDecimal(n);
	}
	public BigDecimal toBigDecimal(Number n) {
	
		return BigDecimal.valueOf(n.doubleValue()).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * 乘法
	 * @param n1
	 * @param n2
	 * @return
	 */
	public BigDecimal multiply(Number n1,Number n2) {
		return toBigDecimal(null2zero(n1).multiply(null2zero(n2), mc));
	}
	/**
	 * 除法
	 * @param n1
	 * @param n2
	 * @return
	 */
	public BigDecimal divide(Number n1,Number n2) {
		return toBigDecimal(null2zero(n1).divide(null2zero(n2), mc));
	}
	/**
	 * 加法
	 * @param n1
	 * @param n2
	 * @return
	 */
	public BigDecimal add(Number n1,Number n2) {
		return toBigDecimal(null2zero(n1).add(null2zero(n2), mc));
	}
	/**
	 * 减法
	 * @param n1
	 * @param n2
	 * @return
	 */
	public BigDecimal subtract(Number n1,Number n2) {
		return toBigDecimal(null2zero(n1).subtract(null2zero(n2), mc));
	}
	public static void main(String[] args) {
		
		System.out.println(MathUtil.getInstance().multiply(1.22323232323232323232d, 2));
	}

	/**
	 * 计算double数字的平均值
	 * @param risePercentArray
	 * @return
	 */
    public static Double getAvgFormDoubleArray(double[] risePercentArray) {
		double sum = 0;
		for(double ra :risePercentArray){
			sum += ra;
		}
		return new BigDecimal(sum).divide(new BigDecimal(risePercentArray.length),2,2).doubleValue();
    }

	public static Double getAvgFormDoubleList(List<Double> list) {
		double sum = 0;
		for(double ra :list){
			sum += ra;
		}
		if(list == null || (list != null &&list.size()==0)){
			return null;
		}
		return new BigDecimal(sum).divide(new BigDecimal(list.size()),2,2).doubleValue();

	}

	public static String format(Number number) {
		return decimalFormat.format(number);
	}

	public static String format(Object number) {
		return decimalFormat.format(number);
	}
}
