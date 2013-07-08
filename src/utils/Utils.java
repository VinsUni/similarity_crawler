package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
	
	public static String GOOGLE_API_KEY = "AIzaSyDFBRMj_R_eCBebGLSFuKviKayRSod8XFE";
	public static String GOOGLE_PROJECT_ID = "002511786605603131953:yrq8myrfscq";
	
	public static String readFromWS(URL request) throws IOException{
		URLConnection yc = request.openConnection();
		yc.setRequestProperty("Accept-Charset", "UTF-8");
		String inputLine = "";
		InputStream is = yc.getInputStream();  
		inputLine = new String(Utils.getBytes(is), "UTF-8");
		is.close();
		return inputLine;
	}
	
	public static byte[] getBytes(InputStream is) throws IOException {
		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
			bos.close();
		}
		return buf;
	}


}
