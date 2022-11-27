package duynn.gotogether.controller;

import duynn.gotogether.dto.entity_dto.ClientDTO;
import duynn.gotogether.dto.entity_dto.UserDTO;
import duynn.gotogether.dto.request.JwtRequest;
import duynn.gotogether.dto.response.JwtResponse;
import duynn.gotogether.dto.response.RegisterRes;
import duynn.gotogether.dto.response.Status;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.User;
import duynn.gotogether.service.security.JwtUserDetailsService;
import duynn.gotogether.util.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userDetailsService.findUserByUsername(authenticationRequest.getUsername());
//        JwtResponse jwtResponse = new JwtResponse(token, modelMapper.map(user, UserDTO.class));
        JwtResponse jwtResponse = new JwtResponse(token, null);
        return ResponseEntity.ok(jwtResponse);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Client client = userDetailsService.findClientByUsername(authenticationRequest.getUsername());
        JwtResponse jwtResponse = new JwtResponse(token, modelMapper.map(client, ClientDTO.class));

        return ResponseEntity.ok(jwtResponse);
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public ResponseEntity<?> checkLogin(){
        return ResponseEntity.ok(new Status("success","login success"));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        boolean isExist = userDetailsService.isExist(user.getAccount().getUsername());
        if (isExist) {
            return ResponseEntity.badRequest().body(new RegisterRes("Fail","Username is already exist"));
        }
        try {
            UserDTO userDTO = modelMapper.map(userDetailsService.save(user), UserDTO.class);
            return ResponseEntity.ok(new RegisterRes("Success", "Register successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegisterRes("Fail", e.getMessage()));
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}