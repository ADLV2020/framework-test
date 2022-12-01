package stepsDefinition;

import context.TestContext;
import pageObjects.Page_sample_Scenario;
import pageObjects.Page_Google;
import pageObjects.Page_Wikipedia;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;

public class Steps_ConsultaWeb {
	
	TestContext tst;
	Page_Google google;
	Page_Wikipedia wikipedia;
	public Steps_ConsultaWeb(TestContext context) {
		tst=context;
		google=tst.getPageObjMng().getGooglePage();
	}
	
	@Given("^accedo al buscador de Google$")
	public void accedo_al_buscador_de_Google() throws Throwable {
	    System.out.println("Step abre Google");
	    google.to_GoogleSearch();
	}

	@Given("^busco por la palabra \"([^\"]*)\" en Google$")
	public void busco_por_la_palabra_en_Google(String searchText) throws Throwable {
		System.out.println("Step busca en Google");
		google.enter_textSearch(searchText);
	}

	@When("^selecciono el resultado para \"([^\"]*)\" en la lista$")
	public void selecciono_el_resultado_para_en_la_lista(String searchPage) throws Throwable {
		System.out.println("Step Selecciona en Google");
		google.search_specificPage(searchPage);
	}

	@When("^analizo los parrafos para encontrar \"([^\"]*)\" en la oraci�n$")
	public void analizo_los_parrafos_para_encontrar_en_la_oraci_n(String textOnParagraph) throws Throwable {
		System.out.println("Step analizo los parrafos en la Wiki . Texto a buscar " + textOnParagraph);
		tst.scenContext.setContext(Context.DATA_INFO_A, textOnParagraph);
		String textParagraph = google.search_textOnParagraph(textOnParagraph);
		tst.scenContext.setContext(Context.DATA_INFO_B, textParagraph);
	}

	@Then("^obtengo el a�o del acontecimiento del hecho$")
	public void obtengo_el_a_o_del_acontecimiento_del_hecho() throws Throwable {
		System.out.println("Step obtengo los resultados");
		System.out.println(tst.scenContext.getContext(Context.DATA_INFO_A));
		String paragraph = google.search_andSplitParagraph((String) tst.scenContext.getContext(Context.DATA_INFO_B), (String) tst.scenContext.getContext(Context.DATA_INFO_A));
		System.out.println("AÑO => " + google.return_ageInText(paragraph));
	}
	
}
