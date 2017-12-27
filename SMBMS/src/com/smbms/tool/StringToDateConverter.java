package com.smbms.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/** 
 * �Զ��������ת����
 * ʵ��ǰ���ַ��������ںͺ��Date����ת��
 * @author jd 
 */
public class StringToDateConverter implements Converter<String, Date> {
	
	//��ȡ��ת����ʽ
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
