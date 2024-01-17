package com.nick.springbootrest.service;


import com.nick.springbootrest.model.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> findAllStorages();

    Storage findStorageById(Long id);

    Storage saveStorage(Storage storage);

    void deleteStorage(Long id);

    void updateStorage(Storage storage);
}