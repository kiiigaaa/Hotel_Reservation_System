/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.Exeptions;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author kishen
 * @since 07 Aug 2023
 */
public class ContractExpiredException extends RuntimeException
{
    public ContractExpiredException(String message) {
        super(message);
    }
}
