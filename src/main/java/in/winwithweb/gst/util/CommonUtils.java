package in.winwithweb.gst.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

public class CommonUtils {

	private static String[] units = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight",
			" Nine" };
	private static String[] teen = { " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
			" Seventeen", " Eighteen", " Nineteen" };
	private static String[] tens = { " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
			" Ninety" };
	private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh", " Crore" };

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

	public static String convertNumberToWords(int n) {

		String input = numToString(n);
		String converted = "";
		int pos = 1;
		boolean hun = false;
		while (input.length() > 0) {
			if (pos == 1) // TENS AND UNIT POSITION
			{
				if (input.length() >= 2) // TWO DIGIT NUMBERS
				{
					String temp = input.substring(input.length() - 2, input.length());
					input = input.substring(0, input.length() - 2);
					converted += digits(temp);
				} else if (input.length() == 1) // 1 DIGIT NUMBER
				{
					converted += digits(input);
					input = "";
				}
				pos++;
			} else if (pos == 2) // HUNDRED POSITION
			{
				String temp = input.substring(input.length() - 1, input.length());
				input = input.substring(0, input.length() - 1);
				if (converted.length() > 0 && digits(temp) != "") {
					converted = (digits(temp) + maxs[pos] + "") + converted;
					hun = true;
				} else {
					if (digits(temp) == "")
						;
					else
						converted = (digits(temp) + maxs[pos]) + converted;
					hun = true;
				}
				pos++;
			} else if (pos > 2) // REMAINING NUMBERS PAIRED BY TWO
			{
				if (input.length() >= 2) // EXTRACT 2 DIGITS
				{
					String temp = input.substring(input.length() - 2, input.length());
					input = input.substring(0, input.length() - 2);
					if (!hun && converted.length() > 0)
						converted = digits(temp) + maxs[pos] + "" + converted;
					else {
						if (digits(temp) == "")
							;
						else
							converted = digits(temp) + maxs[pos] + converted;
					}
				} else if (input.length() == 1) // EXTRACT 1 DIGIT
				{
					if (!hun && converted.length() > 0)
						converted = digits(input) + maxs[pos] + "" + converted;
					else {
						if (digits(input) == "")
							;
						else
							converted = digits(input) + maxs[pos] + converted;
						input = "";
					}
				}
				pos++;
			}
		}
		return converted;
	}

	private static String digits(String temp) // TO RETURN SELECTED NUMBERS IN WORDS
	{
		String converted = "";
		for (int i = temp.length() - 1; i >= 0; i--) {
			int ch = temp.charAt(i) - 48;
			if (i == 0 && ch > 1 && temp.length() > 1)
				converted = tens[ch - 2] + converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
			else if (i == 0 && ch == 1 && temp.length() == 2) // IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
			{
				int sum = 0;
				for (int j = 0; j < 2; j++)
					sum = (sum * 10) + (temp.charAt(j) - 48);
				return teen[sum - 10];
			} else {
				if (ch > 0)
					converted = units[ch] + converted;
			} // IF SINGLE DIGIT PROVIDED
		}
		return converted;
	}

	private static String numToString(int x) // CONVERT THE NUMBER TO STRING
	{
		String num = "";
		while (x != 0) {
			num = ((char) ((x % 10) + 48)) + num;
			x /= 10;
		}
		return num;
	}
	
	public static String numberConverter(String num) {

		if (num.split("\\.")[1].equals("00")) {
			return (CommonUtils.convertNumberToWords(Integer.parseInt(num.split("\\.")[0]))+" rupees");
		} else {
			String[] stringarray = num.split("\\.");
			return (CommonUtils.convertNumberToWords(Integer.parseInt(stringarray[0])) + " rupees and"
					+ CommonUtils.convertNumberToWords(Integer.parseInt(stringarray[1])) + " Paisa");
		}
	}
}