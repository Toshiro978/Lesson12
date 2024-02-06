package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement banner = $(".cl-dialog-inner-content");
    private final SelenideElement closeIconBanner = $(".cl-dialog-close-icon");
    private final SelenideElement searchInput = $("[type='Search']");
    private final SelenideElement searchLoader = $(".input-loader");
    private final SelenideElement searchButtonFind= $("[type='button'] + .ui-btn-primary");

    public void closeBanner() {
        banner.shouldBe(Condition.visible);
        closeIconBanner.click();
    }
    public void inputSearchField(String text){
        searchInput.setValue(text);
        searchLoader.shouldNotBe(Condition.visible);
    }
    public void clickFindButton(){
        searchButtonFind.click();
    }

}
