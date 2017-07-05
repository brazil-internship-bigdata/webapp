
Webapp for participact 
========================


API Contents
--------

The base of the URI is :

[http://localhost:8080/webapp]( /)

The mapping of the URI path space is presented in the following table:

URI path                         | Resource class                                               | Param in body (key/type)                      | HTTP methods
-------------------------------- | ------------------------------------------------------------ | --------------------------------------------- | ------------
**_/upload_**                    | Upload, store the file file of the company company           | file : file, company : text, password : text  | POST                              
**_/download_**                  | Download, download the file *file* of the company *company*. | file : text, company : text, password : text  | POST                                                       





