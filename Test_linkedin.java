package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Test_linkedin {
	public static List list1 = new ArrayList(); 
	
	public static void main(String[] args) throws IOException {		
		ChromeDriver driver;		
		CSVReader reader = new CSVReader(new FileReader("D:\\Sample.csv"));
		String[] csvCell;	
		
		while((csvCell = reader.readNext())!= null){
			
			System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://linkedin.com");
			
			String email = csvCell[0];
			String pswd = csvCell[1];
			
			driver.findElement(By.xpath("//*[@id=\"session_key\"]")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id=\"session_password\"]")).sendKeys(pswd);
			driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/button")).click();			
						
			try {
				Boolean b = driver.findElement(By.xpath("//*[@id=\"username\"]")).isDisplayed();

				if(b) {						
					System.out.print("Fail");
					String update[]={email,pswd,"Fail"};
					list1.add(update);						
					}
				}
			catch(NoSuchElementException exception) {		
						
					System.out.print("Pass");
					String update[]={email,pswd,"Pass"};
					list1.add(update);
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
			System.out.print(list1);
		} // End of while loop.
		
		CSVWriter writer = new CSVWriter(new FileWriter("D:\\Sample.csv"));
		writer.writeAll(list1);
		writer.flush();
		
		
	} //End of main method
} //End of main class
