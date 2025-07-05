package com.felix.service.impl;

import com.felix.model.Salon;
import com.felix.payload.dto.SalonDTO;
import com.felix.payload.dto.UserDTO;
import com.felix.repository.SalonRepository;
import com.felix.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImplementation implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setImages(req.getImages());
        salon.setAddress(req.getAddress());
        salon.setPhoneNumber(req.getPhoneNumber());
        salon.setCity(req.getCity());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId).orElse(null);

        if(!salon.getOwnerId().equals(user.getId())){
            throw new Exception("You don't have permission to update this salon");
        }

        if(existingSalon != null){
            existingSalon.setName(salon.getName());
            existingSalon.setImages(salon.getImages());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setCity(salon.getCity());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());

            return salonRepository.save(existingSalon);
        }
        throw new Exception("salon not exist");
    }

    @Override
    public void deleteSalon(Long id, UserDTO user) {

    }

    @Override
    public Salon getSalonById(Long id) {
        Salon salon = salonRepository.findById(id).orElse(null);
        if(salon == null){
            throw new RuntimeException("salon not exist");
        }
      return salon;
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonByOwner(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
      return  salonRepository.searchSalons(city);
    }
}
