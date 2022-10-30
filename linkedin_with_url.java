package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class linkedin_with_url {
	public static List list1 = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		WebDriver driver;
		CSVReader reader = new CSVReader(new FileReader("D:\\Sample.csv"));
		String[] csvCell;
		
		while((csvCell=reader.readNext())!=null) {
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://linkedin.com");
			
			String email = csvCell[0];
			String pswd = csvCell[1];
						
			driver.findElement(By.xpath("//*[@id=\"session_key\"]")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id=\"session_password\"]")).sendKeys(pswd);
			driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/button")).click();
			
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			String c = driver.getCurrentUrl();
			
			if(c.equals("https://www.linkedin.com/feed/?trk=homepage-basic_signin-form_submit")) {				
				String update[] = {email,pswd,"Test case passed"};
				list1.add(update);				
			}
			else {				
				String update[] = {email,pswd,"Test case failed"};
				list1.add(update);				
			}
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			driver.quit();
		}
		CSVWriter writer = new CSVWriter(new FileWriter("D:\\Sample.csv"));
		writer.writeAll(list1);
		writer.flush();

	}

}
