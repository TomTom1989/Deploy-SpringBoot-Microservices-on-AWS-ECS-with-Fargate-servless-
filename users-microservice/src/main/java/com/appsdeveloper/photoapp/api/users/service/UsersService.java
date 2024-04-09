package com.appsdeveloper.photoapp.api.users.service;

import com.appsdeveloper.photoapp.api.users.shared.UserDto;
import com.appsdeveloper.photoapp.api.users.ui.model.AlbumResponseModel;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService  extends UserDetailsService {
    public List<UserDto> getUsers();
    public UserDto createUser(UserDto userDto);
    public UserDto getUserByEmail(String email);
    public UserDto getUserByUserId(String userId);
    public void deleteUser(String userId, String authorizationHeader);
    public List<AlbumResponseModel> getUserAlbums(String jwt);
}
