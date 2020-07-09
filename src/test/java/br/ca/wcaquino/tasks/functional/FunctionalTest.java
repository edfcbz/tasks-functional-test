package br.ca.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FunctionalTest {
	private WebDriver driver;
		
	public WebDriver acessarAplicacao() throws MalformedURLException {
		try {
			DesiredCapabilities browser = DesiredCapabilities.chrome();
			
			driver = new ChromeDriver();
			//driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), browser);
			driver.navigate().to("http://localhost:8001/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
			
		} catch (Exception e) {
			System.out.println("Nao inicializou o driver");
			return driver;
		}
		
	}	
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			//Clicar no botão de adicionar tarefa
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher o campo descri��o
			driver.findElement(By.id("task")).sendKeys("Descricao automatizada atraves do hub");
			
			//Preencher o campo data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2100");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Verificar mensagem de sucesso.
			String Mensagem = driver.findElement(By.id("message")).getText().toString();

			Assert.assertEquals("Success!", Mensagem);

		} finally {
			driver.quit();	
		}
		
	}

	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
			//Clicar no botão de adicionar tarefa
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher o campo descri��o
			driver.findElement(By.id("task")).sendKeys("Descricao automatizada atraves do hub");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Verificar mensagem de sucesso.
			String Mensagem = driver.findElement(By.id("message")).getText().toString();

			Assert.assertEquals("Fill the due date", Mensagem);

		} finally {
			driver.quit();	
		}
		
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemNome() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			
			//Clicar no bot�o para adicionar nova tarefa
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher o campo data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2100");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Verificar mensagem de sucesso.
			String Mensagem = driver.findElement(By.id("message")).getText().toString();
			
			Assert.assertEquals("Fill the task description", Mensagem);
		} finally {
			driver.quit();	
		}
		
	}	
	
	
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {

			
			//Clicar no bot�o para adicionar nova tarefa
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher o campo descri��o
			driver.findElement(By.id("task")).sendKeys("Descricao automatizada");
			
			//Preencher o campo data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2000");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Verificar mensagem de sucesso.
			String Mensagem = driver.findElement(By.id("message")).getText().toString();
			
			Assert.assertEquals("Due date must not be in past", Mensagem);
			
		} finally {
			driver.quit();
		}
		
	}	
	
	
}
