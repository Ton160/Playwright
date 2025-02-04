import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadExample {
    public static void main(String[] args) {
        try (Playwright playwright= Playwright.create()){
            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page=browser.newPage();
            // الانتقال إلى صفحة التحميل
            page.navigate("https://practice.expandtesting.com/download");
            // انتظار بدء عملية التحميل
            Download download=page.waitForDownload(()-> {
// الضغط على زر تحميل الملف
                page.locator("body > main > div.page-layout > div > div > div:nth-child(3) > a").click();
            });
            // تحديد مسار حفظ الملف
            Path savePath= Paths.get("C:\\Users\\ASUS-\\Downloads\\" + download.suggestedFilename());
            download.saveAs(savePath);

            // طباعة رسالة النجاح
            System.out.println("File downloaded successfully to: " + savePath);

            // الانتظار قليلاً لرؤية النتيجة
            page.waitForTimeout(5000);

            // إغلاق المتصفح
           // browser.close();
}}}
