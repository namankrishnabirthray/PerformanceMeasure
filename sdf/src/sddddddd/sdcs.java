package sddddddd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;






public class sdcs 
{
	ExtentHtmlReporter htmlReporter;
	 ExtentReports extent;
	 ExtentTest logger=null;
	 WebDriver driver=null;
	 public static String Path="";
	static int  linkcounts=0;
	public static String GetCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", ".//Driver/chromedriver.exe");
	}
	 
	 @BeforeTest
	 public void startReport(){
		 
	 
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Report/WebTechExtentReport.html");
		 Path = "./Report/WebTech_Automation_Report"+GetCurrentTimeStamp().replace(":","_").replace(".","_")+".html";
		 htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		 htmlReporter.getConfigContext();
		 extent=new ExtentReports();
	     extent.setSystemInfo("Host Name", "Naman Krishna Birthray");
	     htmlReporter.config().setReportName("Removal Index.html-WebTech");
	     htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	     htmlReporter.config().getTheme();
	     extent.attachReporter(htmlReporter);
	    }
	 @Test
	 public void failTest(){
	        driver = new ChromeDriver();
	        driver.get("http://www.oracle.com");
	        logger = extent.createTest("Pass");
	        String[][] data1 = new String[1][3];
	        String[][] data = new String[1][3];
	        data1[0][0]="status"; 
	        data1[0][1]="Page Title";
	        data1[0][2]="URL";
	        logger.log(Status.INFO,MarkupHelper.createTable(data1));
	       
	                            List<WebElement> linksize =  driver.findElements(By.tagName("a")); 
	                            linkcounts = linksize.size();
	                            System.out.println(linkcounts);
	                            String []links=new String[linkcounts];
	                            for(int i=0;i< 1;i++)
	                             {
	                             links[i] = linksize.get(i).getAttribute("href");
	                             } 
	                            for(int i=0;i<1;i++) { 
	                       
	                            	   try{
	                                         driver.navigate().to(links[i]); 
	                                         WebDriverWait wait=new WebDriverWait(driver,5);
	                                         wait.until(ExpectedConditions.alertIsPresent());
	                                     try {
	                                         Alert alert=driver.switchTo().alert();
	                                                     alert.accept();
	                                          }
	                                         catch(NoAlertPresentException a) {
	                                    	   
	                                                                           }
	                              
	                                                 
	                            	       }
	                                   catch(Exception e){
	                             		 
	                             	                     }
	                            
	                        	 String currentUrl=driver.getCurrentUrl();
	                        	        String title= driver.getTitle();
	                        	          data[0][1]=title;
	                        	          data[0][2]=currentUrl;
	                        	         if(currentUrl.contains("index.html")) {
	                        	        	 data[0][0]="301 redairect succsessful";
	                        	        	 logger.log(Status.FAIL,MarkupHelper.createTable(data))  ;
	                        	         }
	                        	         else {
	                        	        	 data[0][0]="301  redairect failed";
	                        	        	 logger.log(Status.FAIL, MarkupHelper.createTable(data));
	                        	         }
	                        	  
	                          }
	 }
	    
	 @AfterMethod
	 public void browserClose() {
		 driver.close();
		 driver=null;
	 }
	 @AfterTest
	 public void endReport(){
		// extent.
		 if(driver==null) {
		  extent.flush();  
		  driver=new ChromeDriver();
		  driver.get("file:///"+System.getProperty("user.dir")+"/" +"/Report/WebTechExtentReport.html".replace("./", ""));
		  driver.manage().window().maximize();
		                  }}
	

}
