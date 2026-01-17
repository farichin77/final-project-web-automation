//package base;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.testng.ITestResult;
//
//import java.io.File;
//
//
//public class ExtentReportManager {
//
//    // Keep list of keys already added to avoid duplicates
//    private static final java.util.Set<String> INFO_KEYS = new java.util.HashSet<>();
//
//    private static ExtentReports extent;
//
//    // Perbaikan: Gunakan ThreadLocal agar objek 'test' tidak null/tertukar saat paralel
//    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
//    private static boolean baseEnvAdded = false;
//    // Hold current browser name for each thread
//    private static ThreadLocal<String> browserNameHolder = new ThreadLocal<>();
//
//    private static final String REPORT_PATH = "reports/web-b2b-dibimbing-automation-report.html";
//
//    public static void initReport() {
//        if (extent != null) return;
//
//        // Perbaikan: Buat folder 'reports' secara otomatis jika belum ada
//        File dir = new File("reports");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        // Remove previous report to avoid duplicated System Info rows when re-running multiple suites
//        File oldReport = new File(REPORT_PATH);
//        if (oldReport.exists()) {
//            oldReport.delete();
//        }
//
//        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
//
//        spark.config().setDocumentTitle("Automation Web B2B Dibimbing.id module employee and training");
//        spark.config().setReportName("Automation Selenium Execution Summary");
//        spark.config().setTheme(Theme.DARK);
//        spark.config().setEncoding("UTF-8");
//        spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");
//
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//
//        if (!baseEnvAdded) {
//            // Primary environment info (only added once)
//
//        addSystemInfo("Tester", "Ahmad Farichin");
//        addSystemInfo("Tool", "Selenium WebDriver");
//        addSystemInfo("Framework", "TestNG");
//        addSystemInfo("OS", System.getProperty("os.name"));
//        addSystemInfo("Java", System.getProperty("java.version"));
//            baseEnvAdded = true;
//        }
//
//    }
//
//    // ================= TEST HANDLING =================
//
//    public static void setCurrentBrowser(String browser) {
//        browserNameHolder.set(browser);
//    }
//
//    public static void createTest(String testName, String browser) {
//        // Simpan browser di ThreadLocal agar bisa diakses jika diperlukan
//        browserNameHolder.set(browser);
//        ExtentTest t = extent.createTest(testName)
//                .assignCategory(browser);
//        methodTest.set(t);
//    }
//
//    // ================= ACCESSOR =================
//
//    public static ExtentTest getTest() {
//        return methodTest.get();
//    }
//
//    // ================= RESULT LOGGING =================
//
//    public static void logResult(ITestResult result) {
//        ExtentTest currentTest = getTest();
//        if (currentTest == null) return;
//
//        if (result.getStatus() == ITestResult.SUCCESS) {
//            currentTest.pass("Test Passed");
//            return;
//        }
//
//        if (result.getStatus() == ITestResult.FAILURE) {
//            Throwable throwable = result.getThrowable();
//            currentTest.fail("Test Failed");
//
//            if (throwable instanceof AssertionError) {
//                String message = throwable.getMessage();
//                String expected = extractExpected(message);
//                String actual = extractActual(message);
//
//                currentTest.fail("Assertion Error Detail:");
//                currentTest.fail(
//                        MarkupHelper.createTable(new String[][]{
//                                {"Expected", expected},
//                                {"Actual", actual}
//                        })
//                );
//            } else {
//                currentTest.fail(throwable);
//            }
//            return;
//        }
//
//        if (result.getStatus() == ITestResult.SKIP) {
//            currentTest.skip("Test Skipped");
//        }
//    }
//
//    // ================= ASSERT PARSER =================
//
//    private static String extractExpected(String message) {
//        try {
//            // Menangani format standar TestNG: expected [A] but found [B]
//            return message.split("expected \\[")[1].split("]")[0];
//        } catch (Exception e) {
//            return "Check message: " + message;
//        }
//    }
//
//    private static String extractActual(String message) {
//        try {
//            return message.split("but found \\[")[1].split("]")[0];
//        } catch (Exception e) {
//            return "N/A";
//        }
//    }
//
//    // ================= SYSTEM INFO =================
//
//    public static void addSystemInfo(String key, String value) {
//        if (extent != null && !INFO_KEYS.contains(key)) {
//            extent.setSystemInfo(key, value);
//            INFO_KEYS.add(key);
//        }
//    }
//
//    // ================= FLUSH =================
//
//    public static void flush() {
//        if (extent != null) {
//            extent.flush();
//            // Membersihkan ThreadLocal setelah flush
//            methodTest.remove();
//        }
//    }
//}

package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ExtentReportManager {

    private static final Set<String> INFO_KEYS = new HashSet<>();
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    private static ThreadLocal<String> browserNameHolder = new ThreadLocal<>();
    private static boolean baseEnvAdded = false;

    private static final String REPORT_PATH = "reports/web-b2b-dibimbing-automation-report.html";

    public static synchronized void initReport() {
        if (extent != null) return;

        File dir = new File("reports");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Kunci: Pastikan file lama benar-benar bersih agar visual tidak corrupt
        File oldReport = new File(REPORT_PATH);
        if (oldReport.exists()) {
            oldReport.delete();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
        spark.config().setDocumentTitle("Automation Web B2B Dibimbing.id");
        spark.config().setReportName("Automation Selenium Execution Summary");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("UTF-8");
        spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        if (!baseEnvAdded) {
            addSystemInfo("Tester", "Ahmad Farichin");
            addSystemInfo("Tool", "Selenium WebDriver");
            addSystemInfo("Framework", "TestNG");
            addSystemInfo("OS", System.getProperty("os.name"));
            addSystemInfo("Java", System.getProperty("java.version"));
            baseEnvAdded = true;
        }
    }

    public static synchronized void addSystemInfo(String key, String value) {
        if (extent != null && !INFO_KEYS.contains(key)) {
            extent.setSystemInfo(key, value);
            INFO_KEYS.add(key);
        }
    }

    public static void createTest(String testName, String browser) {
        if (extent == null) initReport();

        browserNameHolder.set(browser);
        // AUTHOR DIHAPUS sesuai permintaan
        ExtentTest t = extent.createTest(testName)
                .assignCategory(browser);
        methodTest.set(t);
    }

    public static ExtentTest getTest() {
        return methodTest.get();
    }

    public static void logResult(ITestResult result) {
        ExtentTest currentTest = getTest();
        if (currentTest == null) return;

        if (result.getStatus() == ITestResult.SUCCESS) {
            currentTest.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            handleFailure(result, currentTest);
        } else if (result.getStatus() == ITestResult.SKIP) {
            currentTest.skip("Test Skipped: " + result.getThrowable());
        }
    }

    private static void handleFailure(ITestResult result, ExtentTest currentTest) {
        Throwable throwable = result.getThrowable();
        if (throwable instanceof AssertionError) {
            String message = throwable.getMessage();
            currentTest.fail("Assertion Error Detail:");
            currentTest.fail(MarkupHelper.createTable(new String[][]{
                    {"Expected", extractExpected(message)},
                    {"Actual", extractActual(message)}
            }));
        } else {
            currentTest.fail(throwable);
        }
    }

    private static String extractExpected(String message) {
        try { return message.split("expected \\[")[1].split("]")[0]; }
        catch (Exception e) { return "N/A"; }
    }

    private static String extractActual(String message) {
        try { return message.split("but found \\[")[1].split("]")[0]; }
        catch (Exception e) { return "N/A"; }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            // Penting: jangan bersihkan ThreadLocal di sini jika masih ada test yang berjalan di suite lain
        }
    }

    // Tambahkan ini jika ingin membersihkan manual setelah suite benar-benar selesai
    public static void unload() {
        methodTest.remove();
        browserNameHolder.remove();
    }
}
