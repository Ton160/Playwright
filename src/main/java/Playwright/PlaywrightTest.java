package Playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest{
    public static  void main (String[] args){
        try (Playwright playwright= Playwright.create()){

            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page= browser.newPage();
            page.navigate("https://www.google.com");
            System.out.println("Title:" + page.title());
            browser.close();

        }



    }

}
