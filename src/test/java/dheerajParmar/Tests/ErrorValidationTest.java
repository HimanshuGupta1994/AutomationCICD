package dheerajParmar.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import dheerajParmar.TestComponents.BaseTest;
import dheerajParmar.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {
		landingPage.loginApplication("dheerajparmar014@gmail.com", "Dheeraj@00");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

}
