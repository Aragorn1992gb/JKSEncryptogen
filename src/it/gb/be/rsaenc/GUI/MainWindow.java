/*     */ package it.gb.be.rsaenc.GUI;
/*     */ 
/*     */ import it.gb.be.rsaenc.util.MyDocumentListener;
/*     */ import it.gb.be.rsaenc.util.UtilConstants;
/*     */ import it.gb.be.rsaenc.util.UtilMethods;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.security.cert.CertificateException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ 
/*     */ public class MainWindow
/*     */   extends Frame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  46 */   static JLabel labelResult = new JLabel();
/*     */   
/*     */   private static final String newline = "\n";
/*     */   
/*     */   static JTextField text;
/*     */   
/*     */   static JTextField psw;
/*     */   
/*     */   JTextArea log;
/*     */   static JFileChooser fc;
/*     */   static JButton openConfigBtn;
/*     */   static JButton openKeystoreBtn;
/*     */   static JButton bGen;
/*  59 */   static JFrame frame = new JFrame("JKS Encryptogen");
/*     */   
/*     */   static File configFile;
/*     */   
/*     */   static File jksFile;
/*     */   
/*     */   public static void mainWindow() {
/*  66 */     JPanel panel = new JPanel();
/*  67 */     GridBagLayout layout = new GridBagLayout();
/*     */     
/*  69 */     panel.setLayout(layout);
/*  70 */     GridBagConstraints gbc = new GridBagConstraints();
/*     */     
/*  72 */     ClassLoader classLoader = MainWindow.class.getClassLoader();
/*     */     
/*  74 */     JLabel labelWelcome = new JLabel("Welcome to JKSEncryptogen");
/*  75 */     labelWelcome.setFont(UtilConstants.FONT_TITLE);
/*  76 */     gbc.fill = 3;
/*  77 */     gbc.gridx = 0;
/*  78 */     gbc.gridy = 0;
/*  79 */     gbc.ipady = 0;
/*  80 */     gbc.gridwidth = 3;
/*  81 */     panel.add(labelWelcome, gbc);
/*     */     
/*  83 */     JLabel labelImg = new JLabel();
/*     */     try {
/*  85 */       InputStream stream = MainWindow.class.getClassLoader().getResourceAsStream("lockT32.png");
/*  86 */       BufferedImage img = ImageIO.read(stream);
/*  87 */       ImageIcon icon = new ImageIcon(img);
/*  88 */       labelImg.setIcon(icon);
/*  89 */     } catch (IOException e) {
/*  90 */       e.printStackTrace();
/*     */     } finally {
/*  92 */       gbc.gridx = 1;
/*  93 */       gbc.gridy = 1;
/*  94 */       gbc.ipady = 50;
/*  95 */       gbc.gridwidth = 1;
/*  96 */       panel.add(labelImg, gbc);
/*     */     } 
/*     */     
/*  99 */     JLabel labelName = new JLabel("Keystore pass");
/* 100 */     gbc.gridx = 0;
/* 101 */     gbc.gridy = 6;
/* 102 */     gbc.ipady = 0;
/*     */     
/* 104 */     panel.add(labelName, gbc);
/*     */     
/* 106 */     psw = new JPasswordField(30);
/* 107 */     gbc.gridx = 0;
/* 108 */     gbc.gridy = 7;
/*     */     
/* 110 */     gbc.ipady = 10;
/* 111 */     panel.add(psw, gbc);
/*     */     
/* 113 */     psw.getDocument().addDocumentListener((DocumentListener)new MyDocumentListener());
/*     */     
/* 115 */     JLabel labelName2 = new JLabel("PlanText to encrypt");
/* 116 */     gbc.gridx = 2;
/* 117 */     gbc.gridy = 6;
/* 118 */     gbc.ipady = 0;
/* 119 */     panel.add(labelName2, gbc);
/*     */     
/* 121 */     text = new JPasswordField(30);
/* 122 */     gbc.gridx = 2;
/* 123 */     gbc.gridy = 7;
/* 124 */     gbc.ipady = 10;
/*     */     
/* 126 */     panel.add(text, gbc);
/*     */     
/* 128 */     text.getDocument().addDocumentListener((DocumentListener)new MyDocumentListener());
/*     */     
/* 130 */     JLabel labelSpace2 = new JLabel("");
/* 131 */     gbc.gridx = 0;
/* 132 */     gbc.gridy = 8;
/* 133 */     gbc.ipady = 10;
/* 134 */     panel.add(labelSpace2, gbc);
/*     */     
/* 136 */     fc = new JFileChooser();
/*     */     
/* 138 */     openConfigBtn = new JButton("Open a configuration keystore file");
/*     */     
/* 140 */     openConfigBtn.addActionListener(chooseFile);
/*     */     
/* 142 */     gbc.gridx = 2;
/* 143 */     gbc.gridy = 10;
/* 144 */     gbc.fill = 2;
/* 145 */     panel.add(openConfigBtn, gbc);
/*     */     
/* 147 */     openKeystoreBtn = new JButton("Open your keystore");
/*     */     
/* 149 */     openKeystoreBtn.addActionListener(chooseFile);
/* 150 */     gbc.gridx = 0;
/* 151 */     gbc.gridy = 10;
/* 152 */     gbc.fill = 2;
/* 153 */     panel.add(openKeystoreBtn, gbc);
/*     */     
/* 155 */     JLabel labelSpace3 = new JLabel("");
/* 156 */     gbc.gridx = 0;
/* 157 */     gbc.gridy = 11;
/* 158 */     gbc.ipady = 10;
/* 159 */     panel.add(labelSpace3, gbc);
/*     */     
/* 161 */     bGen = new JButton("Generate");
/* 162 */     gbc.gridx = 0;
/* 163 */     gbc.gridy = 12;
/* 164 */     gbc.gridwidth = 3;
/* 165 */     gbc.fill = 2;
/* 166 */     panel.add(bGen, gbc);
/* 167 */     bGen.setEnabled(false);
/*     */     
/* 169 */     bGen.addActionListener(generate);
/*     */     
/* 171 */     labelResult.setPreferredSize(new Dimension(30, 50));
/* 172 */     labelResult.setMaximumSize(new Dimension(30, 50));
/* 173 */     labelResult.setFont(UtilConstants.FONT_DESCRIPTION);
/* 174 */     gbc.fill = 2;
/* 175 */     gbc.gridx = 0;
/* 176 */     gbc.gridy = 13;
/* 177 */     gbc.ipady = 0;
/* 178 */     panel.add(labelResult, gbc);
/*     */     
/* 180 */     JLabel labelLogo = new JLabel();
/* 181 */     labelLogo.setPreferredSize(new Dimension(30, 50));
/* 182 */     labelLogo.setMaximumSize(new Dimension(30, 50));
/* 183 */     labelLogo.setFont(UtilConstants.FONT_DESCRIPTION);
/* 184 */     gbc.fill = 2;
/* 185 */     labelLogo.setText("Powered by Giacomo Brunetta");
/* 186 */     gbc.gridx = 0;
/* 187 */     gbc.gridy = 14;
/* 188 */     gbc.ipady = 5;
/* 189 */     gbc.gridwidth = 2;
/* 190 */     panel.add(labelLogo, gbc);
/*     */     
/* 192 */     frame.setDefaultCloseOperation(3);
/* 193 */     Container containerPane = frame.getContentPane();
/* 194 */     containerPane.add(panel, "North");
/*     */     
/* 196 */     frame.setPreferredSize(new Dimension(800, 375));
/* 197 */     frame.pack();
/* 198 */     frame.setResizable(false);
/* 199 */     frame.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] arg) {
/* 204 */     mainWindow();
/*     */   }
/*     */   
/* 207 */   private static Action generate = new AbstractAction()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 211 */         String msg = "";
/* 212 */         String type = "Error";
/* 213 */         String labelText = "";
/*     */         try {
/* 215 */           labelText = UtilMethods.generateIt(MainWindow.text.getText(), MainWindow.configFile, MainWindow.psw.getText(), MainWindow.jksFile);
/* 216 */           StringSelection stringSelection = new StringSelection(labelText);
/* 217 */           Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 218 */           clipboard.setContents(stringSelection, null);
/* 219 */           type = "Info";
/* 220 */           msg = "Encrypted text copied to clipboard";
/* 221 */         } catch (ParserConfigurationException|org.xml.sax.SAXException xmlEx) {
/* 222 */           msg = "File configuration parsing problem. Are you sure that it's an XML File?";
/* 223 */           xmlEx.printStackTrace();
/* 224 */         } catch (UnrecoverableKeyException unrKeyEx) {
/* 225 */           msg = "Key cannot be recovered";
/* 226 */           unrKeyEx.printStackTrace();
/* 227 */         } catch (KeyStoreException keyStoreEx) {
/* 228 */           msg = "Problem with KeyStore";
/* 229 */           keyStoreEx.printStackTrace();
/* 230 */         } catch (NoSuchAlgorithmException noSuchAlgEx) {
/* 231 */           msg = "Enrypting problems";
/* 232 */           noSuchAlgEx.printStackTrace();
/* 233 */         } catch (CertificateException certEx) {
/* 234 */           msg = "JKS certificate problem";
/* 235 */           certEx.printStackTrace();
/* 236 */         } catch (IOException ioEx) {
/* 237 */           msg = "File error: " + ioEx.toString().substring(ioEx.toString().indexOf(':') + 1);
/* 238 */           ioEx.printStackTrace();
/* 239 */         } catch (InvalidKeyException invalidKeyEx) {
/* 240 */           msg = "Encrypting problem: Invalid Key (jks)";
/* 241 */           invalidKeyEx.printStackTrace();
/* 242 */         } catch (NoSuchPaddingException|javax.crypto.IllegalBlockSizeException|javax.crypto.BadPaddingException encProblemEx) {
/* 243 */           msg = "Encrypting problem: Check your jks file";
/* 244 */           encProblemEx.printStackTrace();
/*     */         } finally {
/* 246 */           MainWindow.labelResult.setText(labelText);
/* 247 */           MainWindow.labelResult.setToolTipText(MainWindow.labelResult.getText());
/* 248 */           JOptionPane.showMessageDialog(MainWindow.frame, msg, type, -1);
/*     */         } 
/*     */       }
/*     */     };
/*     */ 
/*     */   
/* 254 */   private static Action chooseFile = new AbstractAction()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 258 */         JButton jb = (JButton)e.getSource();
/*     */         
/* 260 */         int returnVal = MainWindow.fc.showOpenDialog(MainWindow.frame);
/* 261 */         File file = null;
/*     */         
/* 263 */         if (returnVal == 0) {
/* 264 */           file = MainWindow.fc.getSelectedFile();
/*     */           
/* 266 */           System.out.println("Opening: " + file.getName() + "." + "\n");
/*     */         } else {
/* 268 */           System.out.println("Open command cancelled by user.\n");
/*     */         } 
/*     */         
/* 271 */         if (jb == MainWindow.openConfigBtn) {
/* 272 */           MainWindow.configFile = file;
/* 273 */         } else if (jb == MainWindow.openKeystoreBtn) {
/* 274 */           MainWindow.jksFile = file;
/*     */         } 
/* 276 */         MainWindow.checkValues();
/*     */       }
/*     */     };
/*     */   
/*     */   public static void checkValues() {
/* 281 */     if (text.getText() != null && !text.getText().isEmpty() && psw.getText() != null && !psw.getText().isEmpty() && 
/* 282 */       configFile != null && jksFile != null) {
/* 283 */       bGen.setEnabled(true);
/*     */     } else {
/* 285 */       bGen.setEnabled(false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Giacomo\Desktop\JKS guide\JKSEncryptogen.jar!\it\gb\be\rsaenc\GUI\MainWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */