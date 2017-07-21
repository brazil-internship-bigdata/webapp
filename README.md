
Webapp for participact 
========================


API Contents
--------

The base of the URI is :

[http://localhost:8080/webapp]( /)

The mapping of the URI path space is presented in the following table:

URI path                         | Resource class                                               | Param  (key/type)                      | HTTP methods
-------------------------------- | ------------------------------------------------------------ | --------------------------------------------- | ------------
**_/upload_**                    | Upload, store the file file of the company company           | Body : file : file, company : text, password : text  | POST                              
**_/download_**                  | Download, download the file *file* of the company *company*. | Body : file : text, company : text, password : text  | POST                                                       
**_/fileList_**                   | List of files from *company_name* | Query : company_name : text  | GET   
**_/loginAPI_**                   | Log in API         | Query : company_name : text, password_company : text  | GET 




