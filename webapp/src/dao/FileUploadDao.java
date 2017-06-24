package dao;

import java.util.List;

import beans.FileUpload;

public interface FileUploadDao {

	public void create(FileUpload company) throws DAOException;

	public FileUpload find(String email) throws DAOException;

	public List<FileUpload> findAll(String email) throws DAOException;

}
