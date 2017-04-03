package application;

import java.util.List;

import javafx.scene.image.Image;

import java.util.Calendar;
import java.util.Date;

public class Photo {
 
	private Image image;
 
	private List<Tag> tags;
	
	private Calendar calendar;
 
 public Photo(Image image, List<Tag> tags, Calendar calendar){
	 this.image = image;
	 this.tags = tags;
	 this.calendar = calendar;
	 calendar.set(Calendar.MILLISECOND, 0);
 }
 
 public Image getImage(){
	 return this.image;
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
 
}
