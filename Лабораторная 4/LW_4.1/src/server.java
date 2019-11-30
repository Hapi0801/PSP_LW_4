import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class server {
    public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket clientAccepted = null;
    ObjectInputStream sois = null;
    ObjectOutputStream soos = null;


    ArrayList<String> db = new ArrayList<>();
    db.add(0,"Mazuro");
    db.add(1,"Byrya");
    db.add(2,"Tolik");
    db.add(3,"Maks");

    try {
        System.out.println("Сервер подключается...");
        serverSocket = new ServerSocket(2525);
        clientAccepted = serverSocket.accept();
        System.out.println("Соединение установлено");
        sois = new ObjectInputStream(clientAccepted.getInputStream());
        soos = new ObjectOutputStream(clientAccepted.getOutputStream());

        while(!sois.readObject().equals("exit"))
        {
            int first = (int) sois.readObject();
            int second = (int) sois.readObject();
            int third = (int) sois.readObject();
            int four = (int) sois.readObject();

            String check;
            int result = Math.max(Math.max(first,second), Math.max(third,four));
            if(Math.max(Math.max(first,second), Math.max(third,four)) == first) {
                check = db.get(0);
                System.out.printf("Чемпион: " + check + "\n" + "C результатом: " + result);
                soos.writeObject(result);
                soos.writeObject(check);
            } else if (Math.max(Math.max(first,second), Math.max(third,four)) == second) {
                check = db.get(1);
                System.out.printf("Чемпион: " + check + "\n" + "C результатом: " + result);
                soos.writeObject(result);
                soos.writeObject(check);
            } else if (Math.max(Math.max(first,second), Math.max(third,four)) == third) {
                check = db.get(2);
                System.out.printf("Чемпион: " + check + "\n" + "C результатом: " + result);
                soos.writeObject(result);
                soos.writeObject(check);
            } else if (Math.max(Math.max(first,second), Math.max(third,four)) == four) {
                check = db.get(3);
                System.out.printf("Чемпион: " + check + "\n" + "C результатом: " + result);
                soos.writeObject(result);
                soos.writeObject(check);
            }
        }
    } catch(Exception e) {}

    finally {
        try {
            sois.close();
            soos.close();
            clientAccepted.close();
            serverSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
            }
        }
    }
}