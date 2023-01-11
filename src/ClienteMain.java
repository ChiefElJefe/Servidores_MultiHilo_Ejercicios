import java.io.IOException;
import java.net.*;
import java.util.Random;

public class ClienteMain {

    final static String DIRECCION_INET = "224.0.1.3";
    final static int PORT = 8888;

    public static void main(String[] args) throws UnknownHostException {
        Random aleatorio = new Random();

        int num = (int) (aleatorio.nextDouble() * 100 + 1);
        System.out.println("Inicio de host receptor " + num);

        InetAddress direccion = InetAddress.getByName(DIRECCION_INET);
        InetSocketAddress grupo = new InetSocketAddress(direccion, PORT);

        byte[] buf = new byte[256];

        try(MulticastSocket clientesocket = new MulticastSocket(PORT)) {
            clientesocket.joinGroup(grupo, null);
            System.out.println("Host receptor se a unido al grupo " + direccion);
            System.out.println("Inicio de recuperación de paqutes");
            while (true){
                DatagramPacket msgpaquete = new DatagramPacket(buf, buf.length);
                clientesocket.receive(msgpaquete);
                String msg = new String(buf, 0, buf.length);
                System.out.println("Mensaje recibido: " + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
