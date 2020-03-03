import sec.Hash;
import util.User;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.util.Scanner;

public class ThreadServ extends Thread {
    private SSLSocket socket;
    private Hash h;

    ThreadServ(SSLSocket socket){
        this.socket = socket;
        h = new Hash();
    }

    @Override
    public void run() {
        super.run();
        try {
            //Streams
            DataInputStream iS = new DataInputStream(socket.getInputStream());
            DataOutputStream oS = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(iS);
            try {
                User u = (User) objectInputStream.readObject();
                //si eñ usuario se encuentra en la bdd el cliente recibe el ping
                if(isUserOnBdd(u)){
                    oS.writeUTF(hacerPingEnElServer());
                }
                else {
                    oS.writeUTF("No se encuentra el usuario.");
                    socket.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //comprueba que el usuario se encuentra en la bdd
    private boolean isUserOnBdd(User u) {
        try {
            File f = new File("src/bdd.txt");
            Scanner read = null;
            read = new Scanner(f);
            read.useDelimiter(";");
            while (read.hasNext()){
                String name = read.next();
                String pass = read.next();
                if(u.getNombre().equals(name)&&u.getPass().equals(pass)){
                    return true;
                }
                read.nextLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private String hacerPingEnElServer() throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        Process proc;
        String line;
        proc = Runtime.getRuntime().exec("cmd /c ping " + "8.8.8.8");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        System.out.println();
        sb.append("-------------------------- Ping --------------------------");
        while ((line = stdInput.readLine()) != null) {
            sb.append(line+"\n");
        }
        stdInput.close();
        proc.waitFor();
        return sb.toString();
    }
}
