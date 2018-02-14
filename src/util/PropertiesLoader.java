package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	
	private Properties props;
	
	File file = new File("properties/conection.properties");
	FileInputStream in;

	protected PropertiesLoader() {
		props = new Properties();
		try {
			in = new FileInputStream(file);
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
		
        try {
            FileOutputStream fos = new FileOutputStream(file);
            props.store(fos, "FILE JDBC PROPERTIES:");
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
	}
}