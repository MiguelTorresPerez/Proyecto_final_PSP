import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servidor {
    public static void main(String[] arg) throws IOException {
        int puerto = 5559;
        System.setProperty("javax.net.ssl.keyStore","src/AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword","1234567");
        SSLServerSocket servidor = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(puerto);
        System.out.println("Esperando clientes: ");
        while(true)new ThreadServ((SSLSocket) servidor.accept()).run();
    }}