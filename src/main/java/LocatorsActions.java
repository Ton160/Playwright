import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorsActions {

    public static  void main (String[] args){
        try (Playwright playwright= Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            //page.navigate("https://www.google.com");

            // موقع تجريبي به Alert Button
            //page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
            // موقع تجريبي به Modal Dialog
            page.navigate("https://getbootstrap.com/docs/5.0/components/modal/");

            //inter a playwright java in search box
         /* Locator searchBox = page.getByRole(AriaRole.COMBOBOX);
           searchBox.fill("Playwright Java");
           searchBox.press("Enter");*/

         /*  Locator searchBox = page.locator("textarea[name='q']");
            searchBox.fill("Playwright Java");*/

           /* Locator searchBox = page.locator("textarea[name='q']");
            searchBox.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));*/

           /* Locator searchButton = page.locator("button[type='submit']");
            searchButton.click();*/
            //عملت scroll لحد ما لقيت الحاجة اللى عاوزها
           /* Locator scrollElement = page.locator("//*[@id=\"SIvCob\"]");
            scrollElement.scrollIntoViewIfNeeded();*/

           /* Locator hoverElement = page.locator("//*[@id=\"SIvCob\"]/a");
            hoverElement.hover();
            hoverElement.click();*/
           /* try {
                Thread.sleep(3000); // تأخير 3 ثوانٍ لمشاهدة النص أثناء الكتابة
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //عملتله Locator لكلمة العربية اللى هتظهر بعد ما ادوس على الانجليزية وبعدها هيقفل
            /*Locator dynamicElement = page.locator("//*[@id=\"SIvCob\"]/a");
            dynamicElement.waitFor(new Locator.WaitForOptions().setTimeout(5000));*/ // مع استخدام setTimeout بشكل صحيح

            /*Locator result = page.locator("h3:has-text('Playwright')");
            result.waitFor(new Locator.WaitForOptions().setTimeout(5000)); // انتظار 5 ثواني
            System.out.println("تم العثور على نتيجة البحث!");*/
// انتظر بعض الوقت لإتمام العمليات
           //ال locator بتاع كلمة العربية هنوصله بشكل صحيح( للتحقق من صحة النتايج assertion)
           /* Locator result= page.locator("//*[@id=\"SIvCob\"]/a");
            assertThat(result).isVisible();
            System.out.println("the locator بتاع كلمة العربية is achieved successfully");*/

            //  التعامل مع أي **Alert Popup**
/*page.onceDialog(dialog -> {System.out.println("popup Message or Alert ظهر" + dialog.message());
    dialog.accept();// يمكنك استخدام dialog.dismiss(); لإغلاقه بدون قبول
});
page.locator("button[onclick='jsAlert()']").click();*/


// التعامل مع Confirm (Yes/No)
/*page.onceDialog(dialog -> {System.out.println("confirmn ظهر" + dialog.message());

    dialog.accept();}); // الضغط على "OK"

            page.locator("button[onclick='jsConfirm()']").click();*/

            // الضغط على زر لفتح الـ Modal
            page.locator("button[data-bs-target='#exampleModal']:nth-of-type(1)").click();
            // التأكد أن الـ Modal ظهر
            Locator modal = page.locator("#exampleModal");
            modal.waitFor();
            System.out.println("✅ الـ Modal ظهر بنجاح!");


            // يمكنك إضافة تأخير أو انتظار لرؤية النتيجة إذا لزم الأمر


            Locator closeButton = page.locator("#exampleModal > div > div > div.modal-footer > button.btn.btn-secondary");
            if (closeButton.isVisible()) {
                closeButton.click();
                System.out.println("❌ تم إغلاق الـ Modal.");
            } else {
                System.out.println("❌ الزر غير مرئي.");
            }



            //فتح نافذة منبثقة جديدة والتحكم فيها
  /*Page popupPage=page.waitForPopup(() -> {
             page.evaluate("window.open('https://www.wikipedia.org', '_blank');");
         });
         if (popupPage !=null){
             popupPage.waitForLoadState();// انتظار تحميل الصفحة الجديدة بالكامل
             System.out.println("✅ تم فتح نافذة جديدة بعنوان: " + popupPage.title());
         } else {
             System.out.println("❌ لم يتم فتح النافذة المنبثقة!");}*/


            //إجراء سكرين شوت عند الفشل (Screenshots)
           /* try {
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("C:/Users/ASUS-/Desktop/screenshot.png")));
                System.out.println("📸 تم التقاط Screenshot بنجاح!");
            } catch (Exception e) {
                System.out.println("❌ فشل في التقاط Screenshot: " + e.getMessage());
            }/*


           /* page.navigate("https://www.google.com");
            System.out.println("✅ تم فتح صفحة Google بنجاح!");*/

     page.waitForTimeout(5000);

 }}}

