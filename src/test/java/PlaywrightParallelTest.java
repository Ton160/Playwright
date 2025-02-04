import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class PlaywrightParallelTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    @Parameters("browserType") // لاستقبال نوع المتصفح من TestNG
    public void setUp(String browserType) {
        playwright = Playwright.create();

        if (browserType.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else if (browserType.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testGoogleTitle() {
        page.navigate("https://www.google.com");
        String title = page.title();
        Assert.assertEquals(title, "Google", "Title does not match!");
    }

    @Test
    public void testWikipediaTitle() {
        page.navigate("https://www.wikipedia.org");
        String title = page.title();
        Assert.assertTrue(title.contains("Wikipedia"), "Title does not match!");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();

    }
}

