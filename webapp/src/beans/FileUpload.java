package beans;

import java.sql.Timestamp;

public class FileUpload {

	private Long		id;
	private String		filename;
	private String		fileType;
	private Long		idComapny;
	private Timestamp	dateUpload;
	private Long		size_file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getIdComapny() {
		return idComapny;
	}

	public void setIdComapny(Long idComapny) {
		this.idComapny = idComapny;
	}

	public Timestamp getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Timestamp dateUpload) {
		this.dateUpload = dateUpload;
	}

	public Long getSize_file() {
		return size_file;
	}

	public void setSizeFile(Long size_file) {
		this.size_file = size_file;
	}

}
