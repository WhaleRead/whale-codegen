package com.whaleread.codegen.config.xml;

import com.whaleread.codegen.config.BuiltInGeneratorConfiguration;
import com.whaleread.codegen.config.Context;
import com.whaleread.codegen.config.TableConfiguration;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

/**
 * Created by Dolphin on 2018/4/8
 */
public class WhaleGeneratorConfigurationParserTest {

    @Test
    public void parseConfiguration() {
    }

    @Test
    public void parseTable_empty() {
        WhaleGeneratorConfigurationParser parser = new WhaleGeneratorConfigurationParser(new Properties());
        Context context = new Context();
        String source = "table_empty.xml";
        Document document = parse(source);
        Node node = document.getElementsByTagName("table").item(0);
        BuiltInGeneratorConfiguration builtInGeneratorConfiguration = new BuiltInGeneratorConfiguration();
        builtInGeneratorConfiguration.setEnableDTO(true);
        builtInGeneratorConfiguration.setEnableDAO(true);
        builtInGeneratorConfiguration.setEnableDAOMethods(true);
        context.setBuiltInGeneratorConfiguration(builtInGeneratorConfiguration);
        parser.parseTable(context, node);
        TableConfiguration configuration = context.getTableConfigurations().get(0);
        assertThat(configuration, allOf(hasProperty("enableDTO", is(true)), hasProperty("enableDAO", is(true)), hasProperty("enableInsertSelective", is(true))));
    }

    @Test
    public void parseBuiltInGenerator() throws Exception {
        WhaleGeneratorConfigurationParser parser = new WhaleGeneratorConfigurationParser(new Properties());
        Context context = new Context();
        String source = "built_in_01.xml";
        Document document = parse(source);
        Node node = document.getElementsByTagName("builtInGenerator").item(0);
        parser.parseBuiltInGenerator(context, node);
        BuiltInGeneratorConfiguration configuration = context.getBuiltInGeneratorConfiguration();
        assertThat(configuration, allOf(hasProperty("enableDTO", is(true)), hasProperty("enableDAO", is(true)), hasProperty("enableDAOMethods", is(true))));
    }

    private static Document parse(String uri) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(WhaleGeneratorConfigurationParserTest.class.getResourceAsStream(uri));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}