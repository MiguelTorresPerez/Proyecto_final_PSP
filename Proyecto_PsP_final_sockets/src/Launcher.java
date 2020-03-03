import Ventanas.VentanaLogin;
import sec.Hash;
import util.User;

import java.io.File;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","src/AlmacenSrv");
        System.setProperty("javax.net.ssl.trustStorePassword","1234567");
        VentanaLogin v = new VentanaLogin();
        v.inicializar();
        /*Hash h = new Hash();
        System.out.println(h.get_SHA_512_SecurePassword("usuario1"));
        System.out.println(h.get_SHA_512_SecurePassword("usuario2"));
        */
    }
}