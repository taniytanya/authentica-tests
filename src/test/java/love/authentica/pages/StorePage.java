package love.authentica.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static java.lang.String.format;

import java.util.List;

public class StorePage {
    public final static String TITLE_TEXT = "Продукт добавлен в корзину";


    private SelenideElement productInput = $("#rcm_search_input"),
            logo = $(".header-top__logo"),
            resultText = $(".rcm-search_title"),
            anyProduct = $(".rcm-search_catalog .product__quick-add2basket"),
            basketResult = $(".basket-result-title"),
            inspirePush = $(byXpath("//a[@href='#']"));


    public StorePage openPage() {
        open("/");
        logo.isDisplayed();
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public StorePage searchProduct(String product) {
        productInput.setValue(product).pressEnter();
        resultText.shouldHave(text(format(("Результат поиска по запросу \"%s\""), product)));
        $$(".rcm-search_catalog .products").shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    public StorePage searchProductEnglishLetters(String englishName, String RussianName) {
        productInput.setValue(englishName).pressEnter();
        resultText.shouldHave(text(format(("Результат поиска по запросу \"%s ( %s )\""), englishName, RussianName)));
        $$(".rcm-search_catalog .products").shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    public StorePage quickAddToBasket() {
        anyProduct.click();
        basketResult.shouldHave(text(TITLE_TEXT));
        return this;
    }
    public StorePage checkInspirationList(String inspiration, List<String> expectedCategory){
        inspirePush.hover();
        $(byText(inspiration)).click();
        $("#navigation").shouldHave(text(format("Главная страница / %s",inspiration)));
        $$("[class = 'article-filter__row'] a").shouldHave(CollectionCondition.texts(expectedCategory));
        return this;
    }


}
