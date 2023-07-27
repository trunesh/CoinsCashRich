package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "api_request")
@Getter
@Setter
@NoArgsConstructor
public class ApiRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "request_response", columnDefinition = "TEXT")
    private String requestResponse;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors, getters, setters, and other methods (if needed)

}
