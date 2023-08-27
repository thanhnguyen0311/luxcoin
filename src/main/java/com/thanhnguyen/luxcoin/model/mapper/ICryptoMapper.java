package com.thanhnguyen.luxcoin.model.mapper;

import com.thanhnguyen.luxcoin.model.dto.cryptodto.request.CryptoAddDto;
import com.thanhnguyen.luxcoin.model.dto.cryptodto.response.CryptoListResponse;
import com.thanhnguyen.luxcoin.model.entity.Crypto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ICryptoMapper {
    ICryptoMapper INSTANCE = Mappers.getMapper(ICryptoMapper.class);
    @Mapping(target = "name", source="cryptoAddDto.name")
    @Mapping(target = "title", source="cryptoAddDto.title")
    @Mapping(target = "image", source="cryptoAddDto.image")
    @Mapping(target = "isActive", source="cryptoAddDto.isActive")
    Crypto convert(CryptoAddDto cryptoAddDto);
    @Mapping(target = "name", source="crypto.name")
    @Mapping(target = "title", source="crypto.title")
    @Mapping(target = "image", source="crypto.image")
    @Mapping(target = "isActive", source="crypto.isActive")
    CryptoListResponse entityToDto(Crypto crypto);
    List<CryptoListResponse> entityToDtoList(List<Crypto> cryptoList);
}
