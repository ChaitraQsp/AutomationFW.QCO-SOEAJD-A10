package inventoryTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class AddLowestPriceproductToCartTest extends BaseClass{

	@Test(groups = "RegressionSuite")
	public void tc_002_addLowestPriceProductTocart() throws IOException
	{

		String SORTOPTION = fUtil.readDataFromExcelFile("Products", 7, 2);
		String PRODUCTNAME = fUtil.readDataFromExcelFile("Products", 7, 3);

		// Click on Product
		InventoryPage ip = new InventoryPage(driver);
		String productAdded = ip.clickOnSortedProduct(driver, PRODUCTNAME, SORTOPTION);

		// Add product To cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddToCartBtn();

		// Navigate to cart
		ip.clickOnCartContainer();

		// Validate in the Cart page
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();

		Assert.assertEquals(productAdded, productInCart);
        System.out.println(productInCart);

	}
	
	@Test
	public void sample()
	{
		System.out.println("sample");
	}
}
