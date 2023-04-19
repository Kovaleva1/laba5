package main;

import commands.Invoker;
import filelogic.FileManager;
import filelogic.FileReader;
import filelogic.Scanners;
import filelogic.Xml;
import models.MusicBand;
import models.MusicBandCollections;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;

public class Application {
    MusicBandCollections musicBandCollections;
    FileManager fileManager;

    Xml xml;
    Scanners scan;

    Invoker invoker;
     FileReader fileReader;

    public void start(String inputFile) {

        musicBandCollections = new MusicBandCollections();
        fileManager = new FileManager();
        xml = new Xml();
        scan = new Scanners();

        fileReader = new FileReader(scan);

        this.invoker = new Invoker(musicBandCollections, scan, inputFile, fileReader);

        try {

            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            String file = fileManager.readFromFile(inputFile);

            MusicBand[] musicBands = xml.parseToCollection(new InputSource(new StringReader(file)));
            for (MusicBand musicBand : musicBands) {
                musicBandCollections.insert(Math.toIntExact(musicBand.getId()), musicBand);
            }
            scan.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        }
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    public void cycle() {
        scan.printCommandText("Программа была запущена.\n");
        while (true) {
            scan.printCommandText("\nВведите название команды:\n");
            scan.printPreamble();
            String line = scan.readLine();
            invoker.execute(line);
        }
    }
}