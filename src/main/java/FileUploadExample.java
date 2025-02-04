import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class FileUploadExample {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            // انتقل إلى الصفحة التي تحتوي على عنصر رفع الملفات
            page.navigate(" https://practice.expandtesting.com/upload#:~:text=A%20file%20upload%20feature%20that%20can%20be%20used,and%20drop%20a%20file%20into%20the%20area%20below.");

            // حدد عنصر رفع الملفات
            Locator fileInput = page.locator("#fileInput");
            String filePath = "C:\\Users\\ASUS-\\Downloads\\ISTQB_CTFL_v4.0_Sample-Exam-B-Questions_v1.6 (1).pdf";

            // قم بتحميل الملف
           fileInput.setInputFiles(Paths.get(filePath));

            // تأكد من تحميل الملف (اختياري)
            System.out.println("File uploaded successfully!");
            page.waitForTimeout(5000);

            // إغلاق المتصفح
           // browser.close();
        }}}


















