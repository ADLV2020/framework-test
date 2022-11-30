package stepsDefinition;

import org.junit.Assert;

import context.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.Page_sample_ScenarioOutline;
import base.GlobalParams;

public class Steps_sample_ScenarioOutline {

	TestContext tst;
	Page_sample_ScenarioOutline personas;
	GlobalParams params;
	public Steps_sample_ScenarioOutline(TestContext context) {
		tst=context;
		personas=tst.getPageObjMng().getPersonas();
		params=tst.getPageObjMng().getGlobalParams();
	}
	
	@When("^hago clic en el menu Personas de la pagina Persona$")
	public void hago_clic_en_el_menu_Personas_de_la_pagina_Personas() throws Throwable {
	    // 
		personas.clic_Sueldo();
	}
	
	@When("^hago clic en el icono Lupa de la pagina Paquetes$")
	public void hago_clic_en_el_icono_Lupa_de_la_pagina_Paquetes() throws Throwable {
	    // 
		personas.clic_Lupa();
	}

	@When("^ingreso la opcion \"([^\"]*)\" para la busqueda$")
	public void ingreso_la_opcion_para_la_busqueda(String sBuscar) throws Throwable {
	    // 
		personas.enter_Busqueda(sBuscar);
		params.setString_A(sBuscar);
	}

	@Then("^obtengo \"([^\"]*)\" coincidencias como resultado de la busqueda$")
	public void obtengo_coincidencias_como_resultado_de_la_busqueda(String resultados) throws Throwable {
	    // 
		System.out.println(personas.get_Resultados());
		Assert.assertEquals("No coinciden los valores. Verificar. ", resultados, personas.get_Resultados());
	}
	
	@Given("^hago clic en la opcion Contactanos de la pagina Personas$")
	public void hago_clic_en_la_opcion_Contactanos_de_la_pagina_Personas() throws Throwable {
	    // 
		personas.clic_Contactanos();
		String algoS=params.getString_A();
	}

	@Given("^hago clic en la sopala Internet de la pagina Contactanos$")
	public void hago_clic_en_la_sopala_Internet_de_la_pagina_Contactanos() throws Throwable {
	    // 
		personas.clic_Internet();
	}

	@Given("^hago clic en el link Formulario Web de la pagina Contactanos$")
	public void hago_clic_en_el_link_Formulario_Web_de_la_pagina_Contactanos() throws Throwable {
	    // 
		personas.clic_FormWeb();
	}

	@When("^hago clic en el boton \"([^\"]*)\" de la pagina Gestion de reclamos$")
	public void hago_clic_en_el_boton_de_la_pagina_Gestion_de_reclamos(String boton) throws Throwable {
	    // 
		switch (boton) {
		case "Servicios":
			personas.clic_Servicos();
			break;
		case "Otros reclamos":
			personas.clic_OtrosServicos();
			break;
		case "Continuar":
			personas.clic_Continuar();
			break;
		case "Enviar":
			personas.clic_Lupa();
			break;
		}
	}

	@When("^ingreso \"([^\"]*)\" como detalle de contacto de la pagina Gestion de reclamos$")
	public void ingreso_como_detalle_de_contacto_de_la_pagina_Gestion_de_reclamos(String detalle) throws Throwable {
	    // 
		personas.enter_Detalle(detalle);
	}

	@When("^completo \"([^\"]*)\", mi \"([^\"]*)\", mi \"([^\"]*)\", mi \"([^\"]*)\" como datos de contacto$")
	public void completo_mis_como_datos_de_contacto(String nombre, String dni, String email, String telefono) throws Throwable {
	    // 
	}

	@Then("^obtengo un mensaje de confirmación$")
	public void obtengo_un_mensaje_de_confirmación() throws Throwable {
	    // 
	}

	@Given("^accedo al login$")
	public void accedo_al_login() throws Throwable {
	    System.out.println("PASO UNO");
	}

	@When("^ingreso usuario \"([^\"]*)\"$")
	public void ingreso_usuario(String arg1) throws Throwable {
		System.out.println("Se uso el dato "+arg1);
	}

	@When("^ingreso clave \"([^\"]*)\"$")
	public void ingreso_clave(String arg1) throws Throwable {
		System.out.println("Se uso el dato "+arg1);
	}

	@Then("^valido el logeo \"([^\"]*)\"$")
	public void valido_el_logeo(String arg1) throws Throwable {
		System.out.println("Se uso el dato "+arg1);
	}
	
	
}
