package org.kholle.s2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class ConvoiTest {

    Wagon wagonVoitures;
    Wagon wagonCamions;
    Convoi convoiVehicules;
    Convoi convoiTestAjoutRec;

    @Before
    public void initialisation(){
        wagonVoitures = new Wagon("Voitures", 20);
        wagonCamions = new Wagon("Camions", 40);
        convoiVehicules = new Convoi("Vehicules");
        convoiTestAjoutRec = new Convoi("test");
        convoiVehicules.add(wagonVoitures);
        convoiVehicules.add(wagonCamions);
    }

    /**
     * test instanciation du convoi Vehicule avec les wagon Voitures et Camions
     */
    @Test
    public void testConvoiVehicules(){
        Convoi testVehicules = new Convoi("testVehicules");
        Wagon testVoiture = new Wagon("Voitures", 20);
        Wagon testCamions = new Wagon("Camions", 40);
        testVehicules.add(testVoiture);
        testVehicules.add(testCamions);
        if(assertArrayEquals(testVehicules.getListWagon(), convoiVehicules.getListWagon()))
            System.out.println("testConvoiVehicules passé\n");
        else
            System.out.println("testConvoiVehicules raté\n");
    }

    /**
     * test du calcul du poids d'un convoi
     */
    @Test
    public void testCalculPoids(){
        assertEquals(60, convoiVehicules.getPoids());
    }


    /**
     * test de l'impossibilité d'ajouter un convoi a lui même
     */
    @Test(expected = AlreadyHereException.class)
    public void testAjoutConvoi(){
        convoiVehicules.add(convoiVehicules);
    }

    /**
     * test de l'ajout d'un convoi comme descendant de lui même
     */
   /* @Test(expected = AlreadyHereException.class)
    public void testConvoitDescendant(){
        convoiTestAjoutRec.add(convoiVehicules);
        convoiVehicules.add(convoiTestAjoutRec);
    }*/

    private boolean assertArrayEquals(List<Element> listWagon, List<Element> listWagon1) {
        for (int i = 0; i < listWagon.size(); i++) {
            if(!(listWagon.get(i).getNom() == listWagon1.get(i).getNom()&& listWagon.get(i).getPoids()== listWagon1.get(i).getPoids()))
                return false;
        }
        return true;
    }
}
