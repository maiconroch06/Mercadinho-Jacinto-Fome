package interfaces.venda;

import services.VendaService;
import classes.Cliente;
import classes.Funcionario;
import classes.RegistroVenda;
import interfaces.cadastrar.CadCliente;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import services.ClienteService;
import services.FuncionarioService;
import utilidades.tabela.Atalhos;

public class Pagamento extends javax.swing.JDialog {
    
    private final ClienteService clientes;
    private final FuncionarioService funcionarios;
    private final RegistroVenda venda;
    private boolean finalizada = false;

    public Pagamento(Window parent, boolean modal, ClienteService clientes, FuncionarioService funcionarios, RegistroVenda venda) {
        super(parent, ModalityType.APPLICATION_MODAL); 

        initComponents();
        this.setLocationRelativeTo(parent);
        
        this.clientes = clientes;
        this.funcionarios = funcionarios;
        this.venda = venda;
        
        Atalhos.enterGlobal(getRootPane(), btnFinalizar);
        Atalhos.atalhoLegenda(getRootPane());
        Atalhos.atalho(btnVoltar, "ESCAPE");
        Atalhos.atalho(cbFuncionario, "F1");
        Atalhos.atalho(opcPix, "F2");
        Atalhos.atalho(opcDebito, "F3");
        Atalhos.atalho(opcCredito, "F4");
        Atalhos.atalho(opcEspecie, "F5");

        txtTotal.setText(String.format("R$ %.2f", venda.getTotalValor()));
        carregarFuncionariosNoCombo();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        opcPix = new javax.swing.JRadioButton();
        opcDebito = new javax.swing.JRadioButton();
        opcCredito = new javax.swing.JRadioButton();
        opcEspecie = new javax.swing.JRadioButton();
        txtNomeCliente = new javax.swing.JTextField();
        txtCpfCliente = new javax.swing.JFormattedTextField();
        btnVoltar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        btnCadNCliente = new javax.swing.JButton();
        cbFuncionario = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Pagamento");

        jLabel1.setText("Nome Cliente: ");

        jLabel2.setText("Atendente:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Metodo de Pagamento");

        jLabel4.setText("Forma de Pagamento: ");

        buttonGroup1.add(opcPix);
        opcPix.setText("PIX");

        buttonGroup1.add(opcDebito);
        opcDebito.setText("Débito");

        buttonGroup1.add(opcCredito);
        opcCredito.setText("Crédito");

        buttonGroup1.add(opcEspecie);
        opcEspecie.setText("Espécie");

        try {
            txtCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfClienteFocusLost(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Total da compra: R$");

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotal.setText("00.00");

        btnCadNCliente.setText("Cadastrar Novo Cliente");
        btnCadNCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadNClienteActionPerformed(evt);
            }
        });

        jLabel6.setText("CPF Cliente:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(opcPix)
                                .addGap(18, 18, 18)
                                .addComponent(opcDebito)
                                .addGap(18, 18, 18)
                                .addComponent(opcCredito))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadNCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFinalizar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(opcEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeCliente)
                            .addComponent(txtCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal)))
                .addContainerGap(32, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(opcPix)
                    .addComponent(opcDebito)
                    .addComponent(opcCredito)
                    .addComponent(opcEspecie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadNCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVoltar)
                        .addComponent(btnFinalizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        String cpfCliente = txtCpfCliente.getText().trim();
        String nomeCliente = txtNomeCliente.getText().trim();
        
        // Verifica se cpf tá vazio ou com menos dígitos;
        if (cpfCliente.isEmpty() || cpfCliente.length() != 14) {
            JOptionPane.showMessageDialog(this, "CPF inválido.");
            return;
        }
        
        Cliente cliente = clientes.consultar(cpfCliente);
        
        if (cliente == null) {
            Object[] options = {"Cadastrar", "Tentar Novamente"};
            int escolha = JOptionPane.showOptionDialog(
                this,
                "Cliente não Cadastrado ou CPF Incorreto." + cpfCliente,
                "Cliente",
                JOptionPane.YES_NO_OPTION,         
                JOptionPane.WARNING_MESSAGE, 
                null,                              
                options,                           
                options[0]                         
            );
            
            if (escolha == 0) {
                CadCliente cad = new CadCliente(this, true, clientes, nomeCliente, cpfCliente);
                cad.setVisible(true);
            }
            return;
        }
        
        Funcionario funcionarioSelecionado =
        (Funcionario) cbFuncionario.getSelectedItem();

        if (funcionarioSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário válido.");
            return;
        }

        String cpfFuncionario = funcionarioSelecionado.getCpf();
        
        String metodoPagamento =
            opcPix.isSelected() ? "PIX" :
            opcDebito.isSelected() ? "DEBITO" :
            opcCredito.isSelected() ? "CREDITO" :
            opcEspecie.isSelected() ? "ESPECIE" :
            null;

        switch (metodoPagamento) {
            case "PIX":
                TelaPix tPix = new TelaPix(this, true, venda.getTotalValor());
                tPix.setLocationRelativeTo(this);
                tPix.setVisible(true);
                tPix.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                if(!tPix.isPago()){
                    return;
                }
                
                break;

            case "DEBITO":
                TelaDebito tDeb = new TelaDebito(this, true, venda.getTotalValor());
                tDeb.setLocationRelativeTo(this);
                tDeb.setVisible(true);
                tDeb.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                if(!tDeb.isPago()){
                    return;
                }
                
                break;

            case "CREDITO":
                TelaCredito tCred = new TelaCredito(this, true, venda.getTotalValor());
                tCred.setLocationRelativeTo(this);
                tCred.setVisible(true);
                tCred.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                if(!tCred.isPago()){
                    return;
                }
                
                break;

            case "ESPECIE":
                TelaEspecie tEsp = new TelaEspecie(this, true, venda.getTotalValor());
                tEsp.setLocationRelativeTo(this);
                tEsp.setVisible(true);
                tEsp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                if(!tEsp.isPago()){
                    return;
                }
                break;

            default:
                JOptionPane.showMessageDialog(this, "Selecione uma forma de pagamento.");
                return;
        }
        
        // Finaliza venda
        venda.setCpfCliente(cpfCliente);
        venda.setCpfFuncionario(cpfFuncionario);
        venda.setMetodo(metodoPagamento);

        finalizada = true;
        this.dispose();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnCadNClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadNClienteActionPerformed
        String nomeCliente = txtNomeCliente.getText().trim();
        String cpfCliente = txtCpfCliente.getText().trim();
        
        CadCliente cad = new CadCliente(this, true, clientes, nomeCliente, cpfCliente);
        cad.setVisible(true);
    }//GEN-LAST:event_btnCadNClienteActionPerformed

    private void txtCpfClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfClienteFocusLost
        try {
            Cliente cliente = clientes.consultar(txtCpfCliente.getText().trim());
            txtNomeCliente.setText(cliente.getNome());
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCpfClienteFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadNCliente;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Funcionario> cbFuncionario;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JRadioButton opcCredito;
    private javax.swing.JRadioButton opcDebito;
    private javax.swing.JRadioButton opcEspecie;
    private javax.swing.JRadioButton opcPix;
    private javax.swing.JFormattedTextField txtCpfCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
    
    private void carregarFuncionariosNoCombo() {
        DefaultComboBoxModel<Funcionario> modelo = new DefaultComboBoxModel<>();

        modelo.addElement(null);

        for (Funcionario f : funcionarios.listarTodos().values()) {
            modelo.addElement(f);
        }

        cbFuncionario.setModel(modelo);

        cbFuncionario.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            javax.swing.JLabel label = new javax.swing.JLabel();

            if (value == null) {
                label.setText("<Funcionários>");
            } else {
                label.setText(value.getNome());
            }

            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }

            return label;
        });
    }

    
    public boolean isFinalizada() {
        return finalizada;
    }
    
}
