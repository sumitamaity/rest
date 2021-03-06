package new1;


import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class GetWeather extends Validation{
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	//Validation va= new Validation();
	
	 @Test
	 public void GetWeatherDetails()
	 {   
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = re.getValueFromCell("baseURI", "Sheet4") ;           //"http://restapi.demoqa.com/utilities/weather/city";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification Request = RestAssured.given();
	 
	 // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = Request.get(ServiceUrl.CITY);  // /Hyderabad
	 //Response response = Request.options(ServiceUrl.CITY);  // /Hyderabad
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 Headers Header = response.getHeaders();//.asString();
	 
	 System.out.println("-------------------------------------------");
	
	 va.printAllInConsole(Header);
	 //System.out.println(response.getBody());
	 va.printAllInConsole(response.getBody().asString());
	 //print Status Line
	 va.printAllInConsole(response.getStatusLine());
		
	 }
}
