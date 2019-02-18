package ru.job4j.wise_oracle;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Valeriy Gyrievskikh
 * @since 14.02.2019
 */
public class OracleClientTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskExitThenStop() throws IOException {
        testClient("exit", String.format("Hello, dear friend, I'm a oracle.%s%sBye!%s%s", LN, LN, LN, LN), "Bye!");
    }

    @Test
    public void whenAskAnyThenAnswerAnyBack() throws IOException {
        testClient(String.format("I ask you about any%sexit", LN),
                String.format("Hello, dear friend, I'm a oracle.%s%sYou say: I ask you about any%s%sBye!%s%s", LN, LN, LN, LN, LN, LN), "Bye!");
    }

    private void testClient(String output, String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(output.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream console = new ByteArrayInputStream(output.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        OracleClient client = new OracleClient(socket);
        client.start(console);
        assertThat(client.getResponse(), is(expected));
    }
}
