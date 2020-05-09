package com.bookkeepo.accounting.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CommonUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dbformat = new SimpleDateFormat("dd-MM-yyyy");

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
		if (imgdata != null) {
			byte[] encodeBase64 = Base64.getEncoder().encode(imgdata);
			try {
				base64Encoded = new String(encodeBase64);
			} catch (Exception e) {
			}
		}
		return base64Encoded;
	}

	/*
	 * 
	 */
	public static byte[] getByteArrayfromImage(String image) {
		byte[] decode = Base64.getDecoder().decode(image);
		return decode;
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

		StringBuilder numberInwords = new StringBuilder();
		if (num.contains("-")) {
			num = num.replace("-", "");
			numberInwords.append("Negative ");
		}

		if (num.split("\\.")[1].equals("00")) {
			return numberInwords
					.append((CommonUtils.convertNumberToWords(Integer.parseInt(num.split("\\.")[0])) + " rupees"))
					.toString();
		} else {
			String[] stringarray = num.split("\\.");
			return numberInwords
					.append((CommonUtils.convertNumberToWords(Integer.parseInt(stringarray[0])) + " rupees and"
							+ CommonUtils.convertNumberToWords(Integer.parseInt(stringarray[1])) + " Paisa"))
					.toString();
		}
	}

	public static String getUniqueID() {
		return UUID.randomUUID().toString();

	}

	public static boolean isValidEndDate(String startDate, String endDate) {
		boolean isValid = false;
		try {
			if (sdf.parse(startDate).before(sdf.parse(endDate)) || sdf.parse(startDate).equals(sdf.parse(endDate))) {
				isValid = true;
			}
		} catch (Exception e) {

		}
		return isValid;
	}

	public static String convertDateIntoFormat(String date) {
		String dbDate = null;
		try {
			dbDate = sdf.format(dbformat.parse(date));
		} catch (Exception e) {
		}

		return dbDate;

	}

	public static boolean isValidDate(String startDate, String endDate, String invoiceDate) {
		boolean isValid = false;
		try {
			if ((sdf.parse(invoiceDate).before(sdf.parse(endDate)) || sdf.parse(endDate).equals(sdf.parse(invoiceDate)))
					&& (sdf.parse(invoiceDate).after(sdf.parse(startDate))
							|| sdf.parse(invoiceDate).equals(sdf.parse(startDate)))) {
				isValid = true;
			}
		} catch (Exception e) {

		}
		return isValid;
	}

	public static boolean isPopulated(String string) {
		return string != null && !string.trim().equals("");
	}

	public static String nullToEmpty(String string) {
		String toReturn = string;
		if (string == null) {
			toReturn = "";
		}

		return toReturn;
	}

	public static boolean isTokenExpired(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECURITY_KEY).parseClaimsJws(token).getBody();
		Date expDate = claims.getExpiration();
		return expDate.before(new Date());
	}

	public static String generateToken(String id) {
		return Jwts.builder().setSubject(id)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECURITY_KEY).compact();
	}

	public static boolean isValidToken(String token, String dbToken) {
		boolean isValidToken = false;
		if (token != null && token.equals(dbToken) && !isTokenExpired(token)) {
			isValidToken = true;
		}

		return isValidToken;

	}

	public static String generateCommonTextPassword() {
		String pwString = generateRandomSpecialCharacters(2).concat(generateRandomNumbers(2))
				.concat(generateRandomAlphabet(2, true)).concat(generateRandomAlphabet(2, false))
				.concat(generateRandomCharacters(2));
		List<Character> pwChars = pwString.chars().mapToObj(data -> (char) data).collect(Collectors.toList());
		Collections.shuffle(pwChars);
		String password = pwChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}

	public static String generateRandomNumbers(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57).build();
		return pwdGenerator.generate(length);
	}

	public static String generateRandomSpecialCharacters(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
		return pwdGenerator.generate(length);
	}

	public static String generateRandomCharacters(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57).build();
		return pwdGenerator.generate(length);
	}

	public static String generateRandomAlphabet(int length, boolean lowerCase) {
		int low;
		int hi;
		if (lowerCase) {
			low = 97;
			hi = 122;
		} else {
			low = 65;
			hi = 90;
		}
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(low, hi).build();
		return pwdGenerator.generate(length);
	}
}