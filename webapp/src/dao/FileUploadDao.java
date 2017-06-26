package dao;

import java.util.List;

import beans.FileUpload;

public interface FileUploadDao {

	public void create(FileUpload company) throws DAOException;

	public FileUpload find(Long id) throws DAOException;

	public List<FileUpload> findAllByCompany(Long id_company) throws DAOException;

}
