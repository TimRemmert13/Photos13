package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
/**
 * Class to represent a user of the photos application
 * @author Tim Remmert
 *
 */
public class User implements Serializable{
	/**
	 * The users user name
	 */
	private String username;
	/**
	 * the users password
	 */
	private String password;
	/**
	 * the list of albums the user created
	 * including the preloaded stock album
	 */
	private List<Album> albums;
	/**
	 * the list of tags the user created 
	 */
	private List<Tag> tags;
	/**
	 * Initializes a user object with the given parameters
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param albums the list of albums the user has
	 * @param tags the list of tags the user has
	 */
	public User(String username, String password, List<Album> albums, List<Tag> tags){
		this.username = username;
		this.password = password;
		this.albums = albums;
		this.tags = tags;
	}
	/**
	 * Method to get the list of albums the user has
	 * @return returns the list of albums a user has
	 */
	public List<Album> getAlbums(){
		return this.albums;
	}
	/**
	 * Method to get the password of the user
	 * @return returns the string password of the user
	 */
	public String getPassword(){
		return this.password;
	}
	/**
	 * Method to get the username of the user 
	 * @return returns the string password of the user
	 */
	public String getUsername(){
		return this.username;
	}
	/**
	 * Method to add an album to the list of albums of the user
	 * @param album the album we want to add to the users list 
	 * of albums
	 */
	public void addAlbum(Album album){
		this.albums.add(album);
	}
	/**
	 * Method to remove an album from the users list of albums
	 * @param album the album we want to remove from the
	 * users list of albums
	 */
	public void removeAlbum(Album album){
		this.albums.remove(album);
	}
	/**
	 * Method the get the list of tags of the user
	 * @return returns the list of tags of the user
	 */
	public List<Tag> getTags(){
		return this.tags;
	}
	/**
	 * Method to add a tag to the list of tags the user has
	 * @param tag the tag we want to add to the users list of tags
	 */
	public void addTag(Tag tag){
		tags.add(tag);
	}
	/**
	 * Method to remove a tag from the user's list of tag 
	 * 
	 */
	public void deleteTag(Tag tag){
		tags.remove(tag);
	}
	/**
	 * Method to show the user's username 
	 * @return returns the string user's username
	 */
	public String toString(){
		return this.username;
	}

}