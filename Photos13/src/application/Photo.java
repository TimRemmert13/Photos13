package application;

import java.util.List;

import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class Photo implements Serializable{
 
	private File file;
	
	private String path;
 
	private List<Tag> tags;
	
	private Calendar calendar;
	
	private String caption;
 
 public Photo(File file, List<Tag> tags, Calendar calendar){
	 this.file = file;
	 this.path = file.getPath();
	 this.tags = tags;
	 this.calendar = calendar;
	 calendar.set(Calendar.MILLISECOND, 0);
 }
 
 
 public File getFile(){
	 return this.file;
 }
 public String getPath(){
	 return this.path;
 }
 
 public List<Tag> getTags(){
	 return this.tags;
 }
 
 public Date getDate(){
	 return calendar.getTime();
 }
 
 public void addTag(Tag tag){
	 this.tags.add(tag);
 }
 
 public void removeTag(Tag tag){
	 this.tags.remove(tag);
 }

public URL getURL() throws MalformedURLException{
	 return this.file.toURL();
 }
 
 public void setCaption(String caption){
	 this.caption = caption;
 }
 public String getCaption(){
	 return this.caption;
 }
 
}
