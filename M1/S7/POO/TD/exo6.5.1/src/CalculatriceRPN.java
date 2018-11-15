public class CalculatriceRPN {

    public MoteurRPN MR;
    public SaisieRPN SR;

    public void CalculatriceRPN(){
        this.MR = new MoteurRPN();
        this.SR = new SaisieRPN(MR);
    }

}
