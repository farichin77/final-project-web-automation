package core;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // WAJIB: Inisialisasi report sebelum test pertama dimulai
        ExtentReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty("browser", "chrome");
        }
        
        // Determine Module based on package name
        String packageName = result.getMethod().getRealClass().getPackage().getName();
        String moduleName = "Unknown Module";
        if (packageName.contains("employee")) {
            moduleName = "Employee Module";
        } else if (packageName.contains("training")) {
            moduleName = "Training Module";
        } else if (packageName.contains("login")) {
            moduleName = "Login Module";
        }

        ExtentReportManager.createTest(result.getMethod().getMethodName(), browser); // Browser still needed for thread safety map?
        // Override category with Module
        ExtentReportManager.getTest().assignCategory(moduleName);
        ExtentReportManager.getTest().info("Test started with browser: " + browser);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.logResult(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // AMBIL SCREENSHOT PERTAMA KALI (secepat mungkin agar tidak kehilangan toast/error message)
        String screenshotPath = ScreenshotUtil.takeScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName()
        );

        // Baru log hasil test
        ExtentReportManager.logResult(result);

        if (screenshotPath != null) {
            try {
                ExtentReportManager.getTest().fail(
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(screenshotPath)
                                .build()
                );
            } catch (Exception e) {
                ExtentReportManager.getTest()
                        .warning("Screenshot could not be attached");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.logResult(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        // Retrieve the browser parameter for this <test> tag
        String browser = context.getCurrentXmlTest().getParameter("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "unknown-browser";
        }



        ExtentReportManager.flush();
    }
}
