import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaywrightTestNGExample {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testGoogleTitle() {
        page.navigate("https://www.google.com");
        String title = page.title();
        Assert.assertEquals(title, "Google", "Title does not match!");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
