import com.codeborne.pdftest.assertj.Assertions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.DriverUtils;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CategoryPage;
import pages.MainPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.files.DownloadActions.click;
import static io.qameta.allure.Allure.step;

@DisplayName("UI-autotests for tiffany.com with POM")
public class TiffanyTests extends TestBase {
    @Test
    @Owner("alexeypartolin")
    @DisplayName("Check the default sort on a product list page")
    public void sortByValueTest() {
        step("Open the main page", () -> {
            open(baseUrl);
        });

        step("Hover mouse over category in main menu", () -> {
            mainPage.mainMenuCategoryHover();
        });

        step("Click on the item in the drop down menu", () -> {
            mainPage.dropDownMenuItemClick();
        });

        step("Checking the Default Sort", () -> {
            categoryPage.sortByDefaultValueCheck();
        });
    }

    @Test
    @Owner("alexeypartolin")
    @DisplayName("Check that console has not any errors")
    public void consoleShouldNotHaveErrorsTest() {
            step("Open the main page'", () -> open(baseUrl));
            step("Checking that there are no word 'SEVERE' in the console", () -> {
                String consoleLogs = DriverUtils.getConsoleLogs();
                String errorText = "SEVERE";

                Assertions.assertThat(consoleLogs).doesNotContain(errorText);
            });
        }

        @Test
        @Owner("alexeypartolin")
        @DisplayName("Check that product search function works ")
        public void productSearchTest() {
            step("Open the main page'", () -> open(baseUrl));

            step(("Click on the search button"), () -> {
                $(".header__search").click();
            });

            step(("Make search input filled"), () -> {
                $("[name=\"searchInput\"]").setValue("Charm").pressEnter();
            });

            step(("Check h1"), () -> {
                $("h1").scrollTo().shouldHave(Condition.text("Charms"));
            });
    }

    @Test
    @Owner("alexeypartolin")
    @DisplayName("Check redirect to local web-site version after location changed")
    public void redirectToLocalWebSiteTest() {
        step("Open the main page'", () -> open(baseUrl));

        step(("Click on the 'Choose location' label"), () -> {
            $(".choose-region").click();
        });

        step(("Click on the region item"), () -> {
            $$("a").findBy(text("France")).click();
        });

        step(("Check redirect"), () -> {
            webdriver().shouldHave(url("https://www.tiffany.fr/"));
        });
    }
}


