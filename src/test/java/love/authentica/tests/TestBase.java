package love.authentica.tests;

;

import com.codeborne.selenide.Configuration;
import love.authentica.pages.*;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    StorePage storePage = new StorePage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://www.authentica.love";
        Configuration.browserSize = "1920x1080";
    }
}