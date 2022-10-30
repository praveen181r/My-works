package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class gmail_test {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
		WebDriver d = new ChromeDriver();
		d.get("https://accounts.google.com");
		d.manage().window().maximize();
		WebElement id =  d.findElement(By.xpath("//*[@id=\"identifierId\"]"));
		id.sendKeys("praveenrr181@gmail.com");
		d.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click();
		WebElement pswd = d.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
		pswd.sendKeys("heloooo");
		d.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/div[3]")).submit();	
		d.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		d.close();

	}

}
