package commands;

import models.MusicBand;
import models.MusicBandCollections;

public class Remove implements Command {
    private MusicBandCollections musicBandCollections;
    private String[] commandArguments;

    public Remove(MusicBandCollections musicBandCollections){
        this.musicBandCollections=musicBandCollections;
    }

    public String descr() {
        return "remuve-удалить элемент из коллекции по его id";
    }

    public void removeId(int id) {

        for (MusicBand musicBand : MusicBandCollections.getMusicband()) {
            if (musicBand.getId() == id) {
                MusicBandCollections.getMusicband().remove(musicBand);
                System.out.print("элемент удален!");
            }
        }


    }

    public void execute() {

        int id = Integer.parseInt(Invoker.getSplit()[1]);
        if (!MusicBandCollections.getMusicband().isEmpty()) {
            removeId(id);
        } else {
            System.out.print("Коллекция пуста");
        }
    }

    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;

    }
}
