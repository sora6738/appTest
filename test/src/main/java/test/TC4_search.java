package test;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TC4_search {
	public static AppiumDriver<AndroidElement> driver;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
    
    static AndroidDriver<MobileElement> wd;

	@BeforeClass
	public void setUp() throws Exception {
	    capabilities.setCapability("appium-version", "{1.18.2}");
	    capabilities.setCapability("automationName", "UiAutomator2");
    	//aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 패키지 명)
		capabilities.setCapability("appPackage", "com.tmon");
   
        //aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 launchable-activity 정보)
		capabilities.setCapability("appActivity", "com.tmon.splash.SplashActivity"); 
        
        //자신의 디바이스 명을 설정. 아무 이름이나 적어도 괜찮습니다.
		capabilities.setCapability("deviceName", "unauthorized");
        
        //adb 명령어를 통해 입력 할 예정(자기 디바이스의 udid)
		capabilities.setCapability("udid", "18281FDF60053U");
        
        // 키보드
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		
		//리셋에 대한 내용
		capabilities.setCapability("noReset", true);
		
		capabilities.setCapability("autoDismissAlerts", true);
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
    // 검색
    private static void search() throws Exception {
    	
    	//검색화면 이동
    	driver.findElement(By.id("com.tmon:id/tabbar_search_layout")).click();
    	System.out.println("검색화면 이동 성공");
    
    	
    	//검색어 입력
    	AndroidElement search = driver.findElement(By.xpath("//android.widget.EditText"));
		search.sendKeys("마스크");
		System.out.println("검색어 입력 성공");
    	
		//돋보기 버튼 클릭
    	driver.findElement(By.xpath("//android.widget.Button[@text='검색']")).click();
    	System.out.println("돋보기 버튼 클릭 성공");
    	Thread.sleep(4000);

    	
    	//결과값 
    	String result = driver.findElementByXPath("//android.view.View").getText();
        Assert.assertNotNull(result);
        System.out.println("결과값이 Null 아님");
        
        //결과값 참고 사이트 : https://blog.naver.com/justice2250/222807500609
        
        

    	
    }
	@Test
	public void searchTest() throws Exception{
		search();
		Thread.sleep(10000);
		//TC4 성공 메시지 출력
		System.out.println("TC_4.검색 실행");

	}
	
	@AfterClass
	public void end() throws Exception {
		driver.quit();
	}


}
