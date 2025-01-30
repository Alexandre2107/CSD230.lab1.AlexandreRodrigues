package csd230.lab2.controllers;

import csd230.lab2.entities.Ticket;
import csd230.lab2.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String tickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets";
    }

    @GetMapping("/add-ticket")
    public String ticketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    @PostMapping("/add-ticket")
    public String ticketSubmit(@ModelAttribute Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        ticketRepository.save(ticket);
        model.addAttribute("tickets", ticketRepository.findAll());
        return "redirect:/tickets";
    }
}