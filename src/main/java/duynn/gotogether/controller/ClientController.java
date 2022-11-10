package duynn.gotogether.controller;

import duynn.gotogether.dto.ClientDTO;
import duynn.gotogether.entity.Client;
import duynn.gotogether.service.ClientServiceImpl;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ModelMapper modelMapper;

    @RequestMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id){
        try {
            Client client = clientService.findById(id);
            ClientDTO dto = modelMapper.map(client, ClientDTO.class);
            System.out.println("success");
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
