package application;

import java.io.Serializable;
import java.util.List;
/**
 * Class to represent an photo album
 * @author Tim Remmert
 *
 */
public class Album implements Serializable{
	private static final long serialVersionUID = -7472286945847741595L; ;
	/**
	 * Name of the album
	 */
	String name;
	/**
	 * List of photos inside of the album
	 */
	List<Photo> photos;
	/**
	 * Initializes a album instance
	 * @param name of the album
	 * @param photos list of photos contained inside the album
	 */
	public Album(String name, List<Photo> photos){
		this.name = name;
		this.photos = photos;
	}
	
	/**
	 * Method to add a photo object to the list of photos in the album
	 * @param photo the photo you want to add to the album
	 */
	public void addPhoto(Photo photo){
		this.photos.add(photo);
	}
	/**
	 * Method to remove a photo object from the list of photos
	 * inside the album
	 * @param photo photo to be removed from the album
	 */
	public void removePhoto(Photo photo){
		this.photos.remove(photo);
	}
	/**
	 * Method to get the name of a given album
	 * @return returns the name of the album
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Method to get the list of photos inside the album
	 * @return returns the list of photos in the album
	 */
	public List<Photo> getPhotos(){
		return this.photos;
	}
	/**
	 * Method to set the name of an album
	 * @param name the name we want the album to have 
	 */
	public void setName(String name){
		this.name = name;
		
	}
	/**
	 * Method to show albums name
	 * @return album name
	 */
	public String toString(){
		return this.name;
	}

}
