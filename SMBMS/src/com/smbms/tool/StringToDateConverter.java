package com.smbms.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/** 
 * 自定义的类型转换器
 * 实现前端字符串的日期和后端Date进行转换
 * @author jd 
 */
public class StringToDateConverter implements Converter<String, Date> {
	
	//获取到转换格式
	private String pattern;
	
	public StringToDateConverter(String pattern){
		this.pattern = pattern;
	}
	
	@Override
	public Date convert(String strDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat(pattern).parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
