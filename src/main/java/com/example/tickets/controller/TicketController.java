package com.example.tickets.controller;

import com.example.tickets.model.Ticket;
import com.example.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/admin/tickets")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/admin/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/admin/ticketsusuario/{nombre}")
    public List<Ticket> getTicketsUsuario(@PathVariable String nombre) {
        return ticketService.getTicketsUsuario(nombre);
    }
    
    @PostMapping("/admin/addticket")
    public ResponseEntity<Ticket> agregarTicket(@RequestBody Ticket ticket) {
        Ticket nuevoTicket = ticketService.agregarTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTicket);
    }
    
    @PutMapping("admin/tickets/{id}")
    public ResponseEntity<Ticket> modificarTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket ticketActualizado = ticketService.modificarTicket(id, ticket.getDescription());
        if (ticketActualizado != null) {
            return ResponseEntity.ok(ticketActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("admin/tickets/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {
        ticketService.borrarTicket(id);
        return ResponseEntity.noContent().build();
    }
}
