# MSA Tool

A multiple sequence alignment (MSA) to align three or more protein sequences using a proxy and a server as a service.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

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

There are two ways to run this software:
* Compile the IDE (Eclipse - Java IDE):
1. Just open the IDE
2. Import the project folder as a Java Project
3. Click on the ServerMultiThread class that is inside the package gui and follow the following steps: Run As -> 2 Java Application
```
$ java * .java -d <target_address_name>
```
```
$ java * .java -d <target_address_name>
```
4. Click on the ProxyMultiThread class that is inside the package gui and follow the following steps: Run As -> 2 Java Application
```
$ java * .java -d <target_address_name>
```
```
$ java * .java -d <target_address_name>
```
5. Click on the Client class that is inside the package gui and follow the following steps: Run As -> 2 Java Application
```
$ java * .java -d <target_address_name>
```
```
$ java * .java -d <target_address_name>
```

* Compile by terminal:
2. Enter the src folder and compile all the .java files with the following command:
```
$ java * .java -d <target_address_name>
```
3. Enter the chosen destination directory and run the following command:
```
$ java -jar -Djavax.net.ssl.keyStore=keystore -Djavax.net.ssl.keyStorePassword=123456 "...JavaSSLServer.jar"
$ java -jar -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=123456 -Djavax.net.ssl.keyStore=keystore -Djavax.net.ssl.keyStorePassword=123456 "...JavaSSLClient.jar"
$ java -jar -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.trustStorePassword=123456 "...JavaSSLClient.jar"
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

