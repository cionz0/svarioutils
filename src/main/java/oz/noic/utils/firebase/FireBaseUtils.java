package oz.noic.utils.firebase;

import java.io.IOException;

import org.json.JSONObject;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

import oz.noic.utils.StatusesMessages;


public class FireBaseUtils { 

	private static FireBaseUtils instance;
	private HttpRequestFactory requestFactory;
	private String googleApi;

	private FireBaseUtils() {
		this.googleApi = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
						  
		HttpTransport HTTP_TRANSPORT = new NetHttpTransport();			
		this.requestFactory =
				HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
					//@Override
					public void initialize(HttpRequest request) {
						request.setParser(new JsonObjectParser(new JacksonFactory()));
					}
				});
	}

	public static synchronized FireBaseUtils getInstance() {
		if (instance == null)
			instance = new FireBaseUtils();
		return instance;
	}
	
	private JSONObject askGoogle(String url, JSONObject content) throws IOException {
		JSONObject response;
		HttpRequest request = requestFactory.buildPostRequest(
				new GenericUrl(url), 
				ByteArrayContent.fromString("application/json", content.toString())
				);
		request.getHeaders().setContentType("application/json");

		HttpResponse http_response = request.execute();			
		response = new JSONObject(http_response.parseAsString());
		if (http_response.getStatusCode() == 200) {
			response.put("status", StatusesMessages.status_ok);
			response.put("message", StatusesMessages.msg_ok);
		}
		else {
			response.put("status", StatusesMessages.status_google_err);
			response.put("message", StatusesMessages.msg_google_err);
		}
		return response;
	}

	public JSONObject verifyPassword(String key, String email, String password) throws IOException {
		
		String url = this.googleApi+"verifyPassword/"+"?key="+key;
		JSONObject content = new JSONObject();
		content.put("email", email);
		content.put("password", password);
		content.put("returnSecureToken", "true");
		JSONObject x = askGoogle(url, content); 
		
		return x;
	}


	public JSONObject verifyCustomToken(String key, String customToken) throws IOException {
		String url = this.googleApi+"verifyCustomToken/"+"?key="+key;
		JSONObject content = new JSONObject();
		content.put("token", customToken);
		content.put("returnSecureToken", true);
		
		return askGoogle(url, content);
	}

	

	public JSONObject resetPassword(String email, String key) throws IOException {
		String url = this.googleApi+"getOobConfirmationCode/"+"?key="+key;
		JSONObject content = new JSONObject();
		content.put("email", email);
		content.put("requestType", "PASSWORD_RESET");
		return askGoogle(url, content);
		
	}
	
	
	





}
