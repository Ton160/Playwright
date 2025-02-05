import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Listeners(org.testng.reporters.FailedReporter.class)
public class PlaywrightAllureTest {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    @BeforeClass
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        // بدء التتبع في Playwright
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @Test
    public void testExample() {
        page.navigate("https://example.com");
        page.locator("body > div > h2").screenshot();
        assert page.locator("body > div > h1").textContent().equals("Example Domain"); // هذا الاختبار سيفشل لتجربة التقاط Allure
    }

    @AfterMethod
    public void afterMethod(@org.jetbrains.annotations.NotNull ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                // التقاط لقطة شاشة عند الفشل
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

                // إضافة لقطة الشاشة إلى تقرير Allure
                Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));

                // حفظ لقطة الشاشة محليًا (اختياري)
                FileUtils.writeByteArrayToFile(new File("failed-screenshot.png"), screenshot);

                // إيقاف التتبع وحفظه في ملف trace.zip
                context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));

                // إضافة ملف التتبع إلى تقرير Allure
                Allure.addAttachment("Playwright Trace", "application/zip",
                        new ByteArrayInputStream(FileUtils.readFileToByteArray(new File("trace.zip"))), "zip");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public static void tearDown() {
        browser.close();
        playwright.close();
    }
}