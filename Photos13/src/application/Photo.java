package application;

import java.util.List;


import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
/**
 * Class to represent a photo in a photo album
 * @author Tim Remmert
 *
 */
public class Photo implements Serializable{
 /**
  * the file the image in contained in
  */
	private File file;
	/**
	 * the path to the file in the project
	 */
	private String path;
    /**
     * list of tags the photo has
     */
	private List<Tag> tags;
	/**
	 * a calendar instance to store the date an time of the photo
	 */
	private Calendar calendar;
	/**
	 * a caption you can give the photo
	 */
	private String caption;
 /**
  * Initializes a photo object to the parameters.
  * Note it sets the calendar instance milliseconds to zero
  * @param file the file of the image 
  * @param tags list of tags associated with the photo
  * @param calendar calendar instance to of the date and time of the photo
  */
 public Photo(File file, List<Tag> tags, Calendar calendar){
	 this.file = file;
	 this.path = file.getPath();
	 this.tags = tags;
	 this.calendar = calendar;
	 calendar.set(Calendar.MILLISECOND, 0);
 }
 
 /**
  * Method to get the image file of the photo
  * @return returns the file object of the image
  */
 public File getFile(){
	 return this.file;
 }
 /**
  * Method to get the path of the file in the project or on 
  * the users computer
  * @return returns the path to the image file
  */
 public String getPath(){
	 return this.path;
 }
 /**
  * Method to return the list of tags in associated with the photo
  * @return returns the list of tags for the photo
  */
 public List<Tag> getTags(){
	 return this.tags;
 }
 /**
  * Method to return the date of the calendar instance of the photo
  * @return return the date object for the photos calendar instance
  */
 public Date getDate(){
	 return this.calendar.getTime();
 }
 /**
  * Method to add a tag to the photos list of tags 
  * @param tag the tag we want to add to the photos list of tags
  */
 public void addTag(Tag tag){
	 this.tags.add(tag);
 }
 /**
  * Method to remove a tag from the photos list of tags 
  * @param tag the tag we want to remove from the photos list of tags
  */
 public void removeTag(Tag tag){
	 this.tags.remove(tag);
 }
/**
 * Method to get the URL of the image file for the photo
 * @return returns the URL of the image file
 * @throws MalformedURLException 
 */
public URL getURL() throws MalformedURLException{
	 return this.file.toURL();
 }
 /**
  * Method to set the caption of a photo to a string
  * @param caption the string we want to set as the photos
  * caption
  */
 public void setCaption(String caption){
	 this.caption = caption;
 }
 /**
  * Method to get the caption of a photo
  * @return returns the caption for the photo
  */
 public String getCaption(){
	 return this.caption;
 }
 
}
