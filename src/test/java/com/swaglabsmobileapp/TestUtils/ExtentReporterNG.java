package com.swaglabsmobileapp.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent;
	//ExtentReports extent;

	
	/*public void ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir") + "/Reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.getConf().setReportName("Web Automation Results");
		reporter.getConf().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter); // attach report conf to main report
		extent.setSystemInfo("Tester", "Gilberto Lizarraga");
		return extent;
	}*/
	
	// Method changed to static to avoid create an object to access to it
	public static ExtentReports getReporterObject() {
		extent = new ExtentReports();
		String path = System.getProperty("user.dir") + "/Reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.getConf().setReportName("Web Automation Results");
		reporter.getConf().setDocumentTitle("Test Results");

		//extent = new ExtentReports();
		extent.attachReporter(reporter); // attach report conf to main report
		extent.setSystemInfo("Tester", "Gilberto Lizarraga");
		return extent;

	}

}
