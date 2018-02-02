package com.nexant.common.pageObjects.checks;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

/**
 * This is Page Object for precessing Exception which we would have during our tests.
 */
public class TraksmartException {
	
	private static final Log log = LogFactory.getLog(TraksmartException.class);
	
	private static final AtomicInteger id = new AtomicInteger(1000000);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
    /**
     * making Sreenshot
     * sending Stack Trace to console
     * Saving exception to log file.
     * Here could be implemented Log4J Methods. But for now it is enough.
     * @param e exception
     */
    public static void Get_TraksmartException(String msg, WebDriver driver, Throwable e) {
    	
    	// id to correlate a log with a saved screen shot
    	int uuid = id.getAndIncrement();
    	String id = uuid + "-" + sdf.format(new Date());

    	// log
    	StringBuilder sb = new StringBuilder();
    	sb.append("id=").append(id);
    	sb.append(", error=").append(msg);
    	if(driver != null) {
    		sb.append(", currentUrl=").append(driver.getCurrentUrl());
    		sb.append(", title=").append(driver.getTitle());
    		sb.append(", windowHandler=").append(driver.getWindowHandle());
    		sb.append(", windowHandles=").append(driver.getWindowHandles());
    		sb.append(", driver=").append(driver);
    		//sb.append(", pageSource=").append(driver.getPageSource()); // the whole page source!
    	}
    	log.error(sb.toString(), e);

    	// making screenshot
    	taskScreenShot(id + ".jpg", driver);
    	
    	// save the page source?
    	if(driver != null && driver.getPageSource() != null){
    		savePageSource(id + ".html", driver.getPageSource());
    	}
    }
    
    static File getLogDir(){
        File dir = new File("logs");
        dir.mkdirs();
        return dir;
    }

    static void taskScreenShot(String filename, WebDriver driver) {

        File f = new File(getLogDir(), filename);
        
        try {
	        Toolkit t = Toolkit.getDefaultToolkit();
	        Rectangle rect = new Rectangle(t.getScreenSize());
	        Robot r = new Robot();
	        BufferedImage img = r.createScreenCapture(rect);
	        ImageIO.write(img, "jpg", f);
        } catch(Exception ex){
        	log.error("cannot save screenshot to " + f, ex);
        }
    }


    
    static void savePageSource(String file_name, String s) {
        File f = new File(getLogDir(), file_name + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(s);
        } catch(Exception ex){
        	throw new RuntimeException("cannot save '" + s + "' to " + f, ex);
        } finally {
        	if(fw != null) {
        		try { fw.close(); } catch(Exception ex){}
        	}
        }
    }

}
