package org.ui;

import org.junit.Test;
import org.pages.common.HeaderPage;
import org.pages.form.FormModel;

import static org.assertj.core.api.Assertions.assertThat;

public class TrialFormTests extends TestBase {

    @Test
    public void navigateToTrialRedirectsToForm() {
        boolean isFormDisplayed = new HeaderPage(driver)
                .navigateToTrialForm()
                .isFormDisplayed();

        assertThat(driver.getCurrentUrl())
                .isEqualTo(baseURL + "/trial/");

        assertThat(isFormDisplayed)
                .isTrue();
    }

    @Test
    public void numberOfUsersFieldIsBlockedForFreelancers() {
        new HeaderPage(driver)
                .navigateToTrialForm()
                .ensureNumberOfUsersFieldIsBlockedForFreelancers();
    }

    @Test
    public void submitFormWithoutAgreementConsentThrowsError() {
        String errorMsg = new HeaderPage(driver)
                .navigateToTrialForm()
                .fillForm(FormModel.getRandomFormModel())
                .checkMailingConsent()
                .submitForm()
                .getAgreementError();

        assertThat(errorMsg).isEqualTo(System.getProperty("agreementErrorMessage"));
    }
}