package com.bookstore.readingisgood.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @NotNull
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    private String phone;

    private String address;

    private Integer dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createTime;

    @CreationTimestamp
    private LocalDateTime updateTime;

}
