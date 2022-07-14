package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CategoryPage {

    String TITLE_TEXT = "Home Office Designs";
    // locators

    public SelenideElement
            categoryTitle = $("h1"),
            sortByElement = $("span .sortby-wrapper__sort-title_option");

    // assert

    public CategoryPage categoryTitleCheck() {
        categoryTitle.shouldHave(Condition.text(TITLE_TEXT));

        return this;
    }

    public CategoryPage sortByDefaultValueCheck() {
        sortByElement.scrollTo().shouldHave(Condition.text("Recommendations"));

        return this;
    }
}
