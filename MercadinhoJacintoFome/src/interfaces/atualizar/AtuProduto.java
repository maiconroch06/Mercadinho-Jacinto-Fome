package interfaces.atualizar;

import services.ProdutoService;
import classes.Produto;
import javax.swing.JOptionPane;
import utilidades.tabela.Atalhos;

public class AtuProduto extends javax.swing.JDialog {
    
    private ProdutoService produtos;
    private String codigo;
    
    public AtuProduto(java.awt.Window parent, boolean modal, ProdutoService produtos) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.produtos = produtos;
        
        Atalhos.atalho(btCancelar, "ESCAPE");
        Atalhos.atalho(btAtualizar, "ENTER");
        Atalhos.atalhoLegenda(getRootPane());
    }
    
    public AtuProduto(ProdutoService produtos, String codigo) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.produtos = produtos;
        this.codigo = codigo;
        
        txtCodigo.setEditable(false);
        Atalhos.focar(txtDescricao);
        
        Atalhos.atalho(btCancelar, "ESCAPE");
        Atalhos.atalho(btAtualizar, "ENTER");
        Atalhos.atalhoLegenda(getRootPane());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        btAtualizar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        txtDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Atualizar - Produto");

        jLabel4.setText("Quantidade:");

        jLabel5.setText("Valor Unitário:");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Atualizar Produto");
        jLabel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Descrição:");

        jLabel3.setText("Código:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btAtualizar)
                        .addGap(52, 52, 52)
                        .addComponent(btCancelar)
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAtualizar)
                    .addComponent(btCancelar))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        String descricao = txtDescricao.getText().trim();
        codigo = txtCodigo.getText().trim();
        String quantidadeS = txtQuantidade.getText().trim();
        String valorUnitarioS = txtValorUnitario.getText().trim();

        // Verifica se campos estão vazios
        if (codigo.replace(".", "").replace("-", "").trim().isEmpty() || descricao.isEmpty()
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

        Produto produto = produtos.consultar(codigo);
        
        if(produto != null) {
            produto.setDescricao(descricao);
            produto.setQuantidade(quantidade);
            produto.setValorUnitario(valorUnitario);
            
            produtos.cadastrar(codigo, produto);

            txtCodigo.setText("");
            txtDescricao.setText("");
            txtQuantidade.setText("");
            txtValorUnitario.setText("");

            String mensagem = 
            "Produto atualizado com sucesso!\n\n" +
            "Código: " + produto.getCodigoProduto() + "\n" +
            "Descrição: " + produto.getDescricao() + "\n" +
            "Quantidade: " + produto.getQuantidade() + "\n" +
            "Valor Unitário: " + produto.getValorUnitario();

            JOptionPane.showMessageDialog(null, mensagem);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void jLabel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel1AncestorAdded
        try {
            Produto produto = produtos.consultar(codigo);

            if (produto != null) {
                txtCodigo.setText(produto.getCodigoProduto());
                txtDescricao.setText(produto.getDescricao());
                txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
                txtValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel1AncestorAdded

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        try {
            Produto p = produtos.consultar(codigo);
            txtDescricao.setText(p.getDescricao());
            txtQuantidade.setText(String.valueOf(p.getQuantidade())); 
            txtValorUnitario.setText(String.valueOf(p.getValorUnitario()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
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
