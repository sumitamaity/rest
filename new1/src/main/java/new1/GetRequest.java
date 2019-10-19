package new1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.responsePojo.DistanceMatrix;
import rest.service.ServiceUrl;

import org.apache.commons.lang3.Validate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetRequest {

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
		/*
		 * Map<String, String> params= new LinkedHashMap<String, String>();
		 * params.put("origin","Washington, DC");
		 * params.put("destinations","New+York+City"); rsb = new RequestSpecBuilder();
		 * rsb.setBaseUri(re.getValueFromCell("baseUri", "Sheet1"));
		 * rsb.setBasePath(ServiceUrl.DIRECTION_URL);
		 * //rsb.addQueryParam(re.getValueFromCell("unit", "Sheet1"));
		 * rsb.addQueryParam("unit","imperial");
		 * rsb.addQueryParam("origin","Washington, DC");
		 * rsb.addQueryParam("destinations", "New+York+City");
		 * rsb.addQueryParam("key","AIzaSyDD8n3WRjESCzg5OHh5rScQrW2ELAd2Ctw"); rs =
		 * rsb.build();
		 */
		
		//rsb.setBaseUri(re.getValueFromCell("baseURI", "Sheet1"));
		//rs= rsb.build();
		
	}

	
	
	//@Test
	 public void statusCode1() throws JsonParseException, JsonMappingException, IOException, ParseException {
		/*
		 * given() .spec(rs) .when() .get(ServiceUrl.DIRECTION_RESOURCE);
		 * 
		 * .then().log().body();
		 */
		
		
		  RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		  RequestSpecification rs1 = RestAssured.given(); 
		  JSONObject requestParams = new JSONObject();
		  requestParams.put("FirstName", "Sumita");
		  requestParams.put("LastName", "Maity");
		  
		  requestParams.put("UserName", "sumita91"); 
		  requestParams.put("Password", "password1"); 
		  requestParams.put("Email", "sumita@gmail.com");
		  
		  rs1.body(requestParams.toJSONString());
		  rs1.header("Content-Type","application/json");
		 
		//Response re= given().body(requestParams).when().post("http://dummy.restapiexample.com/api/v1/create"); 
		//Response re= given().spec(rs).when().get(ServiceUrl.DIRECTION_RESOURCE);
		Response re = rs1.post("/register");
		String s=re.asString();
		System.out.println(s);
		System.out.println(re.statusCode());
		JSONParser jp= new JSONParser();
		Object ob=jp.parse(s);
		JSONObject jo=(JSONObject)ob;
		String result=va.parseJsonObject(jo);
		System.out.println(result);
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
 
