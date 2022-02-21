/*    */ package it.gb.be.rsaenc.util;
/*    */ 
/*    */ import it.gb.be.rsaenc.GUI.MainWindow;
/*    */ import javax.swing.event.DocumentEvent;
/*    */ import javax.swing.event.DocumentListener;
/*    */ import javax.swing.text.Document;
/*    */ 
/*    */ public class MyDocumentListener
/*    */   implements DocumentListener {
/* 10 */   MainWindow mw = new MainWindow();
/*    */   
/*    */   public void insertUpdate(DocumentEvent e) {
/* 13 */     updateLog(e, "inserted into");
/*    */   }
/*    */   
/*    */   public void removeUpdate(DocumentEvent e) {
/* 17 */     updateLog(e, "removed from");
/*    */   }
/*    */   
/*    */   public void changedUpdate(DocumentEvent e) {
/* 21 */     updateLog(e, "changed from");
/*    */   }
/*    */   
/*    */   public void updateLog(DocumentEvent e, String action) {
/* 25 */     Document doc = e.getDocument();
/* 26 */     MainWindow.checkValues();
/* 27 */     System.out.println(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Giacomo\Desktop\JKS guide\JKSEncryptogen.jar!\it\gb\be\rsaen\\util\MyDocumentListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */