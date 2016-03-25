package org.stephen.mdoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtil {
	/**
	 * from spring ResourceUtils
	 *
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getFileFromClasspath(String fileName) throws FileNotFoundException {
		ClassLoader cl = FileUtil.getDefaultClassLoader();
		URL url = (cl != null ? cl.getResource(fileName) : ClassLoader.getSystemResource(fileName));
		if (null == url)
			throw new FileNotFoundException("it does not exist.");

		try {
			URI uri = new URI(url.toString().replace(" ", "%20"));
			return new File(uri.getSchemeSpecificPart());
		} catch (URISyntaxException ex) {
			// Fallback for URLs that are not valid URIs (should hardly ever
			// happen).
			return new File(url.getFile());
		}
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			System.out.println("Cannot access thread context ClassLoader - falling back...");
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = FileUtil.class.getClassLoader();
			if (cl == null) {
				// getClassLoader() returning null indicates the bootstrap
				// ClassLoader
				try {
					cl = ClassLoader.getSystemClassLoader();
				} catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the
					// caller can live with null...
				}
			}
		}
		return cl;
	}
}