package ru.job4j.jdbc;

import org.junit.Test;
import ru.job4j.jdbc.datamanipulate.*;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 02.04.2019
 */
public class StoreSQLTest {

    @Test
    public void whenConnectToSqliteThenTrue() {
        StoreSQL store = new StoreSQL(new Config());
        assertNotNull(store.getConnect());
    }

    @Test
    public void whenGenerateTenThenGetListSizeTen() {
        StoreSQL store = new StoreSQL(new Config());
        store.generate(10);
        List<Entry> list = store.load();
        assertThat(list.size(), is(10));
    }

    @Test
    public void whenSaveToFileThenFileExists() {
        StoreSQL store = new StoreSQL(new Config());
        store.generate(10);
        List<Entry> list = store.load();
        File source = new File("G:\\projects\\job4j-junior\\chapter_002\\StoreXML.xml");
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(list);
        assertTrue(source.exists());
    }

    @Test
    public void whenParseFileThenSum() {
        StoreSQL store = new StoreSQL(new Config());
//        store.generate(1_000_000); //Выполняется 12 сек.
        store.generate(10);
        List<Entry> list = store.load();
        File source = new File("G:\\projects\\job4j-junior\\chapter_002\\StoreXML.xml");
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(list);
        PrintWriter out = null;
        try {
            File schema = new File("G:\\projects\\job4j-junior\\chapter_002\\schema.xml");
            out = new PrintWriter(schema);
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                    + "<xsl:template match=\"/\">\n"
                    + "<entries>"
                    + "   <xsl:for-each select=\"Entries/entry\">\n"
                    + "       <entry>"
                    + "           <xsl:attribute name=\"href\">"
                    + "               <xsl:value-of select=\"field\"/>"
                    + "           </xsl:attribute>"
                    + "       </entry>\n"
                    + "   </xsl:for-each>\n"
                    + " </entries>\n"
                    + "</xsl:template>\n"
                    + "</xsl:stylesheet>\n");
            out.close();
            File dest = new File("G:\\projects\\job4j-junior\\chapter_002\\destXML.xml");
            new ConvertXSQT().convert(source, dest, schema);
            ParserSAX parser = new ParserSAX();
            parser.parseFile(dest);
//            assertThat(parser.getSum(), is(1_784_293_664));
            assertThat(parser.getSum(), is(55));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void parserTest() {
//        File dest = new File("G:\\projects\\job4j-junior\\chapter_002\\destXML_test.xml");
//        ParserSAX parser = new ParserSAX();
//        parser.parseFile(dest);
//        assertThat(parser.getSum(), is(55));
//    }
}
