package com.fpt.niceshoes.controller.client;

import com.fpt.niceshoes.dto.request.properties.BrandRequest;
import com.fpt.niceshoes.dto.response.BrandResponse;
import com.fpt.niceshoes.entity.Brand;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/api/brand")
public class BrandControllerClient {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public PageableObject<BrandResponse> getAll(BrandRequest request) {
        return brandService.getAll(request);
    }


    @GetMapping("/{id}")
    public Brand getOne(@PathVariable Long id) {
        return brandService.getOne(id);
    }

    @PostMapping
    public ResponseObject create(@RequestBody @Valid BrandRequest request) {
        return new ResponseObject(brandService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseObject update(@PathVariable Long id, @RequestBody @Valid BrandRequest request) {
        return new ResponseObject(brandService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseObject delete(@PathVariable Long id) {
        return new ResponseObject(brandService.delete(id));
    }
}
