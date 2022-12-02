package base;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import context.TestContext;
import enums.Context;

public class Functions {
	
	TestContext tst;
	
	// COMPLETE WITH GLOBAL FUNCTION
	public String retorna_id_y_name_de_la_lista() {
		String[][] arrPets = this.retorna_array(tst.scenContext.getContext(Context.DATA_MAPS));
		String msg = "";
		for ( int x = 0; x < arrPets.length; x++) {
			msg = msg + "| Posición " + x + " | ID " + arrPets[x][0] + " | Nombre " + arrPets[x][1] + " |";
		}
		return msg;
	}
	
	public String retorna_contador_nombres_repetidos() {
		String[][] arrPets = this.retorna_array(tst.scenContext.getContext(Context.DATA_MAPS));
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
		return msg;
	}
	
	public String[][] retorna_array(Object jsonData){
		JSONArray jsonarray = new JSONArray(jsonData);
		Object arrPets[][] = new Object[jsonarray.length()][2];
		for ( int o = 0; o < jsonarray.length(); o++) {
			JSONObject obj = jsonarray.getJSONObject(o);
			if ( obj.getString("name") != null ) {
				arrPets[o][0] = obj.getInt("id");
				arrPets[o][1] = obj.getString("name");
			}
		}
		return (String[][]) arrPets;
	}
	
}
