package com.microservice.showroom.Controller;

import com.microservice.common_service.dto.ResponseDTO;
import com.microservice.common_service.util.Constant;
import com.microservice.showroom.entity.Showroom;
import com.microservice.showroom.service.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;

@RestController
@RequestMapping("/api/v1")
public class ShowroomController {

    @Autowired
    private ShowroomService showroomService;

    public ShowroomController(final ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @GetMapping("/showroom/{id}")
    public ResponseDTO getShowroomById(@PathVariable("id") final Integer id) throws ServiceUnavailableException {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.showroomService.getShowroomById(id));
    }

    @PostMapping("/showroom")
    public ResponseDTO createShowroom(@RequestBody final Showroom showroom) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.showroomService.createShowroom(showroom));
    }

    @GetMapping("/showroom/retrieved/{id}")
    public ResponseDTO retrieveShowroomById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.showroomService.retrieveShowroomById(id));
    }

    @GetMapping("/showroom")
    public ResponseDTO retrieveShowroom() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.showroomService.retrieveShowroom());
    }

    @PutMapping("/showroom/{id}")
    public ResponseDTO updateShowroomById(@PathVariable final Integer id, @RequestBody final Showroom showroom) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.showroomService.updateShowroomById(showroom, id));
    }

    @DeleteMapping("/showroom/{id}")
    public ResponseDTO removeShowroomById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.showroomService.removeShowroomById(id));
    }
}
