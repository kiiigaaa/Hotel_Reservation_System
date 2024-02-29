/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.example.booking_management_system.Utility;

/**
 * <b>Utility class for checking the contract expiration</b>
 * This utility class provides a static method for checking whether a hotel's contract has expired.
 * It takes a Hotel object as input and determines if the contract's end date has passed.
 * Utilizes the Hotel entity and LocalDate for comparisons.
 * @author kishen
 * @since 11 Aug 2023
 */
import com.example.booking_management_system.entity.Hotel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContractUtility
{

    /**
     * Checks if a hotel's contract has expired.
     *
     * @param /hotel The Hotel object for which to check the contract expiration.
     * @return Returns true if the hotel's contract has expired, false otherwise.
     */
    public static boolean hasExpired( Hotel hotel)
    {
        LocalDate currentDate = LocalDate.now();// Get the current date
        return currentDate.isAfter(hotel.getContractEndDate());// Compare with the contract end date
    }
}
