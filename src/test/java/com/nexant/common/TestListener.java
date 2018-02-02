package com.nexant.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.nexant.common.pageObjects.checks.TraksmartException;
import com.nexant.webdriver.Driver;

public class TestListener implements ITestListener {
	
	public static final Log log = LogFactory.getLog(TestListener.class);
	

	@Override public void onFinish(ITestContext arg0) {
		log.info("onFinish: " + arg0);
	}

	@Override public void onStart(ITestContext arg0) {
		log.info("onStart: " + arg0);
	}

	@Override public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		log.info("onTestFailedButWithinSuccessPercentage: " + arg0);
	}

	@Override public void onTestFailure(ITestResult arg0) {
		//log.error("test failure: name=" + arg0.getName() + ", testname=" + arg0.getTestName() + ", testclass=" + arg0.getTestClass() + ", arg=" + arg0, arg0.getThrowable());
		TraksmartException.Get_TraksmartException("test failure: name=" + arg0.getName() + ", testname=" + arg0.getTestName() + ", testclass=" + arg0.getTestClass() + ", arg=" + arg0, Driver.get(), arg0.getThrowable());
	}

	@Override public void onTestSkipped(ITestResult arg0) {
		log.info("onTestSkipped: " + arg0);
	}

	@Override public void onTestStart(ITestResult arg0) {
		log.info("onTestStart: " + arg0);
	}

	@Override public void onTestSuccess(ITestResult arg0) {
		log.info("onTestSuccess: " + arg0);
	}

}
