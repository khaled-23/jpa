package com.example.capstone1.Repository;


import com.example.capstone1.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
