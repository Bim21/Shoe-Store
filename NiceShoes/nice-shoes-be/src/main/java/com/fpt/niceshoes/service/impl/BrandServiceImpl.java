package com.fpt.niceshoes.service.impl;

import com.fpt.niceshoes.entity.Brand;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.converter.BrandConvert;
import com.fpt.niceshoes.infrastructure.exception.RestApiException;
import com.fpt.niceshoes.dto.request.properties.BrandRequest;
import com.fpt.niceshoes.dto.response.BrandResponse;
import com.fpt.niceshoes.repository.IBrandRepository;
import com.fpt.niceshoes.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private IBrandRepository repository;
    @Autowired
    private BrandConvert brandConvert;

    @Override
    public PageableObject<BrandResponse> getAll(BrandRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSizePage());
        return new PageableObject<>(repository.getAllBrand(request, pageable));
    }

    @Override
    public Brand getOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Brand create(BrandRequest request) {
        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new RestApiException("Thương hiệu:  " + request.getName() + " đã tồn tại!");
        }
        Brand brand = brandConvert.convertRequestToEntity(request);
        return repository.save(brand);
    }

    @Override
    public Brand update(Long id, BrandRequest request) {
        Brand oldBrand = repository.findById(id).get();
        if (repository.existsByNameIgnoreCase(request.getName())) {
            if (oldBrand.getName().equals(request.getName())) {
                return repository.save(brandConvert.convertRequestToEntity(oldBrand, request));
            }
            throw new RestApiException("Thương hiệu: " + request.getName() + " đã tồn tại!");
        } else {
            return repository.save(brandConvert.convertRequestToEntity(oldBrand, request));
        }
    }

    @Override
    public Brand delete(Long id) {
        Brand brand = this.getOne(id);
        brand.setDeleted(!brand.getDeleted());
        return repository.save(brand);
    }
}
