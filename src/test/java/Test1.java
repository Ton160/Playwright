import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Test1 {
    Playwright playwright;
    Browser browser;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Test
    public void test1() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://example.com");
        System.out.println("Test 1 - Page Title: " + page.title());
        context.close();  // إغلاق السياق بعد الاختبار
    }

    @AfterMethod
    public void teardown() {
        browser.close();  // إغلاق المتصفح بعد الاختبار
        playwright.close();
    }
}

