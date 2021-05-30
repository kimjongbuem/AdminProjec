package com.study.study.service;

import com.study.study.model.entity.Partner;
import com.study.study.model.header.Header;
import com.study.study.model.request.PartnerApiRequest;
import com.study.study.model.response.PartnerApiResponse;
import com.study.study.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerLogicService extends BaseService<PartnerApiResponse, PartnerApiRequest, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest data = request.getData();
        Partner partner = Partner.builder()
                .status(data.getStatus())
                .name(data.getName())
                .address(data.getAddress())
                .callCenter(data.getCallCenter())
                .partnerNumber(data.getPartnerNumber())
                .businessNumber(data.getBusinessNumber())
                .ceoName(data.getCeoName())
                .category(categoryRepository.getOne(data.getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest data = request.getData();
        return baseRepository.findById(data.getId()).map(
                partner -> {
                    partner.setStatus(data.getStatus());
                    partner.setName(data.getName());
                    partner.setAddress(data.getAddress());
                    partner.setCallCenter(data.getCallCenter());
                    partner.setPartnerNumber(data.getPartnerNumber());
                    partner.setBusinessNumber(data.getBusinessNumber());
                    partner.setCeoName(data.getCeoName());
                    partner.setRegisteredAt(data.getRegisteredAt());
                    partner.setUnregisteredAt(data.getUnregisteredAt());
                    return partner;
                }
        ).map(
                partner-> baseRepository.save(partner)
        ).map(
                this::response
        ).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id).map(
                this::response
        ).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id).map(
                partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                }
        ).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse data = PartnerApiResponse.builder()
                .status(partner.getStatus())
                .name(partner.getName())
                .id(partner.getId())
                .address(partner.getAddress())
                .registeredAt(partner.getRegisteredAt())
                .ceoName(partner.getCeoName())
                .partnerNumber(partner.getPartnerNumber())
                .callCenter(partner.getCallCenter())
                .unregisteredAt(partner.getUnregisteredAt())
                .businessNumber(partner.getBusinessNumber())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(data);
    }

    @Override
    public Header<List<PartnerApiResponse>> getPageList(Pageable pageable) {
        return null;
    }
}
