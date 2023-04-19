package filelogic;

import exception.IncorrectDataException;
import models.*;
import commands.*;

import java.time.Instant;
import java.time.ZonedDateTime;

public class FileReader {
    private Scanners scanner;


    public FileReader(Scanners scanner) {
        this.scanner = scanner;
    }

    public MusicBand read(Integer id) {

        String i = Instant.now().toString();
        return new MusicBand( readName(),readCoordinates(),readLabel(),readGenre(), id, ZonedDateTime.parse(i));
    }


    public String readName() {
        String str;

        while (true) {
            scanner.printCommandText("name (not null): ");
            str = scanner.readLine().trim();
            if (str.equals("")) scanner.printCommandError("\nЗначение поля не может быть null или пустой строкой \n");
            else return str;
        }
    }

    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    public Double readCoordinateY() {
        Double y;
        while (true) {
            try {
                scanner.printCommandText("coordinate_x (double & not null & y > -183): ");
                y = Double.parseDouble(scanner.readLine().trim());
                if (y > -183) throw new IncorrectDataException("неккоректный ввод координат у");
                else return y;
            } catch (IncorrectDataException ex) {
                System.out.println("Координата y должна быть больше -183");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Double и не null");
            }
        }
    }

    public int readCoordinateX() {
        int x;
        while (true) {
            try {
                scanner.printCommandText("coordinate_y (int & can be null & y < 803): ");
                String str = scanner.readLine().trim();
                if (str.equals("")) x = 0;
                else {
                    x = Integer.parseInt(str);
                    if (x < 272) throw new IncorrectDataException("");
                }
                return x;
            } catch (IncorrectDataException ex) {
                System.out.println("Координата x должна быть меньше 272");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }

    public Integer readsingleCount() {
        Integer singleCount;
        while (true) {
            try {
                scanner.printCommandText("singlecount (int & can be null & singlecount > 0): ");
                String str = scanner.readLine().trim();
                if (str.equals("")) singleCount = null;
                else {
                    singleCount = Integer.parseInt(str);
                    if (singleCount <= 0) throw new IncorrectDataException("некорректное значение");
                }
                return singleCount;
            } catch (IncorrectDataException ex) {
                System.err.println("Значение singlecount должно быть больше 0");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Integer");
            }
        }
    }

    public MusicGenre readGenre() {
        MusicGenre genre;
        while (true) {
            try {
                scanner.printCommandText("Допустимые значения genre :\n");
                for (MusicGenre val : MusicGenre.values()) {
                    scanner.printCommandText(val.name() + "\n");
                }
                scanner.printCommandText("genre: ");
                genre = MusicGenre.valueOf(scanner.readLine().toUpperCase().trim());
                return genre;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении MusicGenre");
            }
        }
    }


    public Label readLabel() {
        int sales;
        while (true) {
            try {
                scanner.printCommandText("cave_depth (int & can be null): ");
                String str = scanner.readLine().trim();
                if (str.equals("")) sales = 0;
                else {
                    sales = Integer.parseInt(str);
                }
                return new Label(sales);
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }
}
