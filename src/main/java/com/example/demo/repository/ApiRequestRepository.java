package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ApiRequestEntity;

@Repository
public interface ApiRequestRepository extends JpaRepository<ApiRequestEntity, Long> {

}
