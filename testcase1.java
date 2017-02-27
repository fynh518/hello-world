/**
 * Created with IntelliJ IDEA.
 * User: yfu1
 * Date: 2/22/17
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class testcase1 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\yfu1\\tools\\geckodriver\\geckodriver.exe");
        String proxyIp = "web-proxy.sgp.hpecorp.net";
        int proxyPort = 8080;
        FirefoxProfile profile = new FirefoxProfile();
        // 使用代理
        profile.setPreference("network.proxy.type", 1);
        // http协议代理配置
        profile.setPreference("network.proxy.http", proxyIp);
        profile.setPreference("network.proxy.http_port", proxyPort);

        profile.setPreference("network.proxy.ssl", proxyIp);
        profile.setPreference("network.proxy.ssl_port", proxyPort);

        // 所有协议公用一种代理配置，如果单独配置，这项设置为false
        profile.setPreference("network.proxy.share_proxy_settings", true);

        // 对于localhost的不用代理，这里必须要配置，否则无法和webdriver通讯
        profile.setPreference("network.proxy.no_proxies_on", "localhost");

        // 以代理方式启动firefox
        driver  = new FirefoxDriver(profile);
        baseUrl = "https://www.google.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testJUnit4Webdriver() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("StormRunner");
        Thread.sleep(3000);
        driver.findElement(By.className("sbqs_c")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("StormRunner Load - HP")).click();
        Thread.sleep(5000);
        /*
         * driver.findElement(By.id("try-cta")).click(); Thread.sleep(5000);
         * driver.findElement(By.linkText("Home")).click();
         */
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}


