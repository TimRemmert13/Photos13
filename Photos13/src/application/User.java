package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	
	private String username;
	
	private String password;
	
	private List<Album> albums;
	
	private List<Tag> tags;
	
	public User(String username, String password, List<Album> albums, List<Tag> tags){
		this.username = username;
		this.password = password;
		this.albums = albums;
		this.tags = tags;
	}
	
	public List<Album> getAlbums(){
		return this.albums;
	}
	public String getPassword(){
		return this.password;
	}
	public String getUsername(){
		return this.username;
	}
	public void addAlbum(Album album){
		this.albums.add(album);
	}
	public void removeAlbum(Album album){
		this.albums.remove(album);
	}
	public List<Tag> getTags(){
		return this.tags;
	}
	public void addTag(Tag tag){
		tags.add(tag);
	}
	
	public String toString(){
		return this.username;
	}

}