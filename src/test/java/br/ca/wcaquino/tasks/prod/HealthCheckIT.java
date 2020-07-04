package br.ca.wcaquino.tasks.prod;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.junit.*;

public class HealthCheckIT {
	
	@Test
	public void healthCheckTest() throws MalformedURLException {
		WebDriver driver = null;
		try {
			
			DesiredCapabilities browser = DesiredCapabilities.chrome();
			
			//driver = new ChromeDriver();
			driver = new RemoteWebDriver(new URL("http://192.168.99.100:9999/tasks"), browser);
			driver.navigate().to("http://localhost:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			assertTrue(version.contains("build_"));
		} finally {
			// TODO: handle finally clause
			driver.quit();
		}
	}

}
