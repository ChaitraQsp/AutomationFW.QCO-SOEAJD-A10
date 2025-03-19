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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddLowestPricedProductToCart {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Read required Data
		// Property file - common Data

		// open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");

		// Read Test Data From Excel File
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet("Products");
		String SORTOPTION = sh.getRow(7).getCell(2).getStringCellValue();
		String PRODUCTNAME = sh.getRow(7).getCell(3).getStringCellValue();
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

		// Sort the page for lowest Price
		WebElement prodSort = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select sel = new Select(prodSort);
		sel.selectByVisibleText(SORTOPTION);

		Thread.sleep(1000);

		// Click on the Lowest price Product
		WebElement Product = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']"));
		String productToBeAdded = Product.getText();
		Product.click();

		// Add the product To Cart
		driver.findElement(By.id("add-to-cart")).click();

		// Navigate To Cart and Validate
		driver.findElement(By.id("shopping_cart_container")).click();

		// Validate
		String productInCart = driver.findElement(By.className("inventory_item_name")).getText();
		if (productToBeAdded.equals(productInCart)) {
			System.out.println("PASS");
			System.out.println(productInCart);
		} else {
			System.out.println("FAILS");
		}

		// Logout of Application
		driver.findElement(By.xpath("//button[.='Open Menu']")).click();
		driver.findElement(By.linkText("Logout")).click();

	}

}
