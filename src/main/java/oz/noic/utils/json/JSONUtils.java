package oz.noic.utils.json;

import java.io.File;
import java.io.FileInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import oz.noic.utils.RawDebug;





public class JSONUtils {

	private static JSONUtils instance;
	

	private JSONUtils() {
//		this.config_files = null;
//		try {
//			System.out.println(new File("ciao").getAbsolutePath());
//			File file = new File("config_files.json");
//			FileInputStream fis = new FileInputStream(file);
//			byte[] data = new byte[(int) file.length()];
//			fis.read(data);
//			fis.close();
//
//			this.config_files = new JSONObject(new String(data, "UTF-8"));
//		} catch (IOException e) {			
//			e.printStackTrace();			
//		}	
//
	}

	public static JSONUtils getInstance() {

		if (instance == null)
			instance = new JSONUtils();	

		return instance;
	}

	public static JSONObject buildJSONObject(String filename) {	
		
		JSONObject config_data = null;

		try {

			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			config_data = new JSONObject(new String(data, "UTF-8"));
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		
		return config_data;
	}

	

	public static JSONObject buildJSONObject(Object obj) {
		
			
		
		
		JSONObject result = new JSONObject();
		Object wrap = JSONObject.wrap(obj);
		if (wrap == null) {
			System.out.println("could not convert "+obj+ RawDebug.where());
		} else {
			result.put("values", wrap);
			
		}
		if (wrap instanceof String) {
			System.out.println("got a string from conversion of "
					+obj
					+RawDebug.where());
			}
		
		
		return result;
	}

	public boolean contains(JSONArray jsonArray, Object o) {
		boolean found = false;
		for (int i=0; !found && i < jsonArray.length(); i++)
			found |= jsonArray.get(i).equals(o);
		
		return found;
	}



}
