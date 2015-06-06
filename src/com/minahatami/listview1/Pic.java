package com.minahatami.listview1;

public class Pic {
	private String path, name;
	private int image;
	
	public Pic(int image, String path, String name){
		this.image = image;
		this.path = path;
		this.name = name;
	}

	public int getImage() {
		return image;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

}
