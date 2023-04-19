package models;
import exception.IncorrectDataException;

import java.time.ZonedDateTime;
import java.util.Objects;


import java.util.Date;
public class MusicBand implements Comparable<MusicBand> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private Integer numberOfParticipants;
    private Integer singleCount;
    private MusicGenre genre;
    private Label label;

    public MusicBand(long id, String name, Coordinates coordinates, Date creationDate, Integer numberOfParticipants, int singleCount, MusicGenre musicGenre)
            throws IncorrectDataException {
        if (creationDate == null) throw new IncorrectDataException("Неккоректная дата создания объекта MusicBand");
        if (id < 0) throw new IncorrectDataException("Неккоректный id объекта MusicBand");
        this.id = id;
        this.creationDate = creationDate;
    }

    public MusicBand(String name, Coordinates coordinates, Label label, MusicGenre genre, Integer numberOfParticipants, ZonedDateTime singleCount)
            throws IncorrectDataException {
        if (name == null || this.numberOfParticipants == null)
            throw new IncorrectDataException("Не хватает инициализированных полей");
        if (this.singleCount < 0 || name.isEmpty()) throw new IncorrectDataException("Некорректные поля объекта MusicBand");
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.genre= this.genre;
        this.singleCount= this.singleCount;
        this.label= this.label;

    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Integer getSingleCount() {
        return singleCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Label getLabel() {
        return label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setSingleCount(Integer singleCount) {
        this.singleCount = singleCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setLabel(Label label) {
        this.label = label;
    }


    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return numberOfParticipants == musicBand.numberOfParticipants && Objects.equals(id, musicBand.id) && Objects.equals(name, musicBand.name) && Objects.equals(coordinates, musicBand.coordinates) && Objects.equals(creationDate, musicBand.creationDate) && Objects.equals(singleCount, musicBand.singleCount) && genre == musicBand.genre && Objects.equals(label, musicBand.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, numberOfParticipants, singleCount, genre, label);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singleCount=" + singleCount +
                ", genre=" + genre +
                ", label=" + label +
                '}';
    }

    @Override
    public int compareTo(MusicBand o) {
        return 0;
    }
}
