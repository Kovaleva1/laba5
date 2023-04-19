package commands;

import models.MusicBandCollections;

public class Info implements Command {
    private String[] commandArguments;
    private MusicBandCollections musicBandCollections;

    public Info(MusicBandCollections musicBandCollections) {
        this.musicBandCollections = musicBandCollections;
    }


    public String descr() {
        return "info — вывести в стандартный поток вывода информацию коллекции";
    }

    public void execute() throws IllegalArgumentException {
        System.out.print("Тип коллекции:" + MusicBandCollections.musicband.getClass().getName() +
                "\n" + "размер коллекции: " + MusicBandCollections.musicband.size() +
                "\n" + "дата иницилизации:" + MusicBandCollections.dateOfInitialization);


    } public void getCommands(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

}
