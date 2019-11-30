import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            System.out.println("Сервер подключается...");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установление соединения между локальной машиной и указанным портом узла сети
            System.out.println("Соединение установлено");
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());

                int first, second, third , four;
                Scanner in = new Scanner(System.in);
                System.out.println("Прыжок 1: ");
                first = in.nextInt();
                System.out.println("Прыжок 2: ");
                second = in.nextInt();
                System.out.println("Прыжок 3: ");
                third = in.nextInt();
                System.out.println("Прыжок 4: ");
                four = in.nextInt();
                in.close();

                System.out.println("Вы ввели следующую информацию: " + "\n" + "Прыжок 1: " + first + "\n" +
                        "Прыжок 2: " + second + "\n" + "Прыжок 3: " + third + "\n" + "Прыжок 4: " + four + "\n");

                coos.writeObject("EXEC");
                coos.writeObject(first);
                coos.writeObject(second);
                coos.writeObject(third);
                coos.writeObject(four);
                int result = (int) cois.readObject();
                String winner = (String) cois.readObject();
                System.out.println("С максимальным прыжком : " + result + "\n" + "Победитель: " + winner);


            coos.writeObject("OUT");
            coos.close();
            cois.close();
            clientSocket.close();
        } catch(Exception e)	{
            e.printStackTrace();
        }
    }
}