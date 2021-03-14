package gui;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class App {
	private JFrame jFrame;
	private JPanel panelMain;
	private JButton subirFicheroButton;
	private JButton descargarFicheroButton;
	private JButton salirButton;
	private JTextField user;
	private JTextField password;
	private JButton loginButton;

  static final String DEFAULT_HOST = "localhost";

//  public App() {
//	  subirFicheroButton.addActionListener(new ActionListener() {
//		  @Override
//		  public void actionPerformed(ActionEvent e) {
//			  System.out.println("subirFicheroButton");
//		  }
//	  });
//
//	    descargarFicheroButton.addActionListener(new ActionListener() {
//	      @Override
//	      public void actionPerformed(ActionEvent e) {
//	        System.out.println("descargarFicheroButton");
//	      }
//	    });
//
//	    salirButton.addActionListener(new ActionListener() {
//	      @Override
//	      public void actionPerformed(ActionEvent e) {
//	        System.out.println("salirButton");
//	      }
//	    });
//	    loginButton.addActionListener(new ActionListener() {
//	      @Override
//	      public void actionPerformed(ActionEvent e) {
//	        FTPClient ftpClient = new FTPClient();
//
//	        try {
//	          ftpClient.connect(DEFAULT_HOST);
//
//
//	          if (ftpClient.getReplyCode() == 220) {
//	            System.out.println(ftpClient.getReplyString());
//	            System.out.println("El servidor FTP está preparado");
//	          }
//
//	          boolean isLogged = ftpClient.login(user.getText(), password.getText());
//
//	          if (isLogged) {
//	            System.out.println("Login correcto...");
//
//
//
//	          } else {
//	            System.out.println("Login incorrecto...");
//	          }
//
//	        } catch (IOException ioException) {
//	          ioException.printStackTrace();
//	        }
//	      }
//	    });
//	  }

  	public static void main(String[] args) {
//	    jFrame = new JFrame("App");
//	    jFrame.setContentPane(new App().panelMain);
//	    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    jFrame.pack();
//	    jFrame.setVisible(true);
  		App app = new App();
  		app.jFrame = new JFrame("example");
	    loginButton(app);
	    
	    app.jFrame.setSize(300,300);
	    app.jFrame.setLayout(null);
	    app.jFrame.setVisible(true);
		
  }

	private static void loginButton(App app) {
		app.loginButton = new JButton("Login");
		app.loginButton.setBounds(40,90,85,20);
		app.jFrame.add(app.loginButton);
		
		app.loginButton.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		        FTPClient ftpClient = new FTPClient();

		        try {
		          ftpClient.connect(DEFAULT_HOST);


		          if (ftpClient.getReplyCode() == 220) {
		            System.out.println(ftpClient.getReplyString());
		            System.out.println("El servidor FTP está preparado");
		          }

		          boolean isLogged = ftpClient.login("marta", "psp");

		          if (isLogged) {
		            System.out.println("Login correcto...");



		          } else {
		            System.out.println("Login incorrecto...");
		          }

		        } catch (IOException ioException) {
		          ioException.printStackTrace();
		        }
		      }
		    });
		
	}


}
