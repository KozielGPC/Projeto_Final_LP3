package Main;

import DAOs.DAOMateria;
import DAOs.DAOTurma;
import Entidades.Materia;
import Entidades.Turma;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import static java.awt.Frame.NORMAL;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

/**
 *
 * @author radames
 */
class GUI_Materia extends JDialog {

    private Container cp;
    private GridBagConstraints cons = new GridBagConstraints();
    private GridBagLayout GridBagLayout = new GridBagLayout();
    private SpinnerModel value
            = new SpinnerNumberModel(0, //valor inicial  
                    0, //valor mínimo  
                    30, //valor máximo  
                    1); //passo  
    private JSpinner spinner = new JSpinner(value);
    private JRadioButton botaoSim = new JRadioButton();
    private JRadioButton botaoNao = new JRadioButton();
    private CardLayout CardLayout = new CardLayout();
    private BorderLayout BorderLayout = new BorderLayout();

    private JPanel pnNorte = new JPanel(BorderLayout);
    private JPanel pnNorteNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnNorteSul = new JPanel(GridBagLayout);
    private JPanel pnCentro = new JPanel(GridBagLayout);
    private JPanel pnSul = new JPanel(CardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnCard = new JPanel(CardLayout);
    private JPanel pnMateria = new JPanel(new BorderLayout());
    private JPanel pnAluno = new JPanel(new BorderLayout());

    private JPanel pnMenu = new JPanel(GridBagLayout);

    private JLabel lbNome = new JLabel("Nome");
    private JLabel lbCarga = new JLabel("Carga Horária");
    private JLabel lbID = new JLabel("Código Matéria");
    private JLabel lbSim = new JLabel("Sim");
    private JLabel lbNao = new JLabel("Não");
    private JLabel lbRedimensionar = new JLabel("Redimensionar tela");

    private JTextField tfID = new JTextField(5);
    private JTextField tfNome = new JTextField(30);

    private JButton btBuscar = new JButton("Buscar");
    private JButton btInserir = new JButton("Inserir");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton("Gravar");
    private JButton btVoltar = new JButton("Menu");
    private JButton btExcluirTudo = new JButton("Excluir tudo e voltar ao menu");
    private JButton btNBuscar = new JButton("Nova busca");

    String[] colunas = new String[]{"Código", "Nome", "Carga Horária"};
    String[][] dados = new String[0][3];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    JScrollPane scrollList = new JScrollPane();

    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem

    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas

    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar
//essa é a classe de processamento (controle da lista de contatos)
    private DAOMateria daoMateria = new DAOMateria();
    ; //essa é a classe de processamento (controle da lista de contatos)
    private Materia materia; //ver classe contato

    Turma turma = new Turma();
    DAOTurma daoTurma = new DAOTurma();
    List<Turma> turmaList = daoTurma.list();
    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea

    Ferramentas fer = new Ferramentas();

    //métodos auxiliares
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }

    private void travarTextFields(boolean campo) {
        tfID.setEditable(campo); //permite que o usuario digite nesse textField
        tfNome.setEditable(!campo);
        if (!campo) {
            tfNome.requestFocus();
            tfNome.selectAll();
        }
    }

    private void limparValoresDosAtributos() {
        tfNome.setText("");
    }

    //construtor da classe GUI_Materia
    public GUI_Materia() {
        //abrir o arquivo
        btNBuscar.setVisible(false);
        spinner.setEnabled(false);

        tabela.setEnabled(false);

        //faz com que a última linha do 
        //jTextArea seja exibida
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnCard);
        pnCard.setSize(400, 800);
        pnCard.add(pnMateria, "pnMateria");
        pnMateria.add(pnNorte, BorderLayout.NORTH);
        pnMateria.add(pnCentro, BorderLayout.CENTER);
        pnMateria.add(pnSul, BorderLayout.SOUTH);

        pnNorte.add(pnNorteNorte, BorderLayout.NORTH);
        pnNorte.add(pnNorteSul, BorderLayout.SOUTH);

        pnNorteNorte.add(btVoltar);
        pnNorteNorte.add(lbID);
        pnNorteNorte.add(tfID);
        pnNorteNorte.add(btBuscar);
        pnNorteNorte.add(btNBuscar);
        pnNorteNorte.add(btInserir);
        pnNorteNorte.add(btAlterar);
        pnNorteNorte.add(btExcluir);
        pnNorteNorte.add(btSalvar);
        pnNorteNorte.add(btCancelar);
        pnNorteNorte.add(btListar);

//###################### PAINEL NORTE SUL ############################        
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 0;
        pnNorteSul.add(btExcluirTudo, cons);

//###################### PAINEL CENTRO ###############################       
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 2;
        cons.gridx = 0;
        cons.gridwidth = 1;
        pnCentro.add(lbNome, cons);
        cons.gridy = 2;
        cons.gridx = 1;
        pnCentro.add(tfNome, cons);
        cons.gridy = 3;
        cons.gridx = 0;
        pnCentro.add(lbCarga, cons);
        cons.gridy = 3;
        cons.gridx = 1;
        pnCentro.add(spinner, cons);

//############################### PAINEL SUL ##########################
        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("Registro de atividades"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");
        pnSul.setBackground(Color.red);

//############################ Imagens #############################
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

//############################ Tooltips #############################
        btExcluir.setToolTipText("Excluir");
        btVoltar.setToolTipText("Menu");
        btBuscar.setToolTipText("Buscar");
        btListar.setToolTipText("Listar");
        btSalvar.setToolTipText("Salvar");
        btCancelar.setToolTipText("Cancelar");
        btAlterar.setToolTipText("Alterar");
        btExcluirTudo.setToolTipText("Apaga os dados e volta ao menu");
        btNBuscar.setToolTipText("Nova busca");

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
                btGravar.doClick();
                // Sai   
                dispose();
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
                    materia = new Materia();
                    materia.setIdmateria(Integer.valueOf(tfID.getText()));
                    materia.setCargaHoraria((int) spinner.getValue());
                    materia.setNome(tfNome.getText());
                }
            }
        });
// ------------------------BOTAO BUSCAR ----------------------------------------        
        btBuscar.addActionListener((ActionEvent e) -> {

            if (tfID.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Nenhuma informação passada ");
            } else {
                try {
                    tfID.setBackground(Color.green);
                    tfID.setEditable(false);
                    tfNome.setEditable(false);
                    CardLayout.show(pnSul, "pnMsg");
                    materia = daoMateria.obter(Integer.valueOf(tfID.getText()));
                    if (materia == null) { //nao achou
                        btInserir.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        btNBuscar.setVisible(true);
                        btBuscar.setVisible(false);
                        limparValoresDosAtributos();
                        setLog("Não achou na lista, pode inserir se quiser...");
                    } else { //achou
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btInserir.setVisible(false);
                        btNBuscar.setVisible(true);
                        btBuscar.setVisible(false);
                        tfID.setText(String.valueOf(materia.getIdmateria()));
                        tfNome.setText(materia.getNome());
                        spinner.setValue(materia.getCargaHoraria());
                        spinner.setEnabled(false);
                        setLog("Achou [" + materia.getIdmateria() + "-" + materia.getNome() + "-" + materia.getCargaHoraria() + "]");

                    }
                } catch (Exception x) {
                    tfID.selectAll();
                    tfID.setEditable(true);
                    tfID.requestFocus();
                    tfID.setBackground(Color.red);
                    setLog("Erro no tipo de dados da chave. (" + x.getMessage() + ")");
                }//else//else
            }
            tfID.selectAll();
            tfID.requestFocus();
        });

        //########################### Nova Busca ########################
        btNBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.setEditable(true);
                btBuscar.setVisible(true);
                btNBuscar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btInserir.setVisible(false);
                btCancelar.setVisible(false);
                tfNome.setText("");
                tfID.setText("");
                spinner.setValue(0);
                tfID.requestFocus();
            }
        });

        btExcluir.setToolTipText("Excluir");
        btVoltar.setToolTipText("Menu");
        btBuscar.setToolTipText("Buscar");
        btListar.setToolTipText("Listar");
        btSalvar.setToolTipText("Salvar");
        btCancelar.setToolTipText("Cancelar");
        btAlterar.setToolTipText("Alterar");
//*********************** BOTÃO SALVAR ****************************************        
        btSalvar.addActionListener((ActionEvent e) -> {
            Materia contatoOriginal = materia; //para pesquisar na lista
            //precisamos do contato original (para depois modificar)
            if (inserindo) {
                materia = new Materia(); //criar um novo contato
            }
            materia.setIdmateria(Integer.valueOf(tfID.getText()));
            //transfere os valores da GUI_Materia para classe contato
            if (tfNome.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "Campo nome está vazio  e será preenhido automaticamente como 'null' \nCaso ja tenha, o Nome continuará o mesmo");
            } else {
                materia.setNome(tfNome.getText());
            }
            materia.setCargaHoraria((int) spinner.getValue());
            if (inserindo) { //a variavel inserindo é preenchida nos
                daoMateria.inserir(materia);
                setLog("Inseriu [" + materia.getIdmateria() + "-" + materia.getNome() + "-" + materia.getCargaHoraria()
                        + "]");
            } else {//alterar
                daoMateria.atualizar(materia);
                setLog("Alterou [" + materia.getIdmateria() + "-" + materia.getNome() + "-" + materia.getCargaHoraria()
                        + "]");
            }

            //voltar para tela inicial
            tfID.setText("");
            int a = 0;
            spinner.setValue(a);
            tfID.requestFocus();
            tfID.selectAll();
            btGravar.doClick();
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            limparValoresDosAtributos();
            travarTextFields(true);
            //comboBox.setEnabled(false);
            spinner.setEnabled(false);
        });

//**************** Adicionar Background ********************
//**************** Cancelar alteração ou inclusão ********************
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfID.requestFocus();
                tfID.selectAll();
                tfID.setText("");
                btInserir.setVisible(false);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                travarTextFields(true);
                //comboBox.setEnabled(false);
                spinner.setEnabled(false);
                limparValoresDosAtributos();
                setLog("Cancelou a alteração ou inclusão do registro");
            }
        });

//############################# BOTAO ALTERAR #################################
        btAlterar.addActionListener((ActionEvent e) -> {
            //comboBox.setEnabled(true);
            spinner.setEnabled(true);
            tfNome.requestFocus();
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btNBuscar.setVisible(false);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            btListar.setVisible(false);
            inserindo = false;
            travarTextFields(false);
            setLog("Alterando um registro existente");
        });
//||||||||||||||||||||||||||| BOTÃO INSERIR |||||||||||||||||||||||||||||||||||
        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comboBox.setEnabled(true);
                spinner.setEnabled(true);
                tfNome.requestFocus();
                btInserir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btNBuscar.setVisible(false);
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
                List<Materia> materia = daoMateria.listInOrderId();//busca a lista de contatos
                String[] aux;
                colunas = new String[]{"Código", "Nome", "Carga Horária"};
                dados = new String[0][3];
                model.setDataVector(dados, colunas);
                for (Materia a : materia) {
                    int id = a.getIdmateria();
                    String nome = a.getNome();
                    int carga = a.getCargaHoraria();
                    String[] linha = new String[]{String.valueOf(id), nome, String.valueOf(carga)};
                    model.addRow(linha);
                }
            }
        });

//***************************** EXCLUIR *************************************
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(cp, "Vai excluir [ "
                        + tfID.getText() + "-" + tfNome.getText() + "-" + materia.getCargaHoraria() + "-" + "]?", "Exclui da lista", NORMAL);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    daoMateria.remover(materia);
                    setLog("Excluiu [" + materia.getIdmateria() + "-" + materia.getNome() + "-" + materia.getCargaHoraria()
                            + "]");

                }
                tfID.setText("");
                tfNome.setText("");
                //comboBox.setSelectedIndex(0);
                int a = 0;
                spinner.setValue(a);
                tfID.requestFocus();
                tfID.selectAll();
                btGravar.doClick();
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                btNBuscar.setVisible(false);
                btBuscar.setVisible(true);
                tfID.setEditable(true);
            }

        });

//############################# BOTAO EXCLUIR TUDO ###################################
        btExcluirTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fer.LimparArquivo("Materia.txt", "");
                dispose();
            }
        });

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
