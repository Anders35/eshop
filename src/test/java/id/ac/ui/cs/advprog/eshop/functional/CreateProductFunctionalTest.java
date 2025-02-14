package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) throws Exception {
        // Navigate
        driver.get(baseUrl + "/product/create");

        // Find elements
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Input product
        nameInput.sendKeys("Sampo Cap Bambang");
        quantityInput.sendKeys("100");

        // Submit
        submitButton.click();

        // Verify Redirect
        assertTrue(driver.getCurrentUrl().contains(baseUrl + "/product/list"));

        // Find the table with products
        WebElement productTable = driver.findElement(By.cssSelector("table.table"));
        List<WebElement> productRows = productTable.findElements(By.tagName("tr"));

        // Verify product in list
        boolean productFound = false;
        for (int i = 1; i < productRows.size(); i++) {
            List<WebElement> columns = productRows.get(i).findElements(By.tagName("td"));
            if (columns.get(0).getText().equals("Sampo Cap Bambang") &&
                    columns.get(1).getText().equals("100")) {
                productFound = true;
                break;
            }
        }
        assertTrue(productFound);
    }
}