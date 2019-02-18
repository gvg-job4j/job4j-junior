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
     * Последнее полученное сообщение от сервера.
     */
    private String response;

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
     * Метод возвращает последнее полученное сообщение.
     *
     * @return Псоледнее сообщение.
     */
    public String getResponse() {
        return response;
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
            String request = null;
            String serverResponse = null;
            out.println("Hello");
            do {
                while (!(response = in.readLine()).isEmpty()) {
                    System.out.println(response);
                }
                request = console.nextLine();
                out.println(request);
                if ("exit".equals(request)) {
                    while (!(serverResponse = in.readLine()).isEmpty()) {
                        this.response = serverResponse;
                        System.out.println(serverResponse);
                    }
                }
            } while (!"exit".equals(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
