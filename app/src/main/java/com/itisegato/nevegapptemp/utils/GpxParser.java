package com.itisegato.nevegapptemp.utils;

import android.content.Context;
import android.location.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.itisegato.nevegapptemp.R;

/**
 * Created by Davide on 14/03/2018.
 * Parser per il file Gpx del percorso
 */

public class GpxParser {

    public static List<Location> decodeGPX(String tag, Context context){
        List<Location> list = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream streamGPX = context.getResources().openRawResource(R.raw.percorso);
            Document document = documentBuilder.parse(streamGPX);
            Element elementRoot = document.getDocumentElement();
            NodeList nodelist_trkpt = elementRoot.getElementsByTagName(tag);

            for(int i = 0; i < nodelist_trkpt.getLength(); i++){

                Node node = nodelist_trkpt.item(i);
                NamedNodeMap attributes = node.getAttributes();

                String newLatitude = attributes.getNamedItem("lat").getTextContent();
                Double newLatitude_double = Double.parseDouble(newLatitude);

                String newLongitude = attributes.getNamedItem("lon").getTextContent();
                Double newLongitude_double = Double.parseDouble(newLongitude);

                String newLocationName = newLatitude + ":" + newLongitude;
                Location newLocation = new Location(newLocationName);
                newLocation.setLatitude(newLatitude_double);
                newLocation.setLongitude(newLongitude_double);
                list.add(newLocation);
            }

        } catch (ParserConfigurationException
                | SAXException
                | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
