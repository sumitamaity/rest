package new1;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.response.Response;

public class Validation {
	
	static Validation va= new Validation();
	
	public static void ExtractResponse(Response re) {
		String s=re.asString();
		System.out.println(s);
		System.out.println(re.statusCode());
		JSONParser jp= new JSONParser();
		Object ob;
		try {
			ob = jp.parse(s);
		
		JSONObject jo=(JSONObject)ob;
		String result=va.parseJsonObject(jo);
		System.out.println(result);} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void getArray(Object obj) {
		JSONArray ja= (JSONArray) obj;
		for(int i=0; i< ja.size(); i++) {
			if(ja.get(i) instanceof JSONObject ) {
				parseJsonObject((JSONObject)ja.get(i));
			}
			else {
				System.out.println(obj.toString()+"="+ja.get(i));
			}
		}
	}

	static String parseJsonObject(JSONObject jsonobj) {
		// TODO Auto-generated method stub
		Object obj=null;
		Set<Object> set= jsonobj.keySet();
		Iterator<Object> it= set.iterator();
		while(it.hasNext()) {
			obj= it.next();
			if(jsonobj.get(obj) instanceof JSONArray) {
				System.out.println(obj.toString());
				getArray(jsonobj.get(obj));
			}
			else
			if(jsonobj.get(obj) instanceof JSONObject) {
				parseJsonObject((JSONObject)jsonobj.get(obj));
			}
			else {
				System.out.println(obj.toString()+"="+jsonobj.get(obj));
			}
		}
		return obj.toString()+"="+jsonobj.get(obj);
		
	}
	
}
