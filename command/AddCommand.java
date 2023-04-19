package commands;

import models.MusicBand;
import models.MusicBandCollections;

public class AddCommand implements Command {
    private MusicBandCollections musicBandCollections;
    private String[] commandArguments;
    public MusicBand musicBand;
    public AddCommand(MusicBandCollections musicBandCollections){
        this.musicBandCollections=musicBandCollections;
    }

    public String descr() {
        return "add — добавить новый элемент с заданным ключом";
    }

    public void execute() {
        musicBandCollections.add(musicBand);

    }
    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }

    public void setMusicBand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}