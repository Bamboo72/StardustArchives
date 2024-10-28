// Started 10/25/2024
// Made to change email data into HTML to display on the Stardust Archives website.    // EmailToHTML(){}
// WELP, turns out I don't need that, but I can use this to modify the HTML to the format I need.

/*
 * TODO:
 * - Make a process that will clean up all the �?��? characters that I think are replacing hearts and certain emojis... Is there some way to get the emojis so I don't have to replace them later?
 * - Not sure if it's needed, but get the date and use it to sort the data
 * - Move the Happy Father's Day email to chats.HTML
 * - Add a Next/Previous button so that you don't have to go back all the way
 * - Add a search function to the home screen
 */ 

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class EmailToHTML{

    ImageIO image;
    String emailText;
    String newFileName = ".html";
    String filePath = "all/";



    public File process(File file){
        // Scanner scanner;
        // RandomAccessFile writer;
        String filePath = file.getAbsolutePath();

        try {
                String fileContents = new String(Files.readAllBytes(Paths.get(filePath))); 

                // Delete the extra <BR>s
                String oldString1 = "<BR>";
                String newString1 = "";
                fileContents = fileContents.replaceAll(oldString1, newString1);

                // String oldString1 = "href=\"\"";
                // String newString1 = "href=\"../../style.css\"";
                // String oldString2 = "<body>";
                // String newString2 = "<body>\n<h2><a style=\"color:whitesmoke\" href=\"..\\..\\StardustHome.html\">Home</a> | <a style=\"color:whitesmoke\" href=\"..\\\">Back</a></h2>";
                // fileContents = fileContents.replace(oldString1, newString1);
                // fileContents = fileContents.replace(oldString2, newString2);

                Files.write(Paths.get(filePath), fileContents.getBytes());
     
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //System.out.println("<li><a href=\"html/ainsleyHTML/" + file.getName() + "\">" + file.getName() + "</a></li>"); // Does this read emojis too?
        return file;
    }

    public List<File> getFileList(String location){
        
        File directory = new File(location);

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".html")){
                    return true;
                }
                return false;
        
                
            }
        };

        ArrayList<File> list = new ArrayList<File>(Arrays.asList(directory.listFiles(filter))); //Arrays.asList(directory.listFiles()) {});
        // list.sort(null);

        return list;
    }

    public static void main(String[] args){
        EmailToHTML program = new EmailToHTML();
        System.out.println("Start:");
        for (File htmlFile : program.getFileList("html/ainsleyHTML")) {
            
            program.process(htmlFile);
        }
    }

   
} 