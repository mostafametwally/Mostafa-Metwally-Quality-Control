package pages;

import java.awt.Desktop.Action;
import java.awt.Frame;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class LoginPage extends Base {

	// login Elements
	@FindBy(id = "email")
	WebElement LoginEmailAddress;

	@FindBy(id = "passwd")
	WebElement LoginPassword;

	@FindBy(id = "SubmitLogin")
	public WebElement LoginButton;

	// Registration Elements
	// First Page
	@FindBy(id = "email_create")
	WebElement RegisterEmailAddress;

	@FindBy(id = "SubmitCreate")
	WebElement CreateAccountBtn;

	// PERSONAL INFORMATION
	@FindBy(id = "uniform-id_gender1")
	WebElement Male;

	@FindBy(id = "uniform-id_gender2")
	WebElement Female;

	@FindBy(id = "customer_firstname")
	WebElement FirstName;

	@FindBy(id = "customer_lastname")
	WebElement LastName;

	@FindBy(xpath = "//div[@class=\"required password form-group\"]//input[@id=\"passwd\"]")
	WebElement Password;

	// Date of birth Elemenets
	@FindBy(id = "days")
	WebElement Days;

	@FindBy(id = "months")
	WebElement Months;

	@FindBy(id = "years")
	WebElement Years;

	// Address Elements
	@FindBy(id = "firstname")
	WebElement AddressFirstName;

	@FindBy(id = "lastname")
	WebElement AddressLastName;

	@FindBy(id = "company")
	WebElement CompanyName;

	@FindBy(id = "address1")
	WebElement StreetAddress;

	@FindBy(id = "address2")
	WebElement FloorAddress;

	@FindBy(id = "city")
	WebElement City;

	@FindBy(id = "id_state")
	WebElement States;

	@FindBy(id = "postcode")
	WebElement PostCode;

	@FindBy(id = "phone_mobile")
	WebElement MobileNumber;

	@FindBy(id = "alias")
	WebElement FullAddress;

	@FindBy(id = "submitAccount")
	WebElement SubmitBtn;

	Select Day;
	Select Month;
	Select Year;
	Select State;
	static WebDriverWait wait;
	Actions actions;

	// Constructor
	public LoginPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
		actions = new Actions(driver);
	}

	public void openLoginPage(String url) {
		driver.get(prop.getProperty(url));
	}

	public String login(String Email, String Password) {
		LoginEmailAddress.clear();
		LoginEmailAddress.sendKeys(Email);
		LoginPassword.clear();
		LoginPassword.sendKeys(Password);
		LoginPassword.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.urlContains("my-account"));
		return driver.getCurrentUrl();
	}

	public void createAccount_1(String email) {
		RegisterEmailAddress.clear();
		RegisterEmailAddress.sendKeys(email);
		CreateAccountBtn.submit();
	}

	public String createAccount_2(String Gender, String firstName, String lastName, String password,
			String day, String month, String year, String addressFirstName, String addressLastName, String companyName,
			String streetAddress, String floorAddress, String city, String state, String postCode,
			String mobileNumber, String fullAddress) {
		Day = new Select(Days);
		Month = new Select(Months);
		Year = new Select(Years);
		State= new Select (States);
		
		
		if(Gender.equals("Male"))
			actions.moveToElement(Male).click().build().perform();
		else if (Gender.equals("Female"))
			actions.moveToElement(Female).click().build().perform();
		
		FirstName.clear();
		FirstName.sendKeys(firstName);
			
		LastName.clear();
		LastName.sendKeys(lastName);
		
		Password.clear();
		Password.sendKeys(password);
		
		Day.selectByValue(day);
		Month.selectByValue(month);
		Year.selectByValue(year);
		
		AddressFirstName.clear();
		AddressFirstName.sendKeys(addressFirstName);
		
		AddressLastName.clear();
		AddressLastName.sendKeys(addressLastName);
	
		CompanyName.clear();	
		CompanyName.sendKeys(companyName);
		
		StreetAddress.clear();	
		StreetAddress.sendKeys(streetAddress);
		
		FloorAddress.clear();	
		FloorAddress.sendKeys(floorAddress);
		
		City.clear();	
		City.sendKeys(city);
			
		State.selectByVisibleText(state);
		
		PostCode.clear();	
		PostCode.sendKeys(postCode);
		
		MobileNumber.clear();	
		MobileNumber.sendKeys(mobileNumber);
		
		FullAddress.clear();	
		FullAddress.sendKeys(fullAddress);
		FullAddress.sendKeys(Keys.ENTER);
		return driver.getCurrentUrl();
	}
}
