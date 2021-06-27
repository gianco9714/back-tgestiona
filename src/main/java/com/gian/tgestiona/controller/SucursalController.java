package com.gian.tgestiona.controller;

import com.gian.tgestiona.exception.NotFoundException;
import com.gian.tgestiona.model.SucursalEntity;
import com.gian.tgestiona.repository.SucursalRepository;
import com.gian.tgestiona.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value = "SucursalController", produces = "application/json", tags = { "Sucursal Controller" })
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/subsidiarys")
    public List<SucursalEntity> getAllSubsidiarys(){
        return sucursalRepository.findAll();
    }

    @GetMapping("/subsidiarys/{id}")
    public ResponseEntity<SucursalEntity> getSubsidiaryByID(@PathVariable(value = "id") String subsidiaryId) throws NotFoundException {
        SucursalEntity subsidiary = sucursalRepository.findById(subsidiaryId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_SUBSIDIARY_ID_MSG + subsidiaryId));
        return ResponseEntity.ok().body(subsidiary);
    }

    @PostMapping("/subsidiarys")
    public SucursalEntity createSubsidiary(@RequestBody SucursalEntity sucursalEntity){
        return sucursalRepository.save(sucursalEntity);
    }

    @PutMapping("/subsidiarys/{id}")
    public ResponseEntity<SucursalEntity> updateSubsidiary(@PathVariable (value = "id") String subsidiaryId,
                                                    @RequestBody SucursalEntity sucursalDetails) throws NotFoundException{
        SucursalEntity subsidiary = sucursalRepository.findById(subsidiaryId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_SUBSIDIARY_ID_MSG + subsidiaryId));

        subsidiary.setNombre(sucursalDetails.getNombre());
        final SucursalEntity updateSubsidiary = sucursalRepository.save(subsidiary);
        return ResponseEntity.ok(updateSubsidiary);
    }

    @DeleteMapping("/subsidiarys/{id}")
    public Map<String, Boolean> deleteSubsidiarys(@PathVariable(value = "id") String subsidiaryId) throws NotFoundException{
        SucursalEntity subsidiary = sucursalRepository.findById(subsidiaryId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_SUBSIDIARY_ID_MSG + subsidiaryId));

        sucursalRepository.delete(subsidiary);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Sucursual Borrada", Boolean.TRUE);
        return response;
    }

}
