package com.pragmatic;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckProductTest {

    WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        webDriver=new ChromeDriver(options);
        webDriver.get("https://www.saucedemo.com");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testFilterPriceHighToLowOptionInProduct() {

        webDriver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        webDriver.findElement((By.xpath("//option[text()='Price (high to low)']"))).click();

        webDriver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (high to low)']"));
        String selectedValue = webDriver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (high to low)']")).getText();
        Assert.assertEquals(selectedValue,"Price (high to low)");

        List<WebElement> priceElements = webDriver.findElements(By.xpath("//div[contains(@class, 'inventory_item_price')]"));
        String[] prices = new String[priceElements.size()];
        double[] numericPrices = new double[prices.length];

        for (int i = 0; i < priceElements.size(); i++) {
            prices[i] = priceElements.get(i).getText().replace("$", "").trim();
            numericPrices[i] = Double.parseDouble(prices[i]);
        }

        boolean isSortedDescending = isDescendingOrder(numericPrices);
        if(isSortedDescending)
        {
            System.out.println("The prices are sorted in descending order.");
        }
        else
        {
            System.out.println("The prices are not sorted in descending order.");
        }
    }

    @Test
    public void testFilterPriceAtoZOptionInProduct() {
        WebElement eleoptions= webDriver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select selOptions=new Select(eleoptions);
//        selOptions.selectByIndex(2);
//        selOptions.selectByValue("za");
        selOptions.selectByVisibleText("Price (low to high)");
        selOptions.selectByIndex(3);
        Assert.assertEquals(selOptions.getFirstSelectedOption().getText(),"Price (low to high)");

    }

    @Test
    public void testFilterPriceLowToHighOptionInProduct() {

        //this way is not good practice to double click in combo box to select the item
        // webElement <variable name>= webDriver.findElement(<selector>);

        webDriver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        webDriver.findElement((By.xpath("//option[text()='Price (low to high)']"))).click();

        webDriver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (high to low)']"));
        String selectedValue = webDriver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (low to high)']")).getText();
        Assert.assertEquals(selectedValue,"Price (low to high)");

        List<WebElement> priceElements = webDriver.findElements(By.xpath("//div[contains(@class, 'inventory_item_price')]"));
        String[] prices = new String[priceElements.size()];
        double[] numericPrices = new double[prices.length];

        for (int i = 0; i < priceElements.size(); i++) {
            prices[i] = priceElements.get(i).getText().replace("$", "").trim();
            numericPrices[i] = Double.parseDouble(prices[i]);
        }

        boolean isSortedDescending = isAcendingOrder(numericPrices);
        if(isSortedDescending)
        {
            System.out.println("The prices are sorted in acending order.");
        }
        else
        {
            System.out.println("The prices are not sorted in acending order.");
        }
    }

    @Test
    public void testAddCartItems() {
        int p = 0;
        for (int i = 0; i < webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]")).size(); i++) {
            // Re-locate elements to avoid stale references
            List<WebElement> addButtonClickElements = webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]"));

            for (int j = 0; j <= p; j++) {
                addButtonClickElements.get(j).click(); // Click buttons up to the current index
            }
            p=p+1;

            // Assert that the shopping cart badge matches the expected count
            Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText(), String.valueOf(p));

            // Re-locate remove buttons and click to reset
            List<WebElement> removeButtonClickElements = webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(@class, 'btn_secondary')]"));
            for (int z = 0; z < p; z++) {
                removeButtonClickElements.get(z).click(); // Click remove buttons
            }
        }
    }

    @Test
    public void testRemoveCartItems(){
        int p = 0;
        for (int i = 0; i < webDriver.findElements(By.xpath("//div[@class='inventory_item']")).size(); i++) {
            List<WebElement> addToCartButtons_1 = webDriver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
            // Iterate through the list and click each 'Add to cart' button
            for (WebElement button : addToCartButtons_1) {
                button.click();
            }
            // Re-locate elements to avoid stale references
            List<WebElement> removeButtonClickElements = webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(@class, 'btn_secondary')]"));

            for (int j = 0; j <= p; j++)
            {
                removeButtonClickElements.get(j).click(); // Click buttons up to the current index
            }
            p=p+1;

            if (webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(@class, 'btn_secondary')]")).size()==0){
                break;
            }
            else{
                // Assert that the shopping cart badge matches the expected count
                Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText(), String.valueOf(webDriver.findElements(By.xpath("//button[contains(@class, 'btn_inventory') and contains(@class, 'btn_secondary')]")).size()));
            }
        }
    }

    @Test
    public void testVerifySelectedItemNameInsideCart() {

        // Select first item
        selectItem("add-to-cart-sauce-labs-backpack", "Sauce Labs Backpack");

        webDriver.findElement(By.xpath("//button[@id='continue-shopping']")).click();

        // Select second item
        selectItem("add-to-cart-sauce-labs-bike-light", "Sauce Labs Bike Light");
    }

    @Test
    public void testVerifyRemoveItemInsideCart() {
        // Select first item
        selectItem("add-to-cart-sauce-labs-backpack", "Sauce Labs Backpack");
        webDriver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        // Select second item
        selectItem("add-to-cart-sauce-labs-bike-light", "Sauce Labs Bike Light");

        removetItem("remove-sauce-labs-backpack");
        removetItem("remove-sauce-labs-bike-light");

        // Locate the <a> tag
        WebElement anchorElement = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        // Check if the <a> tag contains a <span> child
        List<WebElement> spanElements = anchorElement.findElements(By.xpath(".//span[@class='shopping_cart_badge']"));

        if (spanElements.isEmpty()) {
            System.out.println("All items are removed!");
        }
        webDriver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    @Test
    public void testCheckoutProduct() {
        selectItem("add-to-cart-sauce-labs-backpack", "Sauce Labs Backpack");
        webDriver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        selectItem("add-to-cart-sauce-labs-bike-light", "Sauce Labs Bike Light");

        //get the grand total of selected items
        List<WebElement> getPriceElements = webDriver.findElements(By.xpath("//div[@class='item_pricebar']//div[@class='inventory_item_price' and @data-test='inventory-item-price']"));

        String[] prices = new String[getPriceElements.size()];
        double[] numericPrices = new double[prices.length];
        double grandTotal=0;

        for (int i = 0; i < getPriceElements.size(); i++) {
            prices[i] = getPriceElements.get(i).getText().replace("$", "").trim();
            numericPrices[i] = Double.parseDouble(prices[i]);
            grandTotal=grandTotal+numericPrices[i];
        }

        webDriver.findElement(By.xpath("//button[@id='checkout']")).click();
        Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='title']")).getText(),
                "Checkout: Your Information");

        Faker faker = new Faker();
        String firstName= faker.name().firstName();
        String lastName= faker.name().lastName();
        String zipCode= faker.address().zipCode();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        webDriver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(firstName);
        webDriver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastName);
        webDriver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(zipCode);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        webDriver.findElement(By.xpath("//input[@id='continue']")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='title']")).getText(),
                "Checkout: Overview");

        Assert.assertEquals(String.valueOf(grandTotal),String.valueOf(webDriver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().replace("Item total: $", "").trim()));
        webDriver.findElement(By.id("finish")).click();

        Assert.assertEquals(webDriver.findElement(By.xpath("//h2[@data-test='complete-header']")).getText(),
                "Thank you for your order!");

        // Locate the <a> tag
        WebElement anchorElement = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        // Check if the <a> tag contains a <span> child
        List<WebElement> spanElements = anchorElement.findElements(By.xpath(".//span[@class='shopping_cart_badge']"));

        if (spanElements.isEmpty()) {
            System.out.println("All items are clear!");
        }
        webDriver.findElement(By.xpath("//button[@id='back-to-products']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span[data-test='title']")).getText(),
                "Products");
    }

    private void removetItem(String removeItemName) {
        webDriver.findElement(By.xpath("//button[@id='"+removeItemName+"']")).click();
    }

    private void selectItem(String itemID, String itemName) {
        webDriver.findElement(By.xpath("//button[@id='"+itemID+"']")).click();
        String selectedValue= webDriver.findElement(By.xpath("//div[text()='"+itemName+"']")).getText();
        webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='title']")).getText(),
                "Your Cart");

        String expectedValue= webDriver.findElement(By.xpath("//div[text()='"+itemName+"']")).getText();
        Assert.assertEquals(selectedValue,expectedValue);
    }

    private boolean isAcendingOrder(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isDescendingOrder(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

