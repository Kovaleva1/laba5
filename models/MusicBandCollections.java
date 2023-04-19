package models;

import filelogic.FileManager;
import filelogic.Xml;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Scanner;

public class MusicBandCollections {
    public MusicBandCollections musicBandCollections;

    public static final LinkedHashSet<MusicBand> musicband = new LinkedHashSet<>();
    LinkedHashMap<String, MusicBand> linkedHashMap;
    ZonedDateTime collectionInitialization;
    public static final Date dateOfInitialization = new Date();
    List<MusicBand> MusicBandCatalogue = new ArrayList<>();



    public void add(MusicBand musicBand) {
        MusicBandCatalogue.add(musicBand);
    }

    public static LinkedHashSet<MusicBand> getMusicband() {
        return musicband;
    }

    public static MusicBand getElementID(Long id) {
        for (MusicBand musicBand : musicband) {
            if (musicBand.getId() == id) {
                return musicBand;
            }
        }
        return null;
    }
    public MusicBandCollections() {
        this.linkedHashMap = new LinkedHashMap<>();
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }
    public void insert(Integer id, MusicBand musicBand) {
        if (linkedHashMap.get(id) == null) {
            linkedHashMap.put(String.valueOf(id), musicBand);
        } else System.out.println("Элемент с данным ключом уже существует");
    }
    public void save(String filePath) {
        Xml xmlParser = new Xml();
        FileManager fileManager = new FileManager();

        MusicBand[] musicBands = new MusicBand[linkedHashMap.size()];
        musicBands = linkedHashMap.values().toArray(musicBands);
        String str = xmlParser.parseToXml(musicBands);
        fileManager.writeToFile(str, filePath);
    }

}


