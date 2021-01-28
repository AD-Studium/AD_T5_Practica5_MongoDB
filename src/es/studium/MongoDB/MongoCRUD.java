package es.studium.MongoDB;
/**
 * 
 */
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @author Alvca
 *
 */
public class MongoCRUD {
	static Scanner teclado= new Scanner(System.in);
	/* Función para insertar documentos en la base de datos */
	public static void insertarDocumentos(MongoCollection<Document> HarryPotter)
	{
		/* Añade un único documento */
		/*
		 * Creamos un documento mediante new, al que llamamos documento con append()
		 * añadimos cada propiedad al documento creado.
		 */
		Document documento = new Document("name", "William Arthur Weasley").append("species", "Human").append("gender", "Male").append("house", "Gryffindor").append("dateOfBirth", "06-02-1950").append("yearOfBirth", 1950).append("ancestry", "pure-blood").append("eyeColour", "blue").append("hairColour", "red").append("wand", new Document("wood","").append("core","").append("length","")).append("patronus", "weasel").append("hogwartsStudent", false).append("hogwartsStaff", false).append("actor", "").append("alive", true).append("image", "http://hp-api.herokuapp.com/images/William.jpg");
		/* Añadimos un único documento */
		HarryPotter.insertOne(documento);


		/* Inserta varios documentos */
		/* Insertamos varios documentos creados parseando strings */
		HarryPotter.insertMany(Arrays.asList(Document.parse("{name: 'Fleur Isabelle Weasley', species: 'Human', gender: 'Female', house: '', ancestry: 'pure-blood', eyeColour: 'blue', hairColour: 'red', wand: {wood: '', core: '', length: ''}, patronus: 'weasel', hogwartsStudent: false, hogwartsStaff: false, alive: true, image: 'http://hp-api.herokuapp.com/images/Fleur.jpg'}"),
				Document.parse("{name: 'Percy Ignatius Weasley', species: 'Human', gender: 'Male', house: 'Gryffindor', ancestry: 'pure-blood', eyeColour: 'blue', hairColour: 'red', wand: {wood: '', core: '', length: ''}, patronus: 'weasel', hogwartsStudent: false, hogwartsStaff: false, alive: true, image: 'http://hp-api.herokuapp.com/images/Percy.jpg'}")));
		
	}
	/* Función para consultar documentos de la base de datos */
	public static void consultarDocumentos(MongoCollection<Document> harryPotter)
	{

		int menu;
		do {
			System.out.println("Elije que hacer:");
			System.out.println("1-Mostrar todos los personajes cuyo atributo species tenga como valor human");
			System.out.println("2-Mostrar todos los personajes cuyo atributo yearOfBirth sea anterior a 1979");
			System.out.println("3-Mostrar todos los personajes cuyo atributo wood de la propiedad wand sea holly");
			System.out.println("4-Mostrar todos los personajes que estén vivos (propiedad alive igual a true) y además sean estudiantes (propiedad hogwartsStudent igual a true)");
			System.out.println("Inserte 0 para salir");
			menu=Integer.parseInt(teclado.nextLine());
			if(menu==1) {
				//Mostrar todos los personajes cuyo atributo "species" tenga como valor "human".
				FindIterable<Document> buscarHuman = harryPotter.find(eq("species", "human"));
				System.out.println("Mostramos a todos los human: ");
				for (Document buscado : buscarHuman) {
					System.out.println("\t" + buscado.toJson());
				}}else
					if(menu==2) {
						//Mostrar todos los personajes cuyo atributo "yearOfBirth" sea anterior a 1979.
						FindIterable<Document> busquedaFecha = harryPotter.find(gte("yearOfBirth", 1979));
						System.out.println("Mostramos los que sean anterior a 1979: ");
						for (Document buscado : busquedaFecha) {
							System.out.println("\t" + buscado.toJson());
						}}else
							if(menu==3) {
								//Mostrar todos los personajes cuyo atributo "wood" de la propiedad "wand" sea "holly"
								FindIterable<Document> buscaHolly = harryPotter.find(eq("wand.wood", "holly"));
								System.out.println("Mostramos Mostrar todos los personajes cuyo atributo woodde la propiedad wand sea holly: ");
								for (Document buscado : buscaHolly) {
									System.out.println("\t" + buscado.toJson());
								}}else
									if(menu==4) {
										//Mostrar todos los personajes que estén vivos (propiedad "alive" igual a true) y además sean estudiantes (propiedad "hogwartsStudent" igual a true)
										FindIterable<Document> buscaAnaAprobados = harryPotter.find(and(eq("alive", true), eq("hogwartsStudent", true)));
										System.out.println("Mostramos todos los personajes que estén vivos y además sean estudiantes: ");
										for (Document buscado : buscaAnaAprobados) {
											System.out.println("\t" + buscado.toJson());
										}}
		}while (menu !=0);
	}
	/* Función para actualizar documentos de la base de datos */
	public static void actualizarDocumentos(MongoCollection<Document> harryPotter)
	{
		/* Actualizamos el primer documento que cumpla estas condiciones */
		harryPotter.updateOne(eq("name", "Fleur Isabelle Weasley"), combine(set("hairColour", "green"), set("eyeColour", "red")));
		/*Actualizamos todos los documentos que cumplan esta condición*/
		harryPotter.updateMany(eq("species", "Human"), combine(set("species", "human")));
	}
	/* Función para Eliminar documentos en la base de datos */
	public static void eliminarDocumentos(MongoCollection<Document> harryPotter) {
		/*Eliminamos el primer documento que cumpla esta condición.*/
		harryPotter.deleteOne(eq("name", "Fleur Isabelle Weasley"));
		/*Eliminamos todos los documentos que cumplan esta condición*/
		harryPotter.deleteMany(eq("gender", "Male"));
	}
}