package interfaces.cadastrar;

import services.ProdutoService;
import classes.Produto;
import javax.swing.JOptionPane;
import utilidades.tabela.Atalhos;

public class CadProduto extends javax.swing.JDialog {

    private ProdutoService produtos;
    
    public CadProduto(java.awt.Frame parent, boolean modal, ProdutoService produtos) {
        this.produtos = produtos;
        initComponents();
        this.setLocationRelativeTo(null);
        
        Atalhos.atalho(btCancelar, "ESCAPE");
        Atalhos.enterGlobal(getRootPane(), btCadastrar);
        Atalhos.atalhoLegenda(getRootPane());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btCadastrar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        txtValorUnitario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastrar Produto");

        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jLabel5.setText("Valor Unitário:");

        jLabel4.setText("Quantidade:");

        jLabel2.setText("Descrição:");

        jLabel3.setText("Código:");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValorUnitario)
                    .addComponent(txtQuantidade)
                    .addComponent(txtCodigo)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCadastrar)
                    .addComponent(btCancelar))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        String descricao = txtDescricao.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String quantidadeS = txtQuantidade.getText().trim();
        String valorUnitarioS = txtValorUnitario.getText().trim();

        // Verifica se campos estão vazios
        if (descricao.isEmpty() || codigo.trim().isEmpty() 
            || quantidadeS.isEmpty() || valorUnitarioS.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }

        int quantidade;
        double valorUnitario;
        // Valida quantidade e valor em um único try/catch
        try {
            quantidade = Integer.parseInt(quantidadeS);
            if (quantidade < 0) {
                JOptionPane.showMessageDialog(null, "Quantidade não pode ser negativa!");
                txtQuantidade.requestFocus();
                return;
            }

            // Aceita valor com vírgula ou ponto
            valorUnitario = Double.parseDouble(valorUnitarioS.replace(",", "."));
            if (valorUnitario < 0) {
                JOptionPane.showMessageDialog(null, "Valor unitário não pode ser negativo!");
                txtValorUnitario.requestFocus();
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade e valor devem ser números válidos!");
            return;
        }

        Produto exitenteProduto = produtos.consultar(codigo);

        if (exitenteProduto != null) {
            JOptionPane.showMessageDialog(null, "Produto já cadastrado!");
            return;
        }
        
        Produto novoProduto = produtos.consultar(codigo);
        novoProduto.setDescricao(descricao);
        novoProduto.setCodigoProduto(codigo);
        novoProduto.setQuantidade(quantidade);
        novoProduto.setValorUnitario(valorUnitario);

        produtos.cadastrar(codigo, novoProduto);

        txtDescricao.setText("");
        txtCodigo.setText("");
        txtQuantidade.setText("");
        txtValorUnitario.setText("");
        
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        dispose();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        try {
            Produto p = produtos.consultar(txtCodigo.getText().trim().replaceAll("\\D", ""));
            txtDescricao.setText(p.getDescricao());
            txtQuantidade.setText(String.valueOf(p.getQuantidade()));
            txtValorUnitario.setText(String.valueOf(p.getValorUnitario()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
