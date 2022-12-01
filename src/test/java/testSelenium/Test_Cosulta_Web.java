package testSelenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Cosulta_Web {
	
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",".\\\\configs\\\\drivers\\\\chromedriver.107.0.5304.121.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://google.com");

		WebElement inputSearch = driver.findElement(By.cssSelector("input.gLFyf"));
		inputSearch.sendKeys("automatización");
		inputSearch.sendKeys(Keys.ENTER);
		
		List<WebElement> href = driver.findElements(By.xpath("//div[@id='search']//a[contains(@href,'http')]"));
		
		String lnkUrl = null;
		for ( WebElement elm : href ) {
			if ( (elm.getText()).contains("Wikipedia") ) {
				lnkUrl = elm.getAttribute("href");
				System.out.println("\n<<<<<<<<< " + elm.getText() + " ====== " + lnkUrl + " >>>>>>>>>>\n");
				break;
			}
		}
		
		if (lnkUrl != null) {
			driver.navigate().to(lnkUrl);
		}

		List<WebElement> pParrafos = driver.findElements(By.xpath("//p"));
		
		String texto = "primer proceso";
		String txtResult = "";
		for ( WebElement el : pParrafos) {
			if ( (el.getText()).contains(texto) ) {
				System.out.println("\n <<<<<<<<< " + el.getText() + " >>>>>>>>>>\n");
				txtResult = el.getText();
				break;
			}
		}
		
		System.out.println(">>>>>>>>>>> contenido del texto " + txtResult);
		String arrSplit[] = txtResult.split("[.]");
		System.out.println(">>>>>>>>>>> Array Construcción tamaño " + arrSplit.length);
		for ( int x = 0; x < arrSplit.length; x++ ) {
			System.out.println(">>>>>>>>>>> Array posicion " + x + " resultado = " + arrSplit[x]);
			txtResult = arrSplit[x];
			if ( txtResult.contains(texto) ) {
				System.out.println(">>>>>>>>>>> <<<<<<<<<<<<<<<<< ");
				break;
			}
		}
		/*
		ArrayList<Character> lista = new ArrayList<>();
		for ( int x = 0; x < txtResult.length(); x++ ) {
			if ( Character.isDigit(txtResult.charAt(x) ) ) {
				System.out.println(">>>>>>>>>>> AGREGA posicion " + x );
				lista.add(txtResult.charAt(x));
			}
		}
		
		for ( int j = 0 ; j < lista.size(); j++ ) {
			System.out.println(lista.get(j));
		}
		*/
		Arrays.fill(arrSplit, null);
		txtResult = txtResult.replace(",", "").replace(";", "");
		arrSplit = txtResult.split(" ");
		for ( int k = 0; k < arrSplit.length; k++ ) {
			System.out.println(" <<<<<<<<<<<< Array posicion " + k + " resultado = " + arrSplit[k]);
		}
		
		driver.close();
		
	}

}
