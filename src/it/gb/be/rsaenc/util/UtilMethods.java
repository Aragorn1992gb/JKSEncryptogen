/*     */ package it.gb.be.rsaenc.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PublicKey;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.util.Base64;
/*     */ import java.util.HashMap;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UtilMethods
/*     */ {
/*  38 */   static ClassLoader classL = (new UtilMethods()).getClass().getClassLoader();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String generateIt(String planText, File configFile, String psw, File jksFile) throws ParserConfigurationException, SAXException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
/*  44 */     String jksFileName = "";
/*  45 */     String EncText = "";
/*     */     
/*  47 */     int pos = jksFile.getName().lastIndexOf(".");
/*  48 */     if (pos != -1) {
/*  49 */       jksFileName = jksFile.getName().substring(0, pos);
/*     */     }
/*     */     
/*  52 */     HashMap<String, String> hashMapKeystore = retrieveKeystoreDetailsDomParser(jksFileName, configFile);
/*  53 */     FileInputStream keystoreIn = new FileInputStream(jksFile.getAbsolutePath());
/*  54 */     KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
/*  55 */     keystore.load(keystoreIn, psw.toCharArray());
/*  56 */     Key key = keystore.getKey(hashMapKeystore.get("Alias"), psw.toCharArray());
/*  57 */     if (key instanceof java.security.PrivateKey) {
/*     */       
/*  59 */       Certificate cert = null;
/*  60 */       cert = keystore.getCertificate(hashMapKeystore.get("Alias"));
/*     */ 
/*     */       
/*  63 */       PublicKey publicKey = cert.getPublicKey();
/*     */       
/*  65 */       EncText = encrypt(planText, publicKey);
/*     */     } 
/*     */ 
/*     */     
/*  69 */     return EncText;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String encrypt(String plainText, PublicKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
/*  74 */     String encryptedText = "";
/*  75 */     Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
/*  76 */     byte[] plainTextByte = plainText.getBytes();
/*  77 */     cipher.init(1, secretKey);
/*  78 */     byte[] encryptedByte = cipher.doFinal(plainTextByte);
/*  79 */     Base64.Encoder encoder = Base64.getEncoder();
/*  80 */     encryptedText = encoder.encodeToString(encryptedByte);
/*  81 */     return encryptedText;
/*     */   }
/*     */ 
/*     */   
/*     */   public static HashMap<String, String> retrieveKeystoreDetailsDomParser(String keystoreName, File inputFile) throws ParserConfigurationException, SAXException, IOException {
/*  86 */     HashMap<String, String> keystoreElements = new HashMap<>();
/*  87 */     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
/*  88 */     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
/*  89 */     Document doc = dBuilder.parse(inputFile);
/*  90 */     doc.getDocumentElement().normalize();
/*  91 */     NodeList nList = doc.getElementsByTagName("keystore");
/*     */     
/*  93 */     for (int temp = 0; temp < nList.getLength(); temp++) {
/*  94 */       Node nNode = nList.item(temp);
/*     */       
/*  96 */       if (nNode.getNodeType() == 1) {
/*  97 */         Element eElement = (Element)nNode;
/*  98 */         if (eElement.getAttribute("name").equals(keystoreName)) {
/*  99 */           keystoreElements.put("KeystoreName", eElement.getAttribute("name"));
/* 100 */           keystoreElements.put("Alias", eElement.getElementsByTagName("alias").item(0).getTextContent());
/* 101 */           keystoreElements.put("Extension", 
/* 102 */               eElement.getElementsByTagName("extension").item(0).getTextContent());
/* 103 */           keystoreElements.put("FilePName", 
/* 104 */               eElement.getElementsByTagName("filePName").item(0).getTextContent());
/* 105 */           return keystoreElements;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 111 */     return keystoreElements;
/*     */   }
/*     */ }


/* Location:              C:\Users\Giacomo\Desktop\JKS guide\JKSEncryptogen.jar!\it\gb\be\rsaen\\util\UtilMethods.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */