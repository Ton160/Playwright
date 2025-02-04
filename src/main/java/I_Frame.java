import com.microsoft.playwright.*;

public class I_Frame {
    public static void main (String[] args) {
        try (Playwright playwright=Playwright.create()){
            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // انتقل إلى الصفحة التي تحتوي على Iframe
           // page.navigate("https://the-internet.herokuapp.com/iframe");

            // الانتقال إلى الصفحة اللي فيها الـ nested iframes
            page.navigate("https://the-internet.herokuapp.com/nested_frames");

            // الوصول إلى الـ Iframe عن طريق اسمه أو الـ Locator الخاص به
          //  FrameLocator iframe=page.frameLocator("#mce_0_ifr");

            // الانتقال إلى الـ Parent iframe
            FrameLocator parentFrameLocator= (FrameLocator) page.frameLocator("frame[name='frame-top']");
// الدخول إلى الـ Child iframe داخل الـ Parent
            FrameLocator childFrame= (FrameLocator) parentFrameLocator.frameLocator("frame[name='frame-left']");

            // التفاعل مع عنصر داخل الـ Child iframe
String text = childFrame.locator("body").textContent();
            System.out.println("Text inside nested iframe: " + text);


            // التفاعل مع عنصر داخل الـ Iframe (إدخال نص في الـ Editor)
//iframe.locator("#tinymce > p").dblclick();

// التفاعل مع زر أو رابط خارج الـ Iframe بعد التعامل معه
           // page.locator("#page-footer > div > div > a").click();

            // انتظر قليلًا لرؤية النتيجة
            page.waitForTimeout(5000);

            System.out.println("تم التعامل مع الـ Iframe بنجاح!");



    }}}