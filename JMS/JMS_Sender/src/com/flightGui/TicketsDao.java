package com.flightGui;

import com.flightGui.Tickets;

import java.util.ArrayList;
import java.util.List;

public class TicketsDao {
    private List<Tickets> ticketsList;
    private List<String> airlinesList= new ArrayList<>();
    List<Integer> priceList = new ArrayList<>();
    private List<Tickets> priceList2=new ArrayList<>();


    public TicketsDao() {
        ticketsList = new ArrayList<Tickets>() {{
            add(new Tickets(1, 150, "Transavia"));
            add(new Tickets(2, 200, "Ryan"));
            add(new Tickets(3, 100, "BritishAirways"));
            add(new Tickets(4, 120, "PIA"));
            add(new Tickets(5, 400, "AirCanada"));
            add(new Tickets(6, 136, "AirFrance"));


        }};
    }

    public String getTransavia(){
        return "Transavia";
    }

    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

//    public void createTicket(com.flightGui.Tickets tickets) {
//        ticketsList.add(tickets);
//    }

    public Tickets getTicketById(int id) {
        for (Tickets tickets : ticketsList) {
            if (tickets.getId() == id) {
                return tickets;
            }
        }
        return null;
    }

    public List<String> getAirlineName(){

        for(Tickets tickets:ticketsList){
           airlinesList.add(tickets.getAirlines());
        }
        return airlinesList;
    }
    public int getId(){
        int price=0;
        for (Tickets tickets:ticketsList){
            price=tickets.getId();
        }
        return price;
    }
    public List<Integer> getTicketPrices(){

        for (Tickets tickets:ticketsList){
          priceList.add(tickets.getPrice());

        }
        return priceList;
    }

    public void updateTicketAvailability(Tickets tickets, boolean isAvailable) {
        tickets.setAvailable(isAvailable);
    }
}
