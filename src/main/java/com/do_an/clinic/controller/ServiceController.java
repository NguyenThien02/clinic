package com.do_an.clinic.controller;


import com.do_an.clinic.dtos.ServiceDTO;
import com.do_an.clinic.models.Service;
import com.do_an.clinic.response.MessengerResponse;
import com.do_an.clinic.response.ServiceListResponse;
import com.do_an.clinic.services.IServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/services")
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceService serviceService;

    @GetMapping("")
    public ResponseEntity<?> getAllService(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int limit,
                                           @RequestParam(defaultValue = "0", name = "specialty_id") Long specialtyId
    ){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("id").ascending()
        );
        Page<Service> servicePage = serviceService.getAllServices( specialtyId, pageRequest);
        int totalPages = servicePage.getTotalPages();
        List<Service> services = servicePage.getContent();
        return ResponseEntity.ok(ServiceListResponse.builder()
                .services(services)
                .totalPages(totalPages)
                .build()
        );
    }

    // Lấy ra danh sách các dịch vụ đã chọn
    @PostMapping("/by-ids")
    public List<Service> getServicesByIds(@RequestBody List<Long> serviceIds) {
        return serviceService.findAllByIdIn(serviceIds);
    }

    @PostMapping("")
    public ResponseEntity<?> createService(@RequestBody ServiceDTO serviceDTO){
        Service service = serviceService.createService(serviceDTO);
        return ResponseEntity.ok(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceById(@PathVariable Long id){
        serviceService.deleteServiceById(id);
        MessengerResponse messengerResponse = new MessengerResponse("Xóa thành công dịch vụ có id: " + id);
        return ResponseEntity.ok(messengerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@RequestBody ServiceDTO serviceDTO,
                                           @PathVariable Long id){
        Service service = serviceService.updateService(id, serviceDTO);
        return ResponseEntity.ok(service);
    }

    

}

