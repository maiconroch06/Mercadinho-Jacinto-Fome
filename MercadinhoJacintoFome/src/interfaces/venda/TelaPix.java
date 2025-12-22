package interfaces.venda;

import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import utilidades.tabela.Atalhos;

public class TelaPix extends javax.swing.JDialog {
    private Timer timerFechar;
    private Timer timerSucesso;
    private boolean pago = false;
    
    public TelaPix(Window parent, boolean modal) {
        super(parent, ModalityType.APPLICATION_MODAL); 
        initComponents();
        this.setLocationRelativeTo(this);
        
        Atalhos.atalho(btVoltar, "ESPACE");
        
         // TIMER QUE FECHA SEM PAGAR
        timerFechar = new Timer(8000, e -> {
            cancelarTudo();
            dispose();
        });
        timerFechar.setRepeats(false);
        timerFechar.start();

        // TIMER DO PAGAMENTO
        timerSucesso = new Timer(5000, e -> {
            pago = true; // SÓ AQUI VALIDA O PAGAMENTO
            JOptionPane.showMessageDialog(this, "Pagamento bem sucedido!");
            cancelarTudo();
            dispose();
        });
        timerSucesso.setRepeats(false);
        timerSucesso.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Escaneie o QR CODE abaixo:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/venda/qrcodepix.png"))); // NOI18N

        btVoltar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btVoltar.setText("Precione BARRA DE ESPAÇO para voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btVoltar)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(109, 109, 109)))
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        cancelarTudo();
        this.dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

    private void cancelarTudo() {
        if (timerFechar != null) timerFechar.stop();
        if (timerSucesso != null) timerSucesso.stop();
    }

    public boolean isPago() {
        return pago;
    }
        
}
