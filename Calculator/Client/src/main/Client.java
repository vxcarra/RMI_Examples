package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    Scanner input = new Scanner(System.in);

    public static void main(String[] arg){
        Client client = new Client();
        client.connectServer();
    }

    private void connectServer() {
        try {

            double n1,n2,ans;
            int option;

            Registry registro = LocateRegistry.getRegistry("localhost", 7777);
            MathFunctions interfaz = (MathFunctions) registro.lookup("RemoteServer");

            do {
                System.out.println("Choose an operation:\n"
                        + "1) Add\n"
                        + "2) Subtract\n"
                        + "3) Divide\n"
                        + "4) Multiply\n"
                        + "5) Exit");
                option = input.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Input the first number: ");
                        n1 = input.nextDouble();

                        System.out.print("Input the second number: ");
                        n2 = input.nextDouble();

                        System.out.println(n1 + " + " + n2 + " = " + interfaz.add(n1, n2) + "\n");

                        break;

                    case 2:
                        System.out.print("Input the first number: ");
                        n1 = input.nextDouble();

                        System.out.print("Input the second number: ");
                        n2 = input.nextDouble();

                        System.out.println(n1 + " - " + n2 + " = " + interfaz.subtract(n1, n2) + "\n");

                        break;


                    case 3:
                        System.out.print("Input the first number: ");
                        n1 = input.nextDouble();

                        System.out.print("Input the second number: ");
                        n2 = input.nextDouble();

                        if(interfaz.divide(n1, n2).equals("err")){
                            System.out.println("Cannot divide by 0!\n");
                        }
                        else {
                            System.out.println(n1 + " / " + n2 + " = " + interfaz.divide(n1, n2) + "\n");
                        }

                        break;

                    case 4:
                        System.out.print("Input the first number: ");
                        n1 = input.nextDouble();

                        System.out.print("Input the second number: ");
                        n2 = input.nextDouble();

                        System.out.println(n1 + " * " + n2 + " = " + interfaz.multiply(n1, n2) + "\n");

                        break;

                    case 5:
                        System.out.println("Goodbye!");

                }

            } while (option != 5);


        } catch (RemoteException | NotBoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}