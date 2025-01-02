package server.persistence;

import java.io.*;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto responsavel apenas para leitura e escrita de arquivos.
 * Nele ha os metodos que fazem a leitura e atributos de configuracao
 * do diretorio de saida.
 * <p>Esse objeto nao precisa ser intanciado. Todos os atributos sao estaticos.
 * <p> Nao ha modelo de negocio implementado aqui, utilize com cuidado
 * */
public class FileManeger {

    public static String customersFile = "data/customers.dat";

    /**
     * Funcao para escrita de objeto generico em formato binario, nao e feita verificacao se
     * objeto esta sendo salvo em arquivo errado.
     */
    public static boolean writeObject(Object object) {
        try {
            File file = new File(customersFile);

            boolean append = file.exists();
            FileOutputStream fos = new FileOutputStream(customersFile, append);
            ObjectOutputStream oos;

            if (append) { // Se o arquivo já existe, não adicionamos o cabeçalho novamente
                oos = new ObjectOutputStream(fos) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                    }
                };
            } else {
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(object);
            oos.close();
            fos.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean writeCustomer(Customer customer){

        this.writeObject(customer);

        return true;
    }

    public static List<Customer> readAllCostomers() {
        List<Customer> pessoas = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(customersFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Customer pessoa = (Customer) ois.readObject();
                    pessoas.add(pessoa);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }

        return pessoas;
    }

    public static List<Customer> findCostomer(String costomer) {
        List<Customer> pessoas = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(customersFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Customer pessoa = (Customer) ois.readObject();
                    pessoas.add(pessoa);
                    //TODO add if achou cliente
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }

        return pessoas;
    }


}
