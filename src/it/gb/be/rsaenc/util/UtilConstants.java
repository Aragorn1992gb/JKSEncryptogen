/*    */ package it.gb.be.rsaenc.util;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.util.HashMap;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UtilConstants
/*    */ {
/*    */   public static final String PRODUCTS_FILENAME = "products.csv";
/*    */   public static final String PRODUCTS_FILE_CHARSET = "UTF-8";
/*    */   public static final char PRODUCTS_FILE_DELIMITER = ';';
/*    */   public static final String CART_ICO = "cart21.jpg";
/*    */   public static final String REMOVE_ICO = "delete21.png";
/*    */   public static final String DESCRIPTION_ICO = "lente21.png";
/*    */   public static final String NATION = "Italy";
/*    */   public static final String OK = "Payment succesful. Your order has been confirmed";
/*    */   public static final String KO = "There are some problems with your order. Please try again";
/* 22 */   public static final Font FONT_TITLE = new Font("Calibri", 1, 20);
/* 23 */   public static final Font FONT_DESCRIPTION = new Font("Calibri", 2, 10);
/*    */ 
/*    */   
/* 26 */   public static HashMap<Integer, Class> columnTypes = new HashMap<Integer, Class>()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static int OTHER_COLUMNS = 1;
/*    */ 
/*    */   
/* 40 */   public static HashMap<Integer, Class> columnTypesCart = new HashMap<Integer, Class>()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public static HashMap<Integer, Class> columnTypesCheckout = new HashMap<Integer, Class>()
/*    */     {
/*    */     
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public static int OTHER_COLUMNS_CART = 1;
/*    */ }


/* Location:              C:\Users\Giacomo\Desktop\JKS guide\JKSEncryptogen.jar!\it\gb\be\rsaen\\util\UtilConstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */