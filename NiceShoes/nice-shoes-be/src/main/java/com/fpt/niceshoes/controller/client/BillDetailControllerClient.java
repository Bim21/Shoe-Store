package com.fpt.niceshoes.controller.client;

import com.fpt.niceshoes.dto.request.billdetail.BillDetailRequest;
import com.fpt.niceshoes.dto.response.BillDetailResponse;
import com.fpt.niceshoes.entity.BillDetail;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.service.BillDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/client/api/bill-detail")
public class BillDetailControllerClient {
    @Autowired
    private BillDetailService billDetailService;

    @GetMapping
    public PageableObject<BillDetailResponse> getAll(BillDetailRequest request) {
        return billDetailService.getAll(request);
    }

    @GetMapping("/{id}")
    public BillDetail getOne(@PathVariable Long id) {
        return billDetailService.getOne(id);
    }

    @PostMapping
    public ResponseObject create(@RequestBody @Valid BillDetailRequest request) {
        return new ResponseObject(billDetailService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseObject create(@PathVariable Long id, @RequestBody @Valid BillDetailRequest request) {
        return new ResponseObject(billDetailService.update(id, request));
    }

    @GetMapping("/update-quantity/{id}")
    public ResponseObject updateQuantity(@PathVariable Long id, @RequestParam(required = false, defaultValue = "0") Integer newQuantity,@RequestParam BigDecimal price) {
        return new ResponseObject(billDetailService.updateQuantity(id, newQuantity, price));
    }

    @DeleteMapping("/{id}")
    public ResponseObject delete(@PathVariable Long id){
        return new ResponseObject(billDetailService.delete(id));
    }
}
