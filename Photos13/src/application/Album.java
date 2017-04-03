package application;

import java.util.List;

public class Album {
	
	String name;
	
	List<Photo> photos;
	
	public Album(String name, List<Photo> photos){
		this.name = name;
		this.photos = photos;
	}
	
	public void addPhoto(Photo photo){
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo){
		this.photos.remove(photo);
	}
	
	public String toString(){
		return this.name;
	}

}
