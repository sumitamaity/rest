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

public class PostCustomerRequest {
	RequestSpecBuilder rsb;
	RequestSpecification rs;
	Validation va=new Validation();
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	
	@BeforeClass
	public void setup() {
		
	}

	
	
	@Test(groups= {"hold"})
	 public void postReq() throws IOException {
		
		
		 File file= new File("customer.json");
		  //RestAssured.baseURI =re.getValueFromCell("baseURI", "Sheet1");
		  RestAssured.baseURI="http://restapi.demoqa.com/customer";
		  RequestSpecification rs1 = RestAssured.given(); 
		  rs1.body(file);
		  rs1.header("Content-Type","application/json");
		 
	   	Response re = rs1.post(ServiceUrl.REGISTER);
		
		va.ExtractResponse(re);
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
 
