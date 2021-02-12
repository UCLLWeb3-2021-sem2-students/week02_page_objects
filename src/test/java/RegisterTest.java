import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.PersonOverviewPage;
import pages.SignUpPage;

import static org.junit.Assert.*;

public class RegisterTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
    }

   // @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        // GIVEN STEP = context
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.setFirstName("Jan");
        signUpPage.setLastName("Janssens");
        signUpPage.setEmail("jan.janssens@hotmail.com");
        signUpPage.setPassword("1234");

        // WHEN STEP = action
        HomePage homePage = signUpPage.submitValid();

        // THEN STEP = result
        assertEquals("Home", homePage.getTitle());
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertTrue(personOverviewPage.containsUserWithEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.setFirstName("");
        signUpPage.setLastName("Janssens");
        signUpPage.setEmail("jan.janssens@hotmail.com");
        signUpPage.setPassword("1234");

        signUpPage.submitInvalid();

        //Check correct page
        assertEquals("Sign Up", signUpPage.getTitle());
        //Check sticky values
        assertTrue(signUpPage.hasErrorMessage("No firstname given"));
        assertTrue(signUpPage.hasEmptyFirstName());
        assertTrue(signUpPage.hasStickyLastName("Janssens"));
        assertTrue(signUpPage.hasStickyEmail("jan.janssens@hotmail.com"));
        //Check person is not registered
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertFalse(personOverviewPage.containsUserWithEmail("jan.janssens@hotmail.com"));
    }
}
