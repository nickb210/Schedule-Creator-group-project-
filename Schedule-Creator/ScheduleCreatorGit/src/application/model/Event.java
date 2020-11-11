/**
 * Schedule Creator Application
 * 
 * Event class of the application
 * Contains variables that describe the event object
 * Methods include getters/setters and a toString method 
 * @author Hot Dog or Not Hot Dog
 */
package application.model;

import javafx.scene.paint.Color;

public class Event {
	public String eventName;
	public String eventLocation;
	public String freq;				// Binary number to represent days event is on (EX: MWF = 1010100)
	
	public int eventStart;			// can be: 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
	public int eventEnd;			// can be: 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
	
	public String description = "";
	public Color color;

	/**
	 * Constructor for the event object
	 * 
	 * @param eventName
	 * @param eventLocation
	 * @param freq
	 * @param eventStart
	 * @param eventEnd
	 * @param description
	 * @param color
	 */
	public Event(String eventName, String eventLocation, String freq, int eventStart, int eventEnd, String description,
			Color color) {
		super();
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.freq = freq;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.description = description;
		this.color = color;
	}
	/**
	 * Prints the days of the week that Event object is set to take place on
	 * @return days of the week event is set to
	 */
	public String getFrequencyAsString() {
		String prettyPrint = "";
		int flag = 0;
		for( int i = 0; i < freq.length(); i++ ) {
			if( freq.charAt(i) == '1' ) {
				flag = 1;
				switch( i ) {
				case 0:
					prettyPrint += "Monday" + "\n";
					break;
				case 1:
					prettyPrint += "Tuesday" + "\n";
					break;
				case 2:
					prettyPrint += "Wednesday" + "\n";
					break;
				case 3: 
					prettyPrint += "Thursday" + "\n";
					break;
				case 4:
					prettyPrint += "Friday" + "\n";
					break;
				case 5:
					prettyPrint += "Saturday" + "\n";
					break;
				case 6:
					prettyPrint += "Sunday" + "\n";
					break;
				}
			}
		}
		if( flag == 0 ) {
			prettyPrint = "no days selected";
		}
		
		return prettyPrint;
	}

	/**
	 * returns the start time as a string
	 * @return eventStart String
	 */
	public String getStartTimeAsString() {
		int prettyPrint = eventStart;
		if(prettyPrint > 12) {
			switch(prettyPrint) {
			case 13:
				prettyPrint = 1;
				break;
			case 14:
				prettyPrint = 2;
				break;
			case 15:
				prettyPrint = 3;
				break;
			case 16:
				prettyPrint = 4;
				break;
			case 17: 
				prettyPrint = 5;
				break;
			case 18: 
				prettyPrint = 6;
				break;
				
			}
		}
		return prettyPrint + ":00";
	}
	
	/**
	 * returns the end time as a string
	 * @return eventEnd String
	 */
	public String getEndTimeAsString() {
		int prettyPrint = eventEnd;
		if(prettyPrint > 12) {
			switch(prettyPrint) {
			case 13:
				prettyPrint = 1;
				break;
			case 14:
				prettyPrint = 2;
				break;
			case 15:
				prettyPrint = 3;
				break;
			case 16:
				prettyPrint = 4;
				break;
			case 17: 
				prettyPrint = 5;
				break;
			case 18: 
				prettyPrint = 6;
				break;
				
			}
		}
		return prettyPrint + ":00";
	}

	
	/**
	 * returns the event name
	 * @return eventName
	 */
	public String getEventName() {
		return eventName;
	}


	/**
	 * sets the event name
	 * @param eventName 
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	/**
	 * returns the event location
	 * @return the eventLocation
	 */
	public String getEventLocation() {
		return eventLocation;
	}


	/**
	 * sets the event location
	 * @param eventLocation
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}


	/**
	 * returns the frequency of the event
	 * @return freq
	 */
	public String getFreq() {
		return freq;
	}


	/**
	 * sets the frequency of the event
	 * @param freq
	 */
	public void setFreq(String freq) {
		this.freq = freq;
	}


	/**
	 * returns the event start
	 * @return eventStart
	 */
	public int getEventStart() {
		return eventStart;
	}


	/**
	 * sets the event start time
	 * @param eventStart
	 */
	public void setEventStart(int eventStart) {
		this.eventStart = eventStart;
	}


	/**
	 * returns the eventEnd time
	 * @return eventEnd
	 */
	public int getEventEnd() {
		return eventEnd;
	}


	/**
	 * sets the end time of the event
	 * @param eventEnd 
	 */
	public void setEventEnd(int eventEnd) {
		this.eventEnd = eventEnd;
	}


	/**
	 * returns the description of the event
	 * @return description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * sets the description of the event
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * returns the color of the event
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * sets the color of the event
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * toString method for formatting the event information
	 * @return eventName + \n + eventLocation: String
	 */
	public String toString() {
		String ret = eventName + "\n" + eventLocation;
		return ret;
	}	
}