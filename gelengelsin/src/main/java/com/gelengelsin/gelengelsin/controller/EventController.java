package com.gelengelsin.gelengelsin.controller;

import com.gelengelsin.gelengelsin.model.Event;
import com.gelengelsin.gelengelsin.security.JwtService;
import com.gelengelsin.gelengelsin.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final JwtService jwtService;

    public EventController(EventService eventService, JwtService jwtService) {
        this.eventService = eventService;
        this.jwtService = jwtService;
    }

    // ✅ Etkinlik oluştur
    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestHeader("Authorization") String authHeader,
                                         @RequestBody Event event) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String email = jwtService.extractEmail(token);
        event.setCreatedByEmail(email);

        // Şu an için createdAt string ile yapıyoruz (örneğin yeni bir özellik için LocalDateTime'a geçebiliriz)
        event.setCreatedAt(String.valueOf(System.currentTimeMillis()));

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    // ✅ Tüm etkinlikleri getir
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // ✅ Bir etkinliği id ile getir
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }
}
