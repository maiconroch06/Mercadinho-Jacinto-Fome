package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBD {
    
    String banco = "estoque";
    String host = "localhost";
    String driver = "com.mysql.cj.jdbc.Driver";
    String str_con = "jdbc:mysql://" + host + ":3306/" + banco;
    String usuario = "root";
    String senha = "";

    Connection con;
    PreparedStatement estado;
    
   public void conectar() {
        try {
            Class.forName(driver);

            // só conecta se NÃO existir conexão ativa
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(str_con, usuario, senha);
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o driver");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    
    public void fecharConexao(){
        try {
            if(estado != null) estado.close();
            if(con != null) con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão");
        }
    }
    
}
