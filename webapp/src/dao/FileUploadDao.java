package dao;

import java.util.List;

import beans.FileUpload;

public interface FileUploadDao {

	public void create(FileUpload company) throws DAOException;

	public FileUpload find(Long id) throws DAOException;

	public List<FileUpload> findAllDataByCompany(Long id_company) throws DAOException;

	public int quantityOfData(Long id_company) throws DAOException;

	public int numbertOfDataFile(Long id_company) throws DAOException;

	public int numbertOfResourceFile(Long id_company) throws DAOException;

	public List<FileUpload> findAllResourceByCompany(Long id);
}
