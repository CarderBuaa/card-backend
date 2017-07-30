package cn.card.utils.propertyReader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Description:
 * Created by z on 2017/7/30.
 */
public class PropertyReader {

    static Properties properties = new Properties();

    static{
        try {
            Class clazz = PropertyReader.class;
            InputStreamReader fileReader =
                    new InputStreamReader(clazz.getResourceAsStream("/upload.properties"));
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUploadPath(){
        return properties.getProperty("card.uploads");
    }

    @Test
    public void test(){
        System.out.println(PropertyReader.getUploadPath());
    }
}
