package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открывает главную страницу Github")
    public void openPage() {
        open("https://github.com/");
    }

    @Step("Клик на строку поиска")
    public void clickSearchInput() {
        $(".header-search-button").click();
    }

    @Step("Ввод и отправление введеного значения {repositoryName}")
    public void setValueRepository(String repositoryName) {
        $("#query-builder-test").setValue(repositoryName).submit();
    }

    @Step("Клик на репозиторий {repositoryName}")
    public void clickLinkRepository(String repositoryName) {
        $(linkText(repositoryName)).click();
    }

    @Step("Нажатие на Issue")
    public void clickIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверка налачия Issue c #{issueNumber}")
    public void shouldSeeIssueNumber(int issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }

}
