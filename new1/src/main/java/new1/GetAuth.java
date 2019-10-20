package new1;


import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class GetAuth {
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	Validation va= new Validation();
	// @Test
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
	 
	 System.out.println("-------------------------------------------");
	
	 //System.out.println(Header);
	 va.printAllInConsole(responseBody);
	 //print Status Line
	 va.printAllInConsole(response.getStatusLine());
	 
	 }
	 
	 
	 @Test
	 public void GetWeatherDetailsWithAuth()
	 {   
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = re.getValueFromCell("AuthbaseURI", "Sheet4") ; //"http://restapi.demoqa.com/utilities/weather/city";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification Request = RestAssured.given();
	 
	 Request.auth().basic(re.getValueFromCell("userID", "Sheet4"), re.getValueFromCell("passWord", "Sheet4"));
	  // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = Request.get(ServiceUrl.AUTH);  // /Hyderabad
	 //Response response = Request.options(ServiceUrl.CITY);  // /Hyderabad
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 String responseBody = response.getBody().asString();
	 
	 String value=validateResponse(responseBody, "Fault");// pass in excel
	 
	 if(value.equals("Operation completed successfully")) //pass in excel
		 System.out.println("validated value successfully  "+ value);
		/*
		 * //validate headers Validation.validateHeader(response,
		 * re.getValueFromCell("HeaderKey", "Sheet4"),
		 * re.getValueFromCell("HeaderValue", "Sheet4"));
		 * 
		 * //Print resposeBody va.printAllInConsole(responseBody);
		 * 
		 * //print Status Line va.printAllInConsole(response.getStatusLine());
		 */
	 }
	 
	 
	 public String validateResponse(String res, Object Key) {
		 JSONParser jp= new JSONParser();
		 String Res=null;
		 try {
			Object obj=jp.parse(res);
			JSONObject jo=(JSONObject)obj;
			if(Key instanceof String)
			Res=(String) jo.get(Key);
			System.out.println(jo.get(Key));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Res;}
		 
}
