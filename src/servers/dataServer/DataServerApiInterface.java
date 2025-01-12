package servers.dataServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import models.Customer;

public interface DataServerApiInterface extends Remote{
    
    boolean registerCustomer(Customer customer) throws RemoteException;

    List<Customer> readAllCustomers() throws RemoteException;

    Customer getCustomerByUserName(String userName) throws RemoteException;

    Customer getCustomerByCpf(String cpf) throws RemoteException;
}
