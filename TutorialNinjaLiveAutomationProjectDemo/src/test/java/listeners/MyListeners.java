package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.CommonUtilities;
import utils.ElementUtilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extentReport = CommonUtilities.getExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + ":- Test Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.pass(result.getName() + ":- Test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.fail(result.getName() + ":- Test got Failed");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String destinationScreenShotPath = new ElementUtilities(driver)
				.captureScreenshotAndReturnPath(result.getTestName(), driver);
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.skip(result.getName() + ":- Test got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		File extentReportFilePath = new File(System.getProperty("user.dir") + "\\Reports\\TNExtentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFilePath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
