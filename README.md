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