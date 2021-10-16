package TestCases;



import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortFolioLoginPage;
import baseClasses.BaseTestClass;

import baseClasses.PageBaseClass2;
import utilities.ConstantValue;
import utilities.TestDataProvider;

public class MyStockTest extends BaseTestClass{
	LandingPage landingPage;
	MoneyPage moneyPage;
	PortFolioLoginPage portfolioLoginPage;
	MyPorfolioPage myporfolioPage;
	
	
	@Test(dataProvider="AddStockTestData")
	public void AddStockTest(Hashtable<String, String> testData){
		logger = report.createTest("Add Stock in: " + testData.get("PortfolioName") + " - Stock Name" + testData.get("StockName"));
		
		invokeBrowser("chrome");
		PageBaseClass2 pageBase = new PageBaseClass2(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin(ConstantValue.userName, ConstantValue.password);
		myporfolioPage.verifyMoneyBiz();
		/*
		myporfolioPage.clickCreatePortfolio();
		waitForPageLoad();
		myporfolioPage.enterPortfolioName(testData.get("PortfolioName"));
		myporfolioPage = myporfolioPage.submitPortfolio();
		waitForPageLoad();
		myporfolioPage.isPorfolioExists(testData.get("PortfolioName"));
		*/
		myporfolioPage.clickAddStock();
		myporfolioPage.enterStockName(testData.get("StockName"));
		myporfolioPage.clickStockPurchaseCalendar();
		selectDateIncalendar("13/10/2021");
		myporfolioPage.enterStockQuantity(testData.get("Quantity"));
		myporfolioPage.enterStockPrice(testData.get("StockPrice"));
		myporfolioPage = myporfolioPage.submitStock();
		waitForPageLoad();
		myporfolioPage.verifyStock(testData.get("StockName"));
		
		
	}
	
	@DataProvider
	public Object[][] AddStockTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "StockTestData", "AddStockTest");
	}

}
