/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloper.photoapp.api.users.shared;

import java.util.List;

import com.appsdeveloper.photoapp.api.users.ui.model.AlbumResponseModel;


public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;

   
    public String getUserId() {
        return userId;
    }

 
    public void setUserId(String userId) {
        this.userId = userId;
    }

   
    public String getFirstName() {
        return firstName;
    }

  
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

 
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

   
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }

  
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

   
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

   
    public List<AlbumResponseModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResponseModel> albums) {
        this.albums = albums;
    }
 
    
}
