package new1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class ValidateJsonFile {
	@Test
		 public void validateJsonInFile() throws ParseException
		 {
			 JSONParser jp= new JSONParser();
			 Object obj=null;
			try {
				BufferedReader fis= new BufferedReader(new InputStreamReader(new FileInputStream("jsonFile.json"))) ;
				
				try {
					
					obj=jp.parse(fis);
					System.out.println(obj.toString());
					
					JSONObject jo=(JSONObject)obj;
					JSONObject comp=(JSONObject) jo.get("companies");
					Set<Object> set= comp.keySet();
					Iterator<Object> it=set.iterator();
					while(it.hasNext()) {
						String tech=(String) comp.get(it.next());
						System.out.println(tech);
	                   
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
}
