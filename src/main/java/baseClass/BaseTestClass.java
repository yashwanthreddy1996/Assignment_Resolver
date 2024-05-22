package baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class BaseTestClass {
    public static WebDriver driver;
    public static Properties properties;
    String browserName;
    String url;
    String timeouts;

    public BaseTestClass() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/Config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeBrowser(){
        browserName = properties.getProperty("browser"); //chrome
        url = properties.getProperty("url");
        timeouts = properties.getProperty("timeouts");

        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else {
            System.out.println("Supported Browsers: Chrome, Edge and Firefox");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(timeouts)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(timeouts)));
        String path = Paths.get("src/main/resources/QE-index.html").toAbsolutePath().toString();
        System.out.println(path);
        driver.get("file:///" + path);

    }
}
