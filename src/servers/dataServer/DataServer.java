package servers.dataServer;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import servers.dataServer.storage.FileManeger;
import models.Customer;


public class DataServer extends UnicastRemoteObject implements DataServerApiInterface {
    
    public DataServer() throws RemoteException{
        super();
    }

    public static void main(String[] args){
        try {
			DataServer obj = new DataServer(); 

			Naming.rebind("rmi://localhost/Dados", obj);
			//Naming.rebind("rmi://172.22.70.30:1099/Dados", obj); 
            
			System.out.println("Server de Dados>> ligado no registro RMI sob o nome 'Dados'"); 
		} 
		catch (Exception erro) { 
			System.out.println("ERRO: GatewayServer " + erro.getMessage());
		}
    }
    
    public boolean registerCustomer(Customer customer) throws RemoteException {
        // Simplesmente escreve em disco
        Boolean ret = FileManeger.writeObject(customer);
        return ret;
    }

    public List<Customer> readAllCustomers() {
        List<Customer> ret = FileManeger.readAllCostomers();
        return ret;
    }

    public Customer getCustomerByUserName(String userName) throws RemoteException {
        Customer ret = FileManeger.getCustomerByUserName(userName);
        return ret;
    }
    
    public Customer getCustomerByCpf(String cpf) throws RemoteException {
        Customer ret = FileManeger.getCustomerByCpf(cpf);
        return ret;
    }

}
