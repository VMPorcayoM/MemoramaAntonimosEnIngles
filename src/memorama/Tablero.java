package memorama;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Tablero extends JFrame implements MouseListener{
    private Inicio origen;
    private JButton back;
    private int aciertos, intentos;
    private Carta primerVolteada;
    private JLabel fondo, jlbAciertos, jlbIntentos, jlbReloj;
    private int volteadas;
    boolean flag;
    private Timer temporizador;
    private int segundero=0;
    Sonido s;
    private ArrayList<Carta> arregloCartas=new ArrayList<Carta>();    
    public Tablero() {   
        temporizador=new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbReloj.setText("Time: "+String.valueOf(segundero));
                segundero++;
            }
        });          
        volteadas=0;
        flag=true;
        jlbReloj=new JLabel();
        jlbReloj.setText("Time: "+segundero);
        jlbReloj.setBounds(280, 15, 140, 40);
        jlbReloj.setFont(new Font("Serif", Font.PLAIN, 30));
        jlbReloj.setForeground(new Color(255,255,255));        
        this.add(jlbReloj);
        
        back=new JButton();        
        back.setBounds(10, 10,70,25);
        back.setText("BACK");
        back.addMouseListener(this);
        this.add(back);
        
        jlbIntentos=new JLabel();
        jlbIntentos.setBounds(110, 10, 125, 35);
        jlbIntentos.setFont(new Font("Serif", Font.PLAIN, 26));
        jlbIntentos.setForeground(new Color(255,255,255));
        jlbIntentos.setText("Intentos: 0");
        this.add(jlbIntentos);
        
        jlbAciertos=new JLabel();
        jlbAciertos.setBounds(110, 40, 125, 35);
        jlbAciertos.setFont(new Font("Serif", Font.PLAIN, 26));
        jlbAciertos.setForeground(new Color(255,255,255));
        jlbAciertos.setText("Aciertos: 0");
        this.add(jlbAciertos);
        Carta carta;
        for (int i = 0; i < 16; i++) {
            carta=new Carta(i);                        
            if(i==0)
                carta.setNoPar(1);
            else if(i==1)
                carta.setNoPar(0);
            else if(i%2==0)
                carta.setNoPar(i+1);
            else if(i%2!=0)                      
                carta.setNoPar(i-1);
            
            carta.addMouseListener(this);
            arregloCartas.add(carta);            
        }
        
        int x=0,y=0;
        Collections.shuffle(arregloCartas, new Random());
        for (int i = 0; i < arregloCartas.size(); i++) {
            Carta c=(Carta)arregloCartas.get(i);
            c.setIcon(new ImageIcon("src\\imagenes\\cartavolteada.jpg") );
            if(x==4){
                x=0;
                y++;
            }
            c.setBounds(50+(x*140), 100+(y*140), 140, 140);            
            x++;
            this.add(c);            
        }        
        
        fondo=new JLabel();
        fondo.setBounds(0,0, 660, 710);
        fondo.setIcon(new ImageIcon("src\\imagenes\\fondo.png"));
        this.add(fondo);
        this.setSize(660, 710);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);             
        this.setTitle("Memorama Antonyms");         
        temporizador.start();
    }    

    public Inicio getOrigen(){
        return origen;
    }

    public void setOrigen(Inicio origen) {
        this.origen = origen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent()==back){
            s=new Sonido("boton");   
            s.start();
            origen.setVisible(true);
            origen.getBtnIniciar().setText("Volver a jugar");
            this.dispose();
            return;
        }
        if(flag==true){
            volteaCarta((Carta)e.getComponent());            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    private void volteaCarta(Carta c){        
        for (int i = 0; i < arregloCartas.size(); i++) {
            if(arregloCartas.get(i)==c){
                if(volteadas==1){                                        
                    arregloCartas.get(i).setIcon(new ImageIcon("src\\imagenes\\"+arregloCartas.get(i).getNoImagen()+".png"));
                    if(primerVolteada.getNoImagen()==arregloCartas.get(i).getNoPar()){
                        aciertos++;
                        jlbAciertos.setText("Aciertos: "+aciertos);
                        volteadas=0;
                        if(aciertos>7){
                            temporizador.stop();
                            ganaste();
                        }
                        s=new Sonido("bien");   
                        s.start();
                        return;
                    }else{
                        Activa hilo=new Activa(primerVolteada, arregloCartas.get(i), this);
                        hilo.start();
                        volteadas=0;
                        intentos++;
                        jlbIntentos.setText("Intentos: "+intentos);
                        
                        return;
                    }
                }
                arregloCartas.get(i).setIcon(new ImageIcon("src\\imagenes\\"+arregloCartas.get(i).getNoImagen()+".png"));
                primerVolteada=arregloCartas.get(i);
                volteadas++;   
                return;
            }
        }        
    }
    public void ganaste(){
        s=new Sonido("ganar");   
        s.start();
        JOptionPane.showMessageDialog(null, "¡¡Congratulations you won in "+(segundero-1)+" seconds!!", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
        origen.setVisible(true);
        origen.getBtnIniciar().setText("Volver a jugar");
        this.dispose();
    }
    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int suma) {
        this.aciertos += suma;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int suma) {
        this.intentos += suma;
    }
    
    public ArrayList<Carta> getArregloCartas() {
        return arregloCartas;
    }
    
}
