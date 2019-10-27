package new1;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Validation {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	static Validation va= new Validation();
	@BeforeSuite
	public void setup() {
		
		extent= new ExtentReports("C:\\Users\\summaity.ORADEV\\Documents\\SM\\LastMin\\MyReporter.html");
         
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method method) {
	test= extent.startTest((this.getClass().getSimpleName()+"::"+method.getName()), method.getName());
	test.assignAuthor("Sumita");
	
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "test case failed ");
		}
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "test case Passed");
		}
		if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "test case skipped");
		}
		
	}
	
	@AfterSuite
	public void aftersuite() {
		extent.flush();
		extent.close();
	}
	public static void getArray(Object obj) {
		JSONArray ja= (JSONArray) obj;
		for(int i=0; i< ja.size(); i++) {
			if(ja.get(i) instanceof JSONObject ) {
				parseJsonObject((JSONObject)ja.get(i));
			}
			else {
				System.out.println(obj.toString()+"="+ja.get(i));
			}
		}
	}

	static String parseJsonObject(JSONObject jsonobj) {
		// TODO Auto-generated method stub
		Object obj=null;
		Set<Object> set= jsonobj.keySet();
		Iterator<Object> it= set.iterator();
		while(it.hasNext()) {
			obj= it.next();
			if(jsonobj.get(obj) instanceof JSONArray) {
				System.out.println(obj.toString());
				getArray(jsonobj.get(obj));
			}
			else
			if(jsonobj.get(obj) instanceof JSONObject) {
				parseJsonObject((JSONObject)jsonobj.get(obj));
			}
			else {
				System.out.println(obj.toString()+"="+jsonobj.get(obj));
			}
		}
		return obj.toString()+"="+jsonobj.get(obj);
		
	}
	
	
	public static void ExtractResponse(Response re) {
		String s=re.asString();
		System.out.println(s);
		System.out.println(re.statusCode());
		JSONParser jp= new JSONParser();
		Object ob;
		try {
			ob = jp.parse(s);
		
		JSONObject jo=(JSONObject)ob;
		String result=Validation.parseJsonObject(jo);
		System.out.println(result);} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void validateHeader(Response response, String Key, String value) {
		Headers Header = response.getHeaders();//.asString();
		 
		 for(Header h:Header) {
				if(h.getName().equals(Key)&& h.getValue().equals(value))
					System.out.println("Header Validated successfully");
			 }
	}
	
	public void printAllInConsole(Object response) {
		
		if(response instanceof String)
		System.out.println(response);
		else
		System.out.println(response.toString());
		
		System.out.println("---------------------------------------------");
	}
	
	
	
}
