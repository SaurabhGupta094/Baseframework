package base;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utility.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    public String sURL;
    String sBrowserName;
    int iPageLoadTimeOut;
    String sOsName;
    String sDriverPath;

    final String configPropertiesFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "config" + File.separator
            + "Configuration.properties";

    final String sDriversFolderPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
            + File.separator + "resources" + File.separator + "drivers" + File.separator;

    final String sWinChromeDriverPath = sDriversFolderPath + "chromedriver.exe";
    final String sWinGeckoDriverPath = sDriversFolderPath + "geckodriver.exe";
    final String sLinChromeDriverPath = sDriversFolderPath + "chromedriverlin";
    final String sLinGeckoDriverPath = sDriversFolderPath + "geckodriverlin";
    final String sMacChromeDriverPath = sDriversFolderPath + "chromedrivermac";
    final String sMacGeckoDriverPath = sDriversFolderPath + "geckodrivermac";


    String sTestDataPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
            + File.separator + "resources" + File.separator + "TestData" + File.separator;

    FileInputStream fis;
    Properties configProp;
    Properties properties;

    public WebDriver driver;

    /**
     *
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp() {

    }

    /**
     * Purpose of this method is to set the configuration properties file and to
     * load configuration properties into working variables
     */
    @BeforeClass(alwaysRun = true)
    public void setUpConfigProperties() {
        try {
            fis = new FileInputStream(configPropertiesFilePath);
            configProp = new Properties();
            configProp.load(fis);
            loadConfigProperties();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Error occured while loading configuration properties file");
        }
    }

    /**
     * purpose of this method is to assign value from Configuration properties
     */
    public void loadConfigProperties() {
        sURL = configProp.getProperty("URL");
        sBrowserName = configProp.getProperty("BrowserName");
        iPageLoadTimeOut = Integer.parseInt(configProp.getProperty("PAGELOADTIMEOUT"));

    }

    /**
     * purpose of this method is to load testdata properties file so that it can be
     * used to access test data from properties file to testcase
     *
     * @param sPropertiesFileName
     */
    public void setTestDataProperties(String sPropertiesFileName) {
        sTestDataPath = sTestDataPath + sPropertiesFileName + ".properties";
        try {
            fis = new FileInputStream(sTestDataPath);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occured while loading test data");
        }
    }

    /**
     * Reusable method to get value using key provided in testcase properties file.
     *
     * @param sKey
     * @return
     */
    public String getProperty(String sKey) {
        String sValue = properties.getProperty(sKey);
        return sValue;

    }

    public WebDriver getWebDriver() {
        return driver;
    }


//    final String sWinChromeDriverPath = sDriversFolderPath + "chromedriver.exe";
//    final String sWinGeckoDriverPath = sDriversFolderPath + "geckodriver.exe";
//    final String sLinChromeDriverPath = sDriversFolderPath + "chromedriverlin";
//    final String sLinGeckoDriverPath = sDriversFolderPath + "geckodriverlin";
//    final String sMacChromeDriverPath = sDriversFolderPath + "chromedrivermac";
//    final String sMacGeckoDriverPath = sDriversFolderPath + "geckodrivermac";

    /**
     * purpose of this method is to launch browser based on browsername parameters
     * provided from xml file or by default provided by configuration properties
     *
     * @param parametersBrowsername
     */
    @Parameters({"browsername"})
    @BeforeMethod(alwaysRun = true)
    public void launchBrowser(@Optional("") String parametersBrowsername) {
        if (parametersBrowsername.length() > 0) {
            sBrowserName = parametersBrowsername;
        }

        sOsName = System.getProperty("os.name").toLowerCase();

        if (sOsName.contains("win")) {
            //Windows based drivers
            System.out.println("Loading windows driver");
            if (sBrowserName.toLowerCase().contentEquals("chrome")) {
                sDriverPath = sWinChromeDriverPath;
            } else if (sBrowserName.toLowerCase().contentEquals("firefox")) {
                sDriverPath = sWinGeckoDriverPath;
            } else {
                sDriverPath = sWinChromeDriverPath;
            }
        } else if (sOsName.contains("osx")) {
            //Apple Os based drivers
            System.out.println("Loading MAC OS driver");
            if (sBrowserName.toLowerCase().contentEquals("chrome")) {
                sDriverPath = sMacChromeDriverPath;
            } else if (sBrowserName.toLowerCase().contentEquals("firefox")) {
                sDriverPath = sMacGeckoDriverPath;
            } else {
                sDriverPath = sMacChromeDriverPath;
            }

        } else if (sOsName.contains("nix") || sOsName.contains("aix") || sOsName.contains("nux")) {
            //Linux based drivers
            System.out.println("Loading Linux driver");
            if (sBrowserName.toLowerCase().contentEquals("chrome")) {
                sDriverPath = sLinChromeDriverPath;
            } else if (sBrowserName.toLowerCase().contentEquals("firefox")) {
                sDriverPath = sLinGeckoDriverPath;
            } else {
                sDriverPath = sLinChromeDriverPath;
            }

        }
        System.out.println("sBrowserName :" + sBrowserName);
        System.out.println("sOsName :" + sOsName);
        System.out.println("sDriverPath :" + sDriverPath);

        if (sBrowserName.toLowerCase().contentEquals("chrome")) {
            System.setProperty("webdriver.chrome.driver", sDriverPath);
            driver = new ChromeDriver();
            System.out.println("Launching Chrome Browser");
        } else if (sBrowserName.toLowerCase().contentEquals("firefox")) {
            System.setProperty("webdriver.gecko.driver", sDriverPath);
            driver = new FirefoxDriver();
            System.out.println("Launching FireFox Browser");
        } else {
            System.setProperty("webdriver.chrome.driver", sDriverPath);
            driver = new ChromeDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(iPageLoadTimeOut, TimeUnit.SECONDS);
        driver.get(sURL);
    }

    /**
     * purpose of this method is to close the browser
     */
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

}
