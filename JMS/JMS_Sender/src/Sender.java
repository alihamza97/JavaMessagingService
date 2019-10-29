//import com.flightGui.FlightAgencyManager;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.*;
//import javax.naming.NamingException;
//
//public class Sender {
//    public static void main(String[] args) throws NamingException, JMSException {
//        Connection connection; // to connect to the ActiveMQ
//       // Session session; // session for creating messages, producers and
//         Session session;
//         MessageProducer invalidProducer;
//         com.flightGui.TicketsDao ticketDao = new com.flightGui.TicketsDao();
////         List<com.flightGui.Tickets> ticketsList=ticketDao.getTicketsList();
//
//        ConnectionFactory connectionFactory;
//        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        connection = connectionFactory.createConnection();
////        com.flightGui.FlightAgencyManager senderRequest=new com.flightGui.FlightAgencyManager();
//        FlightAgencyManager.newReplier(connection,"RequestQueue","InvalidQueue");
//        connection.start();
////        com.flightGui.FlightAgencyManager senderRequest=new com.flightGui.FlightAgencyManager();
////        try {
////            ConnectionFactory connectionFactory;
////            connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
////            connection = connectionFactory.createConnection();
////
////          session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
////
//////            Destination requestQueue = session.createQueue("RequestQueue");
////            Queue requestQueue = (Queue) initialContext.lookup("RequestQueue");
////            Destination invalidQueue = session.createQueue("InvalidQueue");
////
////            MessageConsumer requestConsumer = session.createConsumer(requestQueue);
////            //MessageListener listener = this;
////            //requestConsumer.setMessageListener(listener);
////
////            invalidProducer = session.createProducer(invalidQueue);
//////            String body = "Hello world."; //or serialize an object!
////// create a text message
////
////// send the message
//////            producer.send(msg);
////
////
//////       senderRequest.initialize(connection);
////
////
////            requestConsumer.setMessageListener(new MessageListener() {
////            public void onMessage(Message message) {
////                try {
////
////                        TextMessage requestMessage = (TextMessage) message;
////
////                        System.out.println("Received request");
////                        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
////                        System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
////                        System.out.println("\tCorrel. ID: " + requestMessage.getJMSCorrelationID());
////                        System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
////                        System.out.println("\tContents:   " + requestMessage.getText());
////
////                        String contents = requestMessage.getText();
////                        Destination replyDestination = message.getJMSReplyTo();
////                        MessageProducer replyProducer = session.createProducer(replyDestination);
////
////                        TextMessage replyMessage = session.createTextMessage();
////                        replyMessage.setText(contents);
////                        replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());
////                        replyProducer.send(replyMessage);
////
////                        System.out.println("Sent reply");
////                        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
////                        System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
////                        System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
////                        System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
////                        System.out.println("\tContents:   " + replyMessage.getText());
////
//////                    } else {
//////                        System.out.println("Invalid message detected");
//////                        System.out.println("\tType:       " + message.getClass().getName());
//////                        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
//////                        System.out.println("\tMessage ID: " + message.getJMSMessageID());
//////                        System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
//////                        System.out.println("\tReply to:   " + message.getJMSReplyTo());
//////
//////                        message.setJMSCorrelationID(message.getJMSMessageID());
//////                        invalidProducer.send(message);
//////
//////                        System.out.println("Sent to invalid message queue");
//////                        System.out.println("\tType:       " + message.getClass().getName());
//////                        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
//////                        System.out.println("\tMessage ID: " + message.getJMSMessageID());
//////                        System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
//////                        System.out.println("\tReply to:   " + message.getJMSReplyTo());
//////                    }
////                } catch (JMSException e) {
////                    e.printStackTrace();
////                }
////            }
////
//////            TextMessage textMessage=session.createTextMessage("saslghasl;kg");
//////           senderRequest.onMessage(textMessage);
//////            com.flightGui.FlightAgencyManager.newReplier(connection);
////      // Message msg = textMessage;
//////       senderRequest.onMessage(textMessage);
////
////        });
////connection.start();
////        } catch (JMSException e) {
////            e.printStackTrace();
////        }
////    }
//
//    }}