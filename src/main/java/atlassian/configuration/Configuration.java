package atlassian.configuration;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 *  The job of Configuration class is to fetch all
 *  the config parameters specified in the config file
 *
 */
public class Configuration {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private Properties properties = new Properties();
    private String env;

    public Configuration() {
        BasicConfigurator.configure();
        if (System.getProperty("env") != null)
            this.env = System.getProperty("env");
        else

        this.env = "stage";  //set default as stage
        logger.info("Fetching the environmental details from :"+this.env);
    }

    public Configuration(String env) {
        this.env = env;
    }

    public String getConfig(String property) {
        String config = null;
        logger.info("current directory is ::: " + System.getProperty("user.dir"));
        try {
            properties.load(new FileInputStream("src/main/java/atlassian/configuration/properties/env.properties"));
            config = properties.getProperty(env);
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }

}
