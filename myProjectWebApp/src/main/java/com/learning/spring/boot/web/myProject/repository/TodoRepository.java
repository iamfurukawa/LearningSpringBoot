package com.learning.spring.boot.web.myProject.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.spring.boot.web.myProject.model.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer>{
	List<Todo> findByUser(String user);
}
