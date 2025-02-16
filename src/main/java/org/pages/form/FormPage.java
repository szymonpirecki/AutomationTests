package org.pages.form;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.pages.base.BasePage;

import java.util.List;

@Slf4j
public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
        switchToIframe(formIframe);
    }

    @FindBy(css = "iframe[src='https://login.xtm.cloud/saas-manager/trial.jsp']")
    private WebElement formIframe;

    @FindBy(css = "label[name='TRIAL_FORM_TITLE_STANDARD']")
    private WebElement formLabel;

    @FindBy(css = "select[name='numberOfUsers']")
    private WebElement numberOfUsersSelect;

    @FindBy(css = "select[name='accountType']")
    private WebElement accountTypeSelect;

    public boolean isFormDisplayed() {
        return formLabel.isDisplayed();
    }

    public void ensureNumberOfUsersFieldIsBlockedForFreelancers() {
        List<WebElement> accountTypeOptions = new Select(accountTypeSelect).getOptions();
        SoftAssertions softAssertions = new SoftAssertions();

        for (WebElement type : accountTypeOptions) {
            clickElement(type);
            String accountType = type.getText();

            softAssertions.assertThat(isNumberOfUsersDisabled())
                    .isEqualTo(shouldNumberOfUsersBeDisabled(accountType));
        }
        softAssertions.assertAll();
    }

    private boolean shouldNumberOfUsersBeDisabled(String accountType) {
        boolean shouldBeDisabled = accountType.equals("Choose...") || accountType.equals("Freelance - Single user");
        log.debug("Option: {} - number of users select should{} be disabled",
                accountType,
                shouldBeDisabled ? "" : "n't");

        return shouldBeDisabled;
    }

    private boolean isNumberOfUsersDisabled() {
        boolean isDisabled = !numberOfUsersSelect.isEnabled();
        log.debug("Number of users select is{} disabled",
                isDisabled ? "" : "n't");

        return isDisabled;
    }
}