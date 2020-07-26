package cn.lingnan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	private Properties properties;	
	
	public PropertiesLoader(String sourse) {
		InputStream inputStream=null;
		properties = new Properties();
		inputStream=ClassLoader.getSystemResourceAsStream(sourse);
		try {
			properties.load(inputStream);
			this.setProperties(properties);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public Properties getProperties() {		
		return properties;
	}
	public void setProperties(Properties properties) {		
		this.properties = properties;
	}
	
	

}
