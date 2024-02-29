///*
// * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
// *
// * This software is the confidential and proprietary information of CodeGen
// * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
// * such Confidential Information and shall use it only in accordance with the
// * terms of the license agreement you entered into with CodeGen International.
// *
// */
//package com.example.booking_management_system;
//
//import com.example.booking_management_system.Utility.LoginResponse;
//import com.example.booking_management_system.dto.UserDTO;
//import com.example.booking_management_system.entity.User;
//import com.example.booking_management_system.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
///**
// * <b>Description Title</b>
// * Description Text.
// *
// * @author kishen
// * @since 17 Aug 2023
// */
//@Service
//public class LoginService
//{
//    @Autowired
//    UserRepository userRepository;
//    public LoginResponse loginUser( UserDTO userDTO )
//    {
//        String msg ="";
//
//        User user = userRepository.findByUserName( userDTO.getUserName() );
//
//        if(user != null)
//        {
//            String password = userDTO.getPassword();
//            Boolean isPwdRight = password.matches( user.getPassword() );
//
//            if(isPwdRight)
//            {
//                Optional<User> user1 = userRepository.findOneByUserNameAndPassword( userDTO.getUserName(),password );
//                if(user1.isPresent())
//                {
//                    return new LoginResponse( "Login Success", true );
//                }
//                else
//                {
//                    return new LoginResponse( "Login Failed", false );
//                }
//
//            }else
//            {
//                return new LoginResponse( "Password does not match", false );
//            }
//        }else
//        {
//            return new LoginResponse( "User does not exist", false );
//        }
//
//
//
//    }
//}
