package com.fpt.niceshoes.infrastructure.converter;

import com.fpt.niceshoes.entity.Address;
import com.fpt.niceshoes.dto.request.AddressRequest;
import com.fpt.niceshoes.repository.IAccountRepository;
import com.fpt.niceshoes.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConvert {
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IAccountRepository accountRepository;
    public Address convertRequestToEntity(AddressRequest request) {
        return Address.builder()
                .account(request.getAccount() != null ? accountRepository.findById(request.getAccount()).orElse(null) : null)
                .name(request.getName())
                .defaultAddress(false)
                .ward(request.getWard())
                .district(request.getDistrict())
                .province(request.getProvince())
                .phoneNumber(request.getPhoneNumber())
                .specificAddress(request.getSpecificAddress())
                .build();
    }
    public Address convertRequestToEntity(Long id, AddressRequest request){
        Address address = addressRepository.findById(id).get();
        address.setSpecificAddress(request.getSpecificAddress());
        address.setDefaultAddress(request.getDefaultAddress());
        address.setName(request.getName());
        address.setWard(request.getWard());
        address.setDistrict(request.getDistrict());
        address.setProvince(request.getProvince());
        address.setPhoneNumber(request.getPhoneNumber());
        return address;
    }
}
