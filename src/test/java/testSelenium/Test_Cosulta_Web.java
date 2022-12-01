package testSelenium;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test_Cosulta_Web {
	
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.out.println("STEP: Inicilaizando el navegador y accediendo a la página de Goolge.");
		System.setProperty("webdriver.chrome.driver",".\\\\configs\\\\drivers\\\\chromedriver.107.0.5304.121.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://google.com");
		
		System.out.println("STEP: Realizando la busqueda del text 'automatización' en Google.");
		WebElement inputSearch = driver.findElement(By.cssSelector("input.gLFyf"));
		inputSearch.sendKeys("automatización");
		inputSearch.sendKeys(Keys.ENTER);
		
		System.out.println("STEP: Buscando un resultado que sea de Wikipedia en la lista de la primera página.");
		List<WebElement> href = driver.findElements(By.xpath("//div[@id='search']//a[contains(@href,'http')]"));
		String lnkUrl = null;
		for ( WebElement elm : href ) {
			if ( (elm.getText()).contains("Wikipedia") ) {
				lnkUrl = elm.getAttribute("href");
				driver.navigate().to(lnkUrl);
				break;
			}
		}
		
		System.out.println("STEP: Analizando los parrafos en busca de la cadena 'primer proceso' en el texto.");
		List<WebElement> pParrafos = driver.findElements(By.xpath("//p"));
		String texto = "primer proceso";
		String txtResult = "";
		for ( WebElement el : pParrafos) {
			if ( (el.getText()).contains(texto) ) {
				txtResult = el.getText();
				Actions actions = new Actions(driver);
				actions.moveToElement(el);
				actions.perform();
				break;
			}
		}
		
		System.out.println("STEP: Formateando el parrafo obtenido en busca de la oración en particular.");
		String arrSplit[] = txtResult.split("[.]");
		for ( int x = 0; x < arrSplit.length; x++ ) {
			txtResult = arrSplit[x];
			if ( txtResult.contains(texto) ) {
				Arrays.fill(arrSplit, null);
				txtResult = txtResult.replace(",", "").replace(";", "");
				arrSplit = txtResult.split(" ");
				break;
			}
		}
		
		System.out.println("STEP: Buscando dentro de la oración, el año del acontecimiento buscado.");
		for ( int k = 0; k < arrSplit.length; k++ ) {
			if ( arrSplit[k] != null && arrSplit[k].matches("[0-9]+") ) {
				if ( arrSplit[k].length() == 4 ) {
					System.out.println("RESULTADO: El año del acontecimiento '" + texto + "' de automatización es " + arrSplit[k]);
					Assert.assertTrue("Se obtuvo el año del acontecimiento buscado.", true);
				} else {
					Assert.assertFalse("No se pudo encontrar el año del acontecimiento. Verificar.", false);;
				}
			}
		}
		
		
		System.out.println("STEP: Realizando la captura de pantalla del resultado.");
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(".\\target\\reportesCucumber\\reporteExtent\\"+"test_ConsultaWeb.png");
		FileUtils.copyFile(SrcFile, DestFile);
		
		driver.quit();
		
	}

}
