package love.authentica.tests;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;


public class WebTest extends TestBase {
    @ValueSource(strings = {"Aveda", "Alterna"})
    @ParameterizedTest(name = "Searching result of brand {0} is not null")
    void successSearchAnyProduct(String testData) {
        storePage.openPage().searchProduct(testData);
    }


    @ValueSource(strings = {"Aveda", "Alterna"})
    @ParameterizedTest(name = "Quick add any item of brand {0} to basket")
    void successAddAnyProductToBasket(String testData) {
        storePage.openPage().searchProduct(testData).quickAddToBasket();
    }

    @CsvSource(value = {
            "Ifvgeym,  Шампунь",
            "Rhtv,  Крем",
    })
    @ParameterizedTest(name = "Searching result for incorrect language request {0} include correct result {1}")
    void searchProductWithIncorrectLang(String testData, String expectedResult) {
        storePage.openPage().searchProductEnglishLetters(testData, expectedResult);
    }

    static Stream<Arguments> categoriesForItemAvailableTest() {
        return Stream.of(
                Arguments.of("Beauty-блог", List.of("Покупки недели", "Бьюти-гуру", "Backstage", "В салоне", "Интервью", "Шопинг с мастером")),
                Arguments.of("Видео", List.of("Волосы", "Макияж", "Уход за кожей", "Маникюр", "365 BEAUTY-ИСТОРИЙ", "ПОДАРКИ"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Inspiration {0} has categories {1}")
    void categoriesForItemAvailableTest(String item, List<String> expectedCategory) {
        storePage.openPage().checkInspirationList(item, expectedCategory);

    }
}

