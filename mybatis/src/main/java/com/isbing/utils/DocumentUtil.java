package com.isbing.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Created by song bing
 * Created time 2019/8/7 16:18
 */
public class DocumentUtil {

	public static Document createDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(inputStream);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
}
