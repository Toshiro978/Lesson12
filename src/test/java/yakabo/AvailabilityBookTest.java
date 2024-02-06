package yakabo;

import static com.codeborne.selenide.Selenide.*;

import config.Browser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ItemBookPage;
import pages.SearchResultPage;

public class AvailabilityBookTest {

    private static String homeUrl = "https://www.yakaboo.ua/";
    HomePage homePage = new HomePage();
    SearchResultPage searchResultPage = new SearchResultPage();
    ItemBookPage itemBookPage = new ItemBookPage();

    @BeforeTest
    public void setUpBrowser() {
        Browser.setBrowser();
    }

    @Test
    public void searchAndOpenBook() {
        open(homeUrl);
        homePage.closeBanner();
        homePage.inputSearchField("Василь Симоненко");
        homePage.clickFindButton();
        searchResultPage.clickFirstElementByTittle("Василь Симоненко");
    }

    @Test(dependsOnMethods = "searchAndOpenBook")
    public void checkAvalibilityBook() {
        itemBookPage.checkVisibleElementFormat();
        itemBookPage.checkAvailabilityBook();
    }

}
