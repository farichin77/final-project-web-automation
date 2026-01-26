# Web B2B Dibimbing.id Automation

Proyek otomatisasi pengujian untuk platform Web B2B Dibimbing.id. Framework ini dibangun menggunakan Selenium WebDriver, TestNG, dan implementasi Page Object Model (POM) yang tangguh.

## 🛠 Tech Stack
*   **Language:** Java 17
*   **Automation Tool:** Selenium WebDriver (v4.27.0)
*   **Test Runner:** TestNG (v7.10.2)
*   **Reporting:** Extent Reports (v5.1.1)
*   **Build Tool:** Gradle
*   **Dependency Injection:** WebDriverManager
*   **CI/CD:** GitHub Actions (Sequential execution with Slack Notifications)

## 📋 Fitur Utama
1.  **Cross-Browser Testing**: Mendukung eksekusi di Chrome, Firefox, dan Edge.
2.  **Sequential Data & Reporting**:
    *   Test dijalankan secara berurutan di lokal maupun CI untuk memastikan stabilitas.
    *   Laporan Extent Report digabung menjadi satu file terpadu.
3.  **Robust Slack Notification**:
    *   Sistem pelaporan Slack menggunakan Python parser yang akurat.
    *   Menampilkan total statistik (Pass/Fail) gabungan dari semua browser.
4.  **Stability First**:
    *   Menggunakan `Explicit Wait` (20 detik global timeout).
    *   Isolasi data test menggunakan suffix nama browser (misal: "Training Chrome").

## 🚀 Cara Menjalankan Test

### 1. Persiapan
Pastikan Anda memiliki:
*   JDK 17 terinstal.
*   Browser Chrome, Firefox, dan Edge.
*   File `.env` di root project (berisi credential test).

### 2. Menjalankan Semua Browser (Sequential)
Perintah ini akan menjalankan Chrome, kemudian Edge, lalu Firefox secara berurutan sesuai konfigurasi `testng.xml`.

```bash
# Windows
./gradlew clean test

# Mac/Linux
chmod +x gradlew
./gradlew clean test
```

### 3. Menjalankan Browser Tertentu (Override)
Jika Anda hanya ingin menjalankan test di satu browser spesifik (berguna untuk debugging lokal):

```bash
# Format: -Pbrowser=<nama_browser>
./gradlew clean test -Pbrowser=firefox
./gradlew clean test -Pbrowser=edge
```

## 📂 Struktur Project
```
src
├── main
│   ├── java
│   │   ├── core          # BasePage, DriverManager
│   │   ├── pages         # Page Objects (Dashboard, Employee, Training, dll)
│   │   └── utils         # ConfigReader, ExcelDataProvider
├── test
│   ├── java
│   │   ├── core          # BaseTest, Listeners, RetryAnalyzer
│   │   └── tests         # Test Classes (LoginTest, EmployeeTest, dll)
│   └── resources
│       ├── testng.xml    # Suite configuration
│       └── ...           # Test Data (Excel)
```

## 📊 Laporan (Reporting)
Setelah test selesai dijalankan:
1.  Buka folder `reports/`.
2.  Cari file `Web Automation Report_<timestamp>.html`.
3.  Buka di browser untuk melihat detail langkah dan screenshot.

## 🤖 CI/CD (GitHub Actions)
Pipeline dikonfigurasi di `.github/workflows/ci.yml`.
*   **Trigger**: Setiap push ke branch `main`.
*   **Flow**:
    1.  Install Java & Browser (Chrome, Firefox, Edge).
    2.  Jalankan test secara sequential (Chrome -> Edge -> Firefox).
    3.  Parse hasil test menggunakan Python script.
    4.  Kirim notifikasi ke Slack channel.
    5.  Upload artifacts (Extent Report & TestNG Results).
