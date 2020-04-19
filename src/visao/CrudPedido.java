package visao;

import control.CntlCliente;
import control.CntlPedido;
import control.CntlVendedor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CrudPedido extends javax.swing.JFrame {

    /**
     * Creates new form CrudCliente
     *
     * @param id
     */
    String[] dadosPedido = new String[5];
    private final ArrayList<Integer> listCliente = new ArrayList<>();
    private final ArrayList<Integer> listVendedor = new ArrayList<>();

    public CrudPedido() {
        initComponents();
        procuraCliente(0);
        procuraVendedor(0);
    }

    public CrudPedido(int id) {
        initComponents();

        if (id != 0) {
            dadosPedido = CntlPedido.recuperar(id);

        } else {
            Calendar data = Calendar.getInstance();
            Date dataCalendar = data.getTime();
            SimpleDateFormat dataFormatada;
            dataFormatada = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            dadosPedido[0] = "0";
            dadosPedido[1] = dataFormatada.format(dataCalendar);
            dadosPedido[2] = "";
            dadosPedido[3] = "0";
            dadosPedido[4] = "0";
        }

        this.cmp_codigo.setText(dadosPedido[0]);
        this.cmp_data.setText(dadosPedido[1]);
        this.cmp_observação.setText(dadosPedido[2]);
        procuraCliente(Integer.parseInt(dadosPedido[3]));
        procuraVendedor(Integer.parseInt(dadosPedido[4]));
    }

    private void procuraCliente(int id) {
        this.jCcliente.removeAllItems();
        this.listCliente.removeAll(listCliente);
        String[][] dados = null;

        if (id == 0) {
            dados = CntlCliente.recuperarTodos();
        } else {
            dados[0] = CntlCliente.recuperar(id);
        }

        for (int i = 0; i < dados.length; i++) {
            this.jCcliente.addItem(dados[i][2]);
            this.listCliente.add(i, Integer.parseInt(dados[i][0]));
        }
    }

    private void procuraVendedor(int id) {

        this.jCvendedor.removeAllItems();
        this.listVendedor.removeAll(this.listVendedor);

        String[][] dados = null;
        if (id == 0) {
            dados = CntlVendedor.recuperarTodos();
        } else {
            dados[0] = CntlVendedor.recuperar(id);
        }

        for (int i = 0; i < dados.length; i++) {
            this.jCvendedor.addItem(dados[i][1]);
            this.listVendedor.add(i, Integer.parseInt(dados[i][0]));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_titulo = new javax.swing.JPanel();
        txt_titulo = new javax.swing.JLabel();
        pn_dados = new javax.swing.JPanel();
        jCcliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jCvendedor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bt_editar_cliente = new javax.swing.JButton();
        bt_add_cliente = new javax.swing.JButton();
        bt_editar_vendedor = new javax.swing.JButton();
        bt_add_vendedor = new javax.swing.JButton();
        txt_codigo = new javax.swing.JLabel();
        cmp_codigo = new javax.swing.JTextField();
        txt_data = new javax.swing.JLabel();
        cmp_data = new javax.swing.JTextField();
        cmp_observação = new javax.swing.JTextField();
        pn_botao = new javax.swing.JPanel();
        bt_salvar = new javax.swing.JButton();
        bt_excluir = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        bt_lista_produtos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pn_titulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_titulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_titulo.setText("CADASTRO DE PEDIDO");

        javax.swing.GroupLayout pn_tituloLayout = new javax.swing.GroupLayout(pn_titulo);
        pn_titulo.setLayout(pn_tituloLayout);
        pn_tituloLayout.setHorizontalGroup(
            pn_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pn_tituloLayout.setVerticalGroup(
            pn_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        pn_dados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCclienteMouseClicked(evt);
            }
        });

        jLabel2.setText("CLIENTE");

        jCvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCvendedorActionPerformed(evt);
            }
        });

        jLabel3.setText("VENDEDOR");

        jLabel4.setText("OBSERVAÇÃO");

        bt_editar_cliente.setText("...");
        bt_editar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editar_clienteActionPerformed(evt);
            }
        });

        bt_add_cliente.setText("+");
        bt_add_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_clienteActionPerformed(evt);
            }
        });

        bt_editar_vendedor.setText("...");
        bt_editar_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editar_vendedorActionPerformed(evt);
            }
        });

        bt_add_vendedor.setText("+");
        bt_add_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_vendedorActionPerformed(evt);
            }
        });

        txt_codigo.setText("CODIGO");

        cmp_codigo.setEditable(false);
        cmp_codigo.setBackground(new java.awt.Color(255, 255, 255));

        txt_data.setText("DATA");

        cmp_data.setEditable(false);
        cmp_data.setBackground(new java.awt.Color(255, 255, 255));

        cmp_observação.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cmp_observação.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cmp_observação.setDragEnabled(true);

        javax.swing.GroupLayout pn_dadosLayout = new javax.swing.GroupLayout(pn_dados);
        pn_dados.setLayout(pn_dadosLayout);
        pn_dadosLayout.setHorizontalGroup(
            pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_dadosLayout.createSequentialGroup()
                        .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(pn_dadosLayout.createSequentialGroup()
                                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCcliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_dadosLayout.createSequentialGroup()
                                        .addComponent(bt_add_cliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bt_editar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pn_dadosLayout.createSequentialGroup()
                                        .addComponent(bt_add_vendedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bt_editar_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pn_dadosLayout.createSequentialGroup()
                                .addComponent(txt_codigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmp_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_data)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmp_data, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmp_observação))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_dadosLayout.setVerticalGroup(
            pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_codigo)
                    .addComponent(cmp_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_data)
                    .addComponent(cmp_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_dadosLayout.createSequentialGroup()
                        .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pn_dadosLayout.createSequentialGroup()
                                .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bt_add_cliente)
                                    .addComponent(bt_editar_cliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(pn_dadosLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11)))
                        .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCvendedor)
                            .addGroup(pn_dadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_add_vendedor)
                                .addComponent(bt_editar_vendedor)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(pn_dadosLayout.createSequentialGroup()
                        .addComponent(jCcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmp_observação, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_botao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bt_salvar.setText("Salvar");
        bt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvarActionPerformed(evt);
            }
        });

        bt_excluir.setText("Excluir");
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_lista_produtos.setText("Lista de produtos");
        bt_lista_produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lista_produtosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_botaoLayout = new javax.swing.GroupLayout(pn_botao);
        pn_botao.setLayout(pn_botaoLayout);
        pn_botaoLayout.setHorizontalGroup(
            pn_botaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_botaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_lista_produtos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_botaoLayout.setVerticalGroup(
            pn_botaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_botaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_botaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_salvar)
                    .addComponent(bt_excluir)
                    .addComponent(bt_cancelar)
                    .addComponent(bt_lista_produtos))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pn_titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_botao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_dados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pn_dados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pn_botao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvarActionPerformed
        dadosPedido[1] = this.cmp_data.getText();
        dadosPedido[2] = this.cmp_observação.getText();
        dadosPedido[3] = String.valueOf(this.listCliente.get(jCcliente.getSelectedIndex()));
        dadosPedido[4] = String.valueOf(this.listVendedor.get(jCvendedor.getSelectedIndex()));

        CntlPedido.salvar(dadosPedido);
    }//GEN-LAST:event_bt_salvarActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        CntlPedido.deletar(Integer.parseInt(cmp_codigo.getText()));
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        this.cmp_codigo.setText(dadosPedido[0]);
        this.cmp_data.setText(dadosPedido[1]);
        this.cmp_observação.setText(dadosPedido[2]);
        procuraCliente(Integer.parseInt(dadosPedido[3]));
        procuraVendedor(Integer.parseInt(dadosPedido[4]));
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_lista_produtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_lista_produtosActionPerformed
        CrudListaProduto viewListaProduto = new CrudListaProduto();
        viewListaProduto.setVisible(true);
    }//GEN-LAST:event_bt_lista_produtosActionPerformed

    private void bt_add_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_vendedorActionPerformed
        CrudVendedor viewVendedor = new CrudVendedor(0);
        viewVendedor.setVisible(true);
    }//GEN-LAST:event_bt_add_vendedorActionPerformed

    private void bt_editar_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editar_vendedorActionPerformed
        CrudVendedor viewVendedor = new CrudVendedor(listVendedor.get(jCvendedor.getSelectedIndex()));
        viewVendedor.setVisible(true);
    }//GEN-LAST:event_bt_editar_vendedorActionPerformed

    private void bt_add_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_clienteActionPerformed
        CrudCliente viewCliente = new CrudCliente(0);
        viewCliente.setVisible(true);
    }//GEN-LAST:event_bt_add_clienteActionPerformed

    private void bt_editar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editar_clienteActionPerformed
        CrudCliente viewCliente = new CrudCliente(listCliente.get(jCcliente.getSelectedIndex()));
        viewCliente.setVisible(true);
    }//GEN-LAST:event_bt_editar_clienteActionPerformed

    private void jCvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCvendedorActionPerformed
        procuraVendedor(0);
    }//GEN-LAST:event_jCvendedorActionPerformed

    private void jCclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCclienteMouseClicked
        procuraCliente(0);
    }//GEN-LAST:event_jCclienteMouseClicked

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
            java.util.logging.Logger.getLogger(CrudPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudPedido().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_cliente;
    private javax.swing.JButton bt_add_vendedor;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar_cliente;
    private javax.swing.JButton bt_editar_vendedor;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_lista_produtos;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JTextField cmp_codigo;
    private javax.swing.JTextField cmp_data;
    private javax.swing.JTextField cmp_observação;
    private javax.swing.JComboBox<String> jCcliente;
    private javax.swing.JComboBox<String> jCvendedor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pn_botao;
    private javax.swing.JPanel pn_dados;
    private javax.swing.JPanel pn_titulo;
    private javax.swing.JLabel txt_codigo;
    private javax.swing.JLabel txt_data;
    private javax.swing.JLabel txt_titulo;
    // End of variables declaration//GEN-END:variables
}
