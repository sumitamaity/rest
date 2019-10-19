package new1;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class DirectionDuration {
	
	RequestSpecBuilder rsb;
	RequestSpecification rs;
	Validation va=new Validation();
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	@BeforeClass
	public void setup() {
		
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
		rsb = new RequestSpecBuilder();
		rsb.setBaseUri("https://maps.googleapis.com");
		rsb.setBasePath(ServiceUrl.DIRECTION_URL);
		rsb.addQueryParam("unit", "imperial");
		rsb.addQueryParam("origins", "Washington, DC");
		rsb.addQueryParam("destinations", "New+York+City");
		rsb.addQueryParam("key", "AIzaSyDD8n3WRjESCzg5OHh5rScQrW2ELAd2Ctw");
		rs = rsb.build();
		
	}
	@Test
	 public void statusCode() throws JsonParseException, JsonMappingException, IOException, ParseException {
		given()
		.spec(rs)
		.when()
		.get(ServiceUrl.DIRECTION_RESOURCE);
//		.then()
//		.log()
//		.body();

		Response re= given().spec(rs).when().get(ServiceUrl.DIRECTION_RESOURCE);
		String s=re.asString();
		System.out.println(s);
		JSONParser jp= new JSONParser();
		Object ob=jp.parse(s);
		JSONObject jo=(JSONObject)ob;
		JSONObject joEle = null;
		JSONArray ja1 = null;
		JSONArray ja2 = null;
		JSONArray ja=(JSONArray) jo.get("rows");
		for(int j=0; j<ja.size(); j++) {
			JSONObject jo1=(JSONObject) ja.get(j);
		
			ja1=(JSONArray) jo1.get("elements");
			
			for(int i=0; i<ja1.size();i++) {
				JSONObject j1=(JSONObject) ja1.get(i);
				JSONObject jf=(JSONObject) j1.get("duration");
				String text=(String)jf.get("text");
				//System.out.println(text);
			
			}
			
			ja2=(JSONArray)jo.get("origin_addresses");
		    System.out.println(ja2.get(j).toString().equals("Washington, DC, USA"));
			
		}
		}
		
		
		
		
		
}
