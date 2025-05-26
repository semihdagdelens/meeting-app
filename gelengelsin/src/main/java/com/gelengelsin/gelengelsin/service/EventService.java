package com.gelengelsin.gelengelsin.service;

import com.gelengelsin.gelengelsin.model.Event;
import com.gelengelsin.gelengelsin.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByUser(String email) {
        return eventRepository.findByCreatedByEmail(email);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
