package managers;

import org.openqa.selenium.WebDriver;

import base.GlobalParams;
import base.GlobalRest;
import pageObjects.Page_Google;
import pageObjects.Page_Wikipedia;

public class StartPagesMng {

	private WebDriver driver;
	public StartPagesMng(WebDriver driver) {
		this.driver=driver;}
	
	// INIT PAGE 
	private Page_Google google;
	private Page_Wikipedia wikipedia;
	public Page_Google getGooglePage() {
		return (google == null) ? google = new Page_Google(driver) : google;}
	public Page_Wikipedia getWikipediaPage() {
		return (wikipedia == null) ? wikipedia = new Page_Wikipedia(driver) : wikipedia;}
	
	// INIT BASE 
	private GlobalParams params;
	private GlobalRest rest;
	public GlobalParams getGlobalParams() {
		return(params==null)?params=new GlobalParams():params;}
	public GlobalRest getGlobalRest() {
		return(rest==null)?rest=new GlobalRest():rest;}

}
