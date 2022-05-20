package com.devsuperior.bds02.servicies;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.reporitories.CityRepository;
import com.devsuperior.bds02.reporitories.EventRepository;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(EventDTO request, Long id) {

        try {
            Event event = eventRepository.getOne(id);
            City city = cityRepository.getOne(request.getCityId());

            copyDtoToEntity(request, event, city);


            return new EventDTO(eventRepository.save(event));
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }


    private void copyDtoToEntity(EventDTO dto, Event entity, City city) {
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(city);
    }
}
