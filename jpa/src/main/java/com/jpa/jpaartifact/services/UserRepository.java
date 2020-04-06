package com.jpa.jpaartifact.services;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jpa.jpaartifact.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}
