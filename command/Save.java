package commands;
import models.MusicBandCollections;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class Save implements Command{
    private String inputFile;
    private String[] commandArguments;
    private MusicBandCollections musicBandCollections;
    public Save(MusicBandCollections musicBandCollections, String inputFile){
        this.inputFile=inputFile;
        this.musicBandCollections=musicBandCollections;
    }
    public String descr() {
        return "save-сохранить коллекцию в файл";
    }


    public void execute() throws IllegalArgumentException {
        musicBandCollections.save(inputFile);


    }
    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }
}
