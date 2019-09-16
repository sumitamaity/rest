package new1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.responsePojo.DistanceMatrix;
import rest.service.ServiceUrl;

public class GetRequest {
	
	RequestSpecBuilder rsb;
	RequestSpecification rs;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.basePath="/maps/api";
		rsb= new RequestSpecBuilder();
		rsb.setBaseUri("https://maps.googleapis.com");
		rsb.setBasePath(ServiceUrl.DIRECTION_URL);
		rsb.addQueryParam("unit", "imperial");
		rsb.addQueryParam("origins", "Washington, DC");
		rsb.addQueryParam("destinations", "New+York+City");
		rsb.addQueryParam("key", "AIzaSyDD8n3WRjESCzg5OHh5rScQrW2ELAd2Ctw");
		rs=rsb.build();
	}
@Test
 public void statusCode() throws JsonParseException, JsonMappingException, IOException {
	given()
	.spec(rs)
	.when()
	.get(ServiceUrl.DIRECTION_RESOURCE);
//	.then()
//	.log()
//	.body();

	Response re= given().spec(rs).when().get(ServiceUrl.DIRECTION_RESOURCE);
	String s=re.asString();
	ObjectMapper om=new ObjectMapper();
	DistanceMatrix dm= om.readValue(s, DistanceMatrix.class);
	System.out.println(dm.getStatus()+" "+dm.getOriginAddresses());
	
	DistanceMatrix dm1 = new DistanceMatrix();
	List<String> da= dm1.getDestinationAddresses();
	for(String f: da) {
		System.out.println(f);
	}
 }
}
