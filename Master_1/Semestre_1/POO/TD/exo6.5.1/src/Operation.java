public enum Operation {

    PLUS("+"){
        @Override
        public double eval(double x, double y) {
            return x + y;
        }
    },

	MOINS("-"){
        @Override
        public double eval(double x, double y){
            return x - y;
        }
    },

    MULT("*"){
        @Override
        public double eval(double x, double y){
            return x * y;
        }
    },

    DIV("/"){
        @Override
        public double eval(double x, double y) {
            if(y==0){
                throw new IllegalArgumentException("impossible : x / 0");
            }
            return x / y;
        }
    };

    private String symbole;

    private Operation(String psymbole){
        this.setSymbole(psymbole);
    }

    public abstract double eval (double x, double y) throws IllegalArgumentException;

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}
}
