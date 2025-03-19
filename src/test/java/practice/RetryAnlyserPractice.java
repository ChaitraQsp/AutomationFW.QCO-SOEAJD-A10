package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnlyserPractice {
	
	@Test(retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	public void sample()
	{
		Assert.fail();
		System.out.println("Hi");
	}
	
	@Test
	public void sample1()
	{
		System.out.println("Heeloo");
	}

}
