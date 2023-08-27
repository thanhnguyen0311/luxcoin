package com.thanhnguyen.luxcoin.service;

import com.thanhnguyen.luxcoin.model.entity.Crypto;

import java.util.List;

public interface ICryptoService {
    List<Crypto> findAll();

    Crypto findById(Long id);

    void save(Crypto t);

    void remove(Long id);
    boolean existsCrypto(String name);
}
