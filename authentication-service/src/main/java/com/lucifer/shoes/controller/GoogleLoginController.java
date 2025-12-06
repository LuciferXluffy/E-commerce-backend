package com.lucifer.shoes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GoogleLoginController {

    @PostMapping("/auth/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
        String idToken = body.get("credential"); // Google ID token
        try {
            // JWT parts: header.payload.signature
            String[] tokenParts = idToken.split("\\.");
            if (tokenParts.length != 3) {
                return ResponseEntity.status(400).body(Map.of(
                        "status", "error",
                        "message", "Invalid token format"
                ));
            }

            // Decode payload
            String payloadJson = new String(Base64.getUrlDecoder().decode(tokenParts[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> payload = mapper.readValue(payloadJson, Map.class);

            String email = (String) payload.get("email");
            String name = (String) payload.get("name");
            System.out.println("Google User Email: " + email + " " + name);
            System.out.println(payload);
            return ResponseEntity.ok(Map.of(
                    "status", "ok",
                    "email", email
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "status", "error",
                    "message", "Failed to decode token"
            ));
        }
    }
}