import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTest {
    //importing selenium webdriver
    private WebDriver driver;
    @BeforeTest
    public void start() throws InterruptedException{

        //Locating the Chrome driver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //opening chrome browser
        driver = new ChromeDriver();

        //Input Konga page URL
        driver.get("https://www.konga.com/");

        //verifying that the user entered right url and is on the right page
        if (driver.getCurrentUrl().contains("https://www.konga.com/")){
            // test passed
            System.out.println("Correct page entered");
        }
        else{
            //test failed
            System.out.print("Wrong Url entered");
        }

        Thread.sleep(15000);

        // Maximizing the browser
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Click on the Login/Signup button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 0)
    public void positivelogin() throws InterruptedException {
        // Verifying that the user can login with valid credentials

        // Finding the Email Address field and entering a valid Email Address
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("example@gmail.com");

        // Locating the Password field and enter a valid Password
        driver.findElement(By.id("password")).sendKeys("password");

        // Clicking the Login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(10000);
    }
    @Test (priority = 1)
    public void AddItemToCart() throws InterruptedException {

        // Click on Computers and Accessories category
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(15000);

        // Click on Laptops
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(5000);

        // Click on Apple MacBooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(18000);

        // Adding an item to the cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(10000);
    }


    @Test (priority = 2)
    public void OpenTheCart() throws InterruptedException {

        // Opening the cart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(5000);

        // Close the prompt message
        driver.findElement(By.xpath("/html/div/div/div[1]/button")).click();

        //  Checkout button clicked
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(13000);

    }

    @Test (priority = 3)
    public void SelectAddressAndPayNow() throws InterruptedException {

        //Checkout Page
        Thread.sleep(5000);

        // Address change
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
        Thread.sleep(5000);

        // Click Add Delivery Address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(5000);

        // Select Address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(5000);

        // Click on Use this Address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);

        // Click on the Pay Now button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(7000);

        // Click on the Continue to Payment button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(10000);

    }
    @Test (priority = 4)
    public void SelectCard() throws InterruptedException  {

        // Select Card as payment method on the iFrame

        // find the iFrame
        WebElement iframe = driver.findElement(By.tagName("iframe"));

        // Switching focus to the iFrame
        driver.switchTo().frame("kpg-frame-component");

        //find the Card button
        WebElement Cardbutton = driver.findElement(By.className("Card"));

        // Click the button
        Cardbutton.click();
        Thread.sleep(5000);

        // Enter card details

        // find the card number field and enter an invalid card number
        WebElement CardNumberField = driver.findElement(By.id("card-number"));
        CardNumberField.sendKeys("5199325678459088");

        // find the date field and enter an invalid date
        WebElement DateField = driver.findElement(By.id("expiry"));
        DateField.sendKeys("0825");

        //find the CVV field and enter an invalid CVV
        WebElement CVVField = driver.findElement(By.id("cvv"));
        CVVField.sendKeys("565");

        Thread.sleep(3000);

        //Print Out the error message: Invalid Card Number

        //find the error message
        WebElement ErrorMessage = driver.findElement(By.id("card-number_unhappy"));
        Thread.sleep(3000);

        // Print out the error message
        System.out.println(ErrorMessage.getText());
        Thread.sleep(3000);

        // Close the iFrame that displays the input card Modal
        WebElement CloseiFrame = driver.findElement(By.className("data-card__close"));
        CloseiFrame.click();

        //Switch out of iFrame
        driver.switchTo().defaultContent();

        Thread.sleep(3000);

    }

    @AfterTest
    public void closeBrowser() {
        // Close the browser
        driver.quit();
    }

}


/**
 *INSTRUCTIONS

 * Visit the URL (https://www.konga.com)
 * Sign in to Konga Successfully
 * From the Categories, click on the “Computers and Accessories"
 * Click on the Laptop SubCategory
 * Click on the Apple MacBooks
 * Add an item to the cart
 * Select Address
 * Continue to make payment
 * Select a Card Payment Method
 * Input invalid card details
 * Print Out the error message: Invalid card number
 * Close the iFrame that displays the input card Modal
 * Quit the browser.
 */