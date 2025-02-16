package org.ui;

import org.junit.Test;
import org.pages.common.HeaderPage;

import static org.assertj.core.api.Assertions.assertThat;

public class TrialFormTests extends TestBase {

    @Test
    public void navigateToTrialFormRedirectsToCorrectUrl() {
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
}