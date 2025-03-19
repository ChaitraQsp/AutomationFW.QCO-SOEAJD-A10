package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;

public class InventoryPage extends SeleniumUtility{
	
	//Declaration
	@FindBy(className = "product_sort_container")
	private WebElement sortDropDown;
	
	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuBtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutLnk;
	
	@FindBy(id = "shopping_cart_container")
	private WebElement cartContainerBtn;
	
	
	//Intialization
	public InventoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

    //Utilization
	public WebElement getSortDropDown() {
		return sortDropDown;
	}

    public WebElement getMenuBtn() {
		return menuBtn;
	}

	public WebElement getLogoutLnk() {
		return logoutLnk;
	}

	public WebElement getCartContainerBtn() {
		return cartContainerBtn;
	}
	
	//Business Library
	
	/**
	 * This method will click on product read from excel file and return the
	 * details to caller
	 * @param driver
	 * @param PRODUCTNAME
	 * @return
	 */
	public String clickOnProduct(WebDriver driver, String PRODUCTNAME)
	{
		WebElement prod = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']"));
		String productDetails = prod.getText();
		prod.click();
		return productDetails;
	}
	
	/**
	 * This method will click on cart container button
	 */
	public void clickOnCartContainer()
	{
		cartContainerBtn.click();
	}
	
	/**
	 * This method will perform logout operation
	 */
	public void logoutOfApp()
	{
		menuBtn.click();
		logoutLnk.click();
	}

	/**
	 * This method will sort the product and then click on required product
	 * and return the details to caller
	 * @param driver
	 * @param PRODUCTNAME
	 * @param SORTOPTION
	 * @return
	 */
	public String clickOnSortedProduct(WebDriver driver, String PRODUCTNAME, String SORTOPTION)
	{
		handleDropDown(SORTOPTION, sortDropDown);
		WebElement prod = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']"));
		String productDetails = prod.getText();
		prod.click();
		return productDetails;
		
	}
	
}
