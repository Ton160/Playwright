import  com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;

public class API_POST_Response{
    public static void main(String[] args) {
        // إعداد Playwright
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // إعداد البيانات التي سيتم إرسالها بصيغة JSON
            String postData = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": \"1\"}";

            // إرسال الطلب POST إلى API مع بيانات JSON
            APIResponse response = page.request().post("https://jsonplaceholder.typicode.com/posts", RequestOptions.create()
                    .setHeader("Content-Type", "application/json")
                    .setData(postData)
            );

            // طباعة الرد
            String responseBody = response.text();
            System.out.println("Response Body: " + responseBody);

            // إغلاق المتصفح
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
