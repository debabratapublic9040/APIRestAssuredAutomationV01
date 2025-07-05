package api.utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;//UI of the report
	public ExtentReports extent;//populate common info of the report
	public ExtentTest test;//creating test case entries in the report and update status of test methods
	
	String repName;
	
	public void onStart(ITestContext testContext){
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);

		sparkReporter.config().setDocumentTitle("My Test-Report");
		sparkReporter.config().setReportName("Pet Store User Api");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application Name", "Pet Store User Api");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Module", "User");
		
		String os=  testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
	  }
	  
	  public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());//create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,result.getName()+" got successfully executed");// update the test case status(Fail/Pass/Skip)
		
	  } 
	  public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());//create new entry in the report
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
			
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	  }
	  public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());//create new entry in the report
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
			
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	  }
	  public void onFinish(ITestContext context) {
		  
		  extent.flush();
		  
	  }

}
