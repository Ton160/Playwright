import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Paths;


public class Login_Test_with_Test_Driven {


    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        // بدء التتبع وحفظ اللقطات
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)); // يساعد في تسجيل الشيفرة المصدرية للأحداث

    }

    // Data Provider: إرجاع البيانات (اسم المستخدم وكلمة المرور)
    @DataProvider(name = "loginData")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!"},   // مجموعة بيانات 1
                {"user2", "password2"},   // مجموعة بيانات 2
                {"user3", "password3"}    // مجموعة بيانات 3
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        // الانتقال إلى صفحة تسجيل الدخول
        page.navigate("https://the-internet.herokuapp.com/login");

        // ملء حقول اسم المستخدم وكلمة المرور
        page.fill("#username", username);
        page.fill("#password", password);

        // الضغط على زر تسجيل الدخول
        page.click("#login > button");

        // التحقق من تسجيل الدخول بنجاح عبر وجود رسالة الترحيب

        page.waitForSelector("#content > div > a", new Page.WaitForSelectorOptions().setTimeout(5000));
        boolean isLoginSuccessful = page.isVisible("#content > div > a");
        Assert.assertTrue(isLoginSuccessful, "Login failed for user: " + username);
    }

    @AfterClass
    public void teardown() {
        // إيقاف التتبع وحفظه في ملف trace.zip
       context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
        // إغلاق المتصفح بعد الانتهاء
        browser.close();
        playwright.close();
    }
}
