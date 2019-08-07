package com.isbing.utils;

import java.io.InputStream;

/**
 * Created by song bing
 * Created time 2019/8/7 16:10
 */
public class ResourceUtil {

	public static InputStream loadXmlToInputStream(String resource) {
		return ResourceUtil.class.getClassLoader().getResourceAsStream(resource);
	}
}
