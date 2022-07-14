import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("UI-parametrized autotests for tiffany.com")
public class StoreLocatorParametrizedTests extends TestBase {
    public static final String CITY = "Vienna";
    public static final String VIENNA_STORE_ADDRESS = "Kohlmarkt 8-10 Vienna, 1010";
    public static final String NO_RESULTS_ERROR = "Sorry, no results matched your search.";

    static Stream<Arguments> storeLocatorServicesTest() {
        return Stream.of(
                Arguments.of(0, CITY, "All services", VIENNA_STORE_ADDRESS),
                Arguments.of(1, CITY, "Watch Repair & Sales", VIENNA_STORE_ADDRESS),
                Arguments.of(2, CITY, "Jewelry Cleaning & Repair", NO_RESULTS_ERROR)
        );
    }

    @MethodSource(value = "storeLocatorServicesTest")
    @ParameterizedTest(name = "Store Selector test: {1}")
    void mixedArgumentsTest(int arg, String city, String serviceValue, String expectedResult) {
        open(baseUrl + "jewelry-stores/");
        $("[name=\"regionInput\"]").setValue(city);
        $(".services-dropdown").click();
        $$(".storesearchbar__button_dropdownlist-list-item").findBy(text(serviceValue)).click();
        $(".storesearchbar__findastore").click();
        $("#main").shouldHave(text(expectedResult));
    }
}
