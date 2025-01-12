package models;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String name;
    private String password;
    private double bank_balance;

    public Customer(){}

    public Customer(String name, String cpf, String password) {
        this.cpf = cpf;
        this.name = name;
        this.password = password;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { 
        this.password = password;
    }

    public void set_bank_balance(double value){
        this.bank_balance = value;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSaldo(){
        return bank_balance;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Nome: %s", cpf, name);
    }
}
