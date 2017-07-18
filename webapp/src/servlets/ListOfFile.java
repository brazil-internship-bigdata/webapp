package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filelist")
public class ListOfFile extends ServletWithConstants {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String company = req.getParameter("company_name");
		System.out.println(company);

		String folderPath = STORAGE_FILE_PATH + company + RESOURCE_FILE_PATH;
		System.out.println(folderPath);

		File repertoire = new File(folderPath);
		File[] files = repertoire.listFiles();

		String filesList = "";
		for (int i = 0; i < files.length; i++) {

			filesList += files[i].getName() + "\n";
		}

		System.out.println(filesList);

		resp.setContentType("application/text");
		PrintWriter out = resp.getWriter();
		out.print(filesList);
		out.flush();
	}
}
