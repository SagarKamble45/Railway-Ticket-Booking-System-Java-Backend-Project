package ticket.booking.services;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class AppServices {


    //User Signup process
    public static void signUp(Scanner sc, UserBookingService userBookingService) {
        System.out.println("Enter your username to sign up:");
        String username = sc.next();
        System.out.println("Enter your password:");
        String password = sc.next();
        User userToSignup = new User(username, password, UserServiceUtil.hashPassword(password),
                new ArrayList<>(),
                UUID.randomUUID().toString());
        userBookingService.signUp(userToSignup);
    }


    //User Signup process
    public static User login(Scanner sc) {
        System.out.println("Enter your username to login:");
        String userName = sc.next();
        System.out.println("Enter your password:");
        String pass = sc.next();
        User userToLogin = new User(userName, pass, UserServiceUtil.hashPassword(pass));
        try {
            UserBookingService userBookingService = new UserBookingService(userToLogin);
            if (userBookingService.loginUser()) {
                System.out.println("Login successful! Welcome " + userName);
                return userToLogin;
            } else {
                System.out.println("Login failed! Please try again");
            }
        } catch (IOException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }

    //Search the trains from the trains.json
    public static Train searchTrain(Scanner sc, UserBookingService userBookingService) throws IOException {

        System.out.println("Type your source station");
        String sourceStation = sc.next();
        System.out.println("Type your destination station");
        String destinationStation = sc.next();
        List<Train> trains = userBookingService.getTrains(sourceStation, destinationStation);

        if (trains.isEmpty()) {
            System.out.println("No trains found for that route.");
            return null;
        }

        int index = 1;
        for (Train t : trains) {
            System.out.println("\n  [" + index + "] Train ID: " + t.getTrainId() + " | Train No: " + t.getTrainNo());
            System.out.println("  Route: ");
            for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                System.out.println("      --> " + entry.getKey().toUpperCase() + " @ " + entry.getValue());
            }
            System.out.println("  ------------------------------------------------");
            index++;
        }
        System.out.println("\nSelect a train by typing 1, 2, 3...");

        int selectTrain = -1;
        while (selectTrain < 1 || selectTrain > trains.size()) {
            if (sc.hasNextInt()) {
                selectTrain = sc.nextInt();
                if (selectTrain < 1 || selectTrain > trains.size()) {
                    System.out.println("Invalid option. Please choose between 1 and " + trains.size());
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); // removes bad input
            }
        }
        return trains.get(selectTrain - 1);
    }

    //Book a seat from the selected trains
    public static void bookSeat(Scanner sc, UserBookingService userBookingService, Train trainSelectedForBooking, User loggedInUser) throws IOException {
        if (trainSelectedForBooking.getTrainId() == null ||
                trainSelectedForBooking.getTrainId().isEmpty()) {
            System.out.println("First find and select the train.");
            return;
        }

        String[] stations = TrainService.selectSourceAndDestination(trainSelectedForBooking, sc);
        if (stations == null) return;
        String source = stations[0];
        String destination = stations[1];

        System.out.println("Select a seat out of these seats");
        List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
        for (List<Integer> row : seats) {
            for (Integer val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        System.out.println("remember row and column start from 0");
        System.out.println("Select the seat by typing the row and column");
        System.out.println("enter the row");
        int row = sc.nextInt();
        System.out.println("enter the column");
        int column = sc.nextInt();

        System.out.println("Booking your seat...");
        Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, column, source, destination, loggedInUser);
        if (booked.equals(Boolean.TRUE)) {
            System.out.println("Seat Booked Successfully");
        } else {
            System.out.println("Can't book this seat It is already booked");
        }
    }


    //Cancle Booking from the tickets
    public static void cancelBooking(Scanner sc, UserBookingService userBookingService, User loggedInUser) {
        userBookingService.fetchBooking(loggedInUser);
        System.out.println("Enter the TicketId");
        String ticketId = sc.next();
        userBookingService.cancelBooking(ticketId);
    }



}



