package ru.job4j.wise_oracle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.02.2019
 */
public class OracleClient {
    /**
     * Сокет для подключения.
     */
    private Socket socket;

    /**
     * Конструктор, инициализирует сокет для работы клиента.
     *
     * @param socket Сокет для подключения.
     * @throws IOException Ошибка ввода-вывода.
     */
    public OracleClient(Socket socket) throws IOException {
        this.socket = socket;
    }

    /**
     * Метод создает клиент, работающий с указанным сокетом.
     *
     * @param args Входные параметры.
     * @throws IOException Ошибка ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
        new OracleClient(socket).start(System.in);
    }

    /**
     * Метод запускает клиент, получающий сообщения из указанного потока ввода.
     *
     * @param input поток ввода данных.
     */
    public void start(InputStream input) {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            Scanner console = new Scanner(input);
            String request = "";
            out.println("Hello");
            System.out.println("Hello");
            do {
                printServerAnswer(in);
                request = console.nextLine();
                out.println(request);
            } while (!"exit".equals(request));
            printServerAnswer(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выводит на экран сообщения сервера.
     *
     * @param in Входящий поток сокета.
     * @throws IOException Если возникает ошибка ввода-вывода.
     */
    private void printServerAnswer(BufferedReader in) throws IOException {
        String serverResponse = null;
        while (!(serverResponse = in.readLine()).isEmpty()) {
            System.out.println(serverResponse);
        }
    }
}
