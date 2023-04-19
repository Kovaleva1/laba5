package commands;

import exception.Unknowncommand;
import filelogic.FileReader;
import filelogic.Scanners;
import models.MusicBandCollections;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScript implements Command {
    private String[] commandArguments;
    private MusicBandCollections musicBandCollections;
    private Scanners scanners;
    private FileReader fileReader;
    private String scriptPath;
    private Script script;
    public ExecuteScript(MusicBandCollections musicBandCollections, FileReader fileReader, Script script) {
        this.musicBandCollections = musicBandCollections;
        this.fileReader = fileReader;
        this.script = script;
    }

    public void execute() {
        try {
            if (commandArguments.length == 1) {
                scriptPath = commandArguments[0];
                if (script.scriptPaths.contains(scriptPath)) throw new Unknowncommand();
                else script.putScript(scriptPath);
            } else throw new IllegalArgumentException();

            File ioFile = new File(scriptPath);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner= new Scanner(inputStreamReader);
            scanners= new Scanners(scanner);
            Invoker invoker = new Invoker(musicBandCollections, scanners, fileReader, script);

            while (scanner.hasNext()) {
                invoker.execute(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл скрипта не найден");
        } catch (NullPointerException ex) {
            System.err.println("Не выбран файл, из которого читать скрипт");
        } catch (IOException ex) {
            System.err.println("Доступ к файлу невозможен" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("скрипт не передан в качестве аргумента команды, либо кол-во агрументов больше 1");
        } catch (Unknowncommand ex) {
            System.err.println("Скрипт " + scriptPath + " уже существует (Рекурсивный вызов)");
        }
        script.removeScript(scriptPath);

    }
    public void getCommands(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }


    public String descr() {
        return " execute skript-выполняет команды, описанные в скрипте";
    }

    static class Script {
        private ArrayList<String> scriptPaths = new ArrayList<String>();

        public void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }
        public void removeScript(String scriptPath) {
            scriptPaths.remove(scriptPath);
        }
    }

}
