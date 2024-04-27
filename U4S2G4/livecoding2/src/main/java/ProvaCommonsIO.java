import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ProvaCommonsIO {

    public static void main(String[] args) {
        File file = new File("../persistence/newFile.txt");
        try {
            FileUtils.writeStringToFile(file, "ciao", Charset.defaultCharset(), true);
            System.out.println(FileUtils.readFileToString(file, Charset.defaultCharset()));

            FileUtils.deleteQuietly(file);

            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}

