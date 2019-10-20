package new1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

@Test
public class DeleteReq {
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	Validation va= new Validation();
	
	public void DeleteRecord() {
	String empID="15410";
	 RestAssured.baseURI = re.getValueFromCell("DelbaseURI", "Sheet4") ; 
	 RequestSpecification Request = RestAssured.given();
	 Response response= Request.delete(ServiceUrl.DEL+empID);
	 System.out.println(response.toString());
	 Headers Header = response.getHeaders();//.asString();
	 System.out.println(Header);
	 System.out.println("---------------------------------");
	 System.out.println("Response Body is =>  " + response);
	 System.out.println("---------------------------------");
	 System.out.println(response.getStatusLine());
	 System.out.println("---------------------------------");
	 //va.ExtractResponse(response);
}
}