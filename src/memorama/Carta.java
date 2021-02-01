package memorama;
import javax.swing.JButton;
public class Carta extends JButton{
    private int noImagen;
    private int noPar;
    public Carta(int noImagen){
        this.noImagen=noImagen;
    }

    public int getNoImagen() {
        return noImagen;
    }

    public int getNoPar() {
        return noPar;
    }

    public void setNoPar(int noPar) {
        this.noPar = noPar;
    }
    
}
