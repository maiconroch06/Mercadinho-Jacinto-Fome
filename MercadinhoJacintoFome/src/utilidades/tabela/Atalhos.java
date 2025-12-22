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
    
    public static void enterGlobal(JRootPane root, JButton botao) {
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ENTER"), "ENTER_GLOBAL");

        root.getActionMap().put("ENTER_GLOBAL", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao.doClick();
            }
        });
    }
    
    public static void atalho(JButton botao, String tecla) {
        botao.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(tecla), tecla);

        botao.getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao.doClick();
            }
        });
    }
    
    // Tela de pagamento - metodos de pagamento
    public static void atalho(JRadioButton botao, String tecla) {
        botao.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(tecla), tecla);

        botao.getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao.doClick();
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