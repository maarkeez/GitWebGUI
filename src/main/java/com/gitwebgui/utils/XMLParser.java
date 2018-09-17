package com.gitwebgui.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Optional;

public class XMLParser {

    public static <T extends Object> Optional<T> parse(String xmlPath, Class<T> clazz) {

        T obj;
        try {
            String xmlContent = (String) FileUtils.readFileToString(new File(xmlPath),"UTF-8");
            XmlMapper mapper = new XmlMapper();
            obj = mapper.readValue(xmlContent, clazz);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(obj);
    }
}
