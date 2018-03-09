# iv1201project

A recruitment system developed for the IV1201 course held at KTH, Kista.

## Getting Started
Follow these instructions to set up the system on your local machine.

### Prerequisites
* [NetBeans 8.2 Enterprise Edition](https://netbeans.org/downloads/) or equivalent IDE
* [Payara Server 181](https://www.payara.fish/downloads)
* [Git](https://git-scm.com/downloads)


### Installation
Download a copy of the github repository, and open it in your preferred IDE,
the system was developed and tested in NetBeans 8.2 EE

Install the Payara Server on your system, and access the Admin Console. This is by default located on localhost, port 4848.
First, we will configure the data source and connection pool in order to connect to the database.
In the lefthand menu, select Resources->JDBC->JDBC Connection Pools. Create a new Connection Pool, with the name "applicantCP" (without the quotes). For resource, select "javax.sql.DataSource" in the drop-down menu. For classname, type "org.apache.derby.jdbc.ClientDataSource40". The remaining options can be left as default. Save the Connection Pool. Now go to the "Additional Properties" tab and enter the following name and value pairs: "ServerName, your server name (default is localhost)"; "databasename, applicantDB"; "username, your database's username"; "password, you database's password". The database is by default set up without a username or password so the latter two properties can be exempt unless credentials are specified in the database.

Now, select JDBC Resources menu and create a new resource, selecting the newly created applicantCP connection pool as data source.
To customize the properties of the database, please see the glassfish-resources file in the WEB-INF folder. To test that the database can connect to the server, ensure that both are running and press "ping" in the admin console.

Authorization is performed by the Java class AuthorizationFilter.java in the model source package. In order to edit which pages users or various roles can access, modify the doFilter method in this class. To add more roles, manually insert their name along with an incremental id in the ROLE table of the database.

After connecting the database and ensuring the server is set up correctly, 
run login.xhtml to confirm successful installation.

## Acknowledgments
* Hej Leif, du är rätt schysst <3
