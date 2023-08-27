package com.thanhnguyen.luxcoin.controller.rest;

import com.thanhnguyen.luxcoin.model.dto.cryptodto.request.CryptoAddDto;
import com.thanhnguyen.luxcoin.model.dto.cryptodto.response.CryptoListResponse;
import com.thanhnguyen.luxcoin.model.mapper.ICryptoMapper;
import com.thanhnguyen.luxcoin.service.ICryptoAPIService;
import com.thanhnguyen.luxcoin.service.ICryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
public class CryptoRestController {
    @Autowired
    private ICryptoAPIService cryptoAPIService;
    @Autowired
    private ICryptoService cryptoService;

//    @PostMapping("/add")
//    public ResponseEntity<String> addCrypto(@RequestBody CryptoAddDto cryptoAddDto ) {
//        if(!cryptoAPIService.checkCryptoAvailable(cryptoAddDto.getName())){
//            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/add")
        public ResponseEntity<?> addCrypto(@RequestParam("name") String name, @RequestParam("title") String title,@RequestParam("image") MultipartFile image) {
        if(!cryptoAPIService.checkCryptoAvailable(name) || name.length() < 3){
            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
        }
        if(!cryptoService.existsCrypto(name)){
            return new ResponseEntity<>("body",HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            byte[] data = image.getBytes();
            CryptoAddDto cryptoAddDto = new CryptoAddDto(name,title,data);
            cryptoService.save(ICryptoMapper.INSTANCE.convert(cryptoAddDto));
            return ResponseEntity.ok(cryptoAddDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/check-crypto/{name}")
    public ResponseEntity<String> checkCrypto(@PathVariable String name){
        if(!cryptoService.existsCrypto(name) || !cryptoAPIService.checkCryptoAvailable(name)) {
            return new ResponseEntity<>("body", HttpStatus.NOT_ACCEPTABLE);
        }
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCrypto(){
        List<CryptoListResponse> cryptos = ICryptoMapper.INSTANCE.entityToDtoList(cryptoService.findAll());
        return ResponseEntity.ok(cryptos);
    }
}
