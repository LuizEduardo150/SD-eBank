package testes;

import org.jgroups.*;
import java.io.*;
import java.util.Scanner;


public class SimpleChatTest extends ReceiverAdapter{
    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit"))
                    break;
                line="[" + user_name + "] " + line;
                Message msg=new Message(null, null, line);
                channel.send(msg);
            }
            catch (Exception e) {
                System.out.println("erro"); //
            }
        }
    }

    private void start() throws Exception {
        channel=new JChannel(); // use the default config, udp.xml
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    /**
     * funcao chamada sempre que uma nova intancia se junta ao grupo ou uma instancia conhecida sai, seja por final de
     * execucao ou por erro.
     * */
    public void viewAccepted(View new_view) { //
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        SimpleChatTest program = new SimpleChatTest();
        program.setUser_name(nome);
        program.start();
    }
}
