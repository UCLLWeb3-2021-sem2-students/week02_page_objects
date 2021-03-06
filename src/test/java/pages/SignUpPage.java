package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage extends Page {

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;


    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "signUp")
    private WebElement signUpButton;

    @FindBy(css = "div.alert-danger ul li")
    private List<WebElement> errorMessages;

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=SignUp");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        signUpButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        signUpButton.click();
    }

    public boolean hasErrorMessage(String message) {
        for (WebElement errorMessage : errorMessages)
            if (message.equals(errorMessage.getText()))
                return true;
        return false;
    }

    public boolean hasStickyLastName(String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail(String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName() {
        return firstNameField.getAttribute("value").isEmpty();
    }

}
