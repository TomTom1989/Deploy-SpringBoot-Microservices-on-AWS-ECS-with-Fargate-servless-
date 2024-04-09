package com.appsdeveloper.photoalbums.ui.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloper.photoalbums.service.AlbumsService;
import com.appsdeveloper.photoalbums.ui.model.Album;

import jakarta.validation.Valid;
 

@RestController
@RequestMapping("/albums")
public class AlbumsController {
	
	@Autowired
	AlbumsService albumsService;
	
	@GetMapping
    @PostAuthorize("(returnObject.size() > 0) ? principal == returnObject[0].userId : true")
	public List<Album> getAlbums(Principal principal) {
		return albumsService.getAlbums(principal.getName());
	}

	@PostMapping
	public Album createAlbum(@Valid @RequestBody Album album, Principal principal) {
		album.setUserId(principal.getName());
		return albumsService.createAlbum(album);
	}
 
}
