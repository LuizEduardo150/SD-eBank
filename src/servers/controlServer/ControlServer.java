package servers.controlServer;


import java.rmi.*; 
import java.rmi.server.*;

import models.Customer;
import servers.dataServer.DataServerApiInterface;



public class ControlServer extends UnicastRemoteObject implements ControlServerApiInterface{

    public ControlServer() throws RemoteException{
        super();
    }

    public static void main(String[] args){
        try { 
			ControlServer obj = new ControlServer();

			Naming.rebind("rmi://localhost/Controle", obj);
			//Naming.rebind("rmi://172.22.70.30:1099/Controle", obj); 
            
			System.out.println("Server Controle >> ligado no registro RMI sob o nome 'Controle'"); 
		} 
		catch (Exception erro) { 
			System.out.println("ERRO: GatewayServer " + erro.getMessage());
		} 

    }


    /**
     * Retorna  1 : Login permitido, dados consistentes
     * Retorna -1 : Erro loguin negado por senha invalida
     * Retorna -2 : Erro, usuario nao encontrado
     * Retorna -10: Erro de conexão interno dos servers
    */
    
    public int loginRequest(String user, String password) throws RemoteException {
        
        // Server de controle comporta como um cliente do server de dados
        DataServerApiInterface dataServer = null;
        try {
            dataServer = (DataServerApiInterface) Naming.lookup( "rmi://localhost/Dados");
        }catch (Exception erro) { 
            return -10;
		}
        
        Customer customer = dataServer.getCustomerByUserName(user);
        
        if(customer == null){
            return -2;
        }else if(customer.getPassword().equals(password)){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * Retorna  1 : caso tenha registrado com sucesso
     * Retorna -1 : caso esteja faltando informacoes para cadastro
     * Retorna -2 : Nome de usuário ja existe no sistema
     * Retorna -3 : Cpf ja utilizado
     * Retorna -5 : Erro inesperado na hora da escrita
    */
    public int registerCustomer(Customer custumer) throws RemoteException {
        
        DataServerApiInterface dataServer = null;
        try {
            dataServer = (DataServerApiInterface) Naming.lookup( "rmi://localhost/Dados");
        }catch (Exception erro) { 
            return -10;
		}
        
        if(custumer == null){
            return -1;
        }
        if(custumer.getName().length() < 2 || custumer.getName().equals("")){
            return -1;
        }
        if(custumer.getCpf().length() < 2 || custumer.getCpf().equals("")){
            return -1;
        }
        if(custumer.getPassword().length() < 2 || custumer.getPassword().equals("")){
            return -1;
        }

        custumer.set_bank_balance(1000);
        
        // Server de controle comporta como um cliente do server de dados
        Customer search = dataServer.getCustomerByUserName(custumer.getName());
        if(search != null){
            return -2;
        }
        search = dataServer.getCustomerByCpf(custumer.getCpf());
        if(search != null){
            return -3;
        }
        
        // Apos validacoes, se chegar aqui esta apto a registrar
        boolean ret = dataServer.registerCustomer(custumer);
        if(ret){
            return 1;
        }else{
            return - 5;
        }
    }

}
