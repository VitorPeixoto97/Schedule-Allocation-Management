package horarios.presentation;

/**
 *
 * @author Francisco Oliveira
 * @author Raul Vilas Boas
 * @author Vitor Peixoto
 */
import horarios.business.*;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class FormTrabEstudante extends javax.swing.JFrame {
    private Facade facade;
    private final JFrame formPai;
    private Collection<Registo> turnos = new HashSet<>();
    private DefaultTableModel modelTable;
    private DefaultListModel<String> modelList = new DefaultListModel<>();

    public FormTrabEstudante(Facade facade, JFrame formPai) {
        this.facade = facade;
        this.formPai = formPai;
        refreshTable();
        refreshList();
        initComponents();
        this.setDefaultCloseOperation(0);
        this.setLocationRelativeTo(null);
        refreshComboUC();
        refreshComboTurno();
        listPedidosTroca.setSelectedIndex(0);
        fases();
    }
    
    private void fases(){
        int fase = facade.getDocenteDAO().getFase();
        if(fase==1 || fase==3){
            tabEstudante1.setEnabledAt(0, false);
            tabEstudante1.setBackgroundAt(0, Color.gray);
            tabEstudante1.setForegroundAt(0, Color.darkGray);
            tabEstudante1.setEnabledAt(1, false);
            tabEstudante1.setBackgroundAt(1, Color.gray);
            tabEstudante1.setForegroundAt(1, Color.darkGray);
            tabEstudante1.setSelectedIndex(2);
        }
    }
    
    private void refreshComboUC() { 
        comboUCTT.removeAllItems();
        for(Integer i : facade.getRegistoDAO().listarUCAluno(facade.getSessao().getID())) {
            comboUCTT.addItem(Integer.toString(i));
        }
    }
    
    private void refreshComboTurno() {    
        int codUC = Integer.parseInt(comboUCTT.getSelectedItem().toString());
        comboAtTurnoTT.removeAllItems();
        comboNovoTurnoTT.removeAllItems();
        comboAtTurnoTT.addItem(Integer.toString(facade.getRegistoDAO().getTurno(facade.getSessao().getID(), codUC)));
        for(Turno t : facade.getTurnoDAO().turnosDaUC(codUC)) {
            comboNovoTurnoTT.addItem(Integer.toString(t.getTurno()));
        }
        comboNovoTurnoTT.removeItem(comboAtTurnoTT.getSelectedItem().toString());
    }
    
    private void refreshTable(){
        turnos = facade.getTurnos(facade.getSessao().getID());
        
        String[] colunas = {"UC", "Tipo", "Turno", "% Faltas", "Dia", "Hora",};
        Object[][] data = new Object[facade.getRegistoDAO().getNumTurnos(facade.getSessao().getID())][6];
        int i=0;
        for (Registo r : turnos){
            data[i][0] = r.getUC().getAbrev();
            data[i][1] = r.getTurno().getTipoAula().getTipo();
            data[i][2] = r.getTurno().getTurno();
            if(r.getAulas()==0){
                data[i][3] = 0;
            } else{
                data[i][3] = (r.getFaltas()/r.getAulas())*100;
            }
            data[i][4] = r.getTurno().getDia();
            data[i][5] = r.getTurno().getHora();
            
            i++;
        }
        modelTable = new DefaultTableModel(data,colunas);
    }
    
    private void refreshList(){
        modelList.clear();
        Collection<Troca> trocas = facade.verTrocas(facade.getSessao().getID());
        for(Troca t : trocas){
            if(!modelList.contains(t.getUC()) ) modelList.addElement(Integer.toString(t.getID()));
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

        tabEstudante1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        comboUCTT = new javax.swing.JComboBox<>();
        buttonTrocar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboAtTurnoTT = new javax.swing.JComboBox<>();
        comboNovoTurnoTT = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboAlunoTroca = new javax.swing.JComboBox<>();
        buttonPedidoTroca = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPedidosTroca = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        textTurnoAtual = new javax.swing.JTextField();
        textDiaAtual = new javax.swing.JTextField();
        textHoraAtual = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textTurnoNovo = new javax.swing.JTextField();
        textDiaNovo = new javax.swing.JTextField();
        textHoraNovo = new javax.swing.JTextField();
        buttonTrocaAceitar = new javax.swing.JButton();
        buttonTrocaRecusar = new javax.swing.JButton();
        textUC = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboUCTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUCTTActionPerformed(evt);
            }
        });

        buttonTrocar.setText("Efetuar troca imediata");
        buttonTrocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTrocarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel1.setText("UC");

        comboNovoTurnoTT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboNovoTurnoTTFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel2.setText("Atual turno");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel6.setText("Novo turno");

        buttonPedidoTroca.setText("Efetuar pedido de troca ");
        buttonPedidoTroca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPedidoTrocaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel7.setText("Caso o turno esteja lotado, indique um aluno disposto a trocar consigo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUCTT, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboAtTurnoTT, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(comboNovoTurnoTT, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboAlunoTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPedidoTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonTrocar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUCTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAtTurnoTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboNovoTurnoTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonTrocar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPedidoTroca)
                    .addComponent(comboAlunoTroca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        tabEstudante1.addTab("Trocar de turno", jPanel1);

        listPedidosTroca.setModel(modelList);
        listPedidosTroca.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listPedidosTrocaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listPedidosTroca);

        jLabel4.setText("Turno atual:");

        textTurnoAtual.setEditable(false);

        textDiaAtual.setEditable(false);

        textHoraAtual.setEditable(false);

        jLabel5.setText("Turno novo:");

        textTurnoNovo.setEditable(false);

        textDiaNovo.setEditable(false);

        textHoraNovo.setEditable(false);

        buttonTrocaAceitar.setText("Aceitar");
        buttonTrocaAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTrocaAceitarActionPerformed(evt);
            }
        });

        buttonTrocaRecusar.setText("Recusar");
        buttonTrocaRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTrocaRecusarActionPerformed(evt);
            }
        });

        textUC.setEditable(false);
        textUC.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        textUC.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buttonTrocaAceitar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(textHoraAtual, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textHoraNovo)
                                        .addComponent(buttonTrocaRecusar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(textDiaAtual)
                                        .addComponent(textTurnoAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textTurnoNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(textDiaNovo))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(textUC, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textTurnoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textTurnoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textDiaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDiaNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textHoraNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textHoraAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonTrocaAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTrocaRecusar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tabEstudante1.addTab("Pedidos de troca", jPanel2);

        jTable1.setModel(modelTable);
        jTable1.setEnabled(false);
        jTable1.setFocusable(false);
        jScrollPane3.setViewportView(jTable1);

        tabEstudante1.addTab("Consultar turnos", jScrollPane3);

        buttonLogout.setText("Logout");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabEstudante1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(buttonLogout)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabEstudante1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTrocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTrocarActionPerformed
        int codUC = Integer.parseInt(comboUCTT.getSelectedItem().toString());
        int numTurno1 = Integer.parseInt(comboAtTurnoTT.getSelectedItem().toString());
        int numTurno2 = Integer.parseInt(comboNovoTurnoTT.getSelectedItem().toString());
        
        try {
            facade.trocaTurnoEspecial(facade.getSessao().getID(), codUC, numTurno1, numTurno2);
        } catch (SQLException ex) {
            Logger.getLogger(FormTrabEstudante.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refreshTable();
        this.refreshComboTurno();
        jTable1.setModel(modelTable);
        
    }//GEN-LAST:event_buttonTrocarActionPerformed

    private void comboUCTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUCTTActionPerformed
        refreshComboTurno();
    }//GEN-LAST:event_comboUCTTActionPerformed

    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        facade.logout(this,formPai);
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void buttonPedidoTrocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPedidoTrocaActionPerformed
        int idAlunoRequerente = facade.getSessao().getID();
        int idAlunoRequerido  = Integer.parseInt(comboAlunoTroca.getSelectedItem().toString());
        int codUC = Integer.parseInt(comboUCTT.getSelectedItem().toString());
        int nTurnoRequerente = Integer.parseInt(comboAtTurnoTT.getSelectedItem().toString());
        int nTurnoRequerido  = Integer.parseInt(comboNovoTurnoTT.getSelectedItem().toString());
        Troca t = new Troca(0, idAlunoRequerente, idAlunoRequerido, codUC, nTurnoRequerente, nTurnoRequerido);
        try {
            facade.trocaTurno(t);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormTrabEstudante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonPedidoTrocaActionPerformed

    private void comboNovoTurnoTTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboNovoTurnoTTFocusLost
        comboAlunoTroca.removeAllItems();
        for(Integer i : facade.getRegistoDAO().alunosInscritosTurno(Integer.parseInt(comboNovoTurnoTT.getSelectedItem().toString()))) {
            comboAlunoTroca.addItem(Integer.toString(i));
        }
    }//GEN-LAST:event_comboNovoTurnoTTFocusLost

    private void listPedidosTrocaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listPedidosTrocaValueChanged
        Collection<Troca> trocas = facade.verTrocas(facade.getSessao().getID());

        if(!trocas.isEmpty()){ 
            Troca t = facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue()));
        
            textTurnoAtual.setText("Turno "+Integer.toString(facade.getTurnoDAO().getTurno(t.getTID2()).getTurno()));
            textTurnoNovo.setText("Turno "+Integer.toString(facade.getTurnoDAO().getTurno(t.getTID1()).getTurno()));
            textDiaAtual.setText(facade.getTurnoDAO().getTurno(facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue())).getTID2()).getDia());
            textDiaNovo.setText(facade.getTurnoDAO().getTurno(facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue())).getTID1()).getDia());
            textHoraAtual.setText(Integer.toString(facade.getTurnoDAO().getTurno(facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue())).getTID2()).getHora())+"h");
            textHoraNovo.setText(Integer.toString(facade.getTurnoDAO().getTurno(facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue())).getTID1()).getHora())+"h");
            textUC.setText(facade.getTurnoDAO().getUC(t.getUC()).getAbrev());
        }
    }//GEN-LAST:event_listPedidosTrocaValueChanged

    private void buttonTrocaAceitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTrocaAceitarActionPerformed
        Troca t = facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue()));
        try {
            facade.aceitarTroca(t);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormTrabEstudante.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshList();
        listPedidosTroca.setModel(modelList);
        refreshTable();
        refreshComboTurno();
        jTable1.setModel(modelTable);
    }//GEN-LAST:event_buttonTrocaAceitarActionPerformed

    private void buttonTrocaRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTrocaRecusarActionPerformed
        Troca t = facade.getTrocaDAO().getTroca(Integer.parseInt(listPedidosTroca.getSelectedValue()));
        try {
            facade.recusarTroca(t);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormTrabEstudante.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshList();
        listPedidosTroca.setModel(modelList);
    }//GEN-LAST:event_buttonTrocaRecusarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonPedidoTroca;
    private javax.swing.JButton buttonTrocaAceitar;
    private javax.swing.JButton buttonTrocaRecusar;
    private javax.swing.JButton buttonTrocar;
    private javax.swing.JComboBox<String> comboAlunoTroca;
    private javax.swing.JComboBox<String> comboAtTurnoTT;
    private javax.swing.JComboBox<String> comboNovoTurnoTT;
    private javax.swing.JComboBox<String> comboUCTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JList<String> listPedidosTroca;
    private javax.swing.JTabbedPane tabEstudante1;
    private javax.swing.JTextField textDiaAtual;
    private javax.swing.JTextField textDiaNovo;
    private javax.swing.JTextField textHoraAtual;
    private javax.swing.JTextField textHoraNovo;
    private javax.swing.JTextField textTurnoAtual;
    private javax.swing.JTextField textTurnoNovo;
    private javax.swing.JTextField textUC;
    // End of variables declaration//GEN-END:variables
}