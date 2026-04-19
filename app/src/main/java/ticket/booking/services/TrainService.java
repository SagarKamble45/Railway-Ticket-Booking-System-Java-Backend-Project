package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private List<Train> trainList;
    private ObjectMapper objmapper = new ObjectMapper();
    private static final String Train_DB_PATH = "src/main/java/ticket/booking/localDB/trains.json";

    public TrainService() throws IOException {
        File trains = new File(Train_DB_PATH);
        trainList = objmapper.readValue(trains, new TypeReference<List<Train>>(){});
    }


    public void addTrain(Train newTrain){
        Optional<Train> existingTrain = trainList.stream().filter(train->train.getTrainNo().equalsIgnoreCase(newTrain.getTrainNo())).findFirst();

        if (existingTrain.isPresent()){
            updateTrain(newTrain);
        }else{
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    private void saveTrainListToFile() {
        try{
            objmapper.writeValue(new File(Train_DB_PATH),trainList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateTrain(Train updatedTrain){
        OptionalInt index = IntStream.range(0, trainList.size()).filter(i->trainList.get(i).getTrainNo().equalsIgnoreCase(updatedTrain.getTrainNo())).findFirst();
        if (index.isPresent()){
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        }else {
            addTrain(updatedTrain);
        }
    }
    public List<Train> getTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    public  boolean validTrain(Train train, String source, String destination){
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;

    }

    public static String[] selectSourceAndDestination(Train train, Scanner sc) {
        // Get all stations from the train
        List<String> stations = new ArrayList<>(train.getStationTimes().keySet());
        int sourceIndex;
        int destinationIndex;

        // Show available stations
        System.out.println("Available stations for this train:");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". " + stations.get(i));
        }

        while (true) {
            // Ask user to pick source
            System.out.println("Select your source station number:");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }
             sourceIndex = sc.nextInt() - 1;

            // Ask user to pick destination
            System.out.println("Select your destination station number:");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }
            destinationIndex = sc.nextInt() - 1;

            // bounds check
            if (sourceIndex < 0 || sourceIndex >= stations.size() ||
                    destinationIndex < 0 || destinationIndex >= stations.size()) {
                System.out.println("Station number out of range. Try again.");
                continue;
            }
            // Validate source and destination are not the same
            if (sourceIndex == destinationIndex) {
                System.out.println("Source and destination cannot be the same!");
                continue;
            }

            // ✅ Valid input — break out of the loop and proceed
            break;
        }

        String source = stations.get(sourceIndex);
        String destination = stations.get(destinationIndex);

        System.out.println("Source: " + source + " → Destination: " + destination);

        return new String[]{source, destination}; // ← returns both as array
    }
}
