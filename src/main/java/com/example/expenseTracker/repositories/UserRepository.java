package com.example.expenseTracker.repositories;

import com.example.expenseTracker.domain.User;
import com.example.expenseTracker.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password)throws EtAuthException;

    User findByEmailAndPassword(String email, String password)throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
