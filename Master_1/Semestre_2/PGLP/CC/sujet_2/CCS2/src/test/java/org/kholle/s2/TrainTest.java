package org.kholle.s2;


import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class TrainTest {
    Train train;
    Wagon wagonBar;
    Wagon wagonVoitures;
    Wagon wagonCamions;
    Convoi convoiVehicules;

    @Before
    public void initialisation(){
        wagonBar = new Wagon("bar", 10);
        wagonVoitures = new Wagon("Voitures", 20);
        wagonCamions = new Wagon("Camions", 40);
        convoiVehicules = new Convoi("Vehicules");
        convoiVehicules.add(wagonVoitures);
        convoiVehicules.add(wagonCamions);
        train = new Train.Builder("paris1", "Paris").addElement(wagonBar).build();
    }

    /**
     * test l'instanciation d'un train paris1 pour paris avec un wagon bar de poids 10
     */
    @Test
    public void testTrain(){
        Wagon wagontest = new Wagon("bar", 10);
        List<Element> listTest = train.getListWagonConvoi();
        Wagon wagonRef = (Wagon) listTest.get(0);
        assertEquals(wagontest.getNom(), wagonRef.getNom());
        assertEquals(1, train.getListWagonConvoi().size());
        assertEquals("paris1", train.getNom());
        assertEquals("Paris", train.getDest());
    }

    /**
     * test le calcul du poids du train
     */
    public void testPoidsTrain(){
        train.add(convoiVehicules);
        assertEquals(70,train.getPoids());
    }
}
