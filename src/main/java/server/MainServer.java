package server;

import java.util.List;

import server.persistence.FileManeger;
import models.Customer;

public class MainServer {
    public static void main(String[] args) {

        int a = 1;

        if(a == 1){
            Customer ca = new Customer("2", "karen", "456");
            FileManeger.writeObject(ca);
        }
        else if(a == 2){
            List<Customer> lista =  FileManeger.readAllCostomers();
            if (lista != null){
                for (Customer pessoa : lista) {
                    System.out.println(pessoa);
                }
            }else{
                System.out.println("deu ruium");
            }
        }
    }
}