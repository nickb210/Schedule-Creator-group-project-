/**
 * Schedule Creator Application
 * 
 * Main Controller class
 * Contains a handle method to creating an event and a viewEvent method for viewing specific events
 * @author Hot Dog or Not Hot Dog
 */
package application.controller;

import application.Main;
import application.model.Schedule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class MainController implements EventHandler<ActionEvent>  {
	@FXML
	Label loadStatus;
	//Schedule object for loading events
	public static Schedule weeklySchedule = new Schedule("Work");
	/**
	 * handle method for Event creation at the beginning of the application
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Event.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for loading the events into the Schedule object along with a scene change to view the schedule
	 * Called whenever the View Schedule button is clicked
	 * @param event
	 */
	public void viewEvent(ActionEvent event) {
		
		// ERROR: This line is causing an error. I think its because of where gitHub is placing the data
		// We may just have to save the data.csv file a different way in the project
		//weeklySchedule.loadEvents("Data/data.csv");
		
		/*
		 * +++++++++ FOR TESTING .loadEvents( file ) ++++++++++++++
		 * 
		for(int i = 0; i < weeklySchedule.getEvents().size(); i++) {
			System.out.println(weeklySchedule.getEvents().get(i).getEventName());
			}
		*/
			
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Schedule.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void bloadSchedule(ActionEvent event)
	{
		MainController.weeklySchedule.loadEvents("Data/data.csv");
		loadStatus.setText("Successsfully loaded schedule.");
	}
}