package br.ca.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FunctionalTest {

	WebDriver driver;
	
	@Before
	public void before() {
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Clicar no botão para adicionar nova tarefa
		driver.findElement(By.id("addTodo")).click();
		
		//Preencher o campo descrição
		driver.findElement(By.id("task")).sendKeys("Descricao automatizada");
				
		//Preencher o campo data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2100");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Verificar mensagem de sucesso.
		String Mensagem = driver.findElement(By.id("message")).getText().toString();
		
		Assert.assertEquals("Success!", Mensagem);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemNome() {
		
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Clicar no botão para adicionar nova tarefa
		driver.findElement(By.id("addTodo")).click();
		
		//Preencher o campo descrição
		//driver.findElement(By.id("task")).sendKeys("Descricao automatizada");
				
		//Preencher o campo data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2100");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Verificar mensagem de sucesso.
		String Mensagem = driver.findElement(By.id("message")).getText().toString();
		
		Assert.assertEquals("Fill the task description", Mensagem);
	}	
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Clicar no botão para adicionar nova tarefa
		driver.findElement(By.id("addTodo")).click();
		
		//Preencher o campo descrição
		driver.findElement(By.id("task")).sendKeys("Descricao automatizada");
				
		//Preencher o campo data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2000");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Verificar mensagem de sucesso.
		String Mensagem = driver.findElement(By.id("message")).getText().toString();
		
		Assert.assertEquals("Due date must not be in past", Mensagem);
	}	
	
	@After
	public void after() {
		driver.quit();
	}
	
}
