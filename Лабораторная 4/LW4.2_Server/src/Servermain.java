import java.io.*;
import java.net.*;
import java.lang.Math;

public class Servermain {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Сервер подключается...");
        new Servermain();
        System.out.println("Соединение установлено");
    }
    double res = 0;
    DatagramSocket st;
    byte[] buf = new byte[100];
    DatagramPacket dp=new DatagramPacket(buf,100);

    Servermain() throws IOException, InterruptedException{
        st = new DatagramSocket(12345);
        listen();
    }

    private void listen() throws IOException, InterruptedException
    {
        int x, y, z;
        st.receive(dp);
        String str = new String(dp.getData());
        str = str.substring(0, str.indexOf('\n'));
        System.out.println("Значение " + str + " присвоено переменной x");
        x = Integer.parseInt(str);
        writeToFile(str, "C:\\Users\\Dima\\Desktop\\Бгуир_3 курс\\ПСП\\Лабораторная 4\\LW4.2_Server\\result.txt");
        st.receive(dp);
        str = new String(dp.getData());
        str = str.substring(0, str.indexOf('\n'));
        System.out.println("Значение " + str + " присвоено переменной y");
        y = Integer.parseInt(str);
        writeToFile(str, "C:\\Users\\Dima\\Desktop\\Бгуир_3 курс\\ПСП\\Лабораторная 4\\LW4.2_Server\\result.txt");
        st.receive(dp);
        str = new String(dp.getData());
        str = str.substring(0, str.indexOf('\n'));
        System.out.println("Значение " + str + " присвоено переменной z");
        z = Integer.parseInt(str);
        writeToFile(str, "C:\\Users\\Dima\\Desktop\\Бгуир_3 курс\\ПСП\\Лабораторная 4\\LW4.2_Server\\result.txt");
        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                res = Math.sqrt(8 + Math.pow((Math.abs(x + y)),2) + z)/(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2)) -
                        Math.pow(Math.E,Math.abs(x-y)) * (Math.pow(Math.tan(z),2) + Math.pow(Math.abs(z),1/5));
            }
        });
        t1.start();
        t1.join();
        sendBack();
    }

    private static void writeToFile(String str, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(String.valueOf(str));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendBack() throws IOException
    {
        String str = String.valueOf(res);
        writeToFile(String.valueOf(res), "C:\\Users\\Dima\\Desktop\\Бгуир_3 курс\\ПСП\\Лабораторная 4\\LW4.2_Server\\result.txt");
        byte [] send = str.getBytes();
        dp = new DatagramPacket(send, send.length, InetAddress.getByName("localhost"),12346 );
        st.send(dp);
        st.close();
    }
}