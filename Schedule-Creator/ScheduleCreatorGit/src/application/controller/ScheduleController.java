/**
 * Schedule Creator Application
 * 
 * Schedule Controller class of the application
 * Contains multiple JavaFX IDs for the Schedule view, primarily buttons for the individual Events
 * Methods include an initialize and a handle method along with custom methods for changing views and setting events
 * @author Hot Dog or Not Hot Dog
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.model.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class ScheduleController implements EventHandler<ActionEvent>, Initializable{
	
	@FXML
	public Button clearButton, home, newE;
	@FXML
	public Button mon6AM, mon7AM, mon8AM, mon9AM, mon10AM, mon11AM, mon12PM, mon1PM, mon2PM, mon3PM, mon4PM, mon5PM;
	@FXML
	private Button tues6AM, tues7AM, tues8AM, tues9AM, tues10AM, tues11AM, tues12PM, tues1PM, tues2PM, tues3PM, tues4PM, tues5PM;
	@FXML
	private Button wed6AM, wed7AM, wed8AM, wed9AM, wed10AM, wed11AM, wed12PM, wed1PM, wed2PM, wed3PM, wed4PM, wed5PM;
	@FXML
	private Button thur6AM, thur7AM, thur8AM, thur9AM, thur10AM, thur11AM, thur12PM, thur1PM, thur2PM, thur3PM, thur4PM, thur5PM;
	@FXML
	private Button fri6AM, fri7AM, fri8AM, fri9AM, fri10AM, fri11AM, fri12PM, fri1PM, fri2PM, fri3PM, fri4PM, fri5PM;
	@FXML
	private Button sat6AM, sat7AM, sat8AM, sat9AM, sat10AM, sat11AM, sat12PM, sat1PM, sat2PM, sat3PM, sat4PM, sat5PM;
	@FXML
	private Button sun6AM, sun7AM, sun8AM, sun9AM, sun10AM, sun11AM, sun12PM, sun1PM, sun2PM, sun3PM, sun4PM, sun5PM;
	
	public static Event passToViewEvent;

	/**
	 * Method that sets the event based on the button that is clicked
	 * @param buttonClickedName
	 */
	private void setPassToViewEvent( String buttonClickedName ) {
		
		for( Event e : MainController.weeklySchedule.getEvents() ) {
			if( e.getEventName().equals( buttonClickedName ) ) {
				passToViewEvent = e;
			}
		}
	}
	
	/**
	 * Handle method for changing the view to an individual event 
	 * Called when an event is selected from the schedule
	 * @param event
	 */
	@Override
	public void handle( ActionEvent event ) {
		
		String[] b = ( (Button) event.getSource() ).getText().split( "\n",2 );
		setPassToViewEvent( b[0] );
		
		try { 
			Parent root = FXMLLoader.load( getClass().getResource( "../view/displayEvent.fxml" ) );
			Main.stage.setScene( new Scene( root, 800, 800 ) );
			Main.stage.show();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for returning to the main view of the application
	 * Called whenever home button is pressed 
	 * @param event
	 */
	public void home( ActionEvent event ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource( "../view/Main.fxml" ));
			Main.stage.setScene( new Scene( root, 800, 800 ) );
			Main.stage.show();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for creating a new event and switching to the Event view of the application
	 * Called when the newE button is clicked
	 * @param event
	 */
	public void newEvent( ActionEvent event ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource( "../view/Event.fxml" ) );
			Main.stage.setScene( new Scene( root, 800, 800 ) );
			Main.stage.show();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the Schedule view setting the content of the Event Buttons
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		
		// Initialize all buttons based on the info from the events in data.csv
		// Iterate over Schedule object and .setText(event.toString()) each event object
		// Also, set background color of each button based on 
		
		Button[] monday = { mon6AM, mon7AM, mon8AM, mon9AM, mon10AM, mon11AM, mon12PM, mon1PM, mon2PM, mon3PM, mon4PM, mon5PM };
		Button[] tuesday = { tues6AM, tues7AM, tues8AM, tues9AM, tues10AM, tues11AM, tues12PM, tues1PM, tues2PM, tues3PM, tues4PM, tues5PM };
		Button[] wednesday = { wed6AM, wed7AM, wed8AM, wed9AM, wed10AM, wed11AM, wed12PM, wed1PM, wed2PM, wed3PM, wed4PM, wed5PM };
		Button[] thursday = { thur6AM, thur7AM, thur8AM, thur9AM, thur10AM, thur11AM, thur12PM, thur1PM, thur2PM, thur3PM, thur4PM, thur5PM };
		Button[] friday = { fri6AM, fri7AM, fri8AM, fri9AM, fri10AM, fri11AM, fri12PM, fri1PM, fri2PM, fri3PM, fri4PM, fri5PM };
		Button[] saturday = { sat6AM, sat7AM, sat8AM, sat9AM, sat10AM, sat11AM, sat12PM, sat1PM, sat2PM, sat3PM, sat4PM, sat5PM };
		Button[] sunday = { sun6AM, sun7AM, sun8AM, sun9AM, sun10AM, sun11AM, sun12PM, sun1PM, sun2PM, sun3PM, sun4PM, sun5PM };
		
		for( Event e : MainController.weeklySchedule.events ) {
			int i = 1;
			for( char c : e.freq.toCharArray() ) {
				if( c == '1' ) {
					switch( i ) {
					case 1:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							monday[ n ].setText(e.toString() );
							monday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							monday[ n ].setDisable( false );
						}
						break;
					case 2:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							tuesday[ n ].setText(e.toString() );
							tuesday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							tuesday[ n ].setDisable( false );
						}
						break;
					case 3:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							wednesday[ n ].setText( e.toString() );
							wednesday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							wednesday[ n ].setDisable( false );
						}
						break;
					case 4: 
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							thursday[ n ].setText( e.toString() );
							thursday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							thursday[ n ].setDisable( false );
						}
						break;
					case 5:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							friday[ n ].setText( e.toString() );
							friday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							friday[ n ].setDisable(false);
						}
						break;
					case 6:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							saturday[ n ].setText( e.toString() );
							saturday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							saturday[ n ].setDisable( false );
						}
						break;
					case 7:
						for( int n = ( e.getEventStart() - 6 ); n < ( e.getEventEnd() - 6 ); n++ ) {
							sunday[ n ].setText( e.toString() );
							sunday[ n ].setStyle( "-fx-background-color: " + e.getColor().toString().replace( "0x", "#" ) );
							sunday[ n ].setDisable( false );
						}
						break;
					}
				}
				i++;
			}
		}	
	}
	
	/**
	 * Clears the current events and refreshes the view
	 * @param event
	 */
	public void clearSchedule(ActionEvent event) {
		//removes the events from the schedule until it's empty
		while(MainController.weeklySchedule.getEvents().size() != 0) {
			MainController.weeklySchedule.clearSchedule();
		}
		//refreshes the view
		try {
			Parent root = FXMLLoader.load( getClass().getResource( "../view/Schedule.fxml" ));
			Main.stage.setScene( new Scene( root, 800, 800 ) );
			Main.stage.show();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		//saves the changes
		MainController.weeklySchedule.save();
	}
}
