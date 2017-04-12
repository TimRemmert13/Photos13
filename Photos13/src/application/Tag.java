package application;

import java.io.Serializable;
import java.util.List;
/**
 * Class to represent a tag for a photo
 * @author Tim Remmert
 *
 */
public class Tag implements Serializable{
	/**
	 * The name of the tag.
	 * For example, location or person
	 */
	private String name;
	/**
	 * The value of the tag name.
	 * For example, New York for location
	 * or Tim for person
	 */
	private String value;
	/**
	 * list of photos associated with the tag
	 */
	List<Photo> photos;
	/**
	 * Initializes the tag with the given parameters
	 * @param name the name of the tag
	 * @param value the value of the tag
	 * @param photos the list of photos associated with the tag
	 */
	public Tag(String name,String value, List<Photo> photos){
		this.name = name;
		this.value = value;
		this.photos = photos;
	}
	/**
	 * Method to add a photo to the list of photos
	 * associated with the tag
	 * @param photo the photo we want to link with the tag
	 */
	public void addPhoto(Photo photo){
		this.photos.add(photo);
	}
	/**
	 * Method to remove a photo from the list of photos
	 * associated with the tag
	 * @param photo the photo we want to remove from the list
	 */
	public void removePhoto(Photo photo){
		photos.remove(photo);
	}
	/**
	 * Method to return the name of the tag
	 * @return returns the string name of the tag
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Method to return the value of the tag
	 * @return return the string value of the tag
	 */
	public String getValue(){
		return this.value;
	}
	/**
	 * Method to show both the name and the value of the tag
	 * @return the name and value in the format, name: value
	 */
	public String toString(){
		return this.name + ": "+ this.value;
	}

}
