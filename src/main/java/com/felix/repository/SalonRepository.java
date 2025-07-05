package com.felix.repository;

import com.felix.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    Salon findByOwnerId(Long ownerId);

    @Query("select s from Salon s where " +
            "(lower(s.city) like lower(concat('%', :city, '%')) or " +
            " lower(s.name) like lower(concat('%', :city, '%')) or " +
            " lower(s.address) like lower(concat('%', :city, '%'))) ")
    List<Salon> searchSalons(@Param("city") String city);

}
