# Directories

1. Installing dependencies and setting up the project.
2. FIX overview





## 1. Installing the project

In this project, I will be using IntelliJ Community Edition as the IDE, but it should work fine for Eclipse too with slight modifications.

Firstly, the FIX Gateway we will be using is QuickFixJ, specifically versions 2.3.0. Versions 2.3.0 and above comes with lots of bug fixes and ID and password features.  
The Home page:

>https://www.quickfixj.org/index.html
The JAR dependecies to be installed are:
- **QuickFixJ Version 2.3.0 and above** https://sourceforge.net/projects/quickfixj/files/QFJ_RELEASE_2_3_1/
- **SLF4J** https://jar-download.com/artifacts/org.slf4j/slf4j-api
- **Mina-Core-Jar** https://jar-download.com/artifacts/org.apache.mina/mina-core
- **Oracle JDK v19.0 and above**

## 2.FIX overview

In simple terms, FIX is a protocol to communicate between a broker and a client (just like a HTTP protocol between a server and client paradigm). Instead of speaking english,
the message goes something like `35=9|150=8|`, where `35` means FIX version, and `35` means execution type.

Heres a diagram to clearly depict the process.

![Flow drawio](https://user-images.githubusercontent.com/122764198/214790049-fc7209fa-d564-4e85-b12c-2e7aa5e565ad.png)

Every step in the image would be a message in the form of `35=9|150=8|` where `35=9` are the key-value pairs of the image separated by a delimiter `|`.


## 3. Developer's guide (explaining the methods) 

`Application Interface`   
1. `start`  

This method initializes the QuickFIX/J client. It reads the configuration from a file called client.cfg and creates a SessionSettings object with the configuration. It then creates a MessageStoreFactory, a LogFactory, and a MessageFactory. Finally, it creates an Initiator object with the SocketInitiator class and starts the connection to the server.  

2. `stop`  

This method stops the QuickFIX/J client by logging out of the session.  

3. `onLogon`  
This method is called when the client logs in to the FIX server. It sets the session ID for future use.  

4. `onLogout`

This method is called when the client logs out of the FIX server. It prints a message to the console.

5. `toAdmin`  

This method is called when a message is sent to the server. It prints a message to the console. This method is basically used to set your `username` and `password` fields. This handles `OUTGOING` admin messages. Here we are using this method to modify outgoing administrative messages, since in some connections the username and password are not set in the configuration files 

6. `fromAdmin`  

 The purpose of this method is to allow the client application to process and handle incoming administrative messages, which are used for session management and control.

### Below are the application level messages (so far, we have been dealing with the server level messages, which deals with mostly admin stuff)  

7. `fromApp`  

The fromApp method is called by the FIX engine when an application-level message is received from the FIX server. The purpose of this method is to allow the client application to process and handle incoming application messages. So when the lets say your client receives a response from the server after placing an order, this method is processed.  

8. `toApp`   

This method is used to send whatever outgoing application level messages you wish to the server you are connected to.





