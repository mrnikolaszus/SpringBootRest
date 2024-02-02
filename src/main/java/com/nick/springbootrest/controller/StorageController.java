package com.nick.springbootrest.controller;

import com.nick.springbootrest.dto.StorageDTO;
import com.nick.springbootrest.exception.ValidationApiException;
import com.nick.springbootrest.mapper.StorageMapper;
import com.nick.springbootrest.model.Storage;
import com.nick.springbootrest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/storage")
public class StorageController {
    private final StorageService storageService;
    private final StorageMapper mapper;

    @Autowired
    public StorageController(StorageService storageService, StorageMapper mapper) {
        this.storageService = storageService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Storage> StorageList() {
        return storageService.findAllStorages();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Storage storage(@PathVariable Long id) {
        return storageService.findStorageById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Storage createStorage(@RequestBody @Validated StorageDTO storageDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationApiException("Invalid data: " + bindingResult.getAllErrors());
        }
        return storageService.saveStorage(mapper.toEntity(storageDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStorage(@PathVariable Long id) {
        storageService.deleteStorage(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStorage(@PathVariable Long id,
                              @RequestBody @Validated StorageDTO storageDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationApiException("Invalid data: " + bindingResult.getAllErrors());
        }
        Storage storage = storageService.findStorageById(id);

        if (!storageDTO.getId().equals(storage.getId())) {
            throw new ValidationApiException("ID doesn't match");
        }

        storageService.updateStorage(mapper.toEntity(storageDTO));
    }
}