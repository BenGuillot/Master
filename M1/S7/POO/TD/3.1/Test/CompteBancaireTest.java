import org.junit.Test;
import static org.junit.Assert.*;

public class CompteBancaireTest {

    @Test
    public void testNotNull() {
        CompteBancaire cpte = new CompteBancaire(1);
        assertNotNull(cpte);
    }

    @Test
    public void testSolde() {
        CompteBancaire cpte = new CompteBancaire(3);
        int solde1 = cpte.getSolde();
        assertEquals(3,solde1);
    }

    @Test
    public void testSuperieurSolde() {
        CompteBancaire cpte = new CompteBancaire(-3);
        System.out.print("le compte sera a découvert\n");
    }

    @Test
    public void testCredit() {
        CompteBancaire cpte = new CompteBancaire(3);
        cpte.crediteSolde(1);
        assertEquals(4, cpte.getSolde());
    }

    @Test
    public void testCreditNegatif() {
        CompteBancaire cpte = new CompteBancaire(3);
        cpte.crediteSolde(-1);
        System.out.print("le montant est négatif\n");
    }

    @Test
    public void testDebit() {
        CompteBancaire cpte = new CompteBancaire(3);
        cpte.debiteSolde(1);
        assertEquals(2, cpte.getSolde());
    }

    @Test
    public void testDebitNegatif() {
        CompteBancaire cpte = new CompteBancaire(3);
        cpte.debiteSolde(-1);
        System.out.print("Le montant est négatif\n");
    }

    @Test
    public void testDebitSuperieurSolde() {
        CompteBancaire cpte = new CompteBancaire(3);
        cpte.debiteSolde(5);
        System.out.print("le compte sera a découvert\n");
    }

    @Test
    public void testVirement() {
        CompteBancaire cpte1 = new CompteBancaire(300);
        CompteBancaire cpte2 = new CompteBancaire(800);
        cpte1.virement(100, cpte2);
        cpte2.virement(300, cpte1);
        assertEquals(500, cpte1.getSolde());
        assertEquals(600, cpte2.getSolde());
    }

    @Test
    public void testVirementNegatif(){
        CompteBancaire cpte1 = new CompteBancaire(300);
        CompteBancaire cpte2 = new CompteBancaire(800);
        cpte1.virement(-100, cpte2);
        System.out.print("Le montant est négatif\n");
    }

    @Test
    public void testVirementSuperieurSolde(){
        CompteBancaire cpte1 = new CompteBancaire(300);
        CompteBancaire cpte2 = new CompteBancaire(800);
        cpte1.virement(1000, cpte2);
        System.out.print("Le compte sera a découvert\n");
    }
}