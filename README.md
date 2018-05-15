# MSA Tool

A multiple sequence alignment (MSA) to align three or more protein sequences using a proxy and a server as a service.

### Prerequisites

You will need to install the modules below to run the program: 
* [biojava-alignment.jar](https://repo1.maven.org/maven2/org/biojava/biojava-alignment/4.2.0/biojava-alignment-4.2.0.jar)
* [biojava-core.jar](https://repo1.maven.org/maven2/org/biojava/biojava-core/4.2.0/biojava-core-4.2.0.jar)
* [biojava-phylo.jar](https://repo1.maven.org/maven2/org/biojava/biojava-phylo/4.2.0/biojava-phylo-4.2.0.jar)
* [forester.jar](https://github.com/cmzmasek/forester/blob/master/forester/java/forester.jar)
* [slf4j.jar](https://www.slf4j.org/dist/slf4j-1.7.25.tar.gz)

Besides that, without a keystore, both the server, client and proxy will fail. This video show how to generate keystore using keytool program. Then run server, client and proxy with keystore and password.
Type the following command in your command window to create a keystore named examplestore and to generate keys:

```
$ keytool -genkey -alias signFiles -keystore examplestore
```

You will be prompted to enter passwords for the key and keystore. The password in this example is "123456".

### Running

Before running, you will have to modify part of the code to enter your key used by SSL. In method main of ServerMultiThread, you will need to modify the bellow lines of code:
```java
System.setProperty("javax.net.ssl.keyStore",path to key);
System.setProperty("javax.net.ssl.keyStorePassword", password);
```
An example would be:
```java
System.setProperty("javax.net.ssl.keyStore","MSATool/src/examplestore");
System.setProperty("javax.net.ssl.keyStorePassword","123456");
```
In method main of ProxyMultiThread, you will need to modify the bellow lines of code:
```java
System.setProperty("javax.net.ssl.keyStore",path to key);
System.setProperty("javax.net.ssl.keyStorePassword", password);
System.setProperty("javax.net.ssl.trustStore",path to key);
System.setProperty("javax.net.ssl.trustStorePassword",password);
```
An example would be:
```java
System.setProperty("javax.net.ssl.keyStore","MSATool/src/examplestore");
System.setProperty("javax.net.ssl.keyStorePassword","123456");
System.setProperty("javax.net.ssl.trustStore","MSATool/src/examplestore");
System.setProperty("javax.net.ssl.trustStorePassword","123456");
```
In method main of Client, you will need to modify the bellow lines of code:
```java
System.setProperty("javax.net.ssl.trustStore",path to key);
System.setProperty("javax.net.ssl.trustStorePassword",password);
```
An example would be:
```java
System.setProperty("javax.net.ssl.trustStore","MSATool/src/examplestore");
System.setProperty("javax.net.ssl.trustStorePassword","123456");
```
There are two ways to run this software:
* Compile the IDE (Eclipse - Java IDE):
1. Just open the IDE
2. Import the project folder as a Java Project
3. Click on the ServerMultiThread class and go to run configurations, in Arguments tab, modify the "Program arguments" to:
```
$ portOfServer backlogOfServer
```
An example would be:
```
$ 9000 10
```
4. Click on the ProxyMultiThread class and go to run configurations, in Arguments tab, modify the "Program arguments" to:
```
$ portOfServer1 portOfServer2 portOfProxy adressOfServer1 adressOfServer2
```
An example would be:
```
$ 9000 9001 9002 127.0.0.1 127.0.0.1
```
4. Click on the Client class and go to run configurations, in Arguments tab, modify the "Program arguments" to:
```
$ portOfProxy adressOfProxy
```
An example would be:
```
$ 9002 127.0.0.1
```
* Compile by terminal:
2. Enter the src folder and compile all the .java files with the following command:
```
$ java * .java -d <target_address_name>
```
3. Enter the chosen destination directory and run the following command:
For ServerMultiThread:
```
$ java -cp path to biojava-alignment.jar:path to biojava-core.jar:path to biojava-phylo.jar:path to forester.jar:/MSATool/lib/slf4j-api.jar:/MSATool/lib/slf4j-simple.jar:. ServerMultiThread portOfProxy adressOfProxy
```
An example would be:
```
$ java -cp /MSATool/lib/biojava-alignment-4.2.0.jar:/MSATool/lib/biojava-core-4.2.0.jar:/MSATool/lib/biojava-phylo-4.2.0.jar:/MSATool/lib/forester-1.005.jar:/MSATool/lib/slf4j-api-1.7.25.jar:/MSATool/lib/slf4j-simple-1.7.25.jar:. ServerMultiThread 9000 10
```
For ProxyMultiThread:
```
$ java ProxyMultiThread portOfServer1 portOfServer2 portOfProxy adressOfServer1 adressOfServer2
```
An example would be:
```
$ java ProxyMultiThread 9000 9001 9002 127.0.0.1 127.0.0.1
```
For Client:
```
$ java Client portOfProxy adressOfProxy
```
An example would be:
```
$ java Client 9002 127.0.0.1
```
4. From this it only interacts with the system.

## Built With

* [Eclipse](https://www.eclipse.org/) - A IDE used

## Authors
### Developers: 
* **[Luís Eduardo Anunciado Silva](cruxiu@ufrn.edu.br)** 
* **[Shirley Ohara Telemaco de Freitas](shirleyohara@ufrn.edu.br)** 
### Project Advisor: 
* **[Augusto José Venâncio Neto](augusto@dimap.ufrn.br)** 

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the GPL 3.0 - see the [LICENSE.md](LICENSE.md) file for details

