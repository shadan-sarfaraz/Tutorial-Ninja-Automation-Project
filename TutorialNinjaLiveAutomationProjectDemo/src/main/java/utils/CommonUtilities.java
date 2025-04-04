package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtilities {

	public static Properties loadPropertiesFile() {
		Properties prop = new Properties();
		try {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static Properties storePropertiesFile(Properties prop) {

		try {
			FileWriter fw = new FileWriter(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			prop.store(fw, "Updated Properties file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static int convertToInteger(String text) {
		return Integer.parseInt(text);
	}

	public static boolean areItemsInListAreInAscendingOrder(List<String> list) {
		List<String> sortedList = list;
		Collections.sort(sortedList);
		return list.equals(sortedList);
	}

	public static String generateBrandNewEmail() {

		Date date = new Date();
		String dateString = date.toString();
		String dateStringWithoutSpaces = dateString.replaceAll("\\s", "");
		String dateStringWithoutSpacesAndColons = dateStringWithoutSpaces.replaceAll("\\:", "");
		String brandNewEmail = dateStringWithoutSpacesAndColons + "@gmail.com";
		return brandNewEmail;

	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {

		BufferedImage bufferedActualImage = ImageIO.read(new File(actualImagePath));
		BufferedImage bufferedExpectedImage = ImageIO.read(new File(expectedImagePath));
		ImageDiffer differ = new ImageDiffer();
		ImageDiff imageDiff = differ.makeDiff(bufferedExpectedImage, bufferedActualImage);
		return imageDiff.hasDiff();
	}

	public static void takeScreenshot(WebDriver driver, String screenshotPath) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(srcScreenshot, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTextFromMessage(Message message) throws Exception {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("text/html")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		StringBuilder result = new StringBuilder();
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.isMimeType("text/html")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
			}
		}
		return result.toString();
	}

}