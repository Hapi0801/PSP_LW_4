import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер подключается...");
        DatagramSocket st = new DatagramSocket(12346);
        System.out.println("Соединение установлено");
        DatagramPacket dp = null;
        InetAddress loc = InetAddress.getByName("localhost");
        byte[] buf = new byte[100];
        Scanner in = new Scanner(System.in);
        int arr[] = new int [3];
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print("Введите элемент arr["+ i +"]: ");
            int k = System.in.read(buf);
            dp = new DatagramPacket(buf, k, loc,12345 );
            st.send(dp);
        }
        dp = new DatagramPacket(buf,100);
        st.receive(dp);
        System.out.println("Ответ: "+ new String(dp.getData()));
        st.close();
    }
}