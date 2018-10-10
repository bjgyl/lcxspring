package com.lcx.springtest.v1;

import com.lcx.spring.core.io.ClassPathResource;
import com.lcx.spring.core.io.FileSystemResource;
import com.lcx.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws Exception {

		Resource r = new ClassPathResource("springtestv1.xml");

		InputStream is = null;

		try {
			is = r.getInputStream();

			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

	@Test
	public void testFileSystemResource() throws Exception {

		Resource r = new FileSystemResource("/Users/lichenxiang/Downloads/lcxspring/src/test/resources/springtestv1.xml");

		InputStream is = null;

		try {
			is = r.getInputStream();

			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

}
