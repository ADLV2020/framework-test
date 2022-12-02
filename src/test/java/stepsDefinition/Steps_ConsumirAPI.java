package stepsDefinition;

import cucumber.api.java.en.Given;
import base.GlobalParams;
import context.TestContext;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import base.Functions;

public class Steps_ConsumirAPI {
	
	TestContext tst;
	GlobalParams param;
	Functions functions;
	public Steps_ConsumirAPI(TestContext context) {
		tst=context;
		param=tst.getPageObjMng().getGlobalParams();}
	
	@Given("^tengo a la \"([^\"]*)\" del servicio$")
	public void tengo_a_la_del_servicio(String urlBase) throws Throwable {
		System.out.println("STEP: Obtengo la url del servicio "+ urlBase);
		tst.scenContext.setContext(Context.URL, urlBase);
	}

	@Given("^tengo el \"([^\"]*)\" de la api$")
	public void tengo_el_de_la_api(String endpointApi) throws Throwable {
		System.out.println("STEP: Cuento con el endpoint del servicio " + endpointApi);
		tst.scenContext.setContext(Context.API, endpointApi);
	}

	@When("^ingreso los datos de \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" del body$")
	public void ingreso_los_datos_de_del_body(String userName, String firstName, String lastName, String email, String password, String phone) throws Throwable {
		System.out.println("STEP: Se indican los datos para el nuevo usuario."); 
		String body = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"username\": \""+userName+"\",\r\n"
				+ "  \"firstName\": \""+firstName+"\",\r\n"
				+ "  \"lastName\": \""+lastName+"\",\r\n"
				+ "  \"email\": \""+email+"\",\r\n"
				+ "  \"password\": \""+email+"\",\r\n"
				+ "  \"phone\": \""+phone+"\",\r\n"
				+ "  \"userStatus\": 0"
				+ "}";
		tst.scenContext.setContext(Context.BODY, body);
	}
	
	@Then("^obtengo el codigo (\\d+) de respuesta$")
	public void obtengo_el_codigo_como_respuesta(int code) throws Throwable {
		System.out.println("STEP: Se valida el código de respuesta.");
		RestAssured.baseURI = (String) tst.scenContext.getContext(Context.URL);
		RequestSpecification solicitud_postUser = RestAssured.given();
		solicitud_postUser.header("Content-Type","application/json");
		Response respuesta_postUser = solicitud_postUser.body((String) tst.scenContext.getContext(Context.BODY)).post((String) tst.scenContext.getContext(Context.API));
		String jsonDatosPostUser = respuesta_postUser.asString();
		String msg = "Se obtuvo el código de respuesta " + respuesta_postUser.getStatusCode();
		msg = msg + "\n con el typo de mensaje " + JsonPath.from(jsonDatosPostUser).get("type");
		msg = msg + "\n y el siguiente json de respuesta \n" + jsonDatosPostUser;
		tst.scenContext.setContext(Context.MESSAGE, msg);
	}
	
	@When("^ingreso el \"([^\"]*)\" del usuario a consultar$")
	public void ingreso_el_del_usuario_a_consultar(String userName) throws Throwable {
		System.out.println("STEP: Se indica el dato del nombre de usuario "+userName+", para consultar."); 
		tst.scenContext.setContext(Context.DATA_INFO_A, userName);
	}

	@Then("^obtengo el (\\d+) de respuesta$")
	public void obtengo_el_de_respuesta(int arg1) throws Throwable {
		System.out.println("STEP: Se realiza la búsqueda y se muestra el número de id."); 
		RestAssured.baseURI = (String) tst.scenContext.getContext(Context.URL);
		RequestSpecification solicitud_getUser = RestAssured.given();
		solicitud_getUser.header("Content-Type","application/json");
		Response respuesta_getUser = solicitud_getUser.get((String) tst.scenContext.getContext(Context.API)+"/"+(String) tst.scenContext.getContext(Context.DATA_INFO_A));
		String jsonDatosGetUser = respuesta_getUser.asString();
		String msg = "Se obtuvo el código de respuesta " + respuesta_getUser.getStatusCode();
		msg = msg + "\n con el id del usuario " + JsonPath.from(jsonDatosGetUser).get("id");
		msg = msg + "\n y el siguiente json de respuesta \n" + jsonDatosGetUser;
		tst.scenContext.setContext(Context.MESSAGE, msg);
	}
	
	@When("^informo el \"([^\"]*)\" de las mascotas$")
	public void informo_el_de_las_mascotas(String status) throws Throwable {
		System.out.println("STEP: Se informa el estado "+status+" de las mascotas a consultar.");
		tst.scenContext.setContext(Context.DATA_INFO_A, status);
	}

	@Then("^obtengo el una lista de mascotas y sus ids$")
	public void obtengo_el_una_lista_de_mascotas_y_sus_ids() throws Throwable {
		System.out.println("STEP: Se obtiene el id y nombre de las mascotas.");
		RestAssured.baseURI = (String) tst.scenContext.getContext(Context.URL);
		RequestSpecification solicitud_getPet = RestAssured.given();
		solicitud_getPet.header("Content-Type","application/json");
		Response respuesta_getPet = solicitud_getPet.get((String) tst.scenContext.getContext(Context.API)+"status="+(String) tst.scenContext.getContext(Context.DATA_INFO_A));
		String jsonDatosGetPet = respuesta_getPet.asString();
		JSONArray jsonarray = new JSONArray(jsonDatosGetPet);
		Object arrPets[][] = new Object[jsonarray.length()][2];
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			if ( obj.getString("name") != null ) {
				arrPets[o][0] = obj.getInt("id");
				arrPets[o][1] = obj.getString("name");
			}
		}
		String msg = "";
		for ( int x = 0; x < arrPets.length; x++) {
			msg = msg + "| Posición " + x + " | ID " + arrPets[x][0] + " | Nombre " + arrPets[x][1] + " |";
		}
		tst.scenContext.setContext(Context.MESSAGE, msg);
	}
	
	@Then("^obtengo la cantidad de veces que se repite un nombre$")
	public void obtengo_la_cantidad_de_veces_que_se_repite_un_nombre() throws Throwable {
		System.out.println("STEP: Se obtiene la cantidad de veces que esta un nombre de una lista.");
		RestAssured.baseURI = (String) tst.scenContext.getContext(Context.URL);
		RequestSpecification solicitud_getPet = RestAssured.given();
		solicitud_getPet.header("Content-Type","application/json");
		Response respuesta_getPet = solicitud_getPet.get((String) tst.scenContext.getContext(Context.API)+"status="+(String) tst.scenContext.getContext(Context.DATA_INFO_A));
		String jsonDatosGetPet = respuesta_getPet.asString();
		JSONArray jsonarray = new JSONArray(jsonDatosGetPet);
		Object arrPets[][] = new Object[jsonarray.length()][2];
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			if ( obj.getString("name") != null ) {
				arrPets[o][0] = obj.getInt("id");
				arrPets[o][1] = obj.getString("name");
			}
		}
		String msg = "";
		String arr[] = new String[arrPets.length];
		for ( int w = 0; w<arrPets.length; w++) {
			int a = 0;
			for ( int z = 0; z<arrPets.length; z++) {
				if ( arrPets[w][1].equals(arrPets[z][1]) && ! Arrays.asList(arr).contains(arrPets[w][1])) {
					a++;
				}
			}
			arr[w] = (String) arrPets[w][1];
			msg = msg + "\n la mascota " + arrPets[w][1] + " aparece " + a + " veces";
		}
		tst.scenContext.setContext(Context.MESSAGE, msg);
	}

	
}
