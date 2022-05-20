package com.devsuperior.bds02.servicies;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.reporitories.CityRepository;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){

        List<City> cityList = cityRepository.findAll(Sort.by("name"));

        return cityList.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO request) {

        City entity = cityRepository.save(new City(request.getId(), request.getName()));

        return new CityDTO(entity);
    }

    @Transactional
    public void delete(Long request) {
        try{
            cityRepository.deleteById(request);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        } catch (DataIntegrityViolationException ex) {

        }
    }

}
