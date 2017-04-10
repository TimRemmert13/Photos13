package application;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable{
	
	String name;
	
	List<Photo> photos;
	
	public Album(String name, List<Photo> photos){
		this.name = name;
		this.photos = photos;
	}
	public Album(String name){
		this.name = name;
	}
	
	public void addPhoto(Photo photo){
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo){
		this.photos.remove(photo);
	}
	public String getName(){
		return this.name;
	}
	public List<Photo> getPhotos(){
		return this.photos;
	}
	
	public String toString(){
		return this.name;
	}

}
