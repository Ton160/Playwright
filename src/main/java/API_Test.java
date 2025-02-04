import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;

import java.util.HashMap;
import java.util.Map;

public class API_Test {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        // إرسال طلب GET
        APIResponse apiResponse = page.request().get("https://jsonplaceholder.typicode.com/posts/1");
        // إعداد البيانات التي سيتم إرسالها
        String postData = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": \"1\"}";

        // إرسال الطلب إلى API

        // إرسال طلب POST

        System.out.println("Status: " + apiResponse.status());
        System.out.println("Body: " + apiResponse.body());
        // طباعة الرد

}}
