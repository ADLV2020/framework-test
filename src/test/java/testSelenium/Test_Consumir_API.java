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
		
		JSONArray jsonarray = new JSONArray(jsonDatosGetPet);
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			String name = obj.getString("name");
			System.out.println(name);
		}
		
		String arr[] = new String[jsonarray.length()];
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			String name = obj.getString("name");
			if ( name != null ) {
				arr[o] = name;
			}
		}
		System.out.println("<<<<< RECORRO ARRAYLIST >>>>>");
		for ( int u=0;u<arr.length; u++) {
			System.out.println(arr[u]);
		}
		
		for ( int o = 0; o < jsonarray.length(); o++) {
			int vez = 0;
			JSONObject obj_o = jsonarray.getJSONObject(o);
			String name1 = obj_o.getString("name");
			for ( int z = 0; z < jsonarray.length(); z++) {
				JSONObject obj_z = jsonarray.getJSONObject(z);
				if ( obj_o != null && obj_o.equals(obj_z) ) {
					vez = vez+1;
					String name2 = obj_z.getString("name");
					System.out.println(" | nombre 1 " + name1 + " contador o " + o + " | nombre 2 " + name2 + " contador z " + z + " | vez " + vez + " | ");
				}
			}
		}
		
		System.out.println("\n<<<<< RECORRO ARRAYLIST >>>>>");
		for ( int o = 0; o < arr.length; o++) {
			for ( int z = 0; z < arr.length; z++) {
				if ( arr[o] != null && arr[o].equals(arr[z]) ) {
					System.out.println(" | nombre 1 " + arr[o] + " contador o " + o + " | nombre 2 " + arr[z] + " contador z " + z + " | vez | ");
				}
			}
		}
		
		
		
		/*
		System.out.println(respuesta_user.getStatusCode());
		Assert.assertEquals(respuesta_user.getStatusCode(),200);
		
		System.out.println("\n Solicitud POST para TokenGenerator y obtiene Token \n");
		Response respuesta = solicitud.body(datos_1).post(path_Token);
		
		System.out.println(respuesta.getStatusCode());
		Assert.assertEquals(respuesta.getStatusCode(),200);
		
		String jsonDatos = respuesta.asString();
		System.out.println(jsonDatos.toString()+"\n token: "+JsonPath.from(jsonDatos).get("token"));
		Assert.assertTrue(jsonDatos.contains("token"));
		String token  = JsonPath.from(jsonDatos).get("token");
		System.out.println(token);

		
		System.out.println("\n Solicitud GET para consulta \n"); 
		respuesta = solicitud.get(path_Book);
		System.out.println(respuesta.getStatusCode());
		Assert.assertEquals(respuesta.getStatusCode(), 200);
		
        jsonDatos = respuesta.asString();
        System.out.println(jsonDatos);
        List<Map<String, String>> libros = JsonPath.from(jsonDatos).get("books");
        System.out.println(libros.size());
        Assert.assertTrue(libros.size() > 0);
 
        String librosId = libros.get(0).get("isbn");
        System.out.println(librosId);

        System.out.println("\n Agrega un registro con autorizacion \n");
        String userId = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
        String Datos_2 = "{ \"userId\": \""+userId+"\", \"collectionOfIsbns\": [ { \"isbn\": \""+librosId+"\" } ]}";
        
        solicitud.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");
        respuesta = solicitud.body(Datos_2).post(path_Book);
        
        System.out.println(respuesta.getStatusCode()+"\n "+respuesta.getStatusLine()+"\n "+respuesta.getContentType()+"\n "+respuesta.getBody());
		*/
		
	}

}
