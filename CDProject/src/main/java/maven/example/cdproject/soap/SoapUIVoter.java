package maven.example.cdproject.soap;

import java.util.Scanner;

import maven.example.cdproject.db.DB;

public class SoapUIVoter {

	private static Scanner scanner;
	private static int option = -1;
	private static DB db;
	private static String dataBaseName = "test_database";
	private static int votingItemId = 0;
	private static String votingItemName = "";
	
	public static void main(String[] args) throws Exception {
		
		openConnection();
		
		do {
		
			showMenu();
		
			getOption();
			
			workOption();
		
//			System.err.println(option);
			
		} while(option != 99);
		
		closeConnection();
		
	}
	
	private static void workOption() {
		switch(option) {
			case 1: {
				db.getVotingItems(dataBaseName);
				System.out.println();
			}break;
			case 2: {
				
			}break;
			case 3: {
				getVote();
			}break;
			case 4: {
				System.out.println(db.getTotalNumberOfVotes(dataBaseName) + "\r\n");
			}break;
			case 5: {
				
			}break;
			case 6: {
				System.out.println(db.getWinningItem(dataBaseName) + "\r\n");
			}break;
			case 99: 
			case 0: {
				System.out.println();
				break;
			}
			default: {
				System.out.println(
							"Please insert one of the valid values:\r\n"
							+ "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 99"
						);
			}
		}
	}
	
	private static void getVote() {
		System.out.print("Voto? ");
		votingItemId = scanner.nextInt();
		getVotingItemName();
		if (votingItemName != null) {
			System.out.print("Confirma voto em " + votingItemName + " (S/N)? ");
			scanner.nextLine();
			if (scanner.nextLine().equalsIgnoreCase("s")) {
				System.out.println("OK, Votou em " + votingItemName + "\r\n");
				vote();
			}
		} else {
			System.out.println("Não existe nenhum item com o id " + votingItemId);
		}
	}
	
	private static void getVotingItemName() {
		votingItemName = db.getItemName(dataBaseName, String.valueOf(votingItemId));
	}
	
	private static void vote() {
		db.voteItem(dataBaseName, String.valueOf(votingItemId));
	}
	
	private static void getOption() {
		option = scanner.nextInt();
	}
	
	private static void openConnection() throws Exception {
		scanner = new Scanner(System.in);
		db = new DB();
	}
	
	private static void closeConnection() {
		System.out.println(
					"A sair\r\n"
					+ "Cliente Desconectado..."
				);
		scanner.close();
	}
	
	private static void showMenu() {
		System.out.print(
					"##### SOAP Client ####\r\n"
					+ "MENU ADMINISTRADOR\r\n"
					+ "0 - Menu inicial\r\n"
					+ "1 - Listar itens em votação\r\n"
					+ "2 - Tempo restante de votação\r\n"
					+ "3 - Votar\r\n"
					+ "4 - Número total de votos\r\n"
					+ "5 - Listar resultados de votação (%)\r\n"
					+ "6 - Item ganhador\r\n"
					+ "99 - Sair\r\n"
					+ "\r\n"
					+ "Opção? "
				);
	}

}
