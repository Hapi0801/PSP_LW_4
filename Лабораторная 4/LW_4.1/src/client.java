import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            System.out.println("Сервер подключается...");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установлениесоединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Введите строку для отправки серверу \n\t('quite' − programme terminate)");
            String clientMessage = stdin.readLine();
            System.out.println("Вы ввели: "+clientMessage);
            while (!clientMessage.equals("quite"))
            {
                coos.writeObject(clientMessage); //потоку вывода присваивается значение строковой переменной (передается серверу)
                System.out.println("~server~: "+cois.readObject());//выводится на экран содержимое потока ввода (переданное сервером)
                System.out.println("---------------------------");
                clientMessage = stdin.readLine(); //ввод текста с клавиатуры
                System.out.println("Вы ввели: "+clientMessage); //вывод в консоль строки и значения строковой переменной
            }
            coos.close(); //закрытие потока вывода
            cois.close(); //закрытие потока ввода
            clientSocket.close(); //закрытие сокета
        } catch(Exception e)	{
            e.printStackTrace();
        }
    }
}