package org.kholle.s2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WagonTest {

    //variables utilisées pour les tests
    Wagon wagonBar;


    //initialisation des variables
    @Before
    public void initialisation(){
        wagonBar = new Wagon("bar", 10);
    }

    /**
     * test instanciation d'un wagon bar de poids 10
     */
    @Test
    public void testWagonBar(){
        assertEquals(10, wagonBar.getPoids());
    }
}
