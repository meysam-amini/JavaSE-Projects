package Calendar;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

public class Alarm {

	public static Clip clip;
	private AudioInputStream audioIn;
	int alarmFlag = 0;

	public Alarm() throws UnsupportedAudioFileException, Exception {

		chekTimes();

	}

	public void stopalarm() {
		alarmFlag = 1;
	}

	public void playAzan() throws UnsupportedAudioFileException, Exception {
		URL url = getClass().getClassLoader().getResource("azan.mp3");
		audioIn = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}
	
	public void playReminder() throws UnsupportedAudioFileException, Exception{
		
		URL url = getClass().getClassLoader().getResource("09 Setareh.wav");
		audioIn = AudioSystem.getAudioInputStream(url);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
		
	}
	public void chekTimes() throws UnsupportedAudioFileException, Exception{
		 for(int i = 1; i<= Allreminders.cR; i++)
		  {
			  if(NoteMenu.DateCompare(Allreminders.Rd[i], Allreminders.Rt[i])==0)
			  {
				 // this.playReminder();
				  Allreminders.NoteName= Allreminders.Rnd[i];
				  Allreminders.SavedDate= Allreminders.Rd[i];
				  Allreminders.SavedTime= Allreminders.Rt[i];
				Allreminders.playreminder(1);
				main.ReminderDetailMenu();
				this.playReminder();
				
			  }
		  }
		 Time t=new Time(System.currentTimeMillis());
		 PrayTime p=new PrayTime();
			for(int i=0;i<6;i++)
			if((p.getOghat(t)[i]+":00").compareTo(t.toString())==0)
				this.playAzan();
			
			
	}

}
