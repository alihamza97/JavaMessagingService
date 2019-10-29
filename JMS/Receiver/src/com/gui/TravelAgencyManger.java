package com.gui;

import javax.jms.*;
import javax.naming.NamingException;

public class TravelAgencyManger {

    private Session session;
    private Destination replyQueue;
    private MessageProducer requestProducer;
    private MessageConsumer replyConsumer;
    private MessageProducer invalidProducer;

    protected TravelAgencyManger() {
        super();
    }
//         try {
//                jndiContext = new InitialContext();
//            } catch (NamingException e) {
//                LOG.info("Could not create JNDI API context: " + e.toString());
//                System.exit(1);
//            }
    public static TravelAgencyManger newRequestor(Connection connection)
            throws JMSException, NamingException {

        TravelAgencyManger requestor = new TravelAgencyManger();
        requestor.startup(connection);
        return requestor;
    }

    public void startup(Connection connection)
            throws NamingException, JMSException {

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination requestQueue =session.createQueue("RequestQueue");
        requestProducer = session.createProducer(requestQueue);
    }
    public MessageConsumer messageReceiverConsumer() throws JMSException {
        replyQueue = session.createQueue("ReplyQueue");
        return session.createConsumer(replyQueue);
    }

    public void send(String Messages) throws JMSException {
        TextMessage requestMessage = session.createTextMessage();
        requestMessage.setText(Messages);
        requestMessage.setJMSReplyTo(replyQueue);
        requestProducer.send(requestMessage);
        System.out.println("Sent request");
        System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
        System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
        System.out.println("\tCorrel. ID: " + requestMessage.getJMSCorrelationID());
        System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
        System.out.println("\tContents:   " + requestMessage.getText());
    }


    private class JndiUtil {
//        Context jndiContext;

    }
}
