package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PersonOverviewPage extends Page {
    @FindBy(tagName = "td")
    private List<WebElement> tableElements;

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Overview");
    }

    public boolean containsUserWithEmail(String email) {
        boolean found=false;
        for (WebElement listItem:tableElements) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
    }
}
