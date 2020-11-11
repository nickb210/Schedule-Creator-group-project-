/**
 * Schedule Creator Application
 * 
 * View Event Controller class of the application
 * Contains multiple JavaFX IDs for TextAreas in the displayEvent view
 * Methods include an initialize and a handle method plus a custom weeklyView method
 * @author Hot Dog or Not Hot Dog
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class viewEventController implements EventHandler<ActionEvent>, Initializable   {
	
	@FXML
	public TextField eventLocation, eventName, eventDetails, startTime, endTime;
	
	@FXML
	public TextArea frequency;
	
	/**
	 * Handle method for returning to the Main view of the application 
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize method for setting the TextAreas with details of individual events
	 * @param location
	 * @param resources
	 */
	public void initialize(URL location, ResourceBundle resources) {
		//needs to check the event information based on the information passed in by the ScheduleController to populate event fields
		
		eventName.setText(ScheduleController.passToViewEvent.getEventName());
		eventLocation.setText(ScheduleController.passToViewEvent.getEventLocation());
		eventDetails.setText(ScheduleController.passToViewEvent.getDescription());
		
		// Probably still need to convert these as they are in military time
		startTime.setText(ScheduleController.passToViewEvent.getStartTimeAsString());
		endTime.setText(ScheduleController.passToViewEvent.getEndTimeAsString());
		
		// Iterate over Schedule.passToViewEvent.getFreq() and when there is a '1' print corresponding day
		frequency.setText(ScheduleController.passToViewEvent.getFrequencyAsString());
		
	}
	
	/**
	 * Method for returning to the weekly view of the Schedule
	 * @param event: Weekly View Button
	 */
	public void weeklyView(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Schedule.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to delete the event being viewed
	 * @param event: deleteEvent Button
	 */
	public void deleteEvent( ActionEvent event ) {
		MainController.weeklySchedule.deleteEvent(ScheduleController.passToViewEvent);
		MainController.weeklySchedule.save();
		initialize( null, null );
		eventName.setText("");
		eventLocation.setText("");
		eventDetails.setText("");
		startTime.setText("");
		endTime.setText("");
		frequency.setText("");
		
	}
}