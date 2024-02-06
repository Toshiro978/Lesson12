package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ItemBookPage {

    private final SelenideElement elementFormat = $(".format__text");
    private final SelenideElement selectedElementFormat = $(".ui-btn-format.option-button.selected > .format > .format__text");
    private final SelenideElement statusSelectedElement = $(".ui-shipment-status");
    private final SelenideElement priceSelectedItem = $(".ui-btn-format.option-button.selected > .ui-price-display > .ui-price-display__main");

    public void checkVisibleElementFormat() {
        elementFormat.shouldBe(Condition.visible);
    }


    public String takeFormatSelectedItem() {
        String format = selectedElementFormat.getText();
        return format;
    }

    public String checkStatusSelectedItem() {
        String selectedStatusItem = statusSelectedElement.getAttribute("class").substring(19);
        return selectedStatusItem;
    }

    public String takePriceSelectedItem() {
        String price = priceSelectedItem.getText();
        return price;
    }

    // Логіка послідовності елементів в пошуку Паперова>Електронна>Аудіо
    // оскільки ми беремо першу книгу з повним співпадінням тайтлу то ми вибираємо книгу того типу яка є в наявності Паперова>Електронна>Аудіо,
    // Яка книга була вибранна, такий елемент і буде передвибранно в елементі ітема Паперова>Електронна>Аудіо
    public void checkAvailabilityBook() {
        if (takeFormatSelectedItem().equals("Паперова")) {
            if (checkStatusSelectedItem().equals("available")) {
                System.out.println("Паперова книга в наявності і коштує : " + takePriceSelectedItem());
            }
        } else if (takeFormatSelectedItem().equals("Електронна")) {
            if (checkStatusSelectedItem().equals("product")) {
                System.out.println("Електронна книга в наявності і коштує : " + takePriceSelectedItem());
            }
        } else {
            System.out.println("Книга не в наявності");
        }
    }
}
