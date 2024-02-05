package yakabo;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import config.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AvailabilityBookTest {

    @BeforeTest
    public void setUpBrowser() {
        Browser.setBrowser();
    }

    @Test
    public void searchBook(){
        open();


    }

}
