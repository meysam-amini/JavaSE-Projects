package Calendar;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.TimerTask;

import javax.management.Notification;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

class DisplayTime extends TimerTask {
	 
	  public void run() {
        
	    try {Time t=new Time(System.currentTimeMillis());
	    NoteMenu.timer.setText(t.toString());
	    DaysPrayTime.timer.setText(t.toString());
	    Allreminders.Time.setText(t.toString());
	    ReminderDetail.timer.setText(t.toString());
	    NoteDetail.timer.setText(t.toString());
	    Allnotes.Time.setText(t.toString());
	    TimeZon.Time.setText(t.toString());
	    ImportCity.Time.setText(t.toString());
	    Top.timer.setText(t.toString());
	    Notifications.timer.setText(t.toString());
	    new Alarm();
	 
	 
	    }
      
        catch (Exception e) {
        	
        }

	  }
	}