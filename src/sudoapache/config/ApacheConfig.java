package sudoapache.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ApacheConfig {

	private static ApacheConfig instance;
	private HashMap<String, String> config;

	public static ApacheConfig getInstance() {
		if (instance == null) {
			instance = new ApacheConfig();
		}
		return instance;
	}

	private ApacheConfig() {
		config = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("./SudoApache.conf")));
			String optionLine = reader.readLine();
			while (optionLine != null) {
				String value = optionLine.split("=")[1];
				String option = optionLine.split("=")[0];
				config.put(option, value);
				optionLine = reader.readLine();
			}
			reader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public String getConfig(String option) {
		return config.get(option);
	}

	public boolean contains(String option) {
		return config.containsKey(option);
	}

}
