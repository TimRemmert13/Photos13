package application;

import java.util.List;

public class Tag {
	
	private String tag;
	
	List<Photo> photos;
	
	public Tag(String tag, List<Photo> photos){
		this.tag = tag;
		this.photos = photos;
	}
	
	public void addPhoto(Photo photo){
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo){
		photos.remove(photo);
	}
	
	public String toString(){
		return this.tag;
	}

}
