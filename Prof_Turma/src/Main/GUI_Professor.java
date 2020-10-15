package Main;

import DAOs.DAOProfessor;
import Entidades.Professor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
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

/**
 *
 * @author radames
 */
class GUI_Professor extends JDialog {

    private Container cp;
    GridBagConstraints cons = new GridBagConstraints();
    GridBagLayout GridBagLayout = new GridBagLayout();
    JComboBox comboBox = new JComboBox();
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

    private JLabel lbIdProfessor = new JLabel("ID Professor");
    private JLabel lbNome = new JLabel("Nome");
    private JLabel lbEmailProfessor = new JLabel("Email");
    private JLabel lbDataNascimento = new JLabel("Data de Nascimento");
    private JLabel lbId = new JLabel("Código Professor");

    private JCheckBox checkbox = new JCheckBox("Cor do layout personalizada");

    private JTextField tfID = new JTextField(5);
    private JTextField tfNome = new JTextField(30);
    private JTextField tfEmailProfessor = new JTextField(30);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btNBuscar = new JButton("Nova busca");
    private JButton btInserir = new JButton("Inserir");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton("Gravar");
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
    DAOProfessor daoProfessor = new DAOProfessor();
    String acao = "";
    Professor professor;
    Professor professorOriginal;
    

    Ferramentas fer = new Ferramentas();

    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void travarTextFields(boolean campo) {
        tfID.setEditable(campo); //permite que o usuario digite nesse textField
        tfNome.setEditable(!campo);
        tfEmailProfessor.setEditable(!campo);
        if (!campo) {
            tfNome.requestFocus();
            tfNome.selectAll();
        }
    }

    private void limparValoresDosAtributos() {
        tfNome.setText("");
        tfEmailProfessor.setText("");
    }

    //construtor da classe GUI_Materia
    public GUI_Professor() {
        //abrir o arquivo
        btCor.setVisible(false);

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
        btNBuscar.setVisible(false);
        pnNorteNorte.add(btInserir);
        pnNorteNorte.add(btAlterar);
        pnNorteNorte.add(btExcluir);
        pnNorteNorte.add(btSalvar);
        pnNorteNorte.add(btCancelar);
        pnNorteNorte.add(btListar);

        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 0;
        cons.gridwidth = 1;
        pnCentro.add(lbNome, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 1;
        pnCentro.add(tfNome, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 0;
        pnCentro.add(lbEmailProfessor, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 1;
        pnCentro.add(tfEmailProfessor, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 2;
        cons.gridx = 0;
        pnCentro.add(lbDataNascimento, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 2;
        cons.gridx = 1;
        pnCentro.add(dateTextField1, cons);
        dateTextField1.setText(date);

        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 0;
        pnNorteSul.add(checkbox, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 2;
        cons.gridx = 0;
        pnNorteSul.add(btCor, cons);

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
        btInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/inserir.png")));
        btCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/cor.jpg")));

        btExcluir.setToolTipText("Excluir");
        btVoltar.setToolTipText("Menu");
        btBuscar.setToolTipText("Buscar");
        btNBuscar.setToolTipText("Nova busca");
        btListar.setToolTipText("Listar");
        btSalvar.setToolTipText("Salvar");
        btInserir.setToolTipText("Inserir");
        btCancelar.setToolTipText("Cancelar");
        btAlterar.setToolTipText("Alterar");
        btExcluirTudo.setToolTipText("Apaga os dados e volta ao menu");
        btCor.setToolTipText("Escolher cor");

        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btInserir.setVisible(false);
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
                    professor = new Professor();
                    professor.setIdProfessor(Integer.valueOf(tfID.getText()));
                    professor.setEmailProfessor(tfEmailProfessor.getText());
                    professor.setNomeProfessor(tfNome.getText());
                    professor.setNascimentoProfessor(String.valueOf(dateTextField1.getDate()));
                }
            }
        });


//###################### BOTAO EXCLUIR TUDO ################################
        btExcluirTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fer.LimparArquivo("Professor.txt", "");
                dispose();
            }
        });
// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {

            if (tfID.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Nenhuma informação passada ");
            } else {
                try {
                    tfID.setBackground(Color.green);
                    tfNome.setEditable(false);
                    tfEmailProfessor.setEditable(false);
                    tfID.setEditable(false);
                    CardLayout.show(pnSul, "pnMsg");
                    professor = daoProfessor.obter(Integer.valueOf(tfID.getText()));

                    if (professor == null) { //nao achou
                        btInserir.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        limparValoresDosAtributos();
                        btBuscar.setVisible(false);
                        btNBuscar.setVisible(true);
                        setLog("Não achou na lista, pode inserir se quiser...");
                    } else { //achou
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btInserir.setVisible(false);
                        btBuscar.setVisible(false);
                        btNBuscar.setVisible(true);

                        tfID.setText(String.valueOf(professor.getIdProfessor()));
                        tfNome.setText(professor.getNomeProfessor());
                        tfEmailProfessor.setText(professor.getEmailProfessor());
                        dateTextField1.setText(professor.getNascimentoProfessor());
                        setLog("Achou [" + professor.getIdProfessor() + "-" + professor.getNomeProfessor() + "-" + professor.getEmailProfessor() + "-"
                                + professor.getNascimentoProfessor() + "]");

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
                btInserir.setVisible(false);
                tfEmailProfessor.setText("");
                tfNome.setText("");
                tfID.setText("");
                tfID.requestFocus();
                dateTextField1.setDate(date);
            }
        });
//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Professor contatoOriginal = professor; //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                professor = new Professor(); //criar um novo contato
            }
            //transfere os valores da GUI_Materia para classe contato
            professor.setIdProfessor(Integer.valueOf(tfID.getText()));
            if (tfNome.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Campo nome está vazio  e será preenhido automaticamente como 'null' \nCaso ja tenha, o nome será preenchido com o mesmo");
            } else {
                professor.setNomeProfessor(tfNome.getText());
            }

            if (tfEmailProfessor.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Campo Email está vazio  e será preenhido automaticamente como 'null' \nCaso já tenha, o email será o mesmo ");
            } else {
                professor.setEmailProfessor(tfEmailProfessor.getText());
            }

            professor.setNascimentoProfessor(simpleDateFormat.format(dateTextField1.getDate()));

            if (inserindo) { //a variavel inserindo é preenchida nos
                daoProfessor.inserir(professor);
                setLog("Inseriu [" + professor.getIdProfessor() + "-" + professor.getNomeProfessor() + "-" + professor.getEmailProfessor() + "-" + professor.getNascimentoProfessor() + "]");
            } else {//alterar
                daoProfessor.atualizar(professor);
                setLog("Alterou [" + professor.getIdProfessor() + "-" + professor.getNomeProfessor() + "-" + professor.getEmailProfessor() + "-" + professor.getNascimentoProfessor() + "]");
            }

            //voltar para tela inicial
            tfID.setText("");
            dateTextField1.setText(date);
            tfID.requestFocus();
            tfID.selectAll();
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            limparValoresDosAtributos();
            travarTextFields(true);
        });

//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.requestFocus();
                tfID.selectAll();
                tfID.setText("");
                dateTextField1.setDate(date);
                btInserir.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                travarTextFields(true);
                limparValoresDosAtributos();
                setLog("Cancelou a alteração ou inclusão do registro");

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
            setLog("Alterando um registro existente");
        });
//||||||||||||||||||||||||||| BOTÃO INSERIR |||||||||||||||||||||||||||||||||||
        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNome.requestFocus();
                btInserir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btNBuscar.setVisible(false);
                btListar.setVisible(false);
                inserindo = true;
                travarTextFields(false);
                limparValoresDosAtributos();
                setLog("Inserindo um novo registro");
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
                colunas = new String[]{"Código", "Nome", "Email", "Data de Nascimento"};
                dados = new String[0][4];
                model.setDataVector(dados, colunas);
                List<Professor> professores = daoProfessor.listInOrderId();
                for (Professor a : professores) {
                    String nome = a.getNomeProfessor();
                    String nascimento = a.getNascimentoProfessor();
                    String email = a.getEmailProfessor();
                    int id = a.getIdProfessor();
                    String[] linha = new String[]{String.valueOf(id), nome, email, nascimento};
                    // String[] linha = new String[]{"João", "15", "Masculino"};
                    model.addRow(linha);
                }
            }
        });
//***************************** EXCLUIR *************************************
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(cp, "Vai excluir [ "
                        + tfID.getText() + "-" + tfNome.getText() + "-" + professor.getEmailProfessor() + "-" + professor.getNascimentoProfessor() + " ]?", "Exclui da lista", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    daoProfessor.remover(professor);
                    setLog("Excluiu [" + professor.getIdProfessor() + "-" + professor.getNomeProfessor() + "-" + professor.getEmailProfessor() + "-" + professor.getNascimentoProfessor() + "]");

                }
                tfID.setText("");
                tfID.requestFocus();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                tfNome.setText("");
                tfEmailProfessor.setText("");
                dateTextField1.setText(date);
                travarTextFields(true);
                btNBuscar.setVisible(false);
                btBuscar.setVisible(true);

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
