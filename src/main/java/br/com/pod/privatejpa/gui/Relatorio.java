/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.pod.privatejpa.gui;

import java.time.format.DateTimeFormatter;
import java.util.List;
import br.com.pod.privatejpa.persistencia.Cliente;
import br.com.pod.privatejpa.gui.Cadastro;
import br.com.pod.privatejpa.persistencia.ClienteDAO;
import br.com.pod.privatejpa.persistencia.Pedido;
import br.com.pod.privatejpa.persistencia.PedidoDAO;
import br.com.pod.privatejpa.persistencia.Veiculo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stine
 */
public class Relatorio extends javax.swing.JFrame {

    private double valorTotalGeral = 0.0;

    public void preencherTabela(List<Cliente> clientes) {

        valorTotalGeral = 0.0;

        String[] columns = {"ID", "Nome", "CPF", "Telefone", "Endereço", "Data Entrada", "Modelo", "Cor", "Placa", "Ano Fabricação", "Data Saída", "Valor Total"}; // Adicionando a coluna "ID"
        String[][] dados = new String[clientes.size()][columns.length];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;

        ClienteDAO clienteDAO = new ClienteDAO();
        double valorTotal = 0.0;

        for (Cliente c : clientes) {
            Pedido pedido = clienteDAO.buscarPedidoPorClienteId(Long.valueOf(c.getId()));

            String dataSaidaFormatada = (pedido != null && pedido.getDataSaida() != null) ? pedido.getDataSaida().format(formatter) : "";
            String valorTotalString = (pedido != null) ? String.valueOf(pedido.getValorTotal()) : "";

            if (pedido != null) {
                valorTotalGeral += pedido.getValorTotal();
            }

            dados[i] = new String[]{
                String.valueOf(c.getId()), // ID do Cliente
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getEndereco(),
                c.getDataEntrada().format(formatter),
                c.getVeiculo().getModelo(),
                c.getVeiculo().getCor(),
                c.getVeiculo().getPlaca(),
                String.valueOf(c.getVeiculo().getAnofab()),
                dataSaidaFormatada,
                valorTotalString
            };
            i++;
        }

        String valorTotalFormatado = String.format("%.2f", valorTotalGeral);
        txtTotal.setText("R$ " + valorTotalFormatado);

        DefaultTableModel model = new DefaultTableModel(dados, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição das células
            }
        };
        tblRelatorio.setModel(model);

        // Oculta a coluna do ID
        if (tblRelatorio.getColumnModel().getColumnCount() > 0) { // Adiciona esta verificação
            tblRelatorio.getColumnModel().getColumn(0).setMinWidth(0);
            tblRelatorio.getColumnModel().getColumn(0).setMaxWidth(0);
            tblRelatorio.getColumnModel().getColumn(0).setWidth(0);
        }
    }

    /**
     * Creates new form Relatorio
     */
    public Relatorio() {
        initComponents();
        setTitle("PRIVATE PARKING");

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarRelatorio("");
        preencherTabela(clientes);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRelatorio = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Pesquisa Cliente:");

        txtPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        txtPesquisa.setToolTipText("Insira o mês desejado e clique sobre a tecla \"ENTER\"!");
        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        tblRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Telefone", "Endereço", "Data de Entrada ", "Modelo Veículo", "Cor", "Placa", "Ano Fabricação", "Data Saída ", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRelatorio);

        txtTotal.setText("R$:");

        btnExcluir.setBackground(new java.awt.Color(255, 51, 51));
        btnExcluir.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir ");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 255, 153));
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnExcluir)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        try {
            // Verifica se nenhuma linha está selecionada
            if (tblRelatorio.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para ser excluída.");
                return;
            }

            // Obtém o ID do cliente da primeira coluna (que agora está oculta)
            Long clienteId = Long.valueOf(tblRelatorio.getValueAt(tblRelatorio.getSelectedRow(), 0).toString());

            // Janela de confirmação
            String[] botoes = {"Sim", "Não"};
            int resposta = JOptionPane.showOptionDialog(this, "Deseja realmente excluir o Cliente? ", "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]);

            if (resposta == 0) {
                // Chama o método DAO para excluir o cliente pelo ID
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.excluirClienteEVeiculoPorId(clienteId); // Exclui o cliente pelo ID

                // Atualiza a tabela após a exclusão
                ClienteDAO clienteDAOAtualizado = new ClienteDAO();
                List<Cliente> clientesAtualizados = clienteDAOAtualizado.listarRelatorio(""); // Recarrega todos os clientes
                preencherTabela(clientesAtualizados); // Repreenche a tabela após a exclusão

                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu uma falha:\n" + e.getMessage());
            e.printStackTrace(); // Log the exception for debugging
        }

    }//GEN-LAST:event_btnExcluirActionPerformed


    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        try {
            ClienteDAO clienteDAO = new ClienteDAO();

            // Obtém o texto digitado pelo usuário
            String textoDigitado = txtPesquisa.getText();

            // Verifica se o campo está vazio
            if (textoDigitado.isEmpty()) {
                // Se o campo estiver vazio, retorna todos os dados do banco
                List<Cliente> clientes = clienteDAO.listarRelatorio("");

                // Atualiza a tabela com todos os clientes
                preencherTabela(clientes);

            } else {
                try {
                    // Tenta converter o texto para um número (mês)
                    int mesPesquisa = Integer.parseInt(textoDigitado);

                    // Chama o método para buscar os dados do mês informado
                    List<Cliente> clientes = clienteDAO.listarRelatorioPorMes(mesPesquisa);

                    // Atualiza a tabela com os resultados
                    preencherTabela(clientes);

                    // Limpa o campo de pesquisa após a execução da busca
                    txtPesquisa.setText("");

                } catch (NumberFormatException e) {
                    // Caso o texto não seja um número válido, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(this, "Digite um número válido para o mês!");
                }
            }
        } catch (Exception e) {
            // Captura outros erros inesperados e exibe uma mensagem para o usuário
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + e.getMessage());
        }

    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Relatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRelatorio;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
