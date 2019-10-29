package com.flightGui;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlightAgency {
    private JButton SendReplybtn;
    private JPanel ReplyPanel;
    private JTable TReceivedReqs;
    private JButton SendPriceBtn;
    private JTextArea replyText;
    private JButton getPriceBtn;
    private JTable tFlightsInfo;
    //    private static Connection connection; // to connect to the JMS
   FlightAgencyManager myRequest=null;
    private MessageProducer invalidProducer;
////    private static ConnectionFactory connectionFactory;
    public FlightAgency() throws JMSException {
        Object[] columns = {"MessageID","MessagesFromRequestors", "ResponseMessage"};
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        TReceivedReqs.setModel(tableModel);
//        Object[] columnsFlights = {"ID","Price", "Airlines"};
//        DefaultTableModel modelFlights = new DefaultTableModel();
//        tableModel.setColumnIdentifiers(columnsFlights);
//        TReceivedReqs.setModel(modelFlights);
        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        myRequest= new FlightAgencyManager(connection,session);
//        final int[] j=new int[1];{
////            myRequest= new FlightAgencyManager(connection,session);
//            Object[] row = new Object[3];
//            row[0] = myRequest.ticketsDao.getId();
//            row[1] = myRequest.ticketsDao.getTicketPrice();
//            row[2] = myRequest.ticketsDao.getAirlineName();
//            modelFlights.addRow(row);
//
//        }
         int[] i = new int[1];
        {
            myRequest.messageConsumer().setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {

                    try {
                        Object[] r = new Object[3];
                        r[0] = message.getJMSMessageID();
                        r[1] = ((TextMessage) message).getText();
                        r[2] = message.getJMSReplyTo();
                        tableModel.addRow(r);

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            });

            connection.start();
        }
        SendReplybtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String message=tableModel.getValueAt(i[0], 1).toString();
                String messageId=tableModel.getValueAt(i[0], 0).toString();
                Destination replyToQueue= (Destination) tableModel.getValueAt(i[0], 2);

                try {
                   myRequest.SendFullInformation(messageId,replyToQueue,message);


                    tableModel.removeRow(i[0]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });




        SendPriceBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageId=tableModel.getValueAt(i[0], 0).toString();
                String message=tableModel.getValueAt(i[0], 1).toString();
                Destination replyToQueue= (Destination) tableModel.getValueAt(i[0], 2);

                try {
                    myRequest.SendAllAirlines(messageId,replyToQueue,message );

                    tableModel.removeRow(i[0]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        TReceivedReqs.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                i[0]=TReceivedReqs.getSelectedRow();
            }
        });
    }

    public static void main(String[] args) throws JMSException, NamingException {

        JFrame jFrame=new JFrame("FlightAgency");
        jFrame.setContentPane(new FlightAgency().ReplyPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
