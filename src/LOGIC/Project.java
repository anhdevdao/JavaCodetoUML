package LOGIC;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Get all the Java files in the project
 
public class Project {
    private Diagram diagram;
    public Project(String path) throws IOException{
        diagram = new Diagram();
        parseAllFiles(path);
        diagram.reArrangeStructures();
        diagram.getLevelForStructures();
    }

    private void parseAllFiles(String path) throws IOException{
        File file = new File(path);
        if (file.isFile() && file.getName().endsWith(".java")){
            Parser.parse(diagram, new FileInputStream(file));
        }
        else if (file.isDirectory()){
            for (File f : file.listFiles()){
                parseAllFiles(f.getAbsolutePath());
            }
        }
    }

    public ArrayList<Structure> getAllStructures(){
        return diagram.getStructures();
    }

}
