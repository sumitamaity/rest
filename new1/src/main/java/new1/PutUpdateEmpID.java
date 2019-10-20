package new1;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.service.ServiceUrl;

public class PutUpdateEmpID {
	Validation va=new Validation();
	readExcel re=new readExcel("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\FirstApi.xlsx");
	@Test(groups= {"hold"})
	 public void putReq() throws IOException {
		
		//int EmpID=Integer.parseInt(re.getValueFromCell("EmpID", "Sheet1"));
		String EmpID="15410";
		 File file= new File("emp.json");
		  //RestAssured.baseURI =re.getValueFromCell("baseURI", "Sheet1");
		  RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		  RequestSpecification rs1 = RestAssured.given(); 
		  rs1.body(file);
		  rs1.header("Content-Type","application/json");
		 
	   	Response re = rs1.put(ServiceUrl.EMP_ID +EmpID);
	    String responseBody = re.getBody().asString();
		 Headers Header = re.getHeaders();//.asString();
		 System.out.println(Header);
		 System.out.println(responseBody);
		va.ExtractResponse(re);
	}
	
}
