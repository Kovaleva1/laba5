package filelogic;

import exception.IncorrectDataException;
import models.Coordinates;
import models.Label;
import models.MusicBand;
import models.MusicGenre;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Xml {

    public MusicBand[] parseToCollection(InputSource text) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlHandler handler = new XmlHandler();
        try {
            parser.parse(text, handler);
        } catch (SAXException ignored) {
        }
        MusicBand[] mbArr = new MusicBand[handler.musicBands.size()];
        return handler.musicBands.toArray(mbArr);
    }


    private static class XmlHandler extends DefaultHandler {

        private ArrayList<MusicBand> musicBands = new ArrayList<>();
        private String name;
        private Integer x = null;
        private Double y = null;
        private ZonedDateTime creationDate = null;
        private MusicGenre genre = null;
        private Integer sales = null;
        private Integer numberOfParticipants = null;
        private Integer singleCount;
        private Label label;
        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws ClassCastException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();
            try {
                if (!information.isEmpty()) {
                    if (lastElementName.equals("name"))
                        name = information;
                    else if (lastElementName.equals("coordinate_x"))
                        x = Integer.parseInt(information);
                    else if (lastElementName.equals("coordinate_y"))
                        y = Double.parseDouble(information);
                    else if (lastElementName.equals("sales"))
                        sales = Integer.parseInt(information);
                    else if (lastElementName.equals("genre"))
                        genre = MusicGenre.valueOf(information);
                    else if (lastElementName.equals("creation_date"))
                        creationDate = ZonedDateTime.parse(information);
                }
            } catch (IncorrectDataException ex) {
                System.err.println("Указанной константы перечисляемого типа не существует, либо невозможно преобразование типов");
            }
        }


        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("musicband")) {
                if ((name != null && !name.isEmpty()) && (x != null && x < 272) && (y == null || y > -183) && (singleCount == null || singleCount > 0)
                        && (genre != null) && (sales != null) && (numberOfParticipants != null)) {

                    Coordinates coordinates = new Coordinates(x);
                    double douY;
                    if (y != null) {
                        douY = y;
                        coordinates.setY(douY);
                    }

                    if (creationDate == null) {
                        String i = Instant.now().toString();
                        creationDate = ZonedDateTime.parse(i);
                    }

                    Label label = new Label(sales);
                    if (sales != null) label.setSales(sales);

                    MusicBand musicBand = new MusicBand(name, coordinates, label, genre, numberOfParticipants, creationDate);

                    if (singleCount != null) {
                        musicBand.setSingleCount(singleCount);
                    }

                    musicBands.add(musicBand);


                } else System.err.println("Указаны не все параметры, либо параметры не принадлежат допустимой ОДЗ");

                name = null;
                x = null;
                y = null;
                creationDate = null;
                singleCount = null;
                genre = null;
                sales = null;
                numberOfParticipants = null;
            }
        }
    }

    public String parseToXml(MusicBand[] musicBands) {

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version = \"1.0\"?>\n");
        sb.append("<treemap>\n");
        for (MusicBand musicBand : musicBands) {
            sb.append("\t<musicband>\n");
            sb.append("\t\t<name>").append(musicBand.getName()).append("</name>");
            sb.append("\n\t\t<coordinate_x>").append(musicBand.getCoordinates().getX()).append("</coordinate_x>");
            try {
                Double str = musicBand.getCoordinates().getY();
                sb.append("\n\t\t<coordinate_y>").append(str).append("</coordinate_y>");
            } catch (NullPointerException ignored) {
            }
            sb.append("\n\t\t<creation_date>").append(musicBand.getCreationDate()).append("</creation_date>");
            try {
                String str = musicBand.getSingleCount().toString();
                sb.append("\n\t\t<singlecount>").append(str).append("</singlecount>");
            } catch (NullPointerException ignored) {
            }
            sb.append("\n\t\t<genre>").append(musicBand.getGenre()).append("</genre>");
            try {
                String str = musicBand.getLabel().getSales().toString();
                sb.append("\n\t\t<sales>").append(str).append("</sales>");
            } catch (NullPointerException ignored) {
            }
            sb.append("\n\t</musicband>\n");
        }
        sb.append("</treemap>\n");
        return sb.toString();
    }
}
