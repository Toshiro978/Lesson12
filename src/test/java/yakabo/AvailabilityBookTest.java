package yakabo;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import config.Browser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AvailabilityBookTest {

    @BeforeTest
    public void setUpBrowser() {
        Browser.setBrowser();
    }

    @Test
    public void searchAndOpenBook() {
        open("https://www.yakaboo.ua/");
        $(".cl-dialog-inner-content").shouldBe(Condition.visible);
        $(".cl-dialog-close-icon").click(); //Закриваємо баннер
        $("[type='Search']").setValue("Василь Симоненко");
        $("[type='Search']").shouldBe(Condition.exist);
        $(".input-loader").shouldNotBe(Condition.visible);
        $("[type='button'] + .ui-btn-primary").click();
        $("[title='Василь Симоненко']").click();
    }

    @Test(dependsOnMethods = "searchAndOpenBook")
    public void checkAvalibilityBook() {
        $(".format__text").shouldBe(Condition.visible);
        sleep(2000);

        String selectedItemFormat = $(".ui-btn-format.option-button.selected > .format > .format__text").getText();
        // Логіка послідовності елементів в пошуку Паперова>Електронна>Аудіо
        // оскільки ми беремо першу книгу з повним співпадінням тайтлу то ми вибираємо книгу того типу яка є в наявності Паперова>Електронна>Аудіо,
        // Яка книга була вибранна, такий елемент і буде передвибранно в елементі ітема Паперова>Електронна>Аудіо
        if (selectedItemFormat.equals("Паперова")) {
            String selectedStatusItem = $(".ui-shipment-status").getAttribute("class").substring(19);
            if (selectedStatusItem.equals("available")) {
                String price = $(".ui-btn-format.option-button.selected > .ui-price-display > .ui-price-display__main").getText();
                System.out.println("Паперова книга в наявності і коштує : " + price);
            }else {
                $(".format__text");
                sleep(2000);
            }
        } else if (selectedItemFormat.equals("Електронна")) {
            String selectedStatusItem = $(".ui-shipment-status").getAttribute("class").substring(19);
            if (selectedStatusItem.equals("product")) {
                String price = $(".ui-btn-format.option-button.selected > .ui-price-display > .ui-price-display__main").getText();
                System.out.println("Електронна книга в наявності і коштує : " + price);
            }
        } else {
            System.out.println("Книга не в наявності");
        }
    }

}
