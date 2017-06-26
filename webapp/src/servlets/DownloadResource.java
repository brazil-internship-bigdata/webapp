package servlets;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("download")
public class DownloadResource {

	@POST
	@Path("ktr")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/s")
	public Response postKTR(@FormDataParam("company") String companyName) {

		try {
			File fileToSend = new File(APIApplication.KTR_FILE_PATH + companyName + ".ktr");
			return Response.ok(fileToSend, "application/ktr").build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}