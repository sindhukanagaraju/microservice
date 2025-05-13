package com.microservice.showroom.repository;

import com.microservice.showroom.entity.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowroomRepository extends JpaRepository<Showroom,Integer> {
}
