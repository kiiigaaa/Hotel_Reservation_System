///*
// * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
// *
// * This software is the confidential and proprietary information of CodeGen
// * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
// * such Confidential Information and shall use it only in accordance with the
// * terms of the license agreement you entered into with CodeGen International.
// *
// */
//package com.example.booking_management_system.controller;
//
//import com.example.booking_management_system.LoginService;
//import com.example.booking_management_system.Utility.LoginResponse;
//import com.example.booking_management_system.dto.UserDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <b>Description Title</b>
// * Description Text.
// *
// * @author kishen
// * @since 17 Aug 2023
// */
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api/user")
//public class LoginController
//{
//    @Autowired
//    LoginService loginService;
//    @PostMapping(path = "/login")
//    public ResponseEntity<?> login( @RequestBody UserDTO userDTO)
//    {
//        LoginResponse loginResponse = loginService.loginUser(userDTO);
//        return ResponseEntity.ok(loginResponse);
//    }
//
//    @PostMapping(path = "/save")
//    public ResponseEntity<?> save( @RequestBody UserDTO userDTO)
//    {
//        String
//    }
//}
