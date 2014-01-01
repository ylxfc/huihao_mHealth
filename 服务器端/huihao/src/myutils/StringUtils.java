package myutils;

import java.io.UnsupportedEncodingException;

public class StringUtils {

	public static String StringEcode(String str){
		try {
			byte b[] = str.getBytes("ISO-8859-1");
			str = new String(b);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
