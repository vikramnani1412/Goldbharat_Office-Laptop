package com.goldbharath.objectrepository.admin;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.goldbharath.genericutilities.WebDriverUtility;

public class DistributorDetailsPage {

	// Rule-1:-Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//button[.='Reject']")private WebElement RejectBtn;
    
    @FindBy(xpath="//button[.='Accept']")private WebElement AcceptBtn;
    
    @FindBy(xpath="//strong[.='Proprietor PAN:']/../preceding-sibling::div//button[.='Download']")private WebElement PanDownloadBtn;
    
    @FindBy(xpath="//strong[.='Proprietor GST:']/../preceding-sibling::div//button[.='Download']")private WebElement GstDownloadBtn;
    
    @FindBy(xpath="//strong[.='KYC Status']/following-sibling::span[.='Rejected']")private WebElement RejectedStatus;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public DistributorDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getRejectBtn() {
		return RejectBtn;
	}


	public WebElement getAcceptBtn() {
		return AcceptBtn;
	}


	public WebElement getPanDownloadBtn() {
		return PanDownloadBtn;
	}


	public WebElement getRejectedStatus() {
		return RejectedStatus;
	}


	public WebElement getGstDownloadBtn() {
		return GstDownloadBtn;
	}
	
	// Business Library
	public void acceptingDistributorKycDetails() throws Exception
	{
		Thread.sleep(2000);
		AcceptBtn.click();
	}
	
	public void rejectingDistributorKycDetails() throws Exception
	{
		Thread.sleep(2000);
		RejectBtn.click();
	}
	
	public void clickRejectButton(WebDriver driver) {

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(20));

	    List<By> rejectLocators = Arrays.asList(

	        By.xpath("//button[normalize-space()='Reject']"),

	        By.xpath("//button[contains(normalize-space(.),'Reject')]"),

	        By.xpath("//input[@type='submit' and @value='Reject']"),

	        By.xpath("//*[@role='button' and normalize-space()='Reject']"),

	        By.xpath("//a[normalize-space()='Reject']")

	    );

	    for (By locator : rejectLocators) {

	        try {

	            WebElement rejectButton = wait.until(
	                    ExpectedConditions.elementToBeClickable(locator));

	            rejectButton.click();

	            System.out.println("Reject button clicked successfully.");

	            return;

	        } catch (Exception e) {

	            System.out.println(
	                    "Reject locator not available: " + locator);

	        }
	    }

	    throw new RuntimeException(
	            "Reject button not found. Verify the actual application DOM.");
	}
	
//	public void rejectingDistributorKycDetailss() throws Exception
//	{
//		WebDriverUtility wUtil = new WebDriverUtility();
//		Thread.sleep(2000);
//		wUtil.safeClick(null, RejectBtn);
//	}
	
//	public void checkingRejectedStatus() throws Exception
//	{
//		Thread.sleep(2000);
//		if(RejectedStatus.isDisplayed())
//		{
//			System.out.println("Distributor Rejected By ADMIN");
//		}
//		else
//		{
//			System.out.println("Distributor Not Rejected");
//		}
//	}
	
	
	public void checkingRejectedStatus(WebDriver driver) throws Exception
	{
		
		boolean isRejected;
		try {
		    isRejected = new WebDriverWait(driver, Duration.ofSeconds(20))
		            .until(ExpectedConditions.visibilityOf(RejectedStatus)).isDisplayed();
		} catch (org.openqa.selenium.TimeoutException e) {
		    isRejected = false;
		}

		if (isRejected) {
		    System.out.println("Distributor Rejected By ADMIN");
		} else {
		    Assert.fail("Distributor is not rejected by ADMIN");
		}
		
		
		
//	    boolean isRejected;
//	    try {
//	        isRejected = new WebDriverWait(driver, Duration.ofSeconds(20))
//	                .until(ExpectedConditions.visibilityOf(RejectedStatus)).isDisplayed();
//	    } catch (org.openqa.selenium.TimeoutException e) {
//	        isRejected = false;
//	    }
//
//	    Assert.assertTrue(isRejected, "Distributor is not rejected by ADMIN");
//	    System.out.println("Distributor Rejected By ADMIN");
	}
	
}
