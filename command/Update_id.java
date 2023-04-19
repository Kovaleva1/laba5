package commands;

import exception.IncorrectDataException;
import filelogic.Scanners;
import models.MusicBand;
import models.MusicBandCollections;
import models.MusicGenre;

import java.util.Scanner;

public class Update_id implements Command {
    private MusicBandCollections musicBandCollections;
    private Scanners scanners;
    private String[] commandArguments;

    public Update_id(MusicBandCollections musicBandCollections, Scanners scanners) {
        this.musicBandCollections = musicBandCollections;
        this.scanners = scanners;
    }

    private void updateName(Scanner scanner, MusicBand mb) {
        boolean i = true;
        while (i) {
            System.out.println("Введите имя ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                mb.setName(name);
                i = false;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }

    private void updateNumber(Scanner scanner, MusicBand mb) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый возраст дракона");
            String string = scanner.nextLine();
            if (string.matches("([-+]?\\d+)")) {
                int numberOfParticipants = Integer.parseInt(string);
                mb.setNumberOfParticipants(numberOfParticipants);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private void updateGenre(Scanner scanner, MusicBand mb) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый жанр (Цифру или полное название) 1 - PROGRESSIVE_ROCK, 2 - RAP, 3 - SOUL, 4-POP, 5-PHONK");
            String genre = scanner.nextLine();
            if (genre.equals("1") || genre.equals("2") || genre.equals("3") || genre.equals("4") || genre.equals("5") || genre.equals("PROGRESSIVE_ROCK") || genre.equals("RAP") || genre.equals("SOUL") || genre.equals("POP") || genre.equals("PHONK")) {
                switch (genre) {
                    case "1", "PROGRESSIVE_ROCK" -> mb.setGenre(MusicGenre.PROGRESSIVE_ROCK);
                    case "2", "RAP" -> mb.setGenre(MusicGenre.RAP);
                    case "3", "SOUL" -> mb.setGenre(MusicGenre.SOUL);
                    case "4", "POP" -> mb.setGenre(MusicGenre.POP);
                    case "5", "PHONK" -> mb.setGenre(MusicGenre.PHONK);
                }
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private void updateSales(Scanner scanner, MusicBand mb) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое количество зп");
            String string = scanner.nextLine();
            try {
                mb.getLabel().setSales(Integer.parseInt(string));
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private void updateCoordinates(Scanner scanner, MusicBand mb) {
        mb.getCoordinates().setX(getNewXCoordinate(scanner));
        mb.getCoordinates().setY(getNewYCoordinate(scanner));
    }

    private int getNewXCoordinate(Scanner scanner) {
        long x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату X музыкальной групыы");
            String xString = scanner.nextLine();
            try {
                if (xString.matches("([-+]?\\d+)")) {
                    x = Integer.parseInt(xString);
                    if (x > 272) {
                        throw new IncorrectDataException("максимальное значение х =272");
                    } else {
                        i = false;
                    }
                } else {
                    System.out.println("Неверный тип данных");
                }
            } catch (IncorrectDataException incorrectDataException) {
                System.out.println(incorrectDataException.getMessage());
            }
        }
        return (int) x;
    }

    private double getNewYCoordinate(Scanner scanner) {
        double y = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y музыкальной группы");
            String yString = scanner.nextLine();
            try {
                y = Double.parseDouble(yString);
                i = false;
            } catch (IncorrectDataException incorrectDataException) {
                System.out.println("Неверный тип данных");
            }
        }
        return y;
    }

    private String comands(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                    Выберите параметр дракона, который хотите изменить:
                    Имя - введите  1
                    Номер выступления - введите 2
                    Жанр - введитите 3
                    Координаты - введите 4
                    """);
            s = scanner.nextLine();
            i = false;
        }
        return s;
    }

    private void Updaters(String s, Scanner scanner, MusicBand mb) {
        switch (s) {
            case "1" -> updateName(scanner, mb);
            case "2" -> updateNumber(scanner, mb);
            case "3" -> updateGenre(scanner, mb);
            case "4" -> updateCoordinates(scanner, mb);
        }
        System.out.println("Параметр успешно изменены");
    }

    private void updateMb(long id) {
        boolean musicBand = false;
        for (MusicBand mb : MusicBandCollections.getMusicband()) {
            if (mb.getId() == id) {
                musicBand = true;
                Scanner scanner = new Scanner(System.in);
                String s = comands(scanner);
                if (!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4")) {
                    System.out.println("Неверный параметр");
                } else {
                    Updaters(s, scanner, mb);
                }
            }
            if (!musicBand) System.out.println("Такой группы не существует");
        }
    }

    public String descr() {
        return "update id-обновить значение элемента коллекции, id которого равен заданном";
    }


    public void execute() {
        //try {
        // if (Invoker.getSplit().length != 2) {
        //      throw new Unknowncommand();
        // }
        long id = Long.parseLong(Invoker.getSplit()[1]);
        if (!MusicBandCollections.getMusicband().isEmpty()) {
            updateMb(id);
        } else {
            System.out.println("Такой группы не существует");
        }
        // } catch (Unknowncommand e) {
        // System.out.print(e.getMessage());
    }

    public void setMusicBandCollections(MusicBandCollections musicBandCollections) {
        this.musicBandCollections = musicBandCollections;
    }

    public void getCommands(String[] arguments) {
        this.commandArguments = commandArguments;


    }
}

