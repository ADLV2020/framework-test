package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Page_Wikipedia {
	
	WebDriver drv;
	public Page_Wikipedia(WebDriver driver) {
		drv=driver;
		PageFactory.initElements(driver,this);}
	
	// FIND BY HOW AND USING
	@FindBy(how = How.XPATH, using="//p")
	private List<WebElement> pParrafos; 
	
	// USE DEFINED ELEMENT
	public String search_textOnParagraph(String textToSearch) {
		String txtResult = "";
		for ( WebElement el : pParrafos) {
			if ( (el.getText()).contains(textToSearch) ) {
				txtResult = el.getText();
				Actions actions = new Actions(drv);
				actions.moveToElement(el);
				actions.perform();
				break;
			}
		}
		return txtResult;
	}
	public String search_andSplitParagraph(String paragraph, String textToSearch) {
		String arrSplit[] = paragraph.split("[.]");
		String txtResult = "";
		for ( int x = 0; x < arrSplit.length; x++ ) {
			txtResult = arrSplit[x];
			if ( txtResult.contains(textToSearch) ) {
				Arrays.fill(arrSplit, null);
				txtResult = txtResult.replace(",", "").replace(";", "");
				//arrSplit = txtResult.split(" ");
				break;
			}
		}
		return txtResult;
	}
	public String return_ageInText(String textToSearch) {
		String arrSplit[] = textToSearch.split(" ");
		String yearFind = "";
		for ( int k = 0; k < arrSplit.length; k++ ) {
			if ( arrSplit[k] != null && arrSplit[k].matches("[0-9]+") ) {
				if ( arrSplit[k].length() == 4 ) {
					System.out.println("RESULTADO: El año del acontecimiento es en el año " + arrSplit[k]);
					yearFind = arrSplit[k];
					Assert.assertTrue("Se obtuvo el año del acontecimiento buscado.", true);
				} else {
					Assert.assertFalse("No se pudo encontrar el año del acontecimiento. Verificar.", false);;
				}
			}
		}
		return  yearFind;
	}
	
}
