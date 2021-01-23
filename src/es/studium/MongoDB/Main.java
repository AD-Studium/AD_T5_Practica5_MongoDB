/**
 * 
 */
package es.studium.MongoDB;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Alvca
 *
 */
public class Main {
	static Scanner tecladoPrincipal= new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos un objeto de tipo MongoClient, con el que establecemos la conexion a la base de datos
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		//Creamos la base de datos
		MongoDatabase database = conexion.getDatabase("AD_T5_Practica5");
		//
		MongoCollection<Document> harryPotter = database.getCollection("Harry-Potter");
		int menuPrincipal;
		do {
			System.out.println("Elije que hacer:");
			System.out.println("1-Insertar Documentos");
			System.out.println("2-Consultar Documentos");
			System.out.println("3-Actualizar Documentos");
			System.out.println("4-Eliminar Documentos");
			System.out.println("Inserte 0 para salir");
			menuPrincipal=Integer.parseInt(tecladoPrincipal.nextLine());
			if(menuPrincipal==1) {
				MongoCRUD.insertarDocumentos(harryPotter);
			}else
				if(menuPrincipal==2) {
					MongoCRUD.consultarDocumentos(harryPotter);
				}else
					if(menuPrincipal==3) {
						MongoCRUD.actualizarDocumentos(harryPotter);
					}else
						if(menuPrincipal==4) {
							MongoCRUD.eliminarDocumentos(harryPotter);
						}
		}while (menuPrincipal !=0);
	}
}
