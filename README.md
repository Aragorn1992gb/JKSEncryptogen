# JKSEncryptogen
A tool for encrypting data using JKS.

# DECRYPTING USING JAVA KEYSTORE (JKS)

1) JKS generation
From prompt execute:
keytool -genkey -v -keystore [name.jks] -alias [alias] -keyalg RSA -keysize 2048 -validity [giorni]
Replacing square brakets with:
-	Name that I want to give to the file with jks as exstension
-	Alias
-	Value of the key in day, I advise 365000 (1 thousand year)
The command will generate the keystore on the roor path

2) Configuring file generation
An configuring XML file need to be made in order to define the keystore characteristic:

```xml
<?xml version = "1.0"?>
<class>
   <keystore name = "jwtsJKS-keystore">
      <alias>jwtsJKS</alias>
      <extension>jks</extension>
      <filePName>codp</filePName>
   </keystore>
   
   <keystore name = "propEncJwtsJKS-keystore">
      <alias>propEncJwtsJKS</alias>
      <extension>jks</extension>
      <filePName>codp</filePName>
   </keystore>

   <keystore name = "bearer-keystore">
      <alias>bearerJKS</alias>
      <extension>jks</extension>
      <filePName>codp</filePName>
   </keystore>
</class> 
```

the XML file (that is a .txt but in XML format) can contain the description of one or more jks to manage. It is important to define its name withour extension (keystore name), the alias, the extension and filePName. filePName is not mandatory, it indicates the name (including exstension) of the file that contains password to open the      keystore, it can be null if you want to insert it directly on the code. Be careful! If you utilize it, the password must be crypted with another algorithm knew by the source      code, maybe in Aes, in order to be decrited againwhen it is taken. Instead, it can be set "by hand" inside the code himself.

3) Ciphier text generation
To generate ciphier text, you can run MainWindow.java:
insert password of the keystore, clear text to be encrypted, the jks file. After that, click on "Generate". The encrypted text will be generated
