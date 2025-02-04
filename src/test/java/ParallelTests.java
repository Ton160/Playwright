import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParallelTests {
    Playwright playwright;
    Browser browser;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Test(threadPoolSize = 2, invocationCount = 2)
    public void test1() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://example.com");
        System.out.println("Test 1 - Page Title: " + page.title());
        page.waitForTimeout(5000);
        context.close();  // إغلاق السياق بعد الاختبار
    }

    @Test
    public void test2() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://google.com");
        System.out.println("Test 2 - Page Title: " + page.title());
        page.waitForTimeout(5000);
        context.close();  // إغلاق السياق بعد الاختبار
    }

    @AfterMethod
    public void teardown() {
        browser.close();  // إغلاق المتصفح بعد كل اختبار

    }}

