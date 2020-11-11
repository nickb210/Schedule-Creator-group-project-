/**
 * Schedule Creator Application
 * 
 * Schedule class of the application
 * Contains a String variable for the name of the Schedule and an ArrayList of events
 * Functionality includes getters/setters and methods for adding, removing, reading, and writing events 
 * @author Hot Dog or Not Hot Dog
 */
package application.model;

import java.io.File;
import javafx.scene.paint.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Schedule {
	public String scheduleName;
	public ArrayList<Event> events;
	
	/**
	 * Constructor for schedule
	 * @param scheduleName String
	 * @param events ArrayList of Event objects
	 */
	public Schedule(String scheduleName, ArrayList<Event> events) {
		super();
		this.scheduleName = scheduleName;
		this.events = events;
	}

	/**
	 * Overridden constructor for schedule
	 * @param scheduleName String
	 */
	public Schedule(String name) {
		this.scheduleName = name;
		this.events = new ArrayList<Event>();
	}

	/**
	 * returns the schedule name as a String
	 * @return scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}
	/**
	 * Sets the Name of the Schedule of ScheduleObj
	 * @param scheduleName the scheduleName to set
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	/**
	 * Returns Schedule.events as an ArrayList
	 * @return the events
	 */
	public ArrayList<Event> getEvents() {
		return events;
	}
	/**
	 * Sets Schedule.events ArrayList name
	 * @param events the events to set
	 */
	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	/**
	 * Adds event to Schedule.events ArrayList
	 * @param event to add to Schedule ArrayList
	 */
	public void addEvent( Event event ) {
		this.events.add(event);
	}
	/**
	 * remove a single event from events array list
	 * @param event to be removed from events
	 */
	public void deleteEvent( Event event ) {
		this.events.remove(event);
	}
	
	/**
	 * Method for clearing the schedule of events
	 */
	public void clearSchedule( ) {
		for (int i = 0; i < events.size(); i++) {
			events.remove(i);
			
		}
	}
	
	/**
	* checkConflictTimes() - checks event times to make sure the times do not overlap
	* @param tmpE - event to check time conflicts for
	* @return - returns -1 if there is a time conflict, 0 otherwise.
	*/
	public int checkConflictTimes(Event tmpE)
	{
		int result = 0;
		String newFreq = tmpE.getFreq();
		
		//Go through each event 
		for(int i = 0; i < events.size(); i++)
		{
			//Go through each day of the week to see if the event falls on the same day. If so, check the times.
			for(int j = 0; j < 7; j++)
			{
				if(newFreq.charAt(j) == '1' && events.get(i).getFreq().charAt(j) == '1')
				{
					//Checks if start time is in-between an event
					if(tmpE.getEventStart() > events.get(i).getEventStart() && tmpE.getEventStart() < events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
					//Checks if end time is in-between an event
					if(tmpE.getEventEnd() > events.get(i).getEventStart() && tmpE.getEventEnd() < events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
					//Start time is the same but end time is in between 
					if(tmpE.getEventStart() == events.get(i).getEventStart() && tmpE.getEventEnd() < events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
					//Same exact time
					if(tmpE.getEventStart() == events.get(i).getEventStart() && tmpE.getEventEnd() == events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
					//Start time is same but end time is passed the other event's end time
					if(tmpE.getEventStart() == events.get(i).getEventStart() && tmpE.getEventEnd() > events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
					//Start time before other event's start and endtime after other event's endtime 
					if(tmpE.getEventStart() < events.get(i).getEventStart() && tmpE.getEventEnd() > events.get(i).getEventEnd())
					{
						result = -1;
						return result;
					}
					
				}
					
			}
		}
			
		return result;
	}

	/**
	 * Returns Event.name and Event.location of each event in Schedle.events ArrayList
	 * on separate lines
	 * @return ret String of EventObj.toString() on separate lines
	 */
	public String toString() {
		String ret = "";
		ret += getScheduleName() + "\n";
		for(int i = 0; i < events.size(); i++ ) {
			ret += events.get(i).toString() + "\n";
		}
		return ret;
	}
	
	/**
	 * Method for interpreting and loading events into the Event ArrayList
	 * @param s
	 */
	public void loadEvents( String s )
	{
		try 
		{
			Scanner scan = new Scanner(new File(s));
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] tokens = line.split(",", 7);
				Event tmp = null;
				int sTime = Integer.parseInt(tokens[3]);
				int eTime = Integer.parseInt(tokens[4]);
				Color color = new Color(0, 0, 0, 0);
				color = Color.valueOf(tokens[5]);				
				tmp = new Event(tokens[0], tokens[1], tokens[2], sTime, eTime, tokens[6], color);
				events.add(tmp);
			}
			scan.close();
		}catch( IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for writing the events that exist in the Event ArrayList
	 */
	public void save()
	{
		try
		{
			FileWriter writer = new FileWriter("Data/data.csv");
			for(int i = 0; i < events.size(); i++)
			{
				Event event = events.get(i);
				
				String str = event.getEventName() + ",";
				str += event.getEventLocation() + ",";
				str += event.getFreq() + "," + event.getEventStart() + ",";
				str += event.getEventEnd() + ",";
				str += event.getColor().toString() + ",";
				str += event.getDescription() + "\n";
				
				writer.write( str );
			}
			writer.close();
		}catch( IOException e){
			e.printStackTrace();
		}
	}
	
}
