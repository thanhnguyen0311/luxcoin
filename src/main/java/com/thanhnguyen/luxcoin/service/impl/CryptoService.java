package com.thanhnguyen.luxcoin.service.impl;

import com.thanhnguyen.luxcoin.model.dto.cryptodto.response.CryptoListResponse;
import com.thanhnguyen.luxcoin.model.entity.Crypto;
import com.thanhnguyen.luxcoin.repository.ICryptoRepository;
import com.thanhnguyen.luxcoin.service.ICryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoService implements ICryptoService {

    @Autowired
    private ICryptoRepository cryptoRepository;
    @Override
    public List<Crypto> findAll() {
        return cryptoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Crypto findById(Long id) {
        return null;
    }

    @Override
    public void save(Crypto t) {
     this.cryptoRepository.save(t);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public boolean existsCrypto(String name) {
        Optional<Crypto> c = this.cryptoRepository.findByName(name);
        return c.isEmpty();
    }
}
