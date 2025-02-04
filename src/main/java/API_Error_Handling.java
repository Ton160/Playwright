import com.microsoft.playwright.*;

public class API_Error_Handling {
    public static void main(String[] args) {
        // إعداد Playwright
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // إرسال طلب GET مع معاملة الأخطاء
            APIResponse response = page.request().get("https://jsonplaceholder.typicode.com/invalid-url");

            // التحقق من حالة الاستجابة (مثل 404 أو 500)
            int statusCode = response.status();
            if (statusCode == 404) {
                System.out.println("Error: Page Not Found (404)");
            } else if (statusCode == 500) {
                System.out.println("Error: Server Issue (500)");
            } else {
                // في حال كانت الاستجابة سليمة
                String responseBody = response.text();
                System.out.println("Response Body: " + responseBody);
            }

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


