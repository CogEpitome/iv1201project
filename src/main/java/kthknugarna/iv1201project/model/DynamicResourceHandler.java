package kthknugarna.iv1201project.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class DynamicResourceHandler{

    //private ResourceHandler wrapped;

    public DynamicResourceHandler() {
        
    }

    public void testWrite(){
        try{/*
            File file = new File("filename.xhtml");
            file.createNewFile();
            File filen = new File(".");
            for(String fileNames : filen.list()) System.out.println(fileNames);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Some String");
            printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
            printWriter.close();
            Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream("filename.txt"), "utf-8"));
            System.err.println(reader.read());
            System.err.println(reader.ready());
            reader.close();*/
            Path path = Paths.get("iv1201project/src/main/webapp/WEB-INF/test.xhtml");
            
            Files.createDirectories(path.getParent());
            try{
                Files.createFile(path);
            }
            catch(FileAlreadyExistsException e){
                System.err.println("already exists: " + e.getMessage());
            }
        }
        catch(IOException e){
            System.err.println("FELFELFELFEL");
        }
    }
    
    public void createViewResource() {
        
            try {
                File file = File.createTempFile("../../Web Pages/WEB-INF/dynamic", ".xhtml");

                try (Writer writer = new FileWriter(file)) {
                    writer
                        .append("<ui:composition")
                        .append(" xmlns:ui='http://java.sun.com/jsf/facelets'")
                        .append(" xmlns:h='http://java.sun.com/jsf/html'")
                        .append(">")
                        .append("<p>Hello from a dynamic include!</p>")
                        .append("<p>The below should render as a real input field:</p>")
                        .append("<p><h:inputText /></p>")
                        .append("</ui:composition>");
                }
            }
            catch (IOException e) {
                throw new FacesException(e);
            }
    }

}