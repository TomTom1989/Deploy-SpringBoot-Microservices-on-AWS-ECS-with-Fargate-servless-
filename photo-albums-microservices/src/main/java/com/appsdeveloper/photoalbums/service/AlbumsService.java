package com.appsdeveloper.photoalbums.service;

import java.util.List;

import com.appsdeveloper.photoalbums.ui.model.Album;

public interface AlbumsService {
	List<Album> getAlbums(String userId);
	Album createAlbum(Album album);

}
