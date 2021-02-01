package memorama;

import javax.swing.ImageIcon;

public class Activa extends Thread{    
    private Carta cartaUno, cartaDos;
    private Tablero t;
    public Activa(Carta cartaUno, Carta cartaDos, Tablero t) {        
        this.cartaUno=cartaUno;
        this.cartaDos=cartaDos;     
        this.t=t;
    }
    public void run(){  
        try{
            t.flag=false;
            Thread.sleep(800);            
            t.s=new Sonido("error");   
            t.s.start();
            cartaUno.setIcon(new ImageIcon("src\\imagenes\\cartavolteada.jpg"));
            cartaDos.setIcon(new ImageIcon("src\\imagenes\\cartavolteada.jpg"));            
        }catch(Exception e){
            System.out.println("Error en hilo: "+e.getMessage());
        }
        t.flag=true;
    }
}
