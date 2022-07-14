package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import jdk.jfr.internal.tool.Main;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    // locators

    public SelenideElement
            mainMenuCategory = $("a[title=\"Home & Accessories\"]"),
            dropDownMenuItem = $("a[title=\"Home Office\"]");

    // actions

    public MainPage mainMenuCategoryHover() {
        mainMenuCategory.hover();

        return this;
    }

    public MainPage dropDownMenuItemClick() {
        dropDownMenuItem.click();

        return this;
    }


}
