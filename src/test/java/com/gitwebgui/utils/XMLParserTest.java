package com.gitwebgui.utils;

import com.gitwebgui.utils.model.CoverageModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class XMLParserTest {

    @Test
    public void parse() throws IOException {

        ClassLoader loader = this.getClass().getClassLoader();
        String coverageXml = loader.getResource("coverage.xml").getFile();

        CoverageModel coverage = XMLParser.parse(coverageXml, CoverageModel.class).get();
        log.info("COVERAGE ===================> {}", coverage);

    }
}