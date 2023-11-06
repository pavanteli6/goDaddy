//package utilities;
//
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.WebDriver;
//
//public class JsonObjectCall {
//
//	WebDriver driver;
//	static JSONObject jsonObject = null;
//	
//	public static void setupJsonFile() {
//		try {
//
//			FileReader reader = new FileReader(
//					"C:\\Users\\Pavan.Teli\\eclipse-workspace\\AtkcoProjectAutomation\\src\\test\\java\\utilities\\Config.json");
//			JSONParser jsonParser = new JSONParser();
//			jsonObject = (JSONObject) jsonParser.parse(reader);
//
//			String selectBrowser = (String) jsonObject.get("browser");
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
//	}
//}
