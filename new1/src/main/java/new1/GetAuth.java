package new1;


import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class GetAuth {
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	Validation va= new Validation();
	 @Test
	 public void GetWeatherDetailsWithoutAuth()
	 {   
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = re.getValueFromCell("AuthbaseURI", "Sheet4") ;           //"http://restapi.demoqa.com/utilities/weather/city";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification Request = RestAssured.given();
	 
	 // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = Request.get(ServiceUrl.AUTH);  // /Hyderabad
	 //Response response = Request.options(ServiceUrl.CITY);  // /Hyderabad
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 String responseBody = response.getBody().asString();
	 Headers Header = response.getHeaders();//.asString();
	 System.out.println(Header);
	 System.out.println("---------------------------------");
	 System.out.println("Response Body is =>  " + responseBody);
	 System.out.println("---------------------------------");
	 System.out.println(response.getStatusLine());
	 System.out.println("---------------------------------");
	 
	 }
	 
	 
	 @Test
	 public void GetWeatherDetailsWithAuth()
	 {   
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = re.getValueFromCell("AuthbaseURI", "Sheet4") ;           //"http://restapi.demoqa.com/utilities/weather/city";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification Request = RestAssured.given();
	 
	 // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = Request.auth().basic("ToolsQA", "TestPassword").get(ServiceUrl.AUTH);  // /Hyderabad
	 //Response response = Request.options(ServiceUrl.CITY);  // /Hyderabad
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 String responseBody = response.getBody().asString();
	 Headers Header = response.getHeaders();//.asString();
	 System.out.println(Header);
	 System.out.println("---------------------------------");
	 System.out.println("Response Body is =>  " + responseBody);
	 System.out.println("---------------------------------");
	 System.out.println(response.getStatusLine());
	 System.out.println("---------------------------------");
	 
	 }
}
