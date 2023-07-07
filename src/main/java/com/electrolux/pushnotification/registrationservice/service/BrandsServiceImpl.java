package com.electrolux.pushnotification.registrationservice.service;
/*
import com.electrolux.pushnotification.registrationservice.dto.BrandsDTO;
import com.electrolux.pushnotification.registrationservice.mapper.BrandsMapper;
import com.electrolux.pushnotification.registrationservice.repository.BrandsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandsServiceImpl implements BrandsService {

    private final Logger logger = LoggerFactory.getLogger(BrandsServiceImpl.class);

    private final BrandsRepository brandsRepository;

    private final BrandsMapper brandsMapper;

    public BrandsServiceImpl(BrandsRepository brandsRepository, BrandsMapper brandsMapper) {
        this.brandsRepository = brandsRepository;
        this.brandsMapper = brandsMapper;
    }

    @Override
    public List<BrandsDTO> findAll() {
        logger.info("Start of fetching all brands");
        List<BrandsDTO> brandsList = brandsRepository.findAll().stream().map(brandsMapper::toDto).collect(Collectors.toList());
        logger.info("End of fetching all brands");
        return brandsList;
    }
}
*/