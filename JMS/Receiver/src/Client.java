//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.*;
//import javax.naming.NamingException;
//import java.util.Scanner;
//
//public class Client {
//    public static void main(String[] args) {
////    Connection connection; // to connect to the JMS
////        TravelAgencyManger myRequest=new TravelAgencyManger();
//         Scanner Input = null;
//
//try {
//
//        ConnectionFactory connectionFactory;
//
//        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//
//        connection = connectionFactory.createConnection();
//
//
//     myRequest.initialize(connection);
//    System.out.println("Please make a choice between different options" +
//            "\n Press 1 To get list of all available tickets" +
//            "\n Press 2 to get available tickets by airline" +
//            "\n Press 3 to get an overview of all passengers" +
//            "\n Press 4 to reserve ticket");
//    Input = new Scanner(System.in);
//    String selectedOption=null;
//    connection.start();
//
//   while(true) {
//       selectedOption = Input.nextLine();
//       switch (selectedOption){
//           case "1":
//               myRequest.send("airline");
//               System.out.println("get me the list of all airline ");
//               break;
//           case "2":
////               myRequest.send(selectedOption);
//               myRequest.send("canada");
//               System.out.println("get me the list of all airline ");
//               break;
//           case "3":
////               myRequest.send(selectedOption);
//               myRequest.receiveSync();
//               System.out.println("get me the list of all airline ");
//               break;
//       }
//
//
////       if (selectedOption.equals("2")) {
//////           System.out.println("What is the average ticket price");
////       }
//
//
//
//
//   }
//
//        // this is needed to start receiving messages
//
//    } catch ( JMSException e) {
//
//        e.printStackTrace();
//
//    } catch (NamingException e) {
//    e.printStackTrace();
//}
//    }
//
//}
