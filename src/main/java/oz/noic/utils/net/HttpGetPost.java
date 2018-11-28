package oz.noic.utils.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import oz.noic.utils.RawDebug;

public class HttpGetPost { 

	private static HttpGetPost instance;

	private HttpGetPost() {

	}

	public static synchronized HttpGetPost getInstance() {
		if (instance == null)
			instance = new HttpGetPost();
		return instance;
	}

	
	

	public String sendGET(String url_string, Map<String, String> properties, String body){
		StringBuffer response = new StringBuffer();
		
		//System.out.println("Sending GET request to: "+url_string+"\n\t body: "+body+"\n");
		try {
			URL obj = new URL(url_string+"?"+body);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			//set specified connection properties
			if (properties != null) {
				for (String property : properties.keySet())
					con.setRequestProperty(property, properties.get(property));
			}

			int responseCode = con.getResponseCode();
			//System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;


				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();			

			} else {
				BufferedReader err = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer err_msg = new StringBuffer();


				while ((inputLine = err.readLine()) != null) {
					err_msg.append(inputLine);
				}
				err.close();	

				throw new Exception(err_msg.toString());
			}
		}
		catch (Exception e) {
			System.out.println(e+RawDebug.where());
			response.append(" "+e.getMessage());
		}	

		return response.toString();

	}

	public  String sendPOST(String url_string, Map<String, String> properties, String body) {
		StringBuffer response = new StringBuffer();

		//System.out.println("Sending POST request to: "+url_string+"\n\t body: "+body+"\n");

		try {

			URL obj = new URL(url_string);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			//set specified connection properties
			if (properties != null) {
				for (String property : properties.keySet())
					con.setRequestProperty(property, properties.get(property));
			}



			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			// For POST only - END

			int responseCode = con.getResponseCode();
			//			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;


				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
			} else {
				BufferedReader err = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer err_msg = new StringBuffer();


				while ((inputLine = err.readLine()) != null) {
					err_msg.append(inputLine);
				}
				err.close();	

				throw new Exception(err_msg.toString());
			}
		}
		catch (Exception e) {
			response.append("\n"+e.getMessage());
		}

		return response.toString();
	}


}
