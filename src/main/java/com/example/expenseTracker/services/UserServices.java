package com.example.expenseTracker.services;

import com.example.expenseTracker.domain.User;
import com.example.expenseTracker.exceptions.EtAuthException;


public interface UserServices {

    User validateUser(String email, String password)throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password)throws EtAuthException;
}
