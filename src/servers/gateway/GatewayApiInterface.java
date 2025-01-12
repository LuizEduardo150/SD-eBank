package servers.gateway;


import java.rmi.Remote;
import java.rmi.RemoteException;

import models.Customer;

public interface GatewayApiInterface extends Remote{
    
    /**
     * Retorna  1 : Login permitido, dados consistentes
     * Retorna -1 : Erro loguin negado por senha invalida
     * Retorna -2 : Erro, usuario nao encontrado
     * Retorna -10: Erro de conexão interno dos servers
    */
    int loguin(String user, String password) throws RemoteException; 

    /**
     * Retorna  1 : caso tenha registrado com sucesso
     * Retorna -1 : caso esteja faltando informacoes para cadastro
     * Retorna -2 : Nome de usuário ja existe no sistema
     * Retorna -3 : Cpf ja utilizado
     * Retorna -5 : Erro inesperado na hora da escrita
    */
    int requestRegisterCustomer(Customer customer) throws RemoteException;

    // todo add mais metodos

}
