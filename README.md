# Web B2B Dibimbing.id Test Automation

Comprehensive test automation framework for the Web B2B Dibimbing.id platform. Built with **Selenium WebDriver**, **TestNG**, and **Page Object Model (POM)** pattern for robust, maintainable, and scalable test automation.

---

## 🛠️ Technology Stack

| Component | Version |
|-----------|---------|
| **Java** | 17 |
| **Selenium WebDriver** | 4.27.0 |
| **TestNG** | 7.10.2 |
| **Extent Reports** | 5.1.1 |
| **Build Tool** | Gradle |
| **Browser Driver Manager** | WebDriverManager 5.9.2 |
| **CI/CD** | GitHub Actions with Slack Notifications |

---

## ✨ Key Features

### 1. **Cross-Browser Testing**
- Automated execution across **Chrome**, **Firefox**, and **Edge** browsers
- Sequential execution ensures data consistency and stability
- Independent test data sets per browser (isolation strategy)

### 2. **Page Object Model (POM)**
- Clean separation of test logic and UI elements
- Reusable page components across multiple test cases
- Easy maintenance and scalability

### 3. **Advanced Reporting**
- **Extent Reports** with detailed step-by-step execution logs
- Automatic screenshot capture on test failure
- Consolidated HTML reports with timestamps
- Real-time result aggregation from multiple test suites

### 4. **CI/CD Integration**
- Automated test execution on every push to `main` branch
- **Slack notifications** with real-time test results
- Accurate XML parsing and statistics aggregation
- Automatic artifact upload to GitHub Actions

### 5. **Stability & Reliability**
- Explicit waits with **20-second global timeout**
- Robust element detection strategies
- Automatic browser driver installation and management
- Exception handling and retry mechanisms

---

## 🚀 Quick Start

### Prerequisites
Ensure you have the following installed:
- **Java Development Kit (JDK)** 17 or higher
- **Gradle** (included via `gradlew`)
- **Browsers**: Chrome, Firefox, Edge (auto-installed if missing)
- **Environment file** (`.env`) at project root with test credentials

### Environment Setup
Create a `.env` file in the project root with the following variables:
```env
BASE_URL=https://your-app-url
LOGIN_EMAIL=test.user@example.com
LOGIN_PASSWORD=your_secure_password
```

### Running Tests

#### Option 1: Run All Browsers (Sequential)
Execute tests across all configured browsers in sequence:
```bash
# Windows
./gradlew clean test

# Mac/Linux
chmod +x gradlew && ./gradlew clean test
```

#### Option 2: Run Specific Browser
Override default browser configuration for local debugging:
```bash
# Run Firefox only
./gradlew clean test -Pbrowser=firefox

# Run Chrome only
./gradlew clean test -Pbrowser=chrome

# Run Edge only
./gradlew clean test -Pbrowser=edge
```

---

## 📁 Project Structure

```
Final-project-automation-web/
│
├── src/
│   ├── main/java/
│   │   ├── core/
│   │   │   ├── BasePage.java           # Base page class with common methods
│   │   │   └── DriverManager.java      # WebDriver lifecycle management
│   │   ├── pages/                       # Page Object classes
│   │   │   ├── login/
│   │   │   ├── dashboard/
│   │   │   ├── employee/
│   │   │   └── training/
│   │   └── utils/
│   │       ├── ConfigReader.java       # .env file reader
│   │       ├── ExcelDataProvider.java  # Test data from Excel
│   │       ├── ExcelReader.java        # Excel file parsing
│   │       ├── DownloadUtil.java       # File download utilities
│   │       └── ScreenshotUtil.java     # Screenshot capture
│   │
│   └── test/java/
│       ├── core/
│       │   ├── BaseTest.java           # Common test setup & teardown
│       │   ├── ExtentReportManager.java # Report configuration
│       │   └── ExtentTestListener.java # TestNG listener for reporting
│       │
│       └── tests/                       # Test suites
│           ├── login/
│           ├── employee/
│           └── training/
│
├── build.gradle                 # Gradle dependencies & build config
├── .github/workflows/
│   └── ci.yml                   # GitHub Actions CI/CD pipeline
├── testng.xml                   # TestNG suite configuration
└── README.md                    # This file
```

---

## 📊 Test Reports

### Local Execution
After tests complete, find reports in:
- **Extent HTML Report**: `reports/web-b2b-dibimbing-automation-report.html`
- **TestNG XML Results**: `build/test-results/test/TEST-*.xml`
- **Screenshots**: `reports/screenshots/`

### CI/CD (GitHub Actions)
Reports are automatically:
1. Generated after test execution
2. Uploaded as GitHub artifacts
3. Summarized in Slack notification
4. Available for download from Actions tab

---

## 🤖 CI/CD Pipeline (GitHub Actions)

### Workflow Overview
Triggered on every `push` to `main` branch:

1. **Setup Phase**
   - Install Java 17
   - Download and configure browsers (Chrome, Firefox, Edge)
   - Setup Gradle cache

2. **Test Execution Phase**
   - Execute tests sequentially across all browsers
   - Generate TestNG XML reports
   - Create consolidated Extent Reports

3. **Reporting Phase**
   - Parse TestNG XML results with Python
   - Calculate aggregated statistics (Total, Passed, Failed, Skipped)
   - Send formatted Slack notification with results

4. **Artifact Storage**
   - Upload consolidated Extent Report
   - Upload TestNG XML results
   - Preserve test execution history

### Pipeline Configuration
See [.github/workflows/ci.yml](.github/workflows/ci.yml) for detailed workflow configuration.

### GitHub Secrets Required
Configure these in your repository settings:
```
BASE_URL              # Application URL
LOGIN_EMAIL           # Test user email
LOGIN_PASSWORD        # Test user password
SLACK_WEBHOOK_URL     # Slack channel webhook for notifications
```

---

## 📱 Slack Notifications

Real-time test result notifications are posted to your Slack channel for **each browser individually** plus a **consolidated summary**:

### Per-Browser Notifications
Individual notifications are sent for each browser tested:
- 🔵 **Chrome Results** - Separate notification with Chrome test stats
- 🟠 **Firefox Results** - Separate notification with Firefox test stats
- 🟢 **Edge Results** - Separate notification with Edge test stats

### Notification Format (Per Browser)
```
🔵 Web B2B Dibimbing.id - Chrome Automation Results

Result: PASSED (or FAILED)
Total Tests: 15
Passed: 15
Failed: 0
Skipped: 0
```

### Consolidated Summary
After all individual browser notifications, a final summary is sent:
```
📊 Web B2B Dibimbing.id - Consolidated Summary

Overall Result: PASSED (or OVERALL FAILED)
Total Tests (All Browsers): 45
Total Passed: 45
Total Failed: 0
Total Skipped: 0
Browsers Tested: 3
```

### Color Indicators
- 🟢 **Green**: All tests passed
- 🔴 **Red**: One or more tests failed
- 🟡 **Yellow**: No tests were executed

---

## 🔧 Common Commands

### Clean Build
```bash
./gradlew clean
```

### Build Only (No Tests)
```bash
./gradlew build
```

### View Gradle Tasks
```bash
./gradlew tasks
```

### Check Dependencies
```bash
./gradlew dependencies
```

---

## 📝 Best Practices

1. **Always use Page Object Model** - Create page classes for new modules
2. **Explicit Waits Only** - Avoid `Thread.sleep()` in production code
3. **Data Isolation** - Use unique identifiers (browser name suffix) for test data
4. **Meaningful Test Names** - Use clear, descriptive test method names
5. **Screenshot on Failure** - Leverage automatic failure captures
6. **Modular Test Cases** - Keep tests independent and isolated
7. **Review Reports** - Always check detailed Extent Reports for debugging

---

## ⚠️ Troubleshooting

### Tests Show "0" Results in Slack
- Verify `testng.xml` is properly configured
- Check that test suites are correctly named
- Ensure XML files are generated in `build/test-results/`

### Browser Not Launching
- Run `./gradlew clean` to reset WebDriverManager cache
- Check system dependencies (e.g., required libraries)
- Verify Chrome/Firefox/Edge are installed or accessible

### Slack Notification Not Received
- Verify `SLACK_WEBHOOK_URL` secret is configured
- Check webhook URL format in GitHub Secrets
- Review GitHub Actions logs for curl command errors

---

## 📚 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG User Guide](https://testng.org/doc/)
- [Extent Reports](https://extentreports.com/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## 👥 Team

Developed as part of **Dibimbing.id Bootcamp** - Final Project Automation
