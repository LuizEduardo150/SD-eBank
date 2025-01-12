package servers.controlServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import models.Customer;

public interface ControlServerApiInterface extends Remote{
    int loginRequest(String user, String password) throws RemoteException;

    /**
     * Retorna  1 : caso tenha registrado com sucesso
     * Retorna -1 : caso esteja faltando informacoes para cadastro
     * Retorna -2 : Nome de usuário ja existe no sistema
     * Retorna -3 : Cpf ja utilizado
     * Retorna -5 : Erro inesperado na hora da escrita
    */
    int registerCustomer(Customer custumer) throws RemoteException;

}
