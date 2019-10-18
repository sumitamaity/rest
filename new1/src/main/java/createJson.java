import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class createJson {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
	createJson cj= new createJson();
	cj.parseJason();
	}
	
 public void createJson() throws IOException{
	 JSONObject jo= new JSONObject();
	  jo.put("name", "sumita");
	  jo.put("place", "Kolkata");
	 JSONObject jo1= new JSONObject();
	 
	 jo1.put("company1", "Techm");
	 jo1.put("company2", "boa");
	 jo1.put("company3", "snapdeal");
	 jo1.put("company4","oracle");
	 
	 jo.put("companies", jo1);
	 
	 JSONArray ja= new JSONArray();
	 Map m= new LinkedHashMap(2);
	 m.put("phoeType", "mobile");
	 m.put("number", "7685689");
	 
	 	
	ja.put(m);
	
	jo.put("contactDetails", m);
	
	FileWriter f= new FileWriter("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\Example.json");

	f.write(jo.toString());
	f.flush();
	f.close();
	System.out.println("written succesfully:"+ jo.toString());
 }
	
  public void parseJason() throws FileNotFoundException, IOException, ParseException {
	  org.json.simple.JSONObject jo = (org.json.simple.JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\Example.json"));
	  
	  String a= (String) jo.get("name");
	  System.out.println(a);
	  Map companies=((Map)jo.get("companies"));
	  Iterator<Map.Entry> it= companies.entrySet().iterator();
	  while(it.hasNext()) {
		  Map.Entry m=it.next();
		  System.out.println(m.getKey()+" "+m.getValue());
	  }
	  
	  
  }
	
}
