package com.fpt.niceshoes.controller.admin;


import com.fpt.niceshoes.dto.request.giveback.GivebackRequest;
import com.fpt.niceshoes.dto.response.statistic.StatisticBillStatus;
import com.fpt.niceshoes.entity.Bill;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.dto.request.billdetail.BillClientRequest;
import com.fpt.niceshoes.dto.request.bill.BillRequest;
import com.fpt.niceshoes.dto.request.bill.BillSearchRequest;
import com.fpt.niceshoes.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public PageableObject getAll(BillSearchRequest request) {
        return billService.getAll(request);
    }

    @GetMapping ("/new-bill")
    public List<Bill> getNewBill(BillSearchRequest request){
        return billService.getNewBill(request);
    }

    @GetMapping("/statistic-bill-status")
    public List<StatisticBillStatus> statisticBillStatus() {
        return billService.statisticBillStatus();
    }

    @GetMapping("/{id}")
    public Bill getOne(@PathVariable Long id) {
        return billService.getOne(id);
    }

    @PostMapping
    public ResponseObject create() {
        return new ResponseObject(billService.create());
    }

    @PostMapping("/create-bill-client")
    public ResponseObject create(@RequestBody BillClientRequest request) {
        return new ResponseObject(billService.createBillClient(request));
    }

    @PutMapping("/{id}")
    public ResponseObject orderBill(@PathVariable Long id, @RequestBody @Valid BillRequest request) {
        return new ResponseObject(billService.orderBill(id, request));
    }

    @PutMapping("/change-info-customer/{id}")
    public ResponseObject changeInfoCustomer(@PathVariable Long id, @RequestBody BillRequest request){
        return new ResponseObject(billService.changeInfoCustomer(id,request));
    }

    @GetMapping("/change-status/{id}")
    public ResponseObject changeStatus(@PathVariable Long id, @RequestParam String note, @RequestParam(defaultValue = "false") Boolean isCancel) {
        return new ResponseObject(billService.changeStatus(id, note,isCancel));
    }

    @GetMapping("/give-back-all/{id}")
    public ResponseObject givebackAll(@PathVariable Long id, @RequestParam String note){
        return new ResponseObject(billService.givebackAll(id, note));
    }

    @PostMapping("/give-back")
    public ResponseObject giveback(@RequestBody GivebackRequest request){
        return new ResponseObject(billService.giveback(request));
    }
}
