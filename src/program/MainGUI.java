/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package program;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Thread.yield;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
/**
 *
 * @author SzabóRoland(SZF_2023
 */
public class MainGUI extends javax.swing.JFrame implements EI.charachterListener, EI.loadingListener{

    /**
     * Creates new form MainGUI
     */
    int ehe = 0;
    public MainGUI() {
        JOptionPane.showMessageDialog(null, "2546-ot írunk. Most alakult meg\n" +
                                                        "az Időrégészeti társaság, mely-\n" +
                                                        "nek egyik ügynöke vagy. Egy gaz-\n" +
                                                        "dag gyűjtő megrendelésére visz-\n" +
                                                        "sza kell menned a középkorba, és\n" +
                                                        "meg kell szerezned a mesebeli\n" +
                                                        "griffmadár egyik tojását. Utadon\n" +
                                                        "nem segít a modern technika,\n" +
                                                        "mindent egyedül kell csinálnod!\n\n" +
                                                        "Légy nagyon körültekintő, mindent\n" +
                                                        "vizsgálj meg!");
        Loader ld = new Loader();
        initComponents();
        initLoad(ld);
        Idoregesz.addListener(this);
        Idoregesz.Restart(true);

        Pr_eletero.setMaximum(Idoregesz.getMaxEletero());
        this.getContentPane().setBackground( Color.ORANGE );
        La_output.setOpaque(true);
        La_output.setBackground(Color.CYAN);
        
    }
    
    private void initLoad(Loader ld){
       ld.addListener(this);
       ld.setBounds(10, 10, this.getWidth(), this.getHeight());
       ld.setVisible(true);
       ld.repaint();
       ld.setVisible(true);
       this.add(ld);
       //ld.show();
       this.setComponentZOrder(ld, 0);
       La_output.setVisible(false);
       Bt_send.setVisible(false);
       Tf_input.setVisible(false);
       //this.setComponentZOrder(Tf_input, 1);
       ld.play();
    }
    
    @Override
    public void charachterUpdate(){
        Pr_eletero.setValue(Idoregesz.getEletero());
        La_eletero.setText(String.valueOf(Idoregesz.getEletero()));
        La_irany.setText(getUtvonalakToTextForJLabel(Idoregesz.getIranyok()));
        La_appliedCommands.setText(getFeltetelekToTextForJLabel(Idoregesz.getEgyebIranyok()));
        La_hiba.setText(Idoregesz.getHiba());
        La_talal.setText(getListToTextForJLabel(Idoregesz.getTalal()));
        La_inventory.setText(getListToTextForJLabel(Idoregesz.getEszkoztarGUI()));
        
        //Kép
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        //System.out.println(s);
        File f = new File(s + "\\" + Idoregesz.getKep());
        //System.out.println(f.getPath());
        if(f.exists()){
            
            
            try {
                BufferedImage wPic = ImageIO.read(f);
                Image img = wPic.getScaledInstance(La_kep.getWidth(), La_kep.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon ic = new ImageIcon(img);
                La_kep.setBounds(0, 0, this.getWidth(), this.getHeight());
                La_kep.setIcon(ic);
                
               
            } catch (Exception e) {
                
            }
        }
        
        La_output.setText((
            "<html>"
                + "<head>"
                    + "<meta charset=\"UTF-8\">"
                + "</head>"
                + "<p style=\"padding: 3px 1px 3px 5px; \">"+Idoregesz.getLeiras()+"</p>"
            + "</html>"));
        // System.out.println(String.format("%d. Pisztácia elfogyott, vanillia nem is volt.", ehe));
        ehe++;
    }
    
    private void Command(){
        if(Idoregesz.Command(Tf_input.getText().split(" "))){
           //La_hiba
           Tf_input.setText("");
        }
        else{
            //La_hiba.setText("<html>Hiba: Ez a parancs nem elfogadott!</html>");
        }
    }

    private String getListToTextForJLabel(String[] l){
        String s = "<html>";
        for (String item : l){
            s += item + "<br>";
        } s+="</html>";
        return s;
    }
    
    private String getFeltetelekToTextForJLabel(List<Feltetel> l){
        String s = "<html>";
        for (Feltetel item : l){
            s += "- " + item.getMegkozelitesiFeltetel() + "<br>";
        } s+="</html>";
        return s;
    }
    
    private String getUtvonalakToTextForJLabel(List<Utvonal> l){
        String s = "<html>";
        for (Utvonal item : l){
            s += item.getEgtaj() + " ";
        } s+="</html>";
        return s;
    }
    
    private String getListToText(String[] l){
        String s = "";
        for (String item : l){
            s += item + "\n";
        }
       return s;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pa_kep = new javax.swing.JPanel();
        La_kep = new javax.swing.JLabel();
        La_output = new javax.swing.JLabel();
        La_hiba = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Pr_eletero = new javax.swing.JProgressBar();
        La_eletero = new javax.swing.JLabel();
        Bt_send = new javax.swing.JButton();
        Tf_input = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        La_inventory = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        La_talal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        La_appliedCommands = new javax.swing.JLabel();
        La_irany = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));
        setResizable(false);

        Pa_kep.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Pa_kep.setMaximumSize(new java.awt.Dimension(309, 215));
        Pa_kep.setMinimumSize(new java.awt.Dimension(309, 215));
        Pa_kep.setPreferredSize(new java.awt.Dimension(309, 215));

        La_kep.setMaximumSize(new java.awt.Dimension(309, 215));
        La_kep.setMinimumSize(new java.awt.Dimension(309, 215));
        La_kep.setPreferredSize(new java.awt.Dimension(309, 215));

        javax.swing.GroupLayout Pa_kepLayout = new javax.swing.GroupLayout(Pa_kep);
        Pa_kep.setLayout(Pa_kepLayout);
        Pa_kepLayout.setHorizontalGroup(
            Pa_kepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(La_kep, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );
        Pa_kepLayout.setVerticalGroup(
            Pa_kepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pa_kepLayout.createSequentialGroup()
                .addComponent(La_kep, javax.swing.GroupLayout.PREFERRED_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        La_output.setBackground(new java.awt.Color(153, 255, 255));
        La_output.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        La_output.setText("<html>A főnév és helyszín szavak a leírásokban lesznek megtalálhatóak, arra kell figyelni, ha egy adott helyszínen van felvehető tárgy, akkor felvétele után az nem lesz ott, csak a felszereléseink között. Kivéve az étel, az mindig ott marad! Amikor megfelelő helyen használtuk a tárgyat, akkor az a felszerelések közül eltűnhet, de pl. egy kulcs vissza is kerülhet a felszerelések közé.</html>");
        La_output.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        La_output.setMaximumSize(new java.awt.Dimension(200, 160));
        La_output.setOpaque(true);

        La_hiba.setBackground(new java.awt.Color(204, 204, 204));
        La_hiba.setForeground(new java.awt.Color(255, 51, 51));
        La_hiba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        La_hiba.setText("Hiba");
        La_hiba.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setText("Hibaüzenetek:");

        Pr_eletero.setBackground(new java.awt.Color(51, 255, 51));
        Pr_eletero.setMaximum(10);
        Pr_eletero.setValue(1);

        La_eletero.setText("0");

        Bt_send.setText("küldés");
        Bt_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_sendActionPerformed(evt);
            }
        });

        Tf_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tf_inputKeyPressed(evt);
            }
        });

        jLabel1.setText("Életerő:");

        jLabel2.setText("Eszköztár:");

        La_inventory.setText("Minta");
        La_inventory.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel4.setText("Itt van:");

        La_talal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        La_talal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        La_talal.setAutoscrolls(true);

        jLabel5.setText("<html>Egyéb<br>lehetőségek:</html>");

        La_appliedCommands.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        La_irany.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        La_irany.setText("Default");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(La_irany, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(La_eletero, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Pr_eletero, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(La_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(La_output, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Pa_kep, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Tf_input)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(Bt_send)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(La_talal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(La_appliedCommands, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(La_hiba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(La_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(La_eletero))
                    .addComponent(Pr_eletero, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(La_irany)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Pa_kep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(La_output, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bt_send)
                            .addComponent(Tf_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(La_hiba, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(La_appliedCommands, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(La_talal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 57, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Bt_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_sendActionPerformed
        this.Command();
    }//GEN-LAST:event_Bt_sendActionPerformed

    private void Tf_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tf_inputKeyPressed
        if('\n' == evt.getKeyChar()){
            this.Command();
        }
    }//GEN-LAST:event_Tf_inputKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_send;
    private javax.swing.JLabel La_appliedCommands;
    private javax.swing.JLabel La_eletero;
    private javax.swing.JLabel La_hiba;
    private javax.swing.JLabel La_inventory;
    private javax.swing.JLabel La_irany;
    private javax.swing.JLabel La_kep;
    private javax.swing.JLabel La_output;
    private javax.swing.JLabel La_talal;
    private javax.swing.JPanel Pa_kep;
    private javax.swing.JProgressBar Pr_eletero;
    private javax.swing.JTextField Tf_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

    @Override
    public void stateEnd(Loader ld) {
        
        ld.removeListener(this);
        ld.setVisible(false);
        this.remove(ld);
        La_output.setVisible(true);
        Tf_input.setVisible(true);
        Bt_send.setVisible(true);
    }
}
