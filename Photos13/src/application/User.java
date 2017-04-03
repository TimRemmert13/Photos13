package application;

import java.util.List;

public class User {
	
	private String username;
	
	private String password;
	
	private List<Album> albums;
	
	public User(String username, String password, List<Album> albums){
		this.username = username;
		this.password = password;
		this.albums = albums;
	}
	public User(String username, String password){
		this.username = username;
		this.password = password;
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
	public String toString(){
		return this.username;
	}

}
