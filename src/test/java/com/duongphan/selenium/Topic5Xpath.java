package com.duongphan.selenium;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class Topic5Xpath {
    WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  
	  WebDriverManager.chromedriver().setup();

      // Khởi tạo ChromeOptions 
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--start-maximized"); // Mở Chrome ở chế độ toàn màn hình
      options.addArguments("--disable-notifications"); // Tắt thông báo trình duyệt

      // Khởi tạo trình duyệt Chrome
      driver = new ChromeDriver(options);

      // Đặt timeout để Selenium có thể chờ khi tìm phần tử
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
      driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  
  }
 @Test
 public void TC01_RegisterEmptyData() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.cssSelector("button[type='submit']")).click();
	 // driver.findElement(By.xpath("//button[@type='submit']")).click();
 }
  
 @Test
 public void TC02_RegisterWithInvalidEmail() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.id("txtFirstname")).sendKeys("DuongZuiZe");
    driver.findElement(By.id("txtEmail")).sendKeys("D@uong@gmail1x_z2.com");
	  driver.findElement(By.id("txtCEmail")).sendKeys("Duonqqg@gmail.com");
	  driver.findElement(By.id("txtPassword")).sendKeys("1234");
	  driver.findElement(By.id("txtCPassword")).sendKeys("1234");
	  driver.findElement(By.id("txtPhone")).sendKeys("09765876234");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();

    String actualEmailError = driver.findElement(By.id("txtEmail-error")).getText();
    Assert.assertEquals(actualEmailError, "Vui lòng nhập email hợp lệ");
    
    String actualConfirmEmailError = driver.findElement(By.id("txtCEmail-error")).getText();
    Assert.assertEquals(actualConfirmEmailError, "Email nhập lại không đúng");
	
 }
 
  @Test
  public void TC_03_RegisterWithConfirmEmail() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.name("txtFirstname")).sendKeys("DuongZuiZe");
    driver.findElement(By.id("txtEmail")).sendKeys("Duong@gmail.com");
	  driver.findElement(By.id("txtCEmail")).sendKeys("Duonqqg@gmail.com");
	  driver.findElement(By.name("txtPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtCPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtPhone")).sendKeys("09765876234");
	  driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();

    String actualEmailConfirmErro = driver.findElement(By.id("txtCEmail-error")).getText();
    Assert.assertEquals(actualEmailConfirmErro, "Email nhập lại không đúng");
  }
 
@Test
public void TC_04_RegisterWithIncorrectConfirmPass() {
   driver.get("https://alada.vn/tai-khoan/dang-ky.html");
   driver.findElement(By.id("txtFirstname")).sendKeys("DuongZuiZe");
    driver.findElement(By.id("txtEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.name("txtCEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.id("txtPassword")).sendKeys("123456789111"); 
    driver.findElement(By.name("txtCPassword")).sendKeys("123456789");
    driver.findElement(By.name("txtPhone")).sendKeys("09765876234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();

    String actualConfirmPassError = driver.findElement(By.id("txtCPassword-error")).getText();
    Assert.assertEquals(actualConfirmPassError, "Mật khẩu bạn nhập không khớp");
}
@Test
public void TC_05_RegisterWithInvalidPhone() {
   driver.get("https://alada.vn/tai-khoan/dang-ky.html");
   driver.findElement(By.id("txtFirstname")).sendKeys("DuongZuiZe");
    driver.findElement(By.id("txtEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.name("txtCEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.id("txtPassword")).sendKeys("123456789111"); 
    driver.findElement(By.name("txtCPassword")).sendKeys("123456789");
    driver.findElement(By.name("txtPhone")).sendKeys("097658w132e76234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();

    String actualPhoneError = driver.findElement(By.id("txtPhone-error")).getText();
    Assert.assertEquals(actualPhoneError, "Vui lòng nhập con số");
}
@Test
public void TC_06_RegisterWithIncorrectPass() {
   driver.get("https://alada.vn/tai-khoan/dang-ky.html");
   driver.findElement(By.id("txtFirstname")).sendKeys("DuongZuiZe");
    driver.findElement(By.id("txtEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.name("txtCEmail")).sendKeys("Duong@gmail.com");
    driver.findElement(By.id("txtPassword")).sendKeys("19111"); 
    driver.findElement(By.name("txtCPassword")).sendKeys("19111");
    driver.findElement(By.name("txtPhone")).sendKeys("09765876234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();

    String actualPassError = driver.findElement(By.id("txtPassword-error")).getText();
    String actualConfirmPassError = driver.findElement(By.id("txtCPassword-error")).getText();

    // Kiểm tra nội dung thông báo lỗi
    Assert.assertEquals(actualPassError, "Mật khẩu phải có ít nhất 6 ký tự");
    Assert.assertEquals(actualConfirmPassError, "Mật khẩu phải có ít nhất 6 ký tự");
}
  @AfterTest
  public void afterTest() {
	  if (driver != null) {
	        driver.quit();
	    }
  }
}
