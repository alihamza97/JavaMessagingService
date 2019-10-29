package com.flightGui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.*;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
//import com.fasterxml.jackson.databind.*;
public class FlightAgencyManager  {
    private Session session;
    private MessageProducer invalidProducer;
    Connection connection;
    String request;
    private JmsTemplate jmsTemplate;
    TicketsDao ticketsDao=null;

    String InvalidQueuee;

    protected FlightAgencyManager(Connection connection, Session session) throws JMSException {
        super();
        this.session =session;
        ticketsDao=new TicketsDao();
        this.connection=connection;
//        this.request=requestQueueName;
//        this.InvalidQueue=invalidQueueName;
    }
    public MessageConsumer messageConsumer() throws JMSException {
        Destination requestQueue = session.createQueue("RequestQueue");
        return session.createConsumer(requestQueue);
    }

    public void SendAllAirlines(String Messageid, Destination ReplyQueue, String ResponseMessage) throws JMSException, JsonProcessingException {

        ObjectMapper mapObj=new ObjectMapper();
        String flightName= mapObj.writeValueAsString(ticketsDao.getAirlineName());
         MessageProducer replyProducer = session.createProducer(ReplyQueue);


            TextMessage replyMessage = session.createTextMessage();
            replyMessage.setText(flightName);
            replyMessage.setJMSCorrelationID(Messageid);
            replyProducer.send(replyMessage);
            System.out.println("Sent reply");
            System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
            System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
            System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
            System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
            System.out.println("\tContents:   " + replyMessage.getText());


    }
    public void SendFullInformation(String Messageid, Destination ReplyQueue, String ResponseMessage) throws JMSException, JsonProcessingException {

        ObjectMapper mapObj=new ObjectMapper();
        String flightFull= mapObj.writeValueAsString(ticketsDao.getTicketsList());
//        String flightprice= objectMapper.writeValueAsString(ticketsDao.getTicketPrices());
        MessageProducer replyProducer = session.createProducer(ReplyQueue);

        TextMessage replyMessage = session.createTextMessage();
        replyMessage.setText(flightFull);
        replyMessage.setJMSCorrelationID(Messageid);
        replyProducer.send(replyMessage);
        System.out.println("Sent reply");
        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
        System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
        System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
        System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
        System.out.println("\tContents:   " + replyMessage.getText());


    }




}
