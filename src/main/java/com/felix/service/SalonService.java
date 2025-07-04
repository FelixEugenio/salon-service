package com.felix.service;

import com.felix.model.Salon;
import com.felix.payload.dto.SalonDTO;
import com.felix.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception;
    void deleteSalon(Long id, UserDTO user);
    Salon getSalonById(Long id);
    List<Salon> getAllSalons();
    Salon getSalonByOwner(Long ownerId);
    List<Salon> searchSalonByCity(String city);
}
