package in.winwithweb.gst.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

public class CommonUtils {

	/*
	 * Use this method to get the image from the resource
	 */
	public static String getImgfromResource(String path) {
		String base64Encoded = null;
		Resource resource = new ClassPathResource(path);
		try {
			InputStream inputStream = resource.getInputStream();
			byte[] bFile = FileCopyUtils.copyToByteArray(inputStream);
			bFile = Base64.getEncoder().encode(bFile);
			base64Encoded = new String(bFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return base64Encoded;
	}
	
	/*
	 * 
	 */
	public static String getImgfromByteArray(byte[] imgdata) {
		String base64Encoded = null;
		byte[] encodeBase64 = Base64.getEncoder().encode(imgdata);
		try {
			base64Encoded = new String(encodeBase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64Encoded;
	}
	
	public static void main(String [] args) {
		System.out.println(getImgfromResource("/static/images/image-400x400.jpg"));
	}
}
