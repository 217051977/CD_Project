package com.ulht.cd.project.soap.ui;

import java.util.Scanner;

import com.ulht.cd.project.db.DB;

public class SoapUiAdmin {
	
	private static Scanner scanner;
	private static int option = -1;
	private static DB db;
	private static String dataBaseName = "test_database";
	
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
		
		Scanner sc = new Scanner (System.in);
		switch(option) {
			case 1: {
				db.getVotingItems(dataBaseName);
				System.out.println();
			}break;
			case 2: {
				System.out.println("Indique o Nome do Iten a adicionar: ");
				String nomeIten = sc.nextLine();
				System.out.println("Indique o tempo de votação do Iten: ");
				int timeTotal = Integer.parseInt(sc.nextLine());
				Object obj = new FileItem(nomeIten,timeTotal);
				//db.createItemInDatabase(, nomeIten);
				
				
			}break;
			case 3: {
				
			}break;
			case 4: {
				
			}break;
			case 5: {
				System.out.println(db.getTotalNumberOfVotes(dataBaseName));
			}break;
			case 6: {
				
			}break;
			case 7: {
				System.out.println(db.getWinningItem(dataBaseName));
			}break;
			case 8: {
				
			}break;
			case 9: {
				
			}break;
			case 10: {
				
			}break;
			case 11: {
				
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
					+ "2 - Início da sessão\r\n"
					+ "3 - Duração da sessão\r\n"
					+ "4 - Tempo restante de votação\r\n"
					+ "5 - Número total de votos\r\n"
					+ "6 - Listar resultados de votação (%)\r\n"
					+ "7 - Item ganhador\r\n"
					+ "8 - listar utilizadores registados\r\n"
					+ "9 - listar utilizadores da sessão\r\n"
					+ "10 - associar utilizador\r\n"
					+ "11 - remover utilizador\r\n"
					+ "99 - Sair\r\n"
					+ "\r\n"
					+ "Opção? "
				);
	}

}
