package oz.noic.utils;

public class StatusesMessages {
	
	
	public static final int status_ok = 0;
	public static final String msg_ok = "Ok.";

	public static final int status_auth_err = -1;
	public static final String msg_auth_err = "Authentication error.";

	public static final int status_not_owned_device = -2;
	public static final String msg_not_owned_device = "Not owned device.";

	public static final int status_query_cancelled = -3;
	public static final String msg_query_cancelled = "Query cancelled.";

	public static final int status_update_cancelled = -4;
	public static final String msg_update_cancelled = "Update cancelled.";

	public static final int status_db_io_error = -5;
	public static final String msg_db_io_error = "DB I/O error.";

	public static final int status_update_error = -6;
	public static final String msg_update_error = "Update error.";

	public static final int status_HttpGET_error = -7;
	public static final String msg_HttpGET_error = "Error during Http GET.";
	
	public static final int status_HttpPOST_error = -8;
	public static final String msg_HttpPOST_error = "Error during Http POST.";
	
	public static final int status_google_err = -9;
	public static final String msg_google_err = "Error during request at firebase rest api";
	

}
