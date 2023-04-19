package commands;

import filelogic.FileReader;
import filelogic.Scanners;
import models.MusicBandCollections;
import commands.AddCommand;
import java.util.*;


public class Invoker {
    private  static LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    private MusicBandCollections musicBandCollections;
    private Scanners scanners;
    private String inputFile;
    private FileReader fileReader;
    private static String[] split;
    ExecuteScript.Script script;
    public static String[] getSplit() {
        return split;
    }
    public static void setSplit(String[] split) {
        Invoker.split = split;
    }

    public Invoker(MusicBandCollections musicBandCollections, Scanners scanners, String inputFile, FileReader fileReader) {
        this.musicBandCollections = musicBandCollections;
        this.scanners = scanners;
        this.inputFile = inputFile;
        this.fileReader = fileReader;
        commands = new LinkedHashMap<>();
        this.script = new ExecuteScript.Script();

        this.putCommands();
    }

    public Invoker(MusicBandCollections musicBandCollections, Scanners scanners, FileReader fileReader, ExecuteScript.Script script) {
        this.musicBandCollections = musicBandCollections;
        this.scanners = scanners;
        this.fileReader = fileReader;
        commands = new LinkedHashMap<>();
        this.script = script;

        this.putCommands();
    }

    public void putCommands() {
        Help help = new Help(null);
        commands.put("help", help);
        commands.put("add", new AddCommand(musicBandCollections));
        commands.put("show", new Show(musicBandCollections));
        commands.put("info", new Info(musicBandCollections));
        commands.put("clear", new Clear(musicBandCollections));
        commands.put("update", new Update_id(musicBandCollections, scanners));
        commands.put("remove", new Remove(musicBandCollections));
        commands.put("save", new Save(musicBandCollections, inputFile));
        commands.put("execute", new ExecuteScript(musicBandCollections, fileReader, script));
        help = new Help(this);



    }

    public Collection<Command> getCommands() {
        return commands.values();

    }

    public static LinkedHashMap<String, Command> getCommandHashMap() {
        return commands;
    }

    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length); //проверка на размер больше 1

        if (commands.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = commands.get(words[0].toLowerCase(Locale.ROOT));
            command.getCommands(args);
            command.execute();

        } else {
            System.err.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
        }
    }
}
