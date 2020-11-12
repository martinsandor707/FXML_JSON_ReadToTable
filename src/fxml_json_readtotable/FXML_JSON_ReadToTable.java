/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_json_readtotable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nando
 */
public class FXML_JSON_ReadToTable extends Application {
    
    public static List<Person> Persons;

    public static void setPersons(List<Person> Persons) {
        FXML_JSON_ReadToTable.Persons = Persons;
    }

    public static List<Person> getPersons() {
        return Persons;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Persons=new ArrayList<>();
        JSONParser parser=new JSONParser();
        try{
            Object obj= parser.parse(new FileReader("Output.json"));
            JSONArray jsonArray=(JSONArray)obj;            
            

            jsonArray.forEach(p ->parsePersonObj((JSONObject)p));
            
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            
        }
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
//    @Override
//    public void stop(){
//        File temp=new File("OutputTemporary.json");
//        display();
//        
//        try{
//            
//            Path path= Paths.get(temp.getAbsolutePath());
//            Files.delete(path);
//        }
//        catch(NoSuchFileException e){
//            e.printStackTrace();
//        }
//        catch(DirectoryNotEmptyException e){
//            e.printStackTrace();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void parsePersonObj(JSONObject p){
        JSONObject userObj = (JSONObject)p.get("Person");
        Person p1=new Person();
        p1.setName((String)userObj.get("Name"));
        p1.setPhone_adress((String)userObj.get("Phone_adress"));
        p1.setComment((String)userObj.get("Comment"));
        Persons.add(p1);
    }
  public static void display()
{
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");

        Label label1= new Label("Pop up window now displayed");

        Button button1= new Button("Close this pop up window");   
        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);

        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
       
}
    
    
}
