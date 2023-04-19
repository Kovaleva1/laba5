package commands;

import models.MusicBand;
import models.MusicBandCollections;

import java.util.LinkedHashMap;


public class Clear implements Command {
private MusicBandCollections musicBandCollections;
private String[] commandArguments;
public Clear(MusicBandCollections musicBandCollections){
    this.musicBandCollections=musicBandCollections;
}


    public String descr() {
        return "clear—очистить коллекцию";
    }

    public void execute() throws IllegalArgumentException {
       MusicBandCollections.getMusicband().clear();
        System.out.print("коллекция очищена");

    }
    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }
}


