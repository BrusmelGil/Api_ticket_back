package com.example.tickets.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.tickets.model.Ticket;
import org.springframework.stereotype.Service;
import com.example.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsUsuario(String nombre){
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUsuarioCreador().equals(nombre))
                .collect(Collectors.toList());
    }
    
    public Ticket agregarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    public Ticket modificarTicket(Long id, String descripcion) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setDescription(descripcion);
            return ticketRepository.save(ticket);
        } else {
            // Manejo de la situación donde el usuario no se encuentra
            return null;
        }
    }

    public void borrarTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
