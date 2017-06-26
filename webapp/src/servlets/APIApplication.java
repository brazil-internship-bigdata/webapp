package servlets;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

@ApplicationPath("api")
public class APIApplication extends ResourceConfig {

	public static final String	KTR_FILE_PATH	= "./resource/ktr/";
	public static final String	CSV_FILE_PATH	= "./resource/csv/";
	public static final String	DATE_FORMAT		= "dd_MMM_yyyy-HH:mm:hh";

	public APIApplication() {
		super(UploadResource.class, DownloadResource.class, MultiPartFeature.class, HelloWorldResource.class);
		register(JspMvcFeature.class);
	}
}