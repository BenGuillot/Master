public class CompteBancaire {

    private int solde;

    public CompteBancaire(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return solde;
    }

    public void crediteSolde(int montant) {
        montant = Math.abs(montant);
        this.solde += montant;
    }

    public void debiteSolde(int montant) {
        montant = Math.abs(montant);
        this.solde -= montant;
    }

    public void virement(int montant, CompteBancaire receveur) {
        receveur.crediteSolde(montant);
        this.debiteSolde(montant);
    }
}