package new1;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Validation {
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
