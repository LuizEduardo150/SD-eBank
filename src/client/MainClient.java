package client;


import java.rmi.*;

import models.Customer;
import servers.gateway.GatewayApiInterface;
import utils.WelcomeTextByTime;

import utils.KeyBoardReader;


public class MainClient {

    public static void main(String[] args){
        KeyBoardReader reader = new KeyBoardReader();
        GatewayApiInterface frontendServer = null;
        // Tentar fazer conexao com o server gateway e obter o objeto remoto
        try{
            frontendServer = (GatewayApiInterface) Naming.lookup( "rmi://localhost/Banco");
        }catch (Exception erro) { 
			System.out.println("Não será possível conectar no momento.\nServidor em manutenção."); 
            System.exit(0);
		} 
        System.out.println("Bem vindo ao serviço e-bank");

        int choice = -1;
        String user="";
        String password="";
        String cpf = "";
        int registred = -100;

        while (choice != 0) {
            if(registred == -100){
                choice = reader.readInt("\nEscolha entre as seguintes opções:\n(1) - Fazer Login.\n(2) - Criar Conta\n(0) - Sair do programa.\n>> Sua opção:");
                switch (choice) {
                    //LOGIN
                    case 1:
                        while(true){
                            System.out.println("Escreva seu login e senha para prosseguir\nOu digite 0 (zero) em algum dos campos para sair.");                
                            user = reader.readString(">> Nome: ");
                            if(user.equals("0")){
                                break;
                            }
                            
                            password = reader.readString(">> senha: ");
                            if(password.equals("0")){
                                break;
                            }
                            
                            try{
                                registred = frontendServer.loguin(user, password);
                                if(registred == -1){
                                    System.out.println("Erro, usuário ou senha incorretos! Tente novamente.");
                                }else if(registred == -2){
                                    System.out.println("Erro, usuário não existe.");
                                }else if(registred == -10){
                                    System.out.println("Desculpe, não será possível logar. Serviço temporariamente indisponível para login.");
                                }else if (registred == 1){
                                    System.out.println(WelcomeTextByTime.getWelcomeText() + user);
                                    registred = 1;
                                    break;
                                } 
                            }catch(Exception erro) {
                                System.out.println("Desculpe, não será possível logar. Serviço temporariamente indisponível.");
                                break;
                            }
                        }
                        break;
                    
                    // CRIAR NOVA CONTA
                    case 2:
                        user = reader.readString("Preencha todos os campos a seguir\n>> Nome: ");
                        cpf = reader.readString(">> Cpf: ");
                        password = reader.readString(">> Senha: ");
                        
                        Customer customer = new Customer(user, cpf, password);
                        
                        try{
                            int ret = frontendServer.requestRegisterCustomer(customer);
                            if(ret == 1){
                                System.out.println("Cadastrado com sucesso, agora é só logar!");
                            }else if(ret == -1){
                                System.out.println("ERRO! Parece que algumas informações estão faltantes ou insuficientes");
                            }if(ret == -2){
                                System.out.println("ERRO! Esse nome já está sendo utilizado em nosso sistema, tente outro.");
                            }else if(ret == -3){
                                System.out.println("ERRO! Esse CPF já está sendo utilizado em nosso sistema, tente outro.");
                            }if(ret == -5){
                                System.out.println("ERRO! Desculpe, sofremos um erro interno na hora de cirar sua conta.\nTente novamente mais tarde.");
                            }
                        }catch(RemoteException e){
                            System.out.println("ERRO! Desculpe, estamos em manutenção.");
                        }
                        
                        break;
    
                    case 0:
                        System.out.println("Até mais!");
                        break;
    
                    default:
                        break;

                }
                
            }
            else if(registred == 1){
                String menuText = "\n====== " + user + "\n(1) - Consultar Saldo.\n(2) - Obter Extrato." +
                                  "\n(3) Fazer Transferência.\n(0) - Deslogar\n>>Sua escolha: ";
                int option2 = reader.readInt(menuText);
                switch (option2) {
                    case 0:
                        registred = -100;
                        break;
                
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

            }

        }
        
        
        
        
        reader.close();
    }


}
