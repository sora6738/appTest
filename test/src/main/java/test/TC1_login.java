package test;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;



public class TC1_login {
	public static AppiumDriver<AndroidElement> driver;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static final String TMON_ID = "fhwm21";
    public static final String TMON_PW = "abc12@3";
    public static final String TMON_WrongPW = "tmn123!";
    
    static AndroidDriver<MobileElement> wd;

	@BeforeClass
	public void setUp() throws Exception {
	    capabilities.setCapability("appium-version", "{1.18.2}");
	    capabilities.setCapability("automationName", "UiAutomator2");
    	//aapt ��ɾ ���� �Է� �� ����(�׽�Ʈ �� ���� ��Ű�� ��)
		capabilities.setCapability("appPackage", "com.tmon");
   
        //aapt ��ɾ ���� �Է� �� ����(�׽�Ʈ �� ���� launchable-activity ����)
		capabilities.setCapability("appActivity", "com.tmon.splash.SplashActivity"); 
        
        //�ڽ��� ����̽� ���� ����. �ƹ� �̸��̳� ��� �������ϴ�.
		capabilities.setCapability("deviceName", "unauthorized");
        
        //adb ��ɾ ���� �Է� �� ����(�ڱ� ����̽��� udid)
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
	
	/*
	 * public ���� ����: public ���� ������ ��� ��Ű������ �ƹ��� ���� ���� �����ڸ� ȣ���� �� �ֵ��� �մϴ�
	 * private ���� ����: private ���� ������ ������ ��Ű���̰� �ٸ� ��Ű���̰� ������� �����ڸ� ȣ������ ���ϵ��� �����մϴ�. 
	 * 					������ Ŭ���� ���ο����� �����ڸ� ȣ���� �� �ְ� ��ü�� ���� �� �ֽ��ϴ�.
	 */
	
	// ����Ƽ������ �̵�
    private static void moveToMyTmon() {
    	driver.findElement(By.id("com.tmon:id/tabbar_mytmon_layout")).click();   
        System.out.println("move to mytmon.");
    }    
    
    
    //�α����ϱ�
    private static void logIn() {
    	//ID �Է�
  		driver.findElement(By.id("com.tmon:id/username")).setValue(TMON_ID);
  		//PW �Է�
  		driver.findElement(By.id("com.tmon:id/password")).setValue(TMON_PW);
  		//�α��� ��ư Ŭ��
  		driver.findElement(By.id("com.tmon:id/btn_login")).click();
    }
    
  
    //Ȩ���� �̵�
    private static void home() {
    	//Ȩ���� �̵�
    	driver.findElement(By.id("com.tmon:id/tabbar_home_layout")).click();
    }
    
    
	
	// TC1_�α��� ����
	@Test      
	public void TC1() throws Exception {
		//����Ƽ������ �̵�
		moveToMyTmon();
		//�α��� ��ư Ŭ��
		driver.findElement(By.id("com.tmon:id/btnLogin")).click();
		//�ڵ��α��� ���� 
		driver.findElement(By.id("com.tmon:id/btn_autologin")).click();
		//ID �Է�
		driver.findElement(By.id("com.tmon:id/username")).setValue(TMON_ID);
		//PW �Է�(�߸��� ��)
		driver.findElement(By.id("com.tmon:id/password")).setValue(TMON_WrongPW);
		//�α��� ��ư Ŭ��
		driver.findElement(By.id("com.tmon:id/btn_login")).click();
		
		//TC 1 ���� �޽��� ���
		System.out.println("TC_1. PW �߸� �Է� �Ͽ� login ���� ���̽� ����");
	}
	
	
	// �α��� ����
	@Test
	public void TC2() throws Exception {
		
		logIn();
		Thread.sleep(2000);
		
		//TC2 ���� �޽��� ���
		System.out.println("TC_2.login ���� ���̽� ����");
	}
	
	// �α׾ƿ�
	@Test
	public void TC3() throws Exception{
		//���� ������
		driver.findElement(By.id("com.tmon:id/slimNavibarSetting")).click();
		//�α��� �������� 
		driver.findElement(By.id("com.tmon:id/username")).click();
		//�α� �ƿ� ��ư
		driver.findElement(By.id("com.tmon:id/btn_logout")).click();
		
		//�ݱ� ��ư
		driver.findElement(By.xpath("//android.widget.ImageButton")).click();
		Thread.sleep(2000);
		
		//TC2 ���� �޽��� ���
		System.out.println("TC_3.logout ���̽� ����");
				
	}
	

	
	@AfterClass
	public void end() throws Exception {
		driver.quit();
	}

}
