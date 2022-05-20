package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.servicies.CityService;
import com.devsuperior.bds02.servicies.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService evetService;

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@RequestBody EventDTO requestBody, @PathVariable Long id) {

        return ResponseEntity.ok(evetService.update(requestBody, id));
    }


}
