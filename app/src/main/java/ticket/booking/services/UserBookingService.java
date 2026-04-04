package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH="src/main/java/ticket/booking/localDB/users.json";


    public UserBookingService() throws IOException {
        loadUser();
    }

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUser();
    }

    //load the user from file
    public List<User> loadUser() throws IOException {
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        return userList;
    }

    // To check user is login or not
    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(
                user1 -> {
                    return user1.getName().equals(user.getName()) &&
                            UserServiceUtil.checkPassword(user.getPassword(),user1.getHashPassword());
                }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp (User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    //Save the userlist in the file.
    private void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile,userList);
    }

    //Fetch the user Bookings
    public void fetchBooking(){
        Optional<User> userFetched = userList.stream().filter(user1->{return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashPassword());}).findFirst();
        if (userFetched.isPresent()){
            userFetched.get().printTickets();
        }
//        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ticket Id to cancel");
        ticketId = sc.nextLine();

        if(ticketId.isEmpty() || ticketId == null){
            System.out.println("Ticket Id can't be empty");
            return false;
        }

        String finalTicketId = ticketId; //Beacause String are immutable
        boolean removed = user.getTicketBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));

        if (removed){
            System.out.println("Ticket with ID "+ ticketId + " has been cancelled");
            return Boolean.TRUE;
        }else {
            System.out.println("No ticket found with ID" + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.getTrains(source,destination);

        }catch (IOException e){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat){
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if(row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()){
                if (seats.get(row).get(seat)==0){
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                }
                else {return false;}
            }else {
                return false; // Invalid row or seat index

            }

        }catch (IOException e){
            return Boolean.FALSE;
        }
    }

}
