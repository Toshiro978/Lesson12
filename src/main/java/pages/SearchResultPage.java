package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage {

    public void clickFirstElementByTittle(String title){
        $("[title='"+title+"']").click();
    }
}
