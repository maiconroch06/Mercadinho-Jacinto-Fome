package utilidades.tabela;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import interfaces.Legenda;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class Atalhos {
    
    public static void atalho(javax.swing.AbstractButton botao, String tecla) {
        botao.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke(tecla), tecla);

        botao.getActionMap().put(tecla, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao.doClick();
            }
        });
    }
    
    public static void atalhoTelaCheia(javax.swing.JFrame frame, String tecla) {
        frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke(tecla), tecla);
        frame.getRootPane().getActionMap().put(tecla, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean telaCheia = frame.isUndecorated();

                frame.dispose(); // obrigatório

                if (telaCheia) {
                    // VOLTAR AO NORMAL
                    frame.setUndecorated(false);
                    frame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                    frame.setLocationRelativeTo(null);
                } else {
                    // IR PARA TELA CHEIA
                    frame.setUndecorated(true);
                    frame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                }

                frame.setVisible(true);
            }
        });
    }
    
    public static void atalhoSair(javax.swing.JFrame frame, String tecla) {
        frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke(tecla), tecla);
        frame.getRootPane().getActionMap().put(tecla, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int escolha = javax.swing.JOptionPane.showConfirmDialog(
                        null,
                        "Deseja fechar o programa?",
                        "Confirmação",
                        javax.swing.JOptionPane.YES_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
                
                if(escolha == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    
    // Tela de pagamento - combobox dos funcionarios
    public static void atalho(JComboBox combo, String tecla) {
        combo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(tecla), tecla);

        combo.getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo.requestFocusInWindow();  // foco no combo
                combo.showPopup();             // abre o dropdown

                // força a navegação com setas imediatamente
                combo.getEditor().getEditorComponent().requestFocus();
            }
        });
    }
    
    // Atalho generico
    public static void duploClique(JTable tabela, Runnable acao) {
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    acao.run();
                }
            }
        });
    }
    
    // Tela de Venda - Atalhos dinamicos para foco de componentes
    public static void focar(JComponent comp) {
        if (comp == null) return;

        // spinner (Java 8 style)
        if (comp instanceof JSpinner) {
            JSpinner spinner = (JSpinner) comp;
            JComponent editor = spinner.getEditor();
            
            if (editor instanceof JSpinner.DefaultEditor) {
                JTextField txt = ((JSpinner.DefaultEditor) editor).getTextField();
                txt.requestFocusInWindow();
                txt.selectAll();
                return;
            } else {
                spinner.requestFocusInWindow();
                return;
            }
        }

        // text field
        if (comp instanceof JTextField) {
            JTextField txt = (JTextField) comp;
            txt.requestFocusInWindow();
            txt.selectAll();
            return;
        }

        // fallback
        comp.requestFocusInWindow();
    }
    
    // Atalho para abrir tela de atalhos
    public static void atalhoLegenda(JRootPane root) {
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("F8"), "abrirLegenda");

        root.getActionMap().put("abrirLegenda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Legenda tela = new Legenda(null, true);
                tela.setVisible(true);
            }
        });
    }
    
}