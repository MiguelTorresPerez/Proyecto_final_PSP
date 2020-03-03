package controladoras;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sec.Hash;
import util.User;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraLogIn implements Initializable {
    private Hash h;
    public ControladoraLogIn() {
        h = new Hash();
    }



    @FXML
    TextField user;
    @FXML
    PasswordField password;
    @FXML
    Button logIn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logIn.setOnAction(e->{
            //con h hashea la contraseña y el usuario que en el lado del servidor luego comprueba con el metodo isUserOnBdd()
            //si hubiera metodo registro se enviarian las credenciales hasheadas y la comprobacion seria la misma
            clienteS(new User(h.get_SHA_512_SecurePassword(user.getText()),h.get_SHA_512_SecurePassword(password.getText())));
        });
    }

    //envia los datos del usuario al servidor
    public void clienteS(User u){
        try {
            SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", 5559);
            DataOutputStream oS = new DataOutputStream(socket.getOutputStream());
            DataInputStream iS = new DataInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(oS);
            objectOutputStream.writeObject(u);
            System.out.println("Usuario enviado");
            //si se encuentra la contraseña en la base de datos del servidor recibe el ping
            System.out.println(iS.readUTF());
        }catch (Exception e){

        }
    }
}
