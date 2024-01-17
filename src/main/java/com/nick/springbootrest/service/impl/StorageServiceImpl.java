package com.nick.springbootrest.service.impl;

import com.nick.springbootrest.model.Storage;
import com.nick.springbootrest.repository.StorageRepository;
import com.nick.springbootrest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository repository;

    @Autowired
    public StorageServiceImpl(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Storage> findAllStorages() {
        return repository.findAll();
    }

    @Override
    public Storage findStorageById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExpressionException("Storage not found with id [" + id + "]"));
    }

    @Override
    @Transactional
    public Storage saveStorage(Storage storage) {
        return repository.save(storage);
    }

    @Override
    @Transactional
    public void deleteStorage(Long id) {
        Storage storage = repository.findById(id)
                .orElseThrow(() -> new ExpressionException("Storage not found with id [" + id + "]"));
        repository.delete(storage);
    }

    @Override
    public void updateStorage(Storage storage) {
        repository.save(storage);
    }
}