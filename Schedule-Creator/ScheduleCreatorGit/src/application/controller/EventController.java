/**
 * Schedule Creator Application
 * 
 * Event Controller class of the application
 * Contains multiple JavaFX IDs for the Event view
 * Includes methods for creating new Events and setting time/frequency of Event objects
 * @author Hot Dog or Not Hot Dog
 */
package application.controller;

import application.Main;
import application.model.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class EventController implements EventHandler<ActionEvent>{
	@FXML
	public Button cancel, create, weekView;
	@FXML
	public TextField details;
	@FXML
	public TextField name;
	@FXML
	public TextField eventLocate;
	// FIXED: deleted the old location field and renamed it. 
	@FXML
	public ColorPicker cp;
	@FXML
	public CheckBox mon, tue, wed, thu, fri, sat, sun, sixAM, sevenAM, eightAM, nineAM, tenAM, elevenAM, twelvePM, onePM, twoPM, threePM, fourPM, fivePM, sixPM;
	public String startCheck = "";
	@FXML
	Label errorLabel;
	
	/**
	 * handle method that is called whenever someone cancels creating a new event
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
	 * Method for changing the view to the primary Schedule view
	 * Called whenever the weekView button is pressed 
	 * @param event
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
	 * Adds new Event object to Schedule ArrayList<Event> based on user input
	 * Reads from GUI name, location, frequency, start/end time, details, and color 
	 * @param event ActionEvent
	 */
	public void createEvent(ActionEvent event) {
		String weekFreq = setFrequency();
		int startTime = getStart();
		int endTime = getEnd();
		
		Color c = Color.valueOf(cp.getValue().toString());
		
		int numTimes = checkTimeSel();
		if(numTimes > 2)
		{
			errorLabel.setText("Error! Please select only 2 times.");
		}
		
		else
		{
			Event tmpE = null;
			tmpE = new Event(name.getText(), eventLocate.getText(), weekFreq, startTime, endTime, details.getText(), c);
			
			//checkCOnflictTimes returns -1 if there is a conflict
			int conflictCheck = MainController.weeklySchedule.checkConflictTimes(tmpE);
			
			//Conflict
			if(conflictCheck == -1)
			{
				errorLabel.setText("Event time overlaps with prior event. Re-enter event info");
			}
			else
			{
				MainController.weeklySchedule.addEvent(tmpE);
				//MainController.weeklySchedule.loadEvents("Data/data.csv");
				MainController.weeklySchedule.save();
				errorLabel.setText("Event added.");
			}
		}
		
		name.clear();
		eventLocate.clear();
		details.clear();
		CheckBox[] boxes = {mon, tue, wed, thu, fri, sat, sun, sixAM, sevenAM, eightAM, nineAM, tenAM, elevenAM
				, twelvePM, onePM, twoPM, threePM, fourPM, fivePM, sixPM};
		for(int i = 0; i < 20; i++) {
			boxes[i].setSelected(false);
		}
		
	}
	
	/**
	* checkTimeSel - gets the number of checked boxes for the event's time
	* @return int - result returns the number of times checked by the user
	*/
	public int checkTimeSel()
	{
		int result = 0;
			
		if(sixAM.isSelected())
		{
			result++;
		}
		else if(sevenAM.isSelected())
		{
			result++;
		}
		else if(eightAM.isSelected())
		{
			result++;
		}
		else if(nineAM.isSelected())
		{
			result++;
		}
		else if(tenAM.isSelected())
		{
			result++;
		}
		else if(elevenAM.isSelected())
		{
			result++;
		}
		else if(twelvePM.isSelected())
		{
			result++;
		}
		else if(onePM.isSelected())
		{
			result++;
		}
		else if(twoPM.isSelected())
		{
			result++;
		}
		else if(threePM.isSelected())
		{
			result++;
		}
		else if(fourPM.isSelected())
		{
			result++;
		}
		else if(fivePM.isSelected())
		{
			result++;
		}
		else if(sixPM.isSelected())
		{
			result++;
		}

		return result;
	}
	
	/**
	 * setFrequency - gets binary string representation of week frequency
	 * @return String - result returns representation of frequency in a binary string. Example MTues == 1100000
	 */
	public String setFrequency()
	{
		//tester used to see if prior day was checked, if not add a 0 in its' place.
		String result = "0";
		int tester = 0;
		
		if(mon.isSelected())
		{
			result = "1";
			tester = 1;
		}
		
		//if was not selected add 0 instead
		if(tester != 1)
		{
			tester = 0;
		}
		
		tester = 0;
		
		if(tue.isSelected())
		{
			result += "1";
			tester = 1;
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		tester = 0;
		
		if(wed.isSelected())
		{
			result += "1";
			tester = 1;
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		tester = 0;
		
		if(thu.isSelected())
		{
			result += "1";
			tester = 1;
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		tester = 0;
		
		if(fri.isSelected())
		{
			result += "1";
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		tester = 0;
		
		if(sat.isSelected())
		{
			result += "1";
			tester = 1;
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		tester = 0;
		
		if(sun.isSelected())
		{
			result += "1";
			tester = 1;
		}
		
		if(tester != 1)
		{
			result += "0";
			tester = 0;
		}
		
		return result;
	}
	
	/**
	 * getStart - gets start time based on user selection
	 * @return int - result returns start time in military time
	 */
	public int getStart()
	{
		int result = 0;
		
		if(sixAM.isSelected())
		{
			result = 6;
			startCheck = "six";
			return result;
		}
		else if(sevenAM.isSelected())
		{
			result = 7;
			startCheck = "seven";
			return result;
		}
		else if(eightAM.isSelected())
		{
			result = 8;
			startCheck = "eight";
			return result;
		}
		else if(nineAM.isSelected())
		{
			result = 9;
			startCheck = "nine";
			return result;
		}
		else if(tenAM.isSelected())
		{
			result = 10;
			startCheck = "ten";
			return result;
		}
		else if(elevenAM.isSelected())
		{
			result = 11;
			startCheck = "eleven";
			return result;
		}
		else if(twelvePM.isSelected())
		{
			result = 12;
			startCheck = "twelve";
			return result;
		}
		else if(onePM.isSelected())
		{
			result = 13;
			startCheck = "thirteen";
			return result;
		}
		else if(twoPM.isSelected())
		{
			result = 14;
			startCheck = "fourteen";
			return result;
		}
		else if(threePM.isSelected())
		{
			result = 15;
			startCheck = "fifteen";
			return result;
		}
		else if(fourPM.isSelected())
		{
			result = 16;
			startCheck = "sixteen";
			return result;
		}
		else if(fivePM.isSelected())
		{
			result = 17;
			startCheck = "seventeen";
			return result;
		}
		else if(sixPM.isSelected())
		{
			result = 18;
			startCheck = "eighteen";
			return result;
		}
		return result;
	}
	
	/**
	 * getEnd - gets end time from user selection
	 * @return int - result returns end time in military time
	 */
	public int getEnd()
	{
		
		int result = 0;
		
		if(sixAM.isSelected() && startCheck != "six")
		{
			result = 6;
			return result;
		}
		else if(sevenAM.isSelected() && startCheck != "seven")
		{
			result = 7;
			return result;
		}
		else if(eightAM.isSelected() && startCheck != "eight")
		{
			result = 8;
			return result;
		}
		else if(nineAM.isSelected() && startCheck != "nine")
		{
			result = 9;
			return result;
		}
		else if(tenAM.isSelected() && startCheck != "ten")
		{
			result = 10;
			return result;
		}
		else if(elevenAM.isSelected() && startCheck != "eleven")
		{
			result = 11;
			return result;
		}
		else if(twelvePM.isSelected() && startCheck != "twelve")
		{
			result = 12;
			return result;
		}
		else if(onePM.isSelected() && startCheck != "thirteen")
		{
			result = 13;
			return result;
		}
		else if(twoPM.isSelected() && startCheck != "fourteen")
		{
			result = 14;
			return result;
		}
		else if(threePM.isSelected() && startCheck != "fifteen")
		{
			result = 15;
			return result;
		}
		else if(fourPM.isSelected() && startCheck != "sixteen")
		{
			result = 16;
			return result;
		}
		else if(fivePM.isSelected() && startCheck != "seventeen")
		{
			result = 17;
			return result;
		}
		else if(sixPM.isSelected() && startCheck != "eighteen")
		{
			result = 18;
			return result;
		}
		
		return result;
		
	}
}