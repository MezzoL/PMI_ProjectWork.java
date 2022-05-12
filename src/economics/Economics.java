package economics;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *  This program manages bank accounts.
 *  Reads clients information from xml file.
 *  Let you list, add, modify and delete clients.
 */
public class Economics {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        XmlScanner xmlTool = new XmlScanner();
        
        
        
        ArrayList<Client> clients = xmlTool.readUsersFromXml("clients.xml");
        
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listClients(clients);
                case 2 -> addClient(clients);
                case 3 -> modifyClient(clients);
                case 4 -> deleteClient(clients);
            }
            System.out.println("1 - List clients");
            System.out.println("2 - Add new client");
            System.out.println("3 - Modify a client");
            System.out.println("4 - Delete a client");
            System.out.println("0 - Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        xmlTool.saveUsersToXml(clients, "clients.xml");

    }

    /**
     * @param clients list oll the clients
     */
    private static void listClients(ArrayList<Client> clients) {
        for(Client client:clients){
            System.out.println(client.toString());
        }
    }

    /**
     * @param clients takes input from standard input and adds a client
     */
    private static void addClient(ArrayList<Client> clients) throws InputMismatchException{
        String name;
        int birthDate;
        double balance;
        try {
            System.out.print("Enter client's name: ");
            name = scanner.nextLine();
            System.out.print("Enter client's birthDate: ");
            birthDate = scanner.nextInt();
            System.out.print("Enter client's balance: ");
            balance = scanner.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Not Valid, Input Mismatch");
            scanner.nextLine();
            return;
        }
        
        
        clients.add(new Client(name, birthDate, balance));
    }


    /**
     * @param clients takes index from standard input and deletes client
     */
    private static void deleteClient(ArrayList<Client> clients) {
        int index;
        try{
            System.out.print("Enter client's index: ");
            index = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Not Valid, Input Mismatch");
            scanner.nextLine();
            return;
        }
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getIndex() == index){
                clients.remove(i);
            }
        }
    }

    /**
     * @param clients takes input from standard input and modifies a client
     */
    private static void modifyClient(ArrayList<Client> clients) {
        int index;
        try{
            System.out.print("Enter client's index: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Not Valid, Input Mismatch");
            scanner.nextLine();
            return;
        }
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getIndex() == index){
                String name;
                int birthDate;
                double balance;
                try {
                    System.out.print("Enter client's name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter client's birthDate: ");
                    birthDate = scanner.nextInt();
                    System.out.print("Enter client's balance: ");
                    balance = scanner.nextDouble();
                }catch (InputMismatchException e){
                    System.out.println("Not Valid, Input Mismatch");
                    scanner.nextLine();
                    return;
                }


                clients.get(i).setName(name);
                clients.get(i).setBirthDate(birthDate);
                clients.get(i).setBalance(balance);

            }
        }
    }


    
    
}
