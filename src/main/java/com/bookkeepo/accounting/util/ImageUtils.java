package com.bookkeepo.accounting.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

/*
 * @author Ashutosh Anand
 *
 */
public class ImageUtils {

	private static final int IMG_WIDTH = 400;
	private static final int IMG_HEIGHT = 400;

	/*
	 * public static void main(String [] args){
	 * 
	 * try{
	 * 
	 * BufferedImage originalImage = ImageIO.read(new
	 * File("c:\\image\\mkyong.jpg")); int type = originalImage.getType() == 0?
	 * BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	 * 
	 * BufferedImage resizeImageJpg = resizeImage(originalImage, type);
	 * ImageIO.write(resizeImageJpg, "jpg", new File("c:\\image\\mkyong_jpg.jpg"));
	 * 
	 * BufferedImage resizeImagePng = resizeImage(originalImage, type);
	 * ImageIO.write(resizeImagePng, "png", new File("c:\\image\\mkyong_png.jpg"));
	 * 
	 * BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
	 * ImageIO.write(resizeImageHintJpg, "jpg", new
	 * File("c:\\image\\mkyong_hint_jpg.jpg"));
	 * 
	 * BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
	 * ImageIO.write(resizeImageHintPng, "png", new
	 * File("c:\\image\\mkyong_hint_png.jpg"));
	 * 
	 * }catch(IOException e){ System.out.println(e.getMessage()); }
	 * 
	 * }
	 */

	public static BufferedImage resizeImage(BufferedImage originalImage) {
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}
    
    
	public static BufferedImage convertToImage(MultipartFile file) throws IOException {
		InputStream in = new ByteArrayInputStream(file.getBytes());
		return ImageIO.read(in);
	}
    
	public static byte[] convertToArray(BufferedImage image, String contentType) throws IOException {
		byte[] imageInByte;

		String typeName = "jpg";
		if (contentType.equals(MediaType.IMAGE_PNG))
			typeName = "png";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, typeName, baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();

		return imageInByte;
	}
    
    public static boolean validateFile(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType.equals(MediaType.IMAGE_JPEG.toString())  || contentType.equals(MediaType.IMAGE_PNG.toString())) {
        	//if image is png or jpg allow to upload
            return true;
        }
        return false;
    }
    
    public static BufferedImage resizeImage2(BufferedImage img) { 
        Image tmp = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 
}