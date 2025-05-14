package com.microservice.showroom.service;

import com.microservice.common_service.config.WebClientFactory;
import com.microservice.common_service.dto.ResponseDTO;
import com.microservice.common_service.exception.BadRequestServiceAlertException;
import com.microservice.common_service.util.Constant;
import com.microservice.showroom.DTO.ProductResponse;
import com.microservice.showroom.DTO.ShowroomDTO;
import com.microservice.showroom.DTO.ShowroomDetails;
import com.microservice.showroom.entity.Showroom;
import com.microservice.showroom.repository.ShowroomRepository;
import jakarta.transaction.Transactional;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowroomService {

    private  final ShowroomRepository showroomRepository;

    public ShowroomService(ShowroomRepository showroomRepository) {
        this.showroomRepository = showroomRepository;
    }

    @Value("${productservice.base.url}")
    private String productServiceBaseUrl;
    public ShowroomDetails getShowroomById(Integer id) {
        Optional<Showroom> showroomOptional = showroomRepository.findById(id);

        if (showroomOptional.isEmpty()) {
            throw new RuntimeException("Showroom not found with id " + id);
        }

        Showroom showroom = showroomOptional.get();

        WebClient webClient = WebClient.builder()
                .baseUrl(productServiceBaseUrl)
                .build();

        List<ProductResponse> productResponses = new ArrayList<>();

        try {
            ResponseDTO response = webClient.get()
                    .uri("/api/v1/product/retrieve/{showroomId}", id)
                    .retrieve()
                    .bodyToMono(ResponseDTO.class)
                    .block();

            // Parse the `data` field using ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            Object data = response.getData();

            // Convert LinkedHashMap data to actual List<ProductResponse>
            String json = mapper.writeValueAsString(data);
            productResponses = mapper.readValue(json, new TypeReference<List<ProductResponse>>() {});
        } catch (Exception e) {
            // log the error if needed
            productResponses = new ArrayList<>(); // fallback to empty list
        }

        return new ShowroomDetails(showroom, productResponses);
    }



    @Transactional
    public Showroom createShowroom(final Showroom showroom) {
        return this.showroomRepository.save(showroom);
    }

    public Showroom retrieveShowroomById(final Integer id) {
        return this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Showroom> retrieveShowroom() {
        return this.showroomRepository.findAll();
    }

    @Transactional
    public Showroom updateShowroomById(final Showroom showroom, final Integer id) {
        final Showroom existingShowroom = this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (showroom.getId() != null) {
            existingShowroom.setId(showroom.getId());
        }
        if (showroom.getName() != null) {
            existingShowroom.setName(showroom.getName());
        }
        if (showroom.getAddress() != null) {
            existingShowroom.setAddress(showroom.getAddress());
        }
        if (showroom.getContactNumber() != null) {
            existingShowroom.setContactNumber(showroom.getContactNumber());
        }
        return this.showroomRepository.save(existingShowroom);
    }

    public String removeShowroomById(final Integer id) {
        final Showroom showroom = this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.showroomRepository.delete(showroom);
        return Constant.REMOVE;
    }
}

