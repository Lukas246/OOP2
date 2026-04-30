package file;

import exception.FileNotFoundException;

import java.net.URL;
import java.net.URLDecoder;

public class FileManager {
    public static String getFilePathString(String fileName) throws FileNotFoundException{
        URL fileNameURL = FileManager.class.getClassLoader().getResource(fileName);

        if(fileNameURL == null){
            throw new FileNotFoundException("File not found");
        }
        return URLDecoder.decode(fileNameURL.getFile());
    }
}
