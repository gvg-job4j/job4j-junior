package ru.job4j.wiseoracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.02.2019
 */
public class OracleServer {
    /**
     * Сокет для подключения.
     */
    private final Socket socket;

    /**
     * Метод создает новый сервер с сокетом, слушающим указанный порт.
     *
     * @param args Список аргументов.
     * @throws IOException Ошибка ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(5000).accept()) {
            new OracleServer(socket).start();
        }
    }

    /**
     * Метод выполняет запуск сервера.
     *
     * @throws IOException Ошибка ввода-вывода.
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask = null;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask.toLowerCase())) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if ("exit".equals(ask)) {
                out.println("Bye!");
                out.println();
            } else {
                out.println("You say: " + ask);
                out.println();
            }
        } while (!("exit".equals(ask)));
    }

    /**
     * Конструктор, инициализирует сокет для подключения.
     *
     * @param socket Сокет для подключения.
     */
    public OracleServer(Socket socket) {
        this.socket = socket;
    }
}