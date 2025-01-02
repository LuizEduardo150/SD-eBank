package models;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String password;
    private String saldo;

    public Customer(){}

    public Customer(String id, String name, String password){
        this.setId(id);
        this.setPassword(password);
        this.setName(name);
        // Todo fazer teste de validacao
    }

    public void setId(String id) {  //todo implementar regras
        this.id = id;
    }

    public void setName(String name) {  //todo implementar regras
        this.name = name;
    }

    public void setPassword(String password) {    //todo implementar regras
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getSaldo(){
        return  saldo;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "nome=" + name +
                ", id=" + id +
                ", password=" + password +
                '}';
    }
}
