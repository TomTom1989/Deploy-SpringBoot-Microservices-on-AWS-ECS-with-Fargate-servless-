package com.appsdeveloper.photoapp.api.users.ui.model;
 

public class AlbumResponseModel {
    private String albumId;
    private String userId; 
    private String title;

  
    public String getAlbumId() {
        return albumId;
    }

   
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

   
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

    
}
