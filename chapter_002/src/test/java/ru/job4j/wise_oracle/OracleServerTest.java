package ru.job4j.wise_oracle;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Valeriy Gyrievskikh
 * @since 13.02.2019
 */
public class OracleServerTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskExitThenStop() throws IOException {
        testServer("exit", "Bye!");
    }

    @Test
    public void whenAskHelloThenAnswerHello() throws IOException {
        testServer(String.format("hello%sexit", LN),
                String.format("Hello, dear friend, I'm a oracle.%s%sBye!", LN, LN));
    }

    @Test
    public void whenAskAnyThenAnswerAnyBack() throws IOException {
        testServer(String.format("I ask you about any%sexit", LN),
                String.format("You say: I ask you about any%s%sBye!", LN, LN));
    }

    private void testServer(String input, String output) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        OracleServer server = new OracleServer(socket);
        server.start();
        assertThat(out.toString().trim(), is(output));
    }
}
