package PSP_FTP_Maven.FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class App 
{
	private static Scanner sc = new Scanner(System.in);
	private static FTPClient ftp;
	
    
	public static void main( String[] args )
    {
//		try {
//			bypassLogin();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
    	int opcion = 0;
    	do {
    		try {
    			opcion = getOpcionDeMenu();
    			ejecutarOpcion(opcion);
			} catch (Exception e) {
				opcion = 0;
				System.out.println("====> Error: " + e.getMessage());
				System.out.println("La opción introducida no es un número, cerrando programa");
				System.out.println();
			}
    	} while (opcion != 0);
        	   
    }

	private static void ejecutarOpcion(int opcion) {
		
		
		switch (opcion) {
		case 1:
			login();
			break;
		case 2:
			logout();
			break;
		case 3:
			list();
			break;
		case 4:
			storeFile();
			break;
		case 5:
			downloadFile();
			break;
		case 6:
			deleteFile();
			break;
		case 7:
			deleteDir();
			break;
		case 8:
			makeDirectory();
			break;
		case 9:
			cwd();
			break;
		case 10:
			remoteConnection();
			break;
		default:
			break;
		}
		
	}

	private static void downloadFile() {
		try {
			System.out.println("Nombre del fichero a descargar: ");
			String t = sc.nextLine();
			boolean retrieved;
			OutputStream os = new FileOutputStream(t);
			retrieved = ftp.retrieveFile(t, os);
			if (retrieved) {
				System.out.println("Fichero [" + t + "] descargado correctamente");
			} else {
				System.out.println("Error al descargar el fichero");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	private static void storeFile() {
		try {
			System.out.println("Ruta del fichero a almacenar: ");
			String t = sc.nextLine();
			boolean stored;
			File file = new File(t);
			FileInputStream fis = new FileInputStream(file);     //opens a connection to an actual file  
			stored = ftp.storeFile(t, fis);
			if (stored) {
				System.out.println("Fichero [" + t + "] almacenado correctamente");
			} else {
				System.out.println("Error al almacenar el fichero");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void remoteConnection() {
		String host = "speedtest.tele2.net";
		int port = 21;
		System.out.println("... Iniciando conexion Remota FTP de internet a  '"+ host +":" + port + "'...");
		
		try {
				ftp = new FTPClient();
				System.out.println("El sistema se ha iniciado correctamente");
				ftp.connect(host, port);
				if (ftp.login("anonymous", "anonymous")) {
					System.out.println("Usuario conectado correctamente al FTP");
				} else {
					System.out.println("Error al conectarse al FTP");
				}
		} catch (IOException e) {
			System.out.println("No se ha podido conectar al servidor: CODE: " + ftp.getReplyCode() + " message: " +  e.getMessage());
			ftp = null;
		}
		
	}

	private static void deleteDir() {
		try {
			System.out.println("Nombre del directorio a borrar: ");
			String t = sc.nextLine();
			boolean deleted;
			
			deleted = ftp.removeDirectory(t);
			if (deleted) {
				System.out.println("Directorio [" + t + "] eliminado correctamente");
			} else {
				System.out.println("Error al eliminar el directorio");
			}
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void deleteFile() {
		try {
			System.out.println("Nombre del archivo a borrar: ");
			String t = sc.nextLine();
			boolean deleted;
			deleted = ftp.deleteFile(t);
			if(deleted) {
				System.out.println("Archivo [" + t + "] eliminado correctamente");
			} else {
				System.out.println("Error al eliminar el archivo");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void makeDirectory() {
		
		try {
			System.out.println("Nombre del directorio: ");
			String t = sc.nextLine();
			boolean created;
			created = ftp.makeDirectory(t);
			if( created = true) {
				System.out.println("Directorio [" + t + "] creado correctamente");
			}else {
				System.out.println("Error al crear el directorio");
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void cwd() {
		try {
			System.out.println("Directorio al que cambiar: ");
			String t = sc.nextLine();
			int code = ftp.cwd(t);
			System.out.println("Returned code: " + code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void list() {
		try {
			listFTPFiles(ftp.listFiles());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listFTPFiles(FTPFile[] dirs) {
		for (FTPFile ftpFile : dirs) {
			System.out.println(ftpFile);
		}
	}

	private static void logout() {
		if (ftp != null) {
			try {
				if(ftp.logout()) {
					System.out.println("Desconectado del servidor FTP");
				} else {
					System.out.println("HYa habido algún error al desconectarse del FTP");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No puedes desconectarte si no te has conectado aún ;)");
		}
	}

	private static void bypassLogin() throws Exception {
		ftp = new FTPClient();
		ftp.connect("localhost");
		if (!ftp.login("marta", "psp")) {
			throw new Exception("Error en bypasss login");
		} else {
			System.out.println("Bypassing login function");
		}
	}
	
	private static void login() {
		System.out.println("...Iniciando sistema...");
		System.out.println("Introduce el nombre de usuario:");
		String usuario = sc.nextLine();
		System.out.println("Introduce la contraseña:");
		String contrasena = sc.nextLine();
		
		try {
				ftp = new FTPClient();
				System.out.println("El sistema se ha iniciado correctamente");
				ftp.connect("localhost");
				if (ftp.login(usuario, contrasena)) {
					System.out.println("Usuario conectado correctamente al FTP");
				} else {
					System.out.println("Error al conectarse al FTP");
				}
		} catch (IOException e) {
			ftp = null;
			System.out.println("No se ha podido conectar al servidor: " + e.getMessage());
		}
	}

	private static int getOpcionDeMenu() {
		System.out.println("");
		System.out.println("******************************");
		System.out.println("******* Menú principal *******");
		System.out.println("******************************");
		System.out.println( "1.- Inicia sesion" );
		System.out.println( "2.- Cierra sesion" );
		System.out.println( "3.- Listar ficheros y directorios" );
		System.out.println( "4.- Subir fichero" );
		System.out.println( "5.- Descargar fichero del servidor" );
		System.out.println( "6.- Eliminar fichero" );
		System.out.println( "7.- Eliminar directorio" );
		System.out.println( "8.- Crear directorio" );
		System.out.println( "9.- Cambiar el directorio actual" );
		System.out.println( "10.-Establecer conexion remotamente" );
		System.out.print( "Selecciona una opcion (0 para terminar): " );
		
		String s = sc.nextLine();

		return Integer.parseInt(s);
	}
}
