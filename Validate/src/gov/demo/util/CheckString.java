package gov.demo.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用校验
 * @author hanxm
 *
 */
public class CheckString {
    /**  
     * 功能：判断字符串是否为数字  
     * @param str  
     * @return  
     */  
	public static boolean isNumeric(String str) {  
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(str);  
        if (isNum.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    } 
	
	
	/**
	 * 时间格式校验
	 * @param String time
	 * @return 验证成功返回true，验证失败返回false 
	 */
	public static boolean isValidDate(String time) {  
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        try{  
            Date date = (Date)formatter.parse(time);  
            return time.equals(formatter.format(date));  
        }catch(Exception e){  
            return false;
        } 
	}

	
	 /**  
     * 匹配中国邮政编码  
     * @param postcode 邮政编码  
     * @return 验证成功返回true，验证失败返回false  
     */   
    public static boolean isPostCode(String postCode){  
        String reg = "[1-9]\\d{5}";  
        return Pattern.matches(reg, postCode);  
    }

	/** 
     * 手机号验证 
     * @param  data 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String data) {   
        Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        Matcher matcher = pattern.matcher(data);  
        return  matcher.matches();   
    }  
    /** 
     * 电话号码验证 
     * @param  data 
     * @return 验证通过返回true 
     */  
    public static boolean isPhone(String data) {   
        Pattern p1 = null,p2 = null;  
        Matcher matcher = null;  
        boolean flog = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(data.length() >9)  
        {   matcher = p1.matcher(data);  
        	flog = matcher.matches();    
        }else{  
        	matcher = p2.matcher(data);  
            flog = matcher.matches();   
        }    
        return flog;  
    } 
	
	
	/**
	 * 输入值必须介于 min 和 max 之间的字符串(汉字算2个字符)
	 * @param String
	 * @return boolean
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean rangeLength(String data,int min,int max){
		int length = getWordCountRegex(data);
		if(length > min && length < max ){
			return true;
		}
		return false;
	}
	
	/**
	 * 输入值不能大于 n的字符串(汉字算2个字符)
	 * @param String
	 * @return boolean
	 */
	public static boolean maxLength(String data,int n) {
		if(getWordCountRegex(data)>n){
			return false;
		}
		return true;
	}
	
	/**
	 * 输入值不能小于 n的字符串(汉字算2个字符)
	 * @param String
	 * @return boolean
	 */
	public static boolean minLength(String data,int n) {
		if(getWordCountRegex(data)<n){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 输入值必须介于 min 和 max 之间
	 * @param String
	 * @return boolean
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean range(String data,int min,int max){
		int length = data.length();
		if(length > min && length < max ){
			return true;
		}
		return false;
	}
	
	/**
	 * 输入值不能大于 n
	 * @param String
	 * @return boolean
	 */
	public static boolean max(String data,int n) {
		if(data.length()>n){
			return false;
		}
		return true;
	}
	
	/**
	 * 输入值不能小于 n
	 * @param String
	 * @return boolean
	 */
	public static boolean min(String data,int n) {
		if(data.length()<n){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否为空
	 * @param Object
	 * @return boolean
	 */
	public static boolean isNull(Object data) {
		if ("".equals(data) || data == null) {
			return true;
		}
		return false;
	}

	/**
	 * 验证电子邮箱格式
	 * @param String
	 * @return
	 */
	public static boolean email(String data) {
		Pattern emailPattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = emailPattern.matcher(data);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

//================================================================================	
	  
    public static void main( String[] args ) throws UnsupportedEncodingException  
    {  
    	System.out.println(isMobile("13911122321"));
        String str = "掱";  
        System.out.println(str.length());  
        System.out.println(getWordCount(str));  
        System.out.println(getWordCountRegex(str));  
        System.out.println(getWordCountCode(str,"GBK"));  
        System.out.println(getWordCountCode(str,"UTF-8"));  
    }  
      
    /*由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。 
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。 
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。 
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。*/ 
    /** 需要从头扫描根据字符的Ascii来获得具体的长度。 */
    public static int getWordCount(String s)  
    {  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);  
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  
                  
        }  
        return length;  
          
    }  
      
    /** 基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了*/  
    public static  int getWordCountRegex(String s)  
    {  
  
        s = s.replaceAll("[^\\x00-\\xff]", "**");  
        int length = s.length();  
        return length;  
    }  
      
    /** 按特定的编码格式获取长度 */  
    public static int getWordCountCode(String str, String code) throws UnsupportedEncodingException{  
        return str.getBytes(code).length;  
    }  
}
