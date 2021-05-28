package com.study.study.model.service;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.Item;
import com.study.study.model.header.Header;
import com.study.study.model.request.ItemApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.repository.ItemRepository;
import com.study.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemLogicService implements Crud<ItemApiResponse, ItemApiRequest> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;


    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest data = request.getData();

        Item item = Item.builder()
                .status(data.getStatus())
                .title(data.getTitle())
                .name(data.getName())
                .content(data.getContent())
                .price(data.getPrice())
                .brandName(data.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(data.getPartnerId())).build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest data = request.getData();
        return itemRepository.findById(data.getId()).map(
                item ->{
                    item.setBrandName(data.getBrandName());
                    item.setContent(data.getContent());
                    item.setName(data.getName());
                    item.setPartner(partnerRepository.getOne(data.getId()));
                    item.setTitle(data.getTitle());
                    item.setRegisteredAt(data.getRegisteredAt());
                    item.setUnregisteredAt(data.getUnregisteredAt());
                    item.setPrice(data.getPrice());

                    return item;
                }).map(
                         user-> itemRepository.save(user)
                 ).map(
                         this::response
                 ).orElseGet(()-> Header.ERROR("데이터없음"));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id).map(
                this::response)
                .orElseGet(()-> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id).map(
                item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터없음"));
    }

    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse data = ItemApiResponse.builder()
                .brandName(item.getBrandName())
                .content(item.getContent())
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .registeredAt(item.getRegisteredAt())
                .status(item.getStatus())
                .title(item.getTitle())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(data);
    }
}
