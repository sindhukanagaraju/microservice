


package com.microservice.showroom.service;

import com.microservice.common_service.exception.BadRequestServiceAlertException;
import com.microservice.common_service.util.Constant;
import com.microservice.showroom.DTO.ProductResponse;
import com.microservice.showroom.DTO.ShowroomDTO;
import com.microservice.showroom.config.ModelMapperConfig;
import com.microservice.showroom.entity.Showroom;
import com.microservice.showroom.repository.ShowroomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowroomService {

    @Autowired
    private ShowroomRepository showroomRepository;

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    // Map Showroom + ProductResponse to ShowroomDTO
    private ShowroomDTO mapToDTO(Showroom showroom, ProductResponse productResponse) {
        return new ShowroomDTO(
                showroom.getId(),
                showroom.getName(),
                showroom.getAddress(),
                showroom.getContactNumber(),
                productResponse == null ? "PRODUCT_SERVICE_DOWN" : productResponse
        );
    }

    // Combines showroom with product data
    private ShowroomDTO getShowroomWithProducts(Showroom showroom) {
        ProductResponse productResponse = modelMapperConfig.getById(showroom.getId());
        return mapToDTO(showroom, productResponse);
    }

    @Transactional
    public ShowroomDTO createShowroom(Showroom showroom) {
        Showroom saved = showroomRepository.save(showroom);
        return getShowroomWithProducts(saved);
    }

    public ShowroomDTO retrieveShowroomById(Integer id) {
        Showroom showroom = showroomRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        return getShowroomWithProducts(showroom);
    }

    public List<ShowroomDTO> retrieveShowroom() {
        return showroomRepository.findAll().stream()
                .map(this::getShowroomWithProducts)
                .collect(Collectors.toList());
    }

    @Transactional
    public ShowroomDTO updateShowroomById(Integer id, Showroom updated) {
        Showroom existing = showroomRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setContactNumber(updated.getContactNumber());

        Showroom saved = showroomRepository.save(existing);
        return getShowroomWithProducts(saved);
    }

    public String removeShowroomById(Integer id) {
        Showroom showroom = showroomRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        showroomRepository.delete(showroom);
        return "Showroom with ID " + id + " " + Constant.REMOVE;
    }
}


//package com.microservice.showroom.service;
//
//import com.microservice.common_service.exception.BadRequestServiceAlertException;
//import com.microservice.common_service.util.Constant;
//import com.microservice.showroom.DTO.ProductResponse;
//import com.microservice.showroom.DTO.ShowroomDTO;
//import com.microservice.showroom.config.ModelMapperConfig;
//import com.microservice.showroom.entity.Showroom;
//import com.microservice.showroom.repository.ShowroomRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ShowroomService {
//   @Autowired
//    private  ShowroomRepository showroomRepository;
//
//@Autowired
//private ModelMapperConfig modelMapperConfig;
//private ShowroomDTO mapToDTO(Showroom showroom, ProductResponse productResponse){
//    return new ShowroomDTO(
//            showroom.getId(),
//            showroom.getName(),
//            showroom.getAddress(),
//            showroom.getContactNumber(),
//            productResponse == null? "not data":productResponse
//    );
//}
//
//private ShowroomDTO getById(Showroom showroom){
//    ProductResponse productResponse= modelMapperConfig.getById(showroom.getId());
//    return mapToDTO(showroom,productResponse);
//}
//    @Transactional
//    public Showroom createShowroom(final Showroom showroom) {
//        return this.showroomRepository.save(showroom);
//    }
//
//    public Showroom retrieveShowroomById(final Integer id) {
//        return this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
//    }
//
//    public List<Showroom> retrieveShowroom() {
//        return this.showroomRepository.findAll();
//    }
//
//    @Transactional
//    public Showroom updateShowroomById(final Showroom showroom, final Integer id) {
//        final Showroom existingShowroom = this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
//        if (showroom.getId() != null) {
//            existingShowroom.setId(showroom.getId());
//        }
//        if (showroom.getName() != null) {
//            existingShowroom.setName(showroom.getName());
//        }
//        if (showroom.getAddress() != null) {
//            existingShowroom.setAddress(showroom.getAddress());
//        }
//        if (showroom.getContactNumber() != null) {
//            existingShowroom.setContactNumber(showroom.getContactNumber());
//        }
//        return this.showroomRepository.save(existingShowroom);
//    }
//
//    public String removeShowroomById(final Integer id) {
//        final Showroom showroom = this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
//        this.showroomRepository.delete(showroom);
//        return Constant.REMOVE;
//    }
//
//}
//
//
//
//
/// /    public ShowroomService(ShowroomRepository showroomRepository) {
/// /        this.showroomRepository = showroomRepository;
/// /    }
//
////    private ModelMapper modelMapper;
////    private WebClientFactory webClient;
//
////    @Value("${productservice.base.url}")
////    private String productServiceBaseUrl;
////
////    public ShowroomDetails getShowroomById(Integer id) {
////        ModelMapper modelMapper = new ModelMapper();
////        Optional<Showroom> showroomOptional = showroomRepository.findById(id);
////
////        if (showroomOptional.isEmpty()) {
////            throw new RuntimeException("Showroom not found with id " + id);
////        }
////
////        Showroom showroom = showroomOptional.get();
////        Showroom showroomResponse = modelMapper.map(showroom, Showroom.class);
////
////        WebClient webClient = WebClient.builder()
////                .baseUrl("http://localhost:8081/product-service/api/v1")
////                .build();
////
////        ProductResponse productResponse = null;
////
////        try {
////            productResponse = webClient.get()
////                    .uri("/product/{id}", id)
////                    .retrieve()
////                    .bodyToMono(ProductResponse.class)
////                    .block();
////        } catch (WebClientResponseException e) {
////            productResponse = null;
////        } catch (Exception e) {
////
////            productResponse = null;
////        }
////
////        System.out.println(showroom);
////        System.out.println(productResponse);
////        return new ShowroomDetails(showroom, productResponse);
////    }
//
////    public ShowroomService(ModelMapper modelMapper, WebClientFactory webClient) {
////        this.modelMapper = modelMapper;
////        this.webClient = webClient;
////    }
//
////    public ShowroomDTO getShowroomById(Integer id) {
////
////        final Showroom showroom = this.showroomRepository.findById(id)
////                .orElseThrow(() -> new BadRequestServiceAlertException("School with ID " + id + " not found"));
////        final ShowroomDTO showroomDTO = modelMapper.map(showroom, ShowroomDTO.class);
////        final JsonNode productResponse = webClient.getProductWebClient().get()
////                .uri("api/v1/product/showroom/{id}", id)
////                .retrieve()
////                .onStatus(HttpStatusCode::is5xxServerError, response ->
////                        Mono.error(new ServiceUnavailableException("mohan Service is currently unavailable"))
////                )
////                .bodyToMono(ObjectNode.class)
////                .blockOptional()
////                .orElseThrow(() -> new ServiceUnavailableException("Student Service is currently unavailable"));
////        showroomDTO.setProductResponse(productResponse);
////        return showroomDTO;
////    }