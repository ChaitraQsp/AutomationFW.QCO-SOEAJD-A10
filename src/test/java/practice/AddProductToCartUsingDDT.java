package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class AddProductToCartUsingDDT {

	public static void main(String[] args) throws IOException {
		
		//Read required Data
		//Property file - common Data
		
		//open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Create object of properties class - java.util
		Properties p = new Properties();
		
		//load the file input stream to properties
		p.load(fis);
		
		//read the data using keys
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//Read Test Data From Excel File
		//Open the document in Java readable format
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Create a workbook
		Workbook wb = WorkbookFactory.create(fise);
		
		//navigate to sheet
		Sheet sh = wb.getSheet("Products");
		
		//Navigate to Row
		Row rw = sh.getRow(1);
		
		//Navigate to cell
		Cell cl = rw.getCell(2);
		
		//Capture the data inside the cell
		String PRODUCTNAME = cl.getStringCellValue();
		System.out.println(PRODUCTNAME);
		
		
		// Launch the browser
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize(); // 2yrs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Load the Application
		driver.get(URL);

		// Login to Application
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.name("login-button")).click();

		// Click on a product - dynamic xpath
		String ProductToBeAdded = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();
		driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();

		// Add the Product to cart
		driver.findElement(By.id("add-to-cart")).click();

		// Navigate to Cart
		driver.findElement(By.id("shopping_cart_container")).click();

		// Validate for the product
		String productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if (productInCart.equals(ProductToBeAdded)) {
			System.out.println("PASS");
			System.out.println(productInCart);
		} else {
			System.out.println("FAIL");
		}

		// Logout of Application
		driver.findElement(By.xpath("//button[.='Open Menu']")).click();
		driver.findElement(By.linkText("Logout")).click();

	}

}
