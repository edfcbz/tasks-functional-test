package br.ca.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FunctionalTest {

	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
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
		} finally {
			driver.quit();	
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemNome() {
		WebDriver driver = acessarAplicacao();
		
		try {
			
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
		} finally {
			driver.quit();	
		}
		
	}	
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
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
			
		} finally {
			driver.quit();
		}
		
	}	
	
	
}
