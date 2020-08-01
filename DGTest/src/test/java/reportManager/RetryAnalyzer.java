package reportManager;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//this is to make sure tests get run four times before marking failed
public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 4;
	
	@Override
	public boolean retry(ITestResult result) {		
		if(counter < retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	}
}
