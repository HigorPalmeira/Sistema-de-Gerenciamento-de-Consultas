/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.view;

import com.higorpalmeira.github.gerenciadorconsultas.view.detalhes.frmDetalhesMedico;
import com.higorpalmeira.github.gerenciadorconsultas.view.editar.frmEditarMedico;
import com.higorpalmeira.github.gerenciadorconsultas.view.criar.frmCriarMedico;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.higorpalmeira.github.gerenciadorconsultas.client.EspecialidadeClient;
import com.higorpalmeira.github.gerenciadorconsultas.client.MedicoClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.service.EspecialidadeService;
import com.higorpalmeira.github.gerenciadorconsultas.service.MedicoService;
import javax.swing.JOptionPane;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validador;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author higor
 */
public class frmMedico extends frmGenerico {

    private final MedicoService medicoService;

    private final EspecialidadeService especialidadeService;

    private boolean carregarTabela;

    /**
     * Creates new form frmMedico
     */
    public frmMedico() {
        initComponents();

        btnDetalhes.setVisible(false);

        settings();

        this.medicoService = new MedicoService(new MedicoClient());
        this.especialidadeService = new EspecialidadeService(new EspecialidadeClient());
        this.carregarTabela = true;

        this.listar_medicos();
    }

    private void pesquisa_medico() {

        if (txtPesquisa.getText() == null || txtPesquisa.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "O campo de pesquisa não pode estar vazio!", "Não é possível pesquisar", JOptionPane.ERROR_MESSAGE);
            txtPesquisa.requestFocus();

        } else {

            if (rbCrm.isSelected()) {

                if (Validador.isCrm(txtPesquisa.getText().trim().toUpperCase())) {

                    SaidaSimplesMedicoDto medicoDto = this.medicoService.buscarSaidaSimplesMedicoDtoPorCrm(txtPesquisa.getText().trim().toUpperCase());

                    this.preencher_tabela(medicoDto);

                } else {
                    JOptionPane.showMessageDialog(this, "\"" + txtPesquisa.getText() + "\" não é um CRM válido! Informe um válido!", "CRM Inválido", JOptionPane.ERROR_MESSAGE);
                    txtPesquisa.requestFocus();
                }

            } else if (rbEmail.isSelected()) {
                
                if (Validador.isEmail(txtPesquisa.getText().trim())) {
                    
                    SaidaSimplesMedicoDto medicoDto = this.medicoService.buscarSaidaSimplesMedicoDtoPorEmail(txtPesquisa.getText().trim());
                    
                    this.preencher_tabela(medicoDto);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this, "\" " + txtPesquisa.getText() + "\" não é um E-mail válido! Informe um válido!", "E-mail Inválido", JOptionPane.ERROR_MESSAGE);
                    txtPesquisa.requestFocus();
                    
                }
                
            } else if (rbNome.isSelected()) {
                
                if (txtPesquisa.getText().isBlank()) {
                    
                    JOptionPane.showMessageDialog(this, "\"" + txtPesquisa.getText() + "\" não é um nome válido! Informe um válido!", "Nome Inválido", JOptionPane.ERROR_MESSAGE);
                    txtPesquisa.requestFocus();
                    
                } else {
                    
                    List<SaidaSimplesMedicoDto> listaMedicosDto = this.medicoService.listarSaidasSimplesMedicoDtoPorNome(txtPesquisa.getText().trim());
                    
                    this.preencher_tabela(listaMedicosDto);
                    
                }
                
            } else if (rbTelefone.isSelected()) {

                if (Validador.isTelefone(txtPesquisa.getText().trim())) {
                    
                    SaidaSimplesMedicoDto medicoDto = this.medicoService.buscarSaidaSimplesMedicoDtoPorTelefone(txtPesquisa.getText().trim());
                    
                    this.preencher_tabela(medicoDto);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this, "\"" + txtPesquisa.getText().trim() + "\" não é um telefone válido! Informe um válido!", "Telefone Válido", JOptionPane.ERROR_MESSAGE);
                    txtPesquisa.requestFocus();
                    
                }
                
            }

            txtPesquisa.setText("");
        }

    }

    private void preencher_tabela(SaidaSimplesMedicoDto medicoDto) {

        DefaultTableModel dtm = (DefaultTableModel) tblMedicos.getModel();
        dtm.setNumRows(0);

        if (medicoDto != null && medicoDto.getId() != null) {
            Object[] obj = {
                medicoDto.getId(),
                medicoDto.getNome(),
                medicoDto.getCrm(),
                medicoDto.getEspecialidade().getDescricao(),
                medicoDto.getEmail(),
                medicoDto.getTelefone()
            };

            dtm.addRow(obj);
        } else {
            JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum médico!", "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void preencher_tabela(List<SaidaSimplesMedicoDto> listaMedicosDto) {

        DefaultTableModel dtm = (DefaultTableModel) tblMedicos.getModel();
        dtm.setNumRows(0);

        if (!listaMedicosDto.isEmpty()) {

            for (SaidaSimplesMedicoDto medicoDto : listaMedicosDto) {

                Object[] obj = {
                    medicoDto.getId(),
                    medicoDto.getNome(),
                    medicoDto.getCrm(),
                    medicoDto.getEspecialidade().getDescricao(),
                    medicoDto.getEmail(),
                    medicoDto.getTelefone()};

                dtm.addRow(obj);

            }

        }

    }

    private void listar_medicos() {

        if (this.carregarTabela) {

            this.carregarTabela = false;

            List<SaidaSimplesMedicoDto> listaMedicos = medicoService.listarSaidasSimplesMedicoDto();

            /*
            DefaultTableModel dtm = (DefaultTableModel) tblMedicos.getModel();
            dtm.setNumRows(0);
            */
            
            this.preencher_tabela(listaMedicos);

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

        pnlTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtPesquisa = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        rbCrm = new javax.swing.JRadioButton();
        rbNome = new javax.swing.JRadioButton();
        rbEmail = new javax.swing.JRadioButton();
        rbTelefone = new javax.swing.JRadioButton();
        pnlTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();
        pnlBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnDetalhes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("medicos"); // NOI18N
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(0, 204, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MÉDICOS");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(16, 16, 16))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));

        txtPesquisa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoom.png"))); // NOI18N
        btnPesquisar.setMnemonic('p');
        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        rbCrm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbCrm.setSelected(true);
        rbCrm.setText("CRM");
        rbCrm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCrmMouseClicked(evt);
            }
        });

        rbNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbNome.setText("Nome");
        rbNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNomeMouseClicked(evt);
            }
        });

        rbEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbEmail.setText("E-mail");
        rbEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbEmailMouseClicked(evt);
            }
        });

        rbTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbTelefone.setText("Telefone");
        rbTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbTelefoneMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btnPesquisar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbNome)
                            .addComponent(rbCrm))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbEmail)
                            .addComponent(rbTelefone))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCrm)
                    .addComponent(rbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNome)
                    .addComponent(rbTelefone))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CRM", "Especialidade", "E-mail", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicos.setColumnSelectionAllowed(true);
        tblMedicos.getTableHeader().setReorderingAllowed(false);
        tblMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicos);
        tblMedicos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblMedicos.getColumnModel().getColumnCount() > 0) {
            tblMedicos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblMedicos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblMedicos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblMedicos.getColumnModel().getColumn(1).setMaxWidth(220);
            tblMedicos.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblMedicos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblMedicos.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblMedicos.getColumnModel().getColumn(3).setMaxWidth(200);
            tblMedicos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblMedicos.getColumnModel().getColumn(4).setMaxWidth(230);
            tblMedicos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tblMedicos.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        javax.swing.GroupLayout pnlTabelaLayout = new javax.swing.GroupLayout(pnlTabela);
        pnlTabela.setLayout(pnlTabelaLayout);
        pnlTabelaLayout.setHorizontalGroup(
            pnlTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTabelaLayout.setVerticalGroup(
            pnlTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTabelaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBotoes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnNovo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNovo.setMnemonic('n');
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditar.setMnemonic('e');
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeletar.setMnemonic('d');
        btnDeletar.setText("DELETAR");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnDetalhes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetalhes.setMnemonic('v');
        btnDetalhes.setText("VER DETALHES");
        btnDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDetalhes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnNovo)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnDeletar)
                .addGap(18, 18, 18)
                .addComponent(btnDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicosMouseClicked

        if (evt.getClickCount() == 2) {
            JOptionPane.showMessageDialog(this, "Item na tabela selecionado?\nSem suporte para isso por enquanto!");
        }

    }//GEN-LAST:event_tblMedicosMouseClicked

    private void rbCrmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCrmMouseClicked

        rbCrm.setSelected(true);
        rbEmail.setSelected(false);
        rbNome.setSelected(false);
        rbTelefone.setSelected(false);

    }//GEN-LAST:event_rbCrmMouseClicked

    private void rbNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNomeMouseClicked

        rbNome.setSelected(true);
        rbCrm.setSelected(false);
        rbEmail.setSelected(false);
        rbTelefone.setSelected(false);

    }//GEN-LAST:event_rbNomeMouseClicked

    private void rbEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbEmailMouseClicked

        rbEmail.setSelected(true);
        rbNome.setSelected(false);
        rbCrm.setSelected(false);
        rbTelefone.setSelected(false);

    }//GEN-LAST:event_rbEmailMouseClicked

    private void rbTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbTelefoneMouseClicked

        rbTelefone.setSelected(true);
        rbEmail.setSelected(false);
        rbNome.setSelected(false);
        rbCrm.setSelected(false);

    }//GEN-LAST:event_rbTelefoneMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        this.pesquisa_medico();

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        frmCriarMedico frmCriarMedico = new frmCriarMedico(this.medicoService, this.especialidadeService);
        frmCriarMedico.setVisible(true);

    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.pesquisa_medico();
        }

    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        int idx = tblMedicos.getSelectedRow();

        if (idx < 0) {

            JOptionPane.showMessageDialog(this, "É necessário que você selecione um médico, na tabela, para editá-lo!", "Médico não selecionado", JOptionPane.INFORMATION_MESSAGE);

        } else {

            String id = String.valueOf(tblMedicos.getValueAt(idx, 0));

            UUID idMedico = UUID.fromString(id);

            frmEditarMedico frmEditarMedico = new frmEditarMedico(idMedico, this.medicoService, this.especialidadeService);
            frmEditarMedico.setVisible(true);

        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesActionPerformed

        int idx = tblMedicos.getSelectedRow();

        String nome = "Não há valores"; //(String) tblMedicos.getModel().getValueAt(idx, 1);

        JOptionPane.showMessageDialog(this, "Linha " + idx + " selecionada!\nNome: " + nome, "Ver detalhes", JOptionPane.INFORMATION_MESSAGE);

        frmDetalhesMedico frmDetalhesMedico = new frmDetalhesMedico();
        frmDetalhesMedico.setVisible(true);
    }//GEN-LAST:event_btnDetalhesActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

        this.listar_medicos();

    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

        this.carregarTabela = true;

    }//GEN-LAST:event_formWindowLostFocus

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed

        int idx = tblMedicos.getSelectedRow();

        if (idx < 0) {

            JOptionPane.showMessageDialog(this, "Selecione um médico na tabela para excluí-lo!", "Selecione um médico", JOptionPane.ERROR_MESSAGE);

        } else {

            int opcao = JOptionPane.showConfirmDialog(this, "Deseja mesmo deletar o médico selecionado?", "Tem certeza?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {

                String id = String.valueOf(tblMedicos.getValueAt(idx, 0));
                UUID idMedico = UUID.fromString(id);

                boolean statusOperacao = this.medicoService.deletarMedico(idMedico);

                if (statusOperacao) {

                    JOptionPane.showMessageDialog(this, "Médico deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(this, "O médico não foi deletado!", "Falha", JOptionPane.ERROR_MESSAGE);

                }

            } else {

                JOptionPane.showMessageDialog(this, "Operação cancelada com sucesso!", "Cancelado", JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }//GEN-LAST:event_btnDeletarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnDetalhes;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlTabela;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JRadioButton rbCrm;
    private javax.swing.JRadioButton rbEmail;
    private javax.swing.JRadioButton rbNome;
    private javax.swing.JRadioButton rbTelefone;
    private javax.swing.JTable tblMedicos;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
