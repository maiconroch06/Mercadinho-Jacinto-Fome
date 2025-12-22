package interfaces;

import services.Gerenciamento;
import utilidades.tabela.*;
import interfaces.cadastrar.*;
import interfaces.atualizar.*;
import interfaces.venda.NovaVenda;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {
    
    private Gerenciamento g = new Gerenciamento();
    
    DefaultTableModel modeloTableProduto;
    DefaultTableModel modeloTableCliente;
    DefaultTableModel modeloTableFuncionario;
    DefaultTableModel modeloTabelaVenda;
    
    public Main() {
        initComponents();
        configurarTela();
        
        // Pega os modelos das tabelas e define como global para todo codigo.
        this.modeloTableProduto = (DefaultTableModel)jTProdutos.getModel();
        this.modeloTableCliente = (DefaultTableModel)jTClientes.getModel();
        this.modeloTableFuncionario = (DefaultTableModel)jTFuncionarios.getModel();
        this.modeloTabelaVenda = (DefaultTableModel)jTVendas.getModel();

        // Sempre ao executar o main, adicionar valores pre definidos às tabelas
        g.produtos().carregarProdutosPadrao();
        g.clientes().carregarClientesPadrao();
        g.funcionarios().carregarFuncionariosPadrao();
        
        duploClick(jTClientes);
        duploClick(jTFuncionarios);
        duploClick(jTProdutos);
        duploClick(jTVendas);
        
        Atalhos.enterGlobal(getRootPane(), btPesquisar);
        Atalhos.atalhoLegenda(getRootPane());
        
        // Sempre ao executar o main, carrega os dados do HashMap na primeira tabela
        Carregar.tabelaProdutos(modeloTableProduto, g.produtos().listarTodos());
        
        // Sempre ao executar o main, ativar ordernação em todas as tabelas
        Carregar.ordenacao(jTProdutos);
        Carregar.ordenacao(jTClientes, 1);
        Carregar.ordenacao(jTFuncionarios, 1);
        Carregar.ordenacao(jTVendas);
        
        // Habilida a função de sempre mostrar lista inteira, mesmo depois de ter feito uma pesquisa anteriomente.
        Abas.addChangeListener(e -> {
            int index = Abas.getSelectedIndex();

            switch (index) {
                case 0: // Produtos
                    if(g.produtos().isProdutosAtualizados()) {
                        Carregar.tabelaProdutos(modeloTableProduto, g.produtos().listarTodos());
                        g.produtos().setProdutosAtualizados(false);
                    }
                    break;

                case 1: // Clientes
                    if(g.clientes().isClientesAtualizados()) {
                        Carregar.tabelaClientes(modeloTableCliente, g.clientes().listarTodos());
                        g.clientes().setClientesAtualizados(false);
                    }
                    break;

                case 2: // Funcionários
                    if(g.funcionarios().isFuncionariosAtualizados()) {
                        Carregar.tabelaFuncionarios(modeloTableFuncionario, g.funcionarios().listarTodos());
                        g.funcionarios().setFuncionariosAtualizados(false);
                    }
                    break;

                case 3: // Vendas
                    if(g.vendas().isVendasAtualizadas()) {
                        Carregar.tabelaVendas(modeloTabelaVenda, g.vendas().listarTodas());
                        g.vendas().setVendasAtualizadas(false);
                    }
                    break;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        painelImagem = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Abas = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTProdutos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTFuncionarios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTVendas = new javax.swing.JTable();
        btPesquisar = new javax.swing.JButton();
        txtReferencia = new javax.swing.JTextField();
        btExcluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        mnNovaVenda = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        mnCadProduto = new javax.swing.JMenuItem();
        mnCliente = new javax.swing.JMenuItem();
        mnCadFuncionario = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        mnAtuProduto = new javax.swing.JMenuItem();
        mnAtuCliente = new javax.swing.JMenuItem();
        mnAtuFuncionario = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");

        painelImagem = new javax.swing.JPanel() {
            // carrega a imagem uma vez
            private final java.awt.Image img = new javax.swing.ImageIcon(
                getClass().getResource("/interfaces/fundinhoAR.jpg")
            ).getImage();

            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);

                if (img == null) return;

                int pw = getWidth();
                int ph = getHeight();

                int iw = img.getWidth(null);
                int ih = img.getHeight(null);
                if (iw <= 0 || ih <= 0) return;

                // ESCALA TIPO COVER
                double scale = Math.max((double) pw / iw, (double) ph / ih);
                int nw = (int) (iw * scale);
                int nh = (int) (ih * scale);

                int x = (pw - nw) / 2;
                int y = (ph - nh) / 2;

                // desenha a imagem
                g.drawImage(img, x, y, nw, nh, this);

                // -----------------------------
                // CENTRALIZA TEXTO DOS LABELS
                // -----------------------------
                int centroPainel = pw / 2;

                // posições verticais
                int y1 = 40;
                int y2 = 105;

                // centraliza o LABEL (bloco)
                jLabel1.setLocation(centroPainel - jLabel1.getWidth() / 2, y1);
                jLabel2.setLocation(centroPainel - jLabel2.getWidth() / 2, y2);

                // centraliza o TEXTO dentro do label
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                // -----------------------------
                // CENTRALIZAR A TABELA (jPanel1)
                // -----------------------------

                int larguraP1 = jPanel1.getPreferredSize().width;
                int novaX = centroPainel - larguraP1 / 2;

                jPanel1.setLocation(novaX, jPanel1.getY());
            }
        };
        painelImagem.setToolTipText("");
        painelImagem.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Estoque");
        painelImagem.add(jLabel1);
        jLabel1.setBounds(0, 47, 1338, 58);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mercadinho Jacinto Fome");
        painelImagem.add(jLabel2);
        jLabel2.setBounds(0, 111, 1338, 39);

        jPanel1.setToolTipText("Tela Principal");
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setOpaque(false);

        jTProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Produto", "Descrição", "Quantidade (Un)", "Valor Unitario (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTProdutos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTProdutos);
        if (jTProdutos.getColumnModel().getColumnCount() > 0) {
            jTProdutos.getColumnModel().getColumn(0).setResizable(false);
            jTProdutos.getColumnModel().getColumn(1).setResizable(false);
            jTProdutos.getColumnModel().getColumn(2).setResizable(false);
            jTProdutos.getColumnModel().getColumn(3).setResizable(false);
        }

        Abas.addTab("Tabela de Produtos", jScrollPane3);

        jTClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Endereço", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTClientes);

        Abas.addTab("Tabela de Clientes", jScrollPane2);

        jTFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTFuncionarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTFuncionarios);

        Abas.addTab("Tabela de Funcionario", jScrollPane1);

        jTVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Venda", "Funcionario", "Cliente", "Quantidade Total De Itens", "Total da Compra (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTVendas.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTVendas);

        Abas.addTab("Tabela de Vendas", jScrollPane4);

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Visualização dos atalhos precione F8.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btPesquisar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Abas, javax.swing.GroupLayout.PREFERRED_SIZE, 1141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(297, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluir))
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Abas, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        painelImagem.add(jPanel1);
        jPanel1.setBounds(0, 161, 1735, 596);

        jMenu7.setText("Operações");

        mnNovaVenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mnNovaVenda.setText("Nova Venda");
        mnNovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNovaVendaActionPerformed(evt);
            }
        });
        jMenu7.add(mnNovaVenda);

        jMenu8.setText("Cadastrar");

        mnCadProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnCadProduto.setText("Produto");
        mnCadProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCadProdutoActionPerformed(evt);
            }
        });
        jMenu8.add(mnCadProduto);

        mnCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mnCliente.setText("Cliente");
        mnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnClienteActionPerformed(evt);
            }
        });
        jMenu8.add(mnCliente);

        mnCadFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        mnCadFuncionario.setText("Funcionario");
        mnCadFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCadFuncionarioActionPerformed(evt);
            }
        });
        jMenu8.add(mnCadFuncionario);

        jMenu7.add(jMenu8);

        jMenu9.setText("Atualizar");

        mnAtuProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mnAtuProduto.setText("Produto");
        mnAtuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAtuProdutoActionPerformed(evt);
            }
        });
        jMenu9.add(mnAtuProduto);

        mnAtuCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        mnAtuCliente.setText("Cliente");
        mnAtuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAtuClienteActionPerformed(evt);
            }
        });
        jMenu9.add(mnAtuCliente);

        mnAtuFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        mnAtuFuncionario.setText("Funcionario");
        mnAtuFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAtuFuncionarioActionPerformed(evt);
            }
        });
        jMenu9.add(mnAtuFuncionario);

        jMenu7.add(jMenu9);

        jMenuBar2.add(jMenu7);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNovaVendaActionPerformed
        NovaVenda vendaGUI = new NovaVenda(g.vendas(), g.clientes(), g.funcionarios());
        vendaGUI.setModal(true);
        vendaGUI.setVisible(true);
        
        Carregar.tabelaVendas(modeloTabelaVenda, g.vendas().listarTodas());
        Carregar.tabelaProdutos(modeloTableProduto , g.produtos().listarTodos());
    }//GEN-LAST:event_mnNovaVendaActionPerformed

    private void mnCadProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCadProdutoActionPerformed
        CadProduto cadVGUI = new CadProduto(g.produtos());
        cadVGUI.setModal(true);
        cadVGUI.setVisible(true);
        
        Carregar.tabelaProdutos(modeloTableProduto , g.produtos().listarTodos());
    }//GEN-LAST:event_mnCadProdutoActionPerformed

    private void mnCadFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCadFuncionarioActionPerformed
        CadFuncionario cadFGUI = new CadFuncionario(g.funcionarios());
        cadFGUI.setModal(true);
        cadFGUI.setVisible(true);
        
        Carregar.tabelaFuncionarios(modeloTableFuncionario, g.funcionarios().listarTodos());
    }//GEN-LAST:event_mnCadFuncionarioActionPerformed

    private void mnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnClienteActionPerformed
        CadCliente cadCGUI = new CadCliente(g.clientes());
        cadCGUI.setModal(true);
        cadCGUI.setVisible(true);

        Carregar.tabelaClientes(modeloTableCliente, g.clientes().listarTodos());
    }//GEN-LAST:event_mnClienteActionPerformed

    private void mnAtuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAtuProdutoActionPerformed
        AtuProduto AtualizarProd = new AtuProduto(g.produtos());
        AtualizarProd.setModal(true);
        AtualizarProd.setVisible(true);
        
        Carregar.tabelaProdutos(modeloTableProduto , g.produtos().listarTodos());
    }//GEN-LAST:event_mnAtuProdutoActionPerformed

    private void mnAtuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAtuFuncionarioActionPerformed
        AtuFuncionario AtualizarFun = new AtuFuncionario(g.funcionarios());
        AtualizarFun.setModal(true);
        AtualizarFun.setVisible(true);

        Carregar.tabelaFuncionarios(modeloTableFuncionario, g.funcionarios().listarTodos());
    }//GEN-LAST:event_mnAtuFuncionarioActionPerformed

    private void mnAtuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAtuClienteActionPerformed
        AtuCliente AtualizarCli = new AtuCliente(g.clientes());
        AtualizarCli.setModal(true);
        AtualizarCli.setVisible(true);
        
        Carregar.tabelaClientes(modeloTableCliente, g.clientes().listarTodos());
    }//GEN-LAST:event_mnAtuClienteActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        String chave = txtReferencia.getText().trim(); // chave que o usuario procura
        int index = Abas.getSelectedIndex();         // indice da aba atual aberta

        switch (index) {

            // ------------------- PRODUTOS -------------------
            case 0:
                if (chave.isEmpty()) {
                    Carregar.tabelaProdutos(modeloTableProduto , g.produtos().listarTodos());
                    return;
                }
                Pesquisar.pesqProduto(chave, modeloTableProduto, g.produtos());
                txtReferencia.setText("");
                break;

            // ------------------- CLIENTES -------------------
            case 1:
                if (chave.isEmpty()) {
                    Carregar.tabelaClientes(modeloTableCliente, g.clientes().listarTodos());
                    return;
                }
                Pesquisar.pesqCliente(chave, modeloTableCliente, g.clientes());
                txtReferencia.setText("");
                break;

            // ------------------- FUNCIONÁRIOS -------------------
            case 2:
                if (chave.isEmpty()) {
                    Carregar.tabelaFuncionarios(modeloTableFuncionario, g.funcionarios().listarTodos());
                    return;
                }
                Pesquisar.pesqFuncionario(chave, modeloTableFuncionario, g.funcionarios());
                txtReferencia.setText("");
                break;

            // ------------------- VENDAS -------------------
            case 3:
                if (chave.isEmpty()) {
                    Carregar.tabelaVendas(modeloTabelaVenda, g.vendas().listarTodas());
                    return;
                }
                Pesquisar.pesqVenda(chave, modeloTabelaVenda, g.vendas());
                txtReferencia.setText("");
                break;
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int indiceAba = Abas.getSelectedIndex(); // Identifica aba atual
        int linha = -1;
        String chave = "";

        switch (indiceAba) {
            // ---------------------- PRODUTOS ----------------------
            case 0:
                linha = jTProdutos.getSelectedRow();
                
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para excluir!");
                    return;
                }
                
                // Pega chave (codigo do produto) da 1ª coluna
                chave = jTProdutos.getValueAt(linha, 0).toString();

                Deletar.deletarProduto(modeloTableProduto, linha, chave, g.produtos());
                
                break;

            // ---------------------- CLIENTES ----------------------
            case 1:
                linha = jTClientes.getSelectedRow();
                
                if (linha == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir!");
                    return;
                }
                
                // chave = CPF (2ª coluna)
                chave = jTClientes.getValueAt(linha, 1).toString();
                
                Deletar.deletarCliente(modeloTableCliente, linha, chave, g.clientes());
                
                break;

            // ---------------------- FUNCIONÁRIOS ----------------------
            case 2:
                linha = jTFuncionarios.getSelectedRow();
                
                if (linha == -1) {
                    JOptionPane.showMessageDialog(this, "Selecione um funcionário para excluir!");
                    return;
                }

                // chave = CPF (2ª coluna)
                chave = jTFuncionarios.getValueAt(linha, 1).toString();

                Deletar.deletarFuncionario(modeloTableFuncionario, linha, chave, g.funcionarios());
                
                break;

            // ---------------------- VENDAS ----------------------
            case 3:
                linha = jTVendas.getSelectedRow();
                
                if (linha == -1) {
                    JOptionPane.showMessageDialog(this, "Selecione uma venda para excluir!");
                    return;
                }

                // chave = ID_Venda (1ª coluna)
                chave = jTVendas.getValueAt(linha, 0).toString();

                Deletar.deletarVenda(modeloTabelaVenda, linha, chave, g.vendas());
                break;
        }
    }//GEN-LAST:event_btExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Abas;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTable jTFuncionarios;
    private javax.swing.JTable jTProdutos;
    private javax.swing.JTable jTVendas;
    private javax.swing.JMenuItem mnAtuCliente;
    private javax.swing.JMenuItem mnAtuFuncionario;
    private javax.swing.JMenuItem mnAtuProduto;
    private javax.swing.JMenuItem mnCadFuncionario;
    private javax.swing.JMenuItem mnCadProduto;
    private javax.swing.JMenuItem mnCliente;
    private javax.swing.JMenuItem mnNovaVenda;
    private javax.swing.JPanel painelImagem;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables
    
    private void configurarTela() {
        jLabel1.setSize(800, 60);
        jLabel2.setSize(800, 40);

        painelImagem.setLayout(null);
        setContentPane(painelImagem);
        
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void duploClick(JTable tabela) {
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {

                    // AQUI IDENTIFICA QUAL TABELA É
                    if (tabela == jTProdutos) {
                        int linha = tabela.getSelectedRow();
                        String codigo = tabela.getValueAt(linha, 0).toString();

                        AtuProduto AtualizarProd = new AtuProduto(g.produtos(), codigo);

                        AtualizarProd.setModal(true);
                        AtualizarProd.setVisible(true);
                        
                        Carregar.tabelaProdutos(modeloTableProduto, g.produtos().listarTodos());
                    }

                    else if (tabela == jTFuncionarios) {
                        int linha = tabela.getSelectedRow();
                        String cpf = tabela.getValueAt(linha, 1).toString();
                        
                        AtuFuncionario AtualizarFun = new AtuFuncionario(g.funcionarios(), cpf);

                        AtualizarFun.setModal(true);
                        AtualizarFun.setVisible(true);
                        
                        Carregar.tabelaFuncionarios(modeloTableFuncionario, g.funcionarios().listarTodos());
                    }
                    
                    else if (tabela == jTClientes) {
                        int linha = tabela.getSelectedRow();
                        String cpf = tabela.getValueAt(linha, 1).toString();
                        
                        AtuCliente AtualizarCli = new AtuCliente(g.clientes(), cpf);

                        AtualizarCli.setModal(true);
                        AtualizarCli.setVisible(true);
                        
                        Carregar.tabelaClientes(modeloTableCliente, g.clientes().listarTodos());
                    }

                    else if (tabela == jTVendas) {
                        int linha = tabela.getSelectedRow();
                        String idVenda = jTVendas.getValueAt(linha, 0).toString();

                        VerMais tela = new VerMais(Main.this, true, idVenda, g.vendas());
                        
                        tela.setVisible(true);
                        
                        Carregar.tabelaVendas(modeloTabelaVenda, g.vendas().listarTodas());
                    }
                }
            }
        });
    }
}
