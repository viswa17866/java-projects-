package PJ.atm;

public class main {
    public static void main (String[] args) {
        Atm atm = new Atm();
        if (atm.authenticate()) {
            atm.showMenu();
        }
        else {
            System.out.println("Access denied. Please try again.");
        }
    }
    
}
