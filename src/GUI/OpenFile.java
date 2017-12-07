package GUI;
import javax.swing.*;
import java.io.File;
import java.util.Scanner;
/**
 * 
 * @author Minh Hoang
 *
 */
public class OpenFile {
	 JFileChooser fileChooser = new JFileChooser();
	 StringBuilder sb = new StringBuilder();
	 private String DirectoryPath;

	public String getDirectoryPath() {
		return DirectoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		DirectoryPath = directoryPath;
	}

	public void PickFile() throws Exception{
	  
	  if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	   
	   //get the file
	   File file = fileChooser.getSelectedFile();
	   
	   //create a scanner for the file
	   Scanner input = new Scanner(file);
	   
	   //read text from file
	   while(input.hasNext()){
	    sb.append(input.nextLine());
	    sb.append("\n");;
	   }
	   
	   input.close();
	  }
  
	 }
	 public void PickDirectory() throws Exception{
		 fileChooser.setCurrentDirectory(new File("."));
		 fileChooser.setDialogTitle("choosertitle");
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 fileChooser.setAcceptAllFileFilterUsed(false);
		 
		  if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		      System.out.println("getCurrentDirectory(): " + fileChooser.getCurrentDirectory());
		      System.out.println("getSelectedFile() : " + fileChooser.getSelectedFile());
		      setDirectoryPath(fileChooser.getSelectedFile().toString());
		    } else {
		      System.out.println("No Selection ");
		    }
	 }
}
