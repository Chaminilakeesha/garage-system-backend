package com.example.garagesystembackend.services;

import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       VehicleOwner vehicleOwner = vehicleOwnerRepository.findByEmail(email);

       if(vehicleOwner ==null ){
           throw new UsernameNotFoundException("User not found",null);
       }
       return new User(vehicleOwner.getEmail(),vehicleOwner.getPassword(),new ArrayList<>());
    }

}
