package managers;

import org.openqa.selenium.WebDriver;

import base.GlobalParams;
import base.GlobalRest;
import pageObjects.Page_DemoBDDPage;
import pageObjects.PageExampleE2E;
import pageObjects.Page_sample_Scenario;
import pageObjects.Page_sample_ScenarioOutline;
import pageObjects.Page_Google;
import pageObjects.Page_Wikipedia;

public class StartPagesMng {

	private WebDriver driver;
	public StartPagesMng(WebDriver driver) {
		this.driver=driver;}
	
	// INIT PAGE 
	private PageExampleE2E end2end;
	private Page_sample_Scenario home;
	private Page_sample_ScenarioOutline check;
	private Page_DemoBDDPage demoBDDPage;
	private Page_Google google;
	private Page_Wikipedia wikipedia;
	public PageExampleE2E getEnd2End() {
		return(end2end==null)?end2end=new PageExampleE2E(driver):end2end;}
	public Page_sample_Scenario getHome() {
		return(home==null)?home=new Page_sample_Scenario(driver):home;}
	public Page_sample_ScenarioOutline getPersonas() {
		return(check==null)?check=new Page_sample_ScenarioOutline(driver):check;}
	public Page_DemoBDDPage getDemoBDDPage() {
		return (demoBDDPage == null) ? demoBDDPage = new Page_DemoBDDPage(driver) : demoBDDPage;}
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
