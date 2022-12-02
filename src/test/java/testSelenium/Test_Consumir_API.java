package testSelenium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test_Consumir_API {

	public static void main(String[] args) {
		
		// DATOS
		String baseURL = "https://petstore.swagger.io/v2";
		String endpoint_user = "/user";
		String endpoint_pets = "/pet/findByStatus?";
		String username = "AnibalDeLaVega";
		String password = "Test@@123";
		String firstName = "Anibal";
		String lastName = "DeLaVega";
		String email = "hannibal.delavega@gmail.com";
		String phone = "5491164235548";
		String body = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"username\": \""+username+"\",\r\n"
				+ "  \"firstName\": \""+firstName+"\",\r\n"
				+ "  \"lastName\": \""+lastName+"\",\r\n"
				+ "  \"email\": \""+email+"\",\r\n"
				+ "  \"password\": \""+email+"\",\r\n"
				+ "  \"phone\": \""+phone+"\",\r\n"
				+ "  \"userStatus\": 0"
				+ "}";
		String status = "sold";
		System.out.println("Datos: "+baseURL+"\n - body "+body);
		
		// Conexion
		System.out.println("STEP: Conexión al baseURL. Post user.");
		RestAssured.baseURI = baseURL;
		RequestSpecification solicitud_postUser = RestAssured.given();
		
		System.out.println("STEP: Consulta al endpoint. Post user.");
		solicitud_postUser.header("Content-Type","application/json");
		Response respuesta_postUser = solicitud_postUser.body(body).post(endpoint_user);
		
		System.out.println("STEP: Obteniendo respuesta. Post user.");
		System.out.println(respuesta_postUser.getStatusCode());
		String jsonDatosPostUser = respuesta_postUser.asString();
		System.out.println(respuesta_postUser.asString());
		String jsonTypePostUser = JsonPath.from(jsonDatosPostUser).get("type");
		System.out.println(jsonTypePostUser);
		
		
		////
		System.out.println("STEP: Conexión al baseURL. Get user.");
		RestAssured.baseURI = baseURL;
		RequestSpecification solicitud_getUser = RestAssured.given();
		
		System.out.println("STEP: Consulta al endpoint. Get user.");
		solicitud_getUser.header("Content-Type","application/json");
		Response respuesta_getUser = solicitud_getUser.get(endpoint_user+"/"+username);
		
		System.out.println("STEP: Obteniendo respuesta. Get user.");
		System.out.println(respuesta_getUser.getStatusCode());
		String jsonDatosGetUser = respuesta_getUser.asString();
		System.out.println(respuesta_getUser.asString());
		String jsonTypeGetUser = JsonPath.from(jsonDatosGetUser).get("firstName");
		System.out.println(jsonTypeGetUser);
		
		////
		System.out.println("STEP: Conexión al baseURL. Get Pets.");
		RestAssured.baseURI = baseURL;
		RequestSpecification solicitud_getPet = RestAssured.given();
		
		System.out.println("STEP: Consulta al endpoint. Get Pets.");
		solicitud_getPet.header("Content-Type","application/json");
		Response respuesta_getPet = solicitud_getUser.get(endpoint_pets+"status="+status);
		
		System.out.println("STEP: Obteniendo respuesta. Get Pets.");
		System.out.println(respuesta_getPet.getStatusCode());
		String jsonDatosGetPet = respuesta_getPet.asString();
		System.out.println(respuesta_getPet.asString());
		//String jsonTypeGetPet = JsonPath.from(jsonDatosGetPet).get("name");
		//System.out.println(jsonTypeGetPet);
		
		System.out.println("STEP: Formateando el json de respuesta para buscar ID y NAME.");
		JSONArray jsonarray = new JSONArray(jsonDatosGetPet);
		Object arrPets[][] = new Object[jsonarray.length()][2];
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			if ( obj.getString("name") != null ) {
				arrPets[o][0] = obj.getInt("id");
				arrPets[o][1] = obj.getString("name");
			}
		}
		for ( int x = 0; x < arrPets.length; x++) {
			System.out.println("| Posición " + x + " | ID " + arrPets[x][0] + " | Nombre " + arrPets[x][1] + " |");
		}
		
		String msg = "";
		String arr[] = new String[arrPets.length];
		for ( int w = 0; w<arrPets.length; w++) {
			System.out.println("w="+w);
			int a = 0;
			for ( int z = 0; z<arrPets.length; z++) {
				System.out.println("z="+z);
				if ( arrPets[w][1].equals(arrPets[z][1]) && ! Arrays.asList(arr).contains(arrPets[w][1])) {
					System.out.println(arrPets[w][1] +" | "+arrPets[z][1]);
					a++;
				}
			}
			arr[w] = (String) arrPets[w][1];
			msg = msg + "\n la mascota " + arrPets[w][1] + " aparece " + a + " veces";
		}
		System.out.println(msg);
	}
	
}
