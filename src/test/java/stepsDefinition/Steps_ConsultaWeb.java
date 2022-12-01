package stepsDefinition;

import context.TestContext;
import pageObjects.Page_sample_Scenario;

import org.junit.Assert;
import context.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_ConsultaWeb {
	
	TestContext tst;
	Page_sample_Scenario home;
	public Steps_ConsultaWeb(TestContext context) {
		tst=context;
		home=tst.getPageObjMng().getHome();
	}
	
	@Given("^accedo al buscador de Google$")
	public void accedo_al_buscador_de_Google() throws Throwable {
	    System.out.println("Step abre Google");
	}

	@Given("^busco por la palabra \"([^\"]*)\" en Google$")
	public void busco_por_la_palabra_en_Google(String arg1) throws Throwable {
		System.out.println("Step busca en Google");
	}

	@When("^selecciono el resultado para \"([^\"]*)\" en la lista$")
	public void selecciono_el_resultado_para_en_la_lista(String arg1) throws Throwable {
		System.out.println("Step Selecciona en Google");
	}

	@When("^analizo los parrafos para encontrar \"([^\"]*)\" en la oraci�n$")
	public void analizo_los_parrafos_para_encontrar_en_la_oraci_n(String arg1) throws Throwable {
		System.out.println("Step analizo los parrafos wn la Wiki");
	}

	@Then("^obtengo el a�o del acontecimiento del hecho$")
	public void obtengo_el_a_o_del_acontecimiento_del_hecho() throws Throwable {
		System.out.println("Step obtengo los resultados");
	}
	
}
