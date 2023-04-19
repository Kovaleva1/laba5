package commands;

import exception.Unknowncommand;
import models.MusicBand;
import models.MusicBandCollections;

import static models.MusicBandCollections.musicband;

public class Show implements Command {
    private MusicBandCollections musicBandCollections;
private  String[] commandArguments;

    public Show(MusicBandCollections musicBandCollections) {
        this.musicBandCollections = musicBandCollections;
    }

    public String descr() {
        return "show-вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    public void execute() {
        if (MusicBandCollections.getMusicband().isEmpty()) {

            System.out.println("Коллекция пуста.");
        } else {

            for (MusicBand musicBand : MusicBandCollections.getMusicband()) {
                System.out.println(musicBand.toString());
            }
        }

    }
    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }
}