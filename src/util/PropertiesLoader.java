package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	private Properties props;
	File file = new File("properties/conection.properties");

	protected PropertiesLoader() {
		props = new Properties();
		try {
			InputStream in = new FileInputStream(file);
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getValor(String chave) {
		return (String) props.getProperty(chave);
	}
	
	protected void setValor(String chave, String valor) {
		props.setProperty(chave, valor);
	}
}