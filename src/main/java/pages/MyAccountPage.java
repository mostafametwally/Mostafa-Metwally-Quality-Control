package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class MyAccountPage extends Base {
	
	@FindBy(xpath = "//ul[@class=\"sf-menu clearfix menu-content sf-js-enabled sf-arrows\"]/li[3]")
	WebElement T_shirts;
	
	
	@FindBy(xpath = "//img[@title=\"Faded Short Sleeve T-shirts\"]")
	WebElement Shirt_Img;
	@FindBy(xpath = "//button[@name=\"Submit\"]")
	WebElement AddToCartBtn;
	
	@FindBy(xpath = "//p//span[contains(text(),'Proceed to checkout')]")
	WebElement Proceed_To_CheckoutBtn;

	
	//button//span[contains(text(),'Proceed to checkout')]
	@FindBy(id = "cgv")
	WebElement AgreementCheckBox;
	

	@FindBy(xpath = "//a[@title=\"Pay by bank wire\"]")
	WebElement Pay_By_Bank_Wire ;
	
	@FindBy(xpath = "//a[@title=\"Pay by check.\"]")
	WebElement Pay_By_Check ;
	
	@FindBy(xpath = "//button[@class=\"button btn btn-default button-medium\"]//span")
	WebElement ConfirmOrderBtn ;
	
	
	@FindBy(xpath = "//p//strong")
	WebElement ConfirmationMsg ;
	static WebDriverWait wait;
	Select menu;
	Actions actions;

	// Constructor
	public MyAccountPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
		actions = new Actions(driver);
	}
	
	public String T_ShirtsCheckOut() {
		T_shirts.click();
		Shirt_Img.click();
		driver.switchTo().frame(0);
		AddToCartBtn.submit();	
		Proceed_To_CheckoutBtn.click();
		Proceed_To_CheckoutBtn.click();
		AgreementCheckBox.click();
		Proceed_To_CheckoutBtn.click();
		Pay_By_Bank_Wire.click();
		ConfirmOrderBtn.click();
		return ConfirmationMsg.getText();		
		
	}

}
