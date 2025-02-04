import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONObject;

public class API_Data_Validation {
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

            // التحقق من الاستجابة
            String responseBody = response.text();
            JSONObject jsonResponse = new JSONObject(responseBody);

            // التحقق من القيم المستلمة
            if (jsonResponse.getString("title").equals("foo")) {
                System.out.println("Title is correct.");
            } else {
                System.out.println("Title is incorrect.");
            }

            // طباعة الرد
            System.out.println("Response Body: " + responseBody);

            // إغلاق المتصفح
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}