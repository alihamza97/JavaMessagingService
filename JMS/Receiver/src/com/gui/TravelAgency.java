package com.gui;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TravelAgency {
    private JButton SendRequestBtn;
    private JTextArea txtRequest;
    private JPanel panelMain;
    private JTable tReplies;
    private static Connection connection; // to connect to the JMS
    private static TravelAgencyManger myRequest=new TravelAgencyManger();
    private static ConnectionFactory connectionFactory;

    public TravelAgency() throws JMSException, NamingException {
        Object[] columnsRequestors = {"MessageID","MessagesFromFlightAgency"};
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsRequestors);
        tReplies.setModel(tableModel);
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        myRequest.startup(connection);

        myRequest.messageReceiverConsumer().setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try{

                    Object[] r = new Object[2];
                    r[0] = message.getJMSCorrelationID();
                    r[1] = ((TextMessage) message).getText();
                    tableModel.addRow(r);
                }catch (Exception e){ System.out.println("there was an error getting response from the replier");}

            }

        });
        connection.start();
        SendRequestBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myRequest.send(txtRequest.getText());
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws JMSException, NamingException {



        Scanner Input = new Scanner(System.in);
        JFrame jFrame=new JFrame("TravelAgency");
        jFrame.setContentPane(new TravelAgency().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
}
}
