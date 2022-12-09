package com.sportify.ReservationMicroService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportify.ReservationMicroService.entity.Reservation;
import com.sportify.ReservationMicroService.repository.ReservationRepository;



@Service
@Transactional
public class ReservationService {
	@Autowired
    private ReservationRepository orderRepository;


    public Reservation saveReservation(Reservation order) {
        return orderRepository.save(order);
    }
    
    public List<Reservation> findAll() {
        return orderRepository.findAll();
    }

}
