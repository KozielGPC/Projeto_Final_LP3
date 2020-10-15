package Main;

import DAOs.DAOMateria;
import DAOs.DAOTurma;
import DAOs.DAOTurmaHasMateria;
import DAOs.DAOTurmaHasMateriaPK;
import Entidades.Materia;
import static Entidades.Materia_.turmaHasMateriaList;
import Entidades.Turma;
import Entidades.TurmaHasMateria;
import Entidades.TurmaHasMateriaPK;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import static java.awt.Frame.NORMAL;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
class GUI_turmas_materias extends JDialog {

    private Container cp;
    GridBagConstraints cons = new GridBagConstraints();
    GridBagLayout GridBagLayout = new GridBagLayout();
    JComboBox comboBox = new JComboBox();
    JComboBox cbDia = new JComboBox();
    JComboBox cbHorario = new JComboBox();
    JComboBox cbHoras_aula = new JComboBox();
    DateTextField dateTextField1 = new DateTextField();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    private JColorChooser colorchooser = new JColorChooser();

    private CardLayout CardLayout = new CardLayout();
    private BorderLayout BorderLayout = new BorderLayout();
    private JPanel pnNorte = new JPanel(BorderLayout);
    private JPanel pnCentro = new JPanel(GridBagLayout);
    private JPanel pnSul = new JPanel(CardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));

    private JPanel pnCard = new JPanel(CardLayout);
    private JPanel pnMateria = new JPanel(new BorderLayout());
    private JPanel pnAluno = new JPanel(new BorderLayout());
    private JPanel pnNorteNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnNorteSul = new JPanel(GridBagLayout);

    private JLabel lbId = new JLabel("Código Turma");
    private JLabel lbMaterias = new JLabel("Matérias");
    private JLabel lbHorario = new JLabel("Turno");
    private JLabel lbHoras_aula = new JLabel("Horas-Aula");
    private JLabel lbDia = new JLabel("Dia");

    private JCheckBox checkbox = new JCheckBox("Cor do layout personalizada");

    private JTextField tfID = new JTextField(5);
    private JTextField tfNome = new JTextField(30);
    private JTextField tfEmailProfessor = new JTextField(30);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btNBuscar = new JButton("Nova busca");
    private JButton btVincular = new JButton("Vincular");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Desvincular");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton("Gravar");
    private JButton btPAs_PDF = new JButton("Gerar PDF - PA's");
    private JButton btMateria = new JButton();
    private JButton btAluno = new JButton();
    private JButton btProfessor = new JButton();
    private JButton btVoltar = new JButton("Menu");
    private JButton btExcluirTudo = new JButton("Excluir tudo e voltar ao menu");
    private JButton btCor = new JButton("Escolher cor");

    private Color color;

    String[] colunas = new String[]{"Código", "Nome", "CPF", "Data de Nascimento"};
    String[][] dados = new String[0][4];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    JScrollPane scrollList = new JScrollPane();

    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem

    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas

    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar

    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea

    String acao = "";

    DAOTurma daoTurma = new DAOTurma();

    DAOMateria daoMateria = new DAOMateria();
    DAOTurmaHasMateria daoTurmaHasMateria = new DAOTurmaHasMateria();
    DAOTurmaHasMateriaPK daoTurmaHasMateriaPK = new DAOTurmaHasMateriaPK();

    Turma turma = new Turma();
    Materia materia = new Materia();
    TurmaHasMateria turmaHasMateria = new TurmaHasMateria();
    TurmaHasMateriaPK turmaHasMateriaPK = new TurmaHasMateriaPK();
    Ferramentas fer = new Ferramentas();

    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void travarTextFields(boolean campo) {
        tfID.setEditable(campo); //permite que o usuario digite nesse textField
        if (!campo) {
            tfNome.requestFocus();
            tfNome.selectAll();
        }
    }

    private void limparValoresDosAtributos() {
        comboBox.setSelectedIndex(0);
    }

    //construtor da classe GUI_Materia
    public GUI_turmas_materias() {
        //abrir o arquivo
        btCor.setVisible(false);
        comboBox.setEnabled(false);
        cbHorario.setEnabled(false);
        cbHoras_aula.setEnabled(false);
        cbDia.setEnabled(false);
        tabela.setEnabled(false);

        //faz com que a última linha do 
        //jTextArea seja exibida
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnMateria);
        pnNorte.add(pnNorteNorte, BorderLayout.NORTH);
        pnNorte.add(pnNorteSul, BorderLayout.SOUTH);

        pnNorteNorte.add(btVoltar);
        pnNorteNorte.add(lbId);
        pnNorteNorte.add(tfID);
        pnNorteNorte.add(btBuscar);
        pnNorteNorte.add(btNBuscar);
        pnNorteNorte.add(btPAs_PDF);
        btNBuscar.setVisible(false);
        btPAs_PDF.setVisible(false);
        pnNorteNorte.add(btVincular);
        pnNorteNorte.add(btAlterar);
        pnNorteNorte.add(btExcluir);
        pnNorteNorte.add(btSalvar);
        pnNorteNorte.add(btCancelar);
        pnNorteNorte.add(btListar);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 0;
        pnNorteSul.add(checkbox, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 2;
        cons.gridx = 0;
        pnNorteSul.add(btCor, cons);

        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 0;
        pnCentro.add(lbMaterias, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 1;
        pnCentro.add(comboBox, cons);

        cons.gridy = 1;
        cons.gridx = 0;
        pnCentro.add(lbDia, cons);
        cons.gridy = 1;
        cons.gridx = 1;
        pnCentro.add(cbDia, cons);
        cons.gridy = 2;
        cons.gridx = 0;
        pnCentro.add(lbHorario, cons);
        cons.gridy = 2;
        cons.gridx = 1;
        pnCentro.add(cbHorario, cons);
        cons.gridy = 3;
        cons.gridx = 0;
        pnCentro.add(lbHoras_aula, cons);
        cons.gridy = 3;
        cons.gridx = 1;
        pnCentro.add(cbHoras_aula, cons);
        lbDia.setVisible(false);
        cbDia.setVisible(false);

        cbDia.addItem("Segunda");
        cbDia.addItem("Terça");
        cbDia.addItem("Quarta");
        cbDia.addItem("Quinta");
        cbDia.addItem("Sexta");

        cbHoras_aula.addItem("1");
        cbHoras_aula.addItem("2");
        cbHoras_aula.addItem("3");
        cbHoras_aula.addItem("4");
        cbHoras_aula.addItem("5");

        cbHorario.addItem("Manhã");
        cbHorario.addItem("Tarde");
        cbHorario.addItem("Noite");

        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("Registro de atividades"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");

        pnMateria.add(pnNorte, BorderLayout.NORTH);
        pnMateria.add(pnCentro, BorderLayout.CENTER);
        pnMateria.add(pnSul, BorderLayout.SOUTH);

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/busca.png")));
        btNBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/busca.png")));
        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/lista.jpg")));
        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/alterar.png")));
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/excluir.png")));
        btExcluirTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/excluir.png")));
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/voltar.jpg")));
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/cancelar.png")));
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/salvar.png")));
        btVincular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/inserir.png")));
        btCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/cor.jpg")));

        btExcluir.setToolTipText("Excluir");
        btVoltar.setToolTipText("Menu");
        btBuscar.setToolTipText("Buscar");
        btNBuscar.setToolTipText("Nova busca");
        btListar.setToolTipText("Listar");
        btSalvar.setToolTipText("Salvar");
        btVincular.setToolTipText("Vincular");
        btCancelar.setToolTipText("Cancelar");
        btAlterar.setToolTipText("Alterar");
        btExcluirTudo.setToolTipText("Apaga os dados e volta ao menu");
        btCor.setToolTipText("Escolher cor");

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btVincular.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);

        travarTextFields(true);
        textAreaMsg.setEditable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

//################ COLOR ##################
        btCor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = colorchooser.showDialog(null, "Escolher Cor", null);
                pnSulListagem.setBackground(color);
                pnSulMsg.setBackground(color);
                pnCentro.setBackground(color);
                pnNorteSul.setBackground(color);
                pnNorteNorte.setBackground(color);

            }
        });
//################## Check Box ##################33
        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkbox.isSelected() == false) {
                    pnSulListagem.setBackground(null);
                    pnSulMsg.setBackground(null);
                    pnCentro.setBackground(null);
                    pnNorteSul.setBackground(null);
                    pnNorteNorte.setBackground(null);
                    cp.setBackground(null);
                    btCor.setVisible(false);

                } else {

                    btCor.setVisible(true);
                    pnSulListagem.setBackground(color);
                    pnSulMsg.setBackground(color);
                    pnCentro.setBackground(color);
                    pnNorteSul.setBackground(color);
                    pnNorteNorte.setBackground(color);
                }
            }
        });

//############################# BOTAO VOLTAR #################################
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

//*********************** BOTÃO GRAVAR ****************************************
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inserindo) {

                    // turma.setMateriaList((List<Materia>) materiaList.get((int) comboBox.getSelectedItem()));
                }
            }
        });

//###################### BOTAO EXCLUIR TUDO ################################
        btExcluirTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {

            if (tfID.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Nenhuma informação passada ");
            } else {
                try {
                    turma = new Turma();
                    String nBox = "";
                    tfID.setBackground(Color.green);
                    comboBox.setEnabled(true);
                    cbDia.setEnabled(false);
                    tfID.setEditable(false);
                    CardLayout.show(pnSul, "pnMsg");
                    turma = daoTurma.obter(Integer.valueOf(tfID.getText()));

                    if (turma == null) { //nao achou
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        btBuscar.setVisible(false);
                        btNBuscar.setVisible(true);
                        setLog("Não achou na lista, procure outra turma");
                    } else { //achou
                        btVincular.setVisible(true);
                        btExcluir.setVisible(true);
                        btBuscar.setVisible(false);
                        btNBuscar.setVisible(true);
                        btPAs_PDF.setVisible(true);
                        List<TurmaHasMateria> materias = daoTurmaHasMateria.list();
                        String turmas_str = "";
                        for (TurmaHasMateria materia : materias) {
                            if (materia.getTurma().getIdTurma() == Integer.valueOf(tfID.getText())) {
                                nBox += materia.getMateria().getNome();
                                comboBox.addItem(materia.getMateria().getIdmateria() + " - " + materia.getMateria().getNome());
                                turmas_str += materia.getMateria().getIdmateria() + " - " + materia.getMateria().getNome() + ", ";
                            }
                        }
                        //JOptionPane.showMessageDialog(cp, nBox);
                        if (materias.isEmpty()) {
                            btExcluir.setVisible(false);
                            setLog("Achou [" + turma.getIdTurma() + "-" + turma.getNomeTurma() + ", sem materias vinculadas]");
                        } else {
                            setLog("Achou [" + turma.getIdTurma() + "-" + turma.getNomeTurma() + ", com as materias "
                                    + turmas_str + "]");
                        }

                        tfID.setText(String.valueOf(turma.getIdTurma()));

                    }
                } catch (Exception x) {
                    tfID.selectAll();
                    tfID.requestFocus();
                    tfID.setBackground(Color.red);
                    tfID.setEditable(true);
                    setLog("Erro no tipo de dados da chave. (" + x.getMessage() + ")");
                }//else
            }
            tfID.selectAll();
            tfID.requestFocus();
        });

//########################3 Nova Busca ##########################3
        btNBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.setEditable(true);
                btBuscar.setVisible(true);
                btNBuscar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btVincular.setVisible(false);
                btPAs_PDF.setVisible(false);
                tfID.setText("");
                tfID.requestFocus();
                comboBox.setEnabled(false);
                cbDia.setEnabled(false);
                cbDia.setSelectedIndex(0);
                comboBox.removeAllItems();

            }
        });
//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            String id = (String) comboBox.getSelectedItem();
            String[] id_vetor = id.split(" ");
            id = id_vetor[0];
            String nome = id_vetor[2];
            setLog("Vinculou [" + id + "-" + nome + " à Turma " + turma.getIdTurma() + "-" + turma.getNomeTurma() + "]");

            String id_materia = (String) comboBox.getSelectedItem();
            String[] idmat = id_materia.split(" ");
            int idmateria = Integer.valueOf(idmat[0]);
            materia = daoMateria.obter(idmateria);
            TurmaHasMateriaPK thmpk = new TurmaHasMateriaPK(turma.getIdTurma(), materia.getIdmateria());
            turmaHasMateria = daoTurmaHasMateria.obter(thmpk);
            if (turmaHasMateria == null) {
                turmaHasMateria = new TurmaHasMateria();
                turmaHasMateria.setDiaSemana(String.valueOf(cbDia.getSelectedItem()));
                turmaHasMateria.setTurno(String.valueOf(cbHorario.getSelectedItem()));
                turmaHasMateria.setHorasAula(String.valueOf(cbHoras_aula.getSelectedItem()));
                turmaHasMateria.setTurmaHasMateriaPK(thmpk);
                turmaHasMateria.setMateria(materia);
                turmaHasMateria.setTurma(turma);
                daoTurmaHasMateria.inserir(turmaHasMateria);
                turmaHasMateria = new TurmaHasMateria();
                turmaHasMateriaPK = new TurmaHasMateriaPK();
                daoTurma.atualizar(turma);
            } else {
                setLog("Turma ja está vinculada à essa matéria");
            }
            //voltar para tela inicial
            tfID.setText("");
            comboBox.setEnabled(false);
            comboBox.setEnabled(false);
            cbDia.setSelectedIndex(0);
            cbHorario.setEnabled(false);
            cbHoras_aula.setEnabled(false);
            cbHorario.setSelectedIndex(0);
            cbHoras_aula.setSelectedIndex(0);
            tfID.requestFocus();
            tfID.selectAll();
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            travarTextFields(true);
            comboBox.removeAllItems();
            lbDia.setVisible(false);
            cbDia.setVisible(false);
            btPAs_PDF.setVisible(false);

        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.requestFocus();
                tfID.selectAll();
                tfID.setText("");
                comboBox.setEnabled(false);
                cbHorario.setEnabled(false);
                cbHoras_aula.setEnabled(false);
                cbHorario.setSelectedIndex(0);
                cbHoras_aula.setSelectedIndex(0);
                cbDia.setVisible(false);
                lbDia.setVisible(false);
                cbDia.setSelectedIndex(0);
                btVincular.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                travarTextFields(true);
                btPAs_PDF.setVisible(false);
                setLog("Cancelou a inclusão do registro");
                comboBox.removeAllItems();

            }
        });

//############################# BOTAO ALTERAR #################################
        btAlterar.addActionListener((ActionEvent e) -> {
            tfNome.requestFocus();
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            btListar.setVisible(false);
            btNBuscar.setVisible(false);
            inserindo = false;
            travarTextFields(false);
            comboBox.setEnabled(true);
            cbDia.setEnabled(true);
            setLog("Alterando um registro existente");
        });

//||||||||||||||||||||||||||| BOTÃO VINCULAR |||||||||||||||||||||||||||||||||||
        btVincular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNome.requestFocus();
                btVincular.setVisible(false);
                btExcluir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btNBuscar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
                travarTextFields(false);
                comboBox.setEnabled(true);
                cbHorario.setEnabled(true);
                cbHoras_aula.setEnabled(true);
                comboBox.setEnabled(true);
                cbDia.setEnabled(true);
                setLog("Vinculando...");
                lbDia.setVisible(true);
                cbDia.setVisible(true);
                comboBox.removeAllItems();

                List<TurmaHasMateria> materias = turma.getTurmaHasMateriaList();
                String turmas_str = "";
                for (TurmaHasMateria materia : materias) {
                    comboBox.addItem(materia.getMateria().getIdmateria() + " - " + materia.getMateria().getNome());
                    turmas_str += materia.getMateria().getIdmateria() + " - " + materia.getMateria().getNome() + ", ";
                }

                List<TurmaHasMateria> materias_turma = turma.getTurmaHasMateriaList();
                List<Materia> materias_total = daoMateria.list();
                String materias_lista = "";
                for (Materia materia : materias_total) {
                    materias_lista += String.valueOf(materia.getIdmateria()) + ",";

                }

                String materiasTurma = "";
                for (TurmaHasMateria materia1 : materias_turma) {
                    materiasTurma += materia1.getMateria().getIdmateria() + ",";
                }

                comboBox.removeAllItems();
                for (Materia mat : materias_total) {
                    comboBox.addItem(mat.getIdmateria() + " - " + mat.getNome());
                }

            }
        });

//======================= LISTAR =============================================
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout.show(pnSul, "pnLst");
                scrollList.setPreferredSize(tabela.getPreferredSize());
                pnSulListagem.add(scrollList);
                scrollList.setViewportView(tabela);
                //busca a lista de contatos
                String[] aux;
                colunas = new String[]{"ID Turma", "Nome Turma", "ID Materia", "Nome Materia", "Dia da semana", "Turno", "Aulas"};
                dados = new String[0][7];
                model.setDataVector(dados, colunas);
                List<TurmaHasMateria> vinculados = daoTurmaHasMateria.list();
                System.out.println(vinculados.toString());
                for (TurmaHasMateria a : vinculados) {
                    System.out.println(a.getMateria().getIdmateria() + " - " + a.getMateria().getNome()
                            + " turma > " + a.getTurma().getIdTurma() + " - " + a.getTurma().getNomeTurma());

                    int id_turma = a.getTurma().getIdTurma();
                    int id_materia = a.getMateria().getIdmateria();
                    String nome_turma = daoTurma.obter(a.getTurmaHasMateriaPK().getTurmaidTurma()).getNomeTurma();
                    String nome_materia = daoMateria.obter(a.getTurmaHasMateriaPK().getMateriaIdmateria()).getNome();
                    String dia = a.getDiaSemana();
                    String turno = a.getTurno();
                    String aulas = a.getHorasAula();
                    String[] linha = new String[]{String.valueOf(id_turma), nome_turma, String.valueOf(id_materia),
                        nome_materia, dia, turno, aulas};

                    model.addRow(linha);
                }
            }
        });
//***************************** EXCLUIR *************************************
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id_materia = (String) comboBox.getSelectedItem();
                if (id_materia == null) {
                    setLog("Não há PA's para serem desmarcados");
                    JOptionPane.showMessageDialog(cp, "Não há PA's para serem desmarcados ");

                } else {
                    String[] idmat = id_materia.split(" ");
                    int idmateria = Integer.valueOf(idmat[0]);
                    materia = daoMateria.obter(idmateria);
                    int id_turma = Integer.valueOf(tfID.getText());
                    int dialogResult = JOptionPane.showConfirmDialog(cp, "Vai desvincular [ "
                            + materia.getIdmateria() + "-" + materia.getNome() + " ]?", "Exclui da lista", NORMAL);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        setLog("Desvinculou [" + materia.getIdmateria() + "-" + materia.getNome() + "]");

                    }

                    TurmaHasMateriaPK thmpk = new TurmaHasMateriaPK(turma.getIdTurma(), materia.getIdmateria());
                    turmaHasMateria = daoTurmaHasMateria.obter(thmpk);
                    turmaHasMateria.setTurmaHasMateriaPK(thmpk);
                    daoTurmaHasMateria.remover(turmaHasMateria);
                }

                tfID.setText("");
                tfID.requestFocus();
                btExcluir.setVisible(false);
                btVincular.setVisible(false);
                btAlterar.setVisible(false);
                comboBox.setEnabled(false);
                cbHorario.setEnabled(false);
                cbHoras_aula.setEnabled(false);
                comboBox.setEnabled(false);
                cbDia.setEnabled(false);
                travarTextFields(true);
                btNBuscar.setVisible(false);
                btBuscar.setVisible(true);
                comboBox.removeAllItems();
                cbDia.setSelectedIndex(0);

            }

        });

//################# COLRO CHOOSER #################
//$$$$$$$$$$$$$$ FIM DOS LISTENERS $$$$$$$$$$$$$
        // parâmetros para janela inicial
        setSize(900, 400);
        setTitle("UZUM");
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
        setResizable(true);
    }
}
