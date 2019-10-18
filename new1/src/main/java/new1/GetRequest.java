package new1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.responsePojo.DistanceMatrix;
import rest.service.ServiceUrl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetRequest {

	RequestSpecBuilder rsb;
	RequestSpecification rs;

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

	public static void parseJsonObject(JSONObject jsonobj) {
		Set<Object> set= jsonobj.keySet();
		Iterator<Object> it= set.iterator();
		while(it.hasNext()) {
			Object obj= it.next();
			if(jsonobj.get(obj) instanceof JSONArray) {
				System.out.println(obj.toString());
				getArray(jsonobj.get(obj));
			}
			else
			if(jsonobj.get(obj) instanceof JSONObject) {
				parseJsonObject((JSONObject)jsonobj.get(obj));
			}
			else {
				System.out.println(obj.toString()+" "+jsonobj.get(obj));
			}
		}
	}
	
	public static void getArray(Object obj) {
		JSONArray ja= (JSONArray) obj;
		for(int i=0; i< ja.size(); i++) {
			if(ja.get(i) instanceof JSONObject ) {
				parseJsonObject((JSONObject)ja.get(i));
			}
			else {
				System.out.println(ja.get(i));
			}
		}
	}
	
	@Test
	 public void statusCode1() throws JsonParseException, JsonMappingException, IOException, ParseException {
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
		parseJsonObject(jo);
	}
	
//@Test
 public void statusCode() throws JsonParseException, JsonMappingException, IOException, ParseException {
	given()
	.spec(rs)
	.when()
	.get(ServiceUrl.DIRECTION_RESOURCE);
//	.then()
//	.log()
//	.body();

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
			System.out.println(text);
		
		}
		
		ja2=(JSONArray)jo.get("origin_addresses");
	    System.out.println(ja2.get(j));
		
	}
	}
	
	
	
	
	
//	ObjectMapper om=new ObjectMapper();
//	DistanceMatrix dm= om.readValue(s, DistanceMatrix.class);
//	System.out.println(dm.getStatus()+" "+dm.getOriginAddresses());
//	
//	DistanceMatrix dm1 = new DistanceMatrix();
//	List<String> da= dm1.getDestinationAddresses();
//	for(String f: da) {
//		System.out.println(f);
	}
 
