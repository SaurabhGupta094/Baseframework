package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	 
    private int retryCount = 0;
    private int maxRetryCount = 1;

    /* 
     * Purpose of this method is to retry tests on failure
     * 
     * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
     */
    public boolean retry(ITestResult result) {

       if (retryCount < maxRetryCount) {
              retryCount++;
              return true;
       }
              return false;
    }

//    @Test(retryAnalyzer = Retry.class)
//    public void testGenX() {
//
//          // ListenerTest Pass
//          Assert.assertEquals("Saibaba", "Saibaba");
//    }
//
//    @Test(retryAnalyzer = Retry.class)
//    public void testGenY() {
//
//           // ListenerTest fails
//           Assert.assertEquals("hello", "World");
//    }
}
