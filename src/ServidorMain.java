import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.UnknownHostException;

public class ServidorMain {
    final static String DIRECCION_INET = "224.0.1.3";
    final static int PORT = 8888;
    public static void main(String[] args) throws UnknownHostException, InterruptedException, java.net.UnknownHostException {
        InetAddress direccion = InetAddress.getByName(DIRECCION_INET);
        try(DatagramSocket serverSocket = new DatagramSocket()){
            for (int i = 0; i < 5; i++) {
                String mensaje = "--Mensaje " + i + " del host emisor--";
                DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, direccion, PORT);

                serverSocket.send(datagrama);
                System.out.println("Host emisor ha enviado el datagrama con el mensaje: " + mensaje);
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
