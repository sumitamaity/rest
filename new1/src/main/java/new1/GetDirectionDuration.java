package new1;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Uitility.CommonFunctions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class GetDirectionDuration {

	RequestSpecBuilder rsb;
	RequestSpecification rs;
	Validation va = new Validation();
	readExcel re = new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");

	@BeforeClass
	public void setup() {

		CommonFunctions cf = new CommonFunctions();
		// Map<String, String > mp=cf.setParams(re.getValueFromCell("params",
		// "Sheet1"));
		Map<String, String> mp = new LinkedHashMap<String, String>();
		mp.put("key", "AIzaSyDD8n3WRjESCzg5OHh5rScQrW2ELAd2Ctw");
		mp.put("origins", "Washington, DC");
		mp.put("destinations", "New+York+City");

		rsb = new RequestSpecBuilder();
		rsb.setBaseUri(re.getValueFromCell("baseURI", "Sheet2"));

		rsb.setBasePath(ServiceUrl.DIRECTION_URL);
		rsb.addQueryParams(mp);
		// rsb.addQueryParam("key","AIzaSyDD8n3WRjESCzg5OHh5rScQrW2ELAd2Ctw");
		rs = rsb.build();
		

	}

	@Test
	public void statusCode() throws JsonParseException, JsonMappingException, IOException, ParseException {
		given().spec(rs).when().get(ServiceUrl.DIRECTION_RESOURCE);
//		.then()
//		.log()
//		.body();

		Response re = given().spec(rs).when().get(ServiceUrl.DIRECTION_RESOURCE);
		String s = re.asString();
		// System.out.println(s);

		System.out.println(validateResponse(s, "rows", "elements","distance"));
		

	}

	public JSONObject GetJSONFromResP(String res) {
		JSONParser jp = new JSONParser();
		Object Res = null;
		Object obj;
		JSONObject jo = null;
		try {
			obj = jp.parse(res);
			jo = (JSONObject) obj;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;

	}

	public Object validateResponseJsonObj(Object jo, Object Key) {

		Object Res = null;
		if (jo instanceof JSONObject) {
			System.out.println(jo + " is a JSONoBJECT");
			Res = ((JSONObject) jo).get(Key);

			// System.out.println(Res);
		} else {
			// System.out.println("it might be an array");
			Res = validateResponseJsonArray(jo, Key);
		}
		return Res;
	}

	public Object validateResponseJsonArray(Object a, Object Key) {
		Object Res = null;
		System.out.println(a + " is an array!");
		JSONArray ja = (JSONArray) a;
		for (int i = 0; i < ja.size(); i++) {
			if (ja.get(i) instanceof JSONObject) {
				Res = validateResponseJsonObj((JSONObject) ja.get(i), Key);
			} else if (ja.get(i) instanceof JSONArray) {
				Res = validateResponseJsonArray(ja.get(i), Key);
			}
			// System.out.println(a.toString()+"="+ja.get(i));
		}
		// System.out.println(a.toString());
		return Res;
	}

	public Object validateResponse(String res, Object Key, Object value1) 
	{       JSONObject jo=GetJSONFromResP(res);
		    Object Res=null;
		    Object Res1=null;
		     Object a=validateResponseJsonObj(jo, Key);
		     
			 if(a instanceof JSONObject) 
			 {
				//System.out.println(a+" is a jsonObject");
				/*Res1=validateResponseJsonObj(a,value1);
				if(Res1 instanceof JSONObject) {*/
					Res=validateResponseJsonObj(a,value1);
				}
				else {
					Res=validateResponseJsonArray(a,value1);
				}
			
	return Res;
}
	public Object validateResponse(String res, Object Key, Object value1, Object value2) 
	{
		Object Res1=validateResponse(res,Key,value1);
		
		 Object Res=null;
		if(Res1 instanceof JSONObject) 
		 {
			
				Res=validateResponseJsonObj(Res1,value2);
			}
			else {
				Res=validateResponseJsonArray(Res1,value2);
			}
		return Res;
	}

}
