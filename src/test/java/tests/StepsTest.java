package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends TestBase {

    private static final String repository = "eroshenkoam/allure-example";
    private static final int issueNumber = 87;

    @Test
    public void repositoryPageContainIssueButtonWithLamdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открывает главную страницу Github", () -> {
            open("https://github.com/");
        });
        step("Клик на строку поиска", () -> {
            $(".header-search-button").click();
        });
        step("Ввод и отправление введеного значения " + repository, () -> {
            $("#query-builder-test").setValue(repository).submit();
        });
        step("Клик на репозиторий " + repository, () -> {
            $(linkText(repository)).click();
        });
        step("Нажатие на Issue", () -> {
            $("#issues-tab").click();
        });
        step("Проверка налачия Issue c #" + issueNumber, () -> {
            $(withText("#" + issueNumber)).should(Condition.exist);
        });
    }

    @Test
    public void repositoryPageContainIssueButtonWithAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openPage();
        steps.clickSearchInput();
        steps.setValueRepository(repository);
        steps.clickLinkRepository(repository);
        steps.clickIssueTab();
        steps.shouldSeeIssueNumber(issueNumber);
    }
}
