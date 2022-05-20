package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.servicies.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping()
    public ResponseEntity<List<CityDTO>> findAll() {

        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping()
    public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO request) {

        CityDTO dto = cityService.insert(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();


        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable Long id) {

        cityService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
