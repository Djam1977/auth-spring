package com.auth.test.controller;

import com.auth.test.entity.ERole;
import com.auth.test.entity.Role;
import com.auth.test.entity.User;
import com.auth.test.payload.request.LoginRequest;
import com.auth.test.payload.request.SignupRequest;
import com.auth.test.payload.response.JwtResponse;
import com.auth.test.payload.response.MessageResponse;
import com.auth.test.repository.RoleRepository;
import com.auth.test.repository.UserRepository;

import com.auth.test.security.jwt.JwtUtils;
import com.auth.test.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
//Methode d'authentification qui requier un coprs de type login( obje clé valeur username password)
    //Si username exist verifie le password avec l'encoder
    //la class SecurityContextHoldercréé une nvvle authentification et si pas d'erreur genere un Token
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequestBody) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(), loginRequestBody.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
// Methode pour enregistrer un utilisateur, accessible de l'exterieur de la class authentificateur, elle renvoie une reponse entity(typage)
    //la methode registrerUser attend obligatoirement un objet dans on corps de type signUpRequestBody
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequestBody) {
        //Si le username existe déja je renvoie une erreur avec un message
        if (userRepository.existsByUsername(signUpRequestBody.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
//Si l'Email existe déja je renvoie une erreur avec un message
        if (userRepository.existsByEmail(signUpRequestBody.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User newUser = new User(signUpRequestBody.getUsername(),
                signUpRequestBody.getEmail(),
                encoder.encode(signUpRequestBody.getPassword()));

        Set<String> strRoles = signUpRequestBody.getRoles();
        Set<Role> roles = new HashSet<>();
// Si il y'a ROLE_USER dans mon dépot roleRepository je l'assigne à une variable Role userRole
        // En cas d'erreur(orElseThrow(()) je créé une erreur
        //Sinon je l'ajoute dans mon nouveau []
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {

                switch (role) {

                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}