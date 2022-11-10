package duynn.gotogether.controller;

import duynn.gotogether.dto.TransportWithoutOwnerDTO;
import duynn.gotogether.entity.Transport;
import duynn.gotogether.service.TransportServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/transport")
public class TransportController {

    @Autowired
    TransportServiceImpl transportService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/get-by-user-id")
    public ResponseEntity<?> getTransportByUserId(@RequestParam("id") Long id) {
        List<Transport> list = transportService.getTransportByUserId(id);
        List<TransportWithoutOwnerDTO> listDTO = new ArrayList<>();
        for (Transport transport : list) {
            TransportWithoutOwnerDTO dto = modelMapper.map(transport, TransportWithoutOwnerDTO.class);
            listDTO.add(dto);
        }
        return ResponseEntity.ok(listDTO);
    }
}
