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
    	//aapt ���ɾ ���� �Է� �� ����(�׽�Ʈ �� ���� ��Ű�� ��)
		capabilities.setCapability("appPackage", "com.tmon");
   
        //aapt ���ɾ ���� �Է� �� ����(�׽�Ʈ �� ���� launchable-activity ����)
		capabilities.setCapability("appActivity", "com.tmon.splash.SplashActivity"); 
        
        //�ڽ��� ����̽� ���� ����. �ƹ� �̸��̳� ��� �������ϴ�.
		capabilities.setCapability("deviceName", "unauthorized");
        
        //adb ���ɾ ���� �Է� �� ����(�ڱ� ����̽��� udid)
		capabilities.setCapability("udid", "18281FDF60053U");
        
        // Ű����
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		
		//���¿� ���� ����
		capabilities.setCapability("noReset", true);
		
		capabilities.setCapability("autoDismissAlerts", true);
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
    // �˻�
    private static void search() throws Exception {
    	
    	//�˻�ȭ�� �̵�
    	driver.findElement(By.id("com.tmon:id/tabbar_search_layout")).click();
    	System.out.println("�˻�ȭ�� �̵� ����");
    
    	
    	//�˻��� �Է�
    	AndroidElement search = driver.findElement(By.xpath("//android.widget.EditText"));
		search.sendKeys("����ũ");
		System.out.println("�˻��� �Է� ����");
    	
		//������ ��ư Ŭ��
    	driver.findElement(By.xpath("//android.widget.Button[@text='�˻�']")).click();
    	System.out.println("������ ��ư Ŭ�� ����");
    	Thread.sleep(4000);

    	
    	//����� 
    	String result = driver.findElementByXPath("//android.view.View").getText();
        Assert.assertNotNull(result);
        System.out.println("������� Null �ƴ�");
        
        //����� ���� ����Ʈ : https://blog.naver.com/justice2250/222807500609
        
        

    	
    }
	@Test
	public void searchTest() throws Exception{
		search();
		Thread.sleep(10000);
		//TC4 ���� �޽��� ���
		System.out.println("TC_4.�˻� ����");

	}
	
	@AfterClass
	public void end() throws Exception {
		driver.quit();
	}


}