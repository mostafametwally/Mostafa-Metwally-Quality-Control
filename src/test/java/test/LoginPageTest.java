package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;
import pages.LoginPage;
import pages.MyAccountPage;
import utilities.ReadExcelFile;

public class LoginPageTest extends Base {

	LoginPage loginPage;
	MyAccountPage MyAccountPage;
	
	
	@BeforeTest
	public void setupTest() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html",true);
	}
	
	@BeforeMethod
	public void setupMethod() {
		Base.initialization();
		loginPage = new LoginPage();
		loginPage.openLoginPage("LoginPageLink");
		MyAccountPage= new MyAccountPage();
	}

	@DataProvider // to read registration data from excel sheet
	public Object[][] readRegistrationDataFromExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "Registration_Data";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);
	}

	@Test(dataProvider = "readRegistrationDataFromExcelFile")
	public void VerifyRegistration(String Email, String Gender, String FirstName, String LastName, String Password,
			String Day, String Month, String Year, String AddressFirstName, String AddressLastName, String CompanyName,
			String StreetAddress, String FloorAddress, String City, String State, String PostCode, String MobileNumber,
			String FullAddress) 
	{
		extentTest = extent.startTest("VerifyRegistration");
		loginPage.createAccount_1(Email);
		String ActualUrl = loginPage.createAccount_2(Gender, FirstName, LastName, Password, Day, Month, Year,
				AddressFirstName, AddressLastName, CompanyName, StreetAddress, FloorAddress, City, State, PostCode,
				MobileNumber, FullAddress);
		String ExpectedUrl = prop.getProperty("MyAccountPageLink");
		Assert.assertEquals(ActualUrl, ExpectedUrl);

	}

	@DataProvider // to read login data from excel sheet
	public Object[][] readLoginDataFromExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "Login_Data";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);
	}

	@Test(dataProvider = "readLoginDataFromExcelFile", priority = 2)
	public void VerifyLogin(String Email, String Password) {
		extentTest = extent.startTest("VerifyLogin");
		Assert.assertTrue(loginPage.login(Email, Password).contains("my-account"));
	}
	
	@Test
	public void VerifyCheckOut() {
		extentTest = extent.startTest("VerifyCheckOut");
		loginPage.login("xyz@mail.com", "Mostaf121");
		String ExpectedMsg= "Your order on My Store is complete.";
		String ActualMsg= MyAccountPage.T_ShirtsCheckOut();		
		Assert.assertEquals(ActualMsg, ExpectedMsg);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) { // in case of failure
			extentTest.log(LogStatus.FAIL, "FAILED:  " + result.getName()); // to add the test name to the extent report
			extentTest.log(LogStatus.FAIL, "ERROR MESSAGE:  " + result.getThrowable()); // to add error/exception 	
			String screenshotPath = Base.takeScreenShot(result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot 
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "SKIPPED:  " + result.getName());
		}else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "PASSED: " + result.getName());
		}
		driver.close();
	}
	
	@AfterTest
	public void tearDownTest() {
		//extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		extent.flush();
	}

}
