package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
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
}
