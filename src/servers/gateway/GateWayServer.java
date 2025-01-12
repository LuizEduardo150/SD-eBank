package servers.gateway;

import java.rmi.*; 
import java.rmi.server.*;

import models.Customer;
import servers.controlServer.ControlServerApiInterface;

public class GateWayServer extends UnicastRemoteObject implements GatewayApiInterface { 

    public GateWayServer() throws RemoteException { 
        super(); //UnicastRemoteObject constructor
    }

	
	/**
     * Retorna  1 : Login permitido, dados consistentes
     * Retorna -1 : Erro loguin negado por senha invalida
     * Retorna -2 : Erro, usuario nao encontrado
     * Retorna -10: Erro de conexão interno dos servers
    */
	public int loguin(String user, String password) throws RemoteException{    
		
		ControlServerApiInterface controlServer = null;
		try{
			controlServer = (ControlServerApiInterface) Naming.lookup( "rmi://localhost/Controle");
		}catch (Exception err){
			return -10;
		}

		int ret = controlServer.loginRequest(user, password);
		
		return ret;
	}

	
	public int requestRegisterCustomer(Customer customer) throws RemoteException {
		
		ControlServerApiInterface controlServer = null;
		try{
			controlServer = (ControlServerApiInterface) Naming.lookup( "rmi://localhost/Controle");
		}catch (Exception err){
			return -10;
		}

		int ret = controlServer.registerCustomer(customer);
		
		return ret;
	}

	

    public static void main(String args[]) { 
		try { 
			GateWayServer obj = new GateWayServer(); 

			Naming.rebind("rmi://localhost/Banco", obj);
			//Naming.rebind("rmi://172.22.70.30:1099/Banco", obj); 
            
			System.out.println("Server >> ligado no registro RMI sob o nome 'Banco'"); 
		} 
		catch (Exception erro) { 
			System.out.println("ERRO: GatewayServer " + erro.getMessage());
		} 

	}

}
