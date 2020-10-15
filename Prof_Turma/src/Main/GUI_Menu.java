package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUI_Menu extends JFrame {

    private Container cp;

    private CardLayout CardLayout = new CardLayout();
    private BorderLayout BorderLayout = new BorderLayout();
    GridBagLayout GridBagLayout = new GridBagLayout();
    GridBagConstraints cons = new GridBagConstraints();

    private JLabel lbUzum = new JLabel("CLIQUE NA IMAGEM PARA PROSSEGUIR", SwingConstants.CENTER);
    private JLabel lbSulUzum = new JLabel("UZUM, VOCÊ NÚMERO 1! ", SwingConstants.CENTER);
    private JLabel Versao = new JLabel("Versão 1.0 ", SwingConstants.CENTER);

    private JButton btUzum = new JButton();
    private JButton btTurma = new JButton();
    private JButton btMateria = new JButton();
    private JButton btProfessor = new JButton();
    private JButton btMateria_Turma = new JButton();

    private JPanel pnUzum = new JPanel(new BorderLayout());
    private JPanel pnCard = new JPanel(CardLayout);
    private JPanel pnMenu = new JPanel(GridBagLayout);
    private JPanel pnNorteUzum = new JPanel(new GridLayout(1, 1));
    private JPanel pnSulUzum = new JPanel(new BorderLayout());
    private JPanel pnSulSulUzum = new JPanel(new BorderLayout());
    private JPanel pnSulNorteUzum = new JPanel(new BorderLayout());

    public GUI_Menu() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnCard);

        btUzum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/uzumpng.png")));
        btMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/materia.png")));
        btTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/turma.jpg")));
        btProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/professor.png")));
        btMateria_Turma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Figuras/turma.png")));

        pnCard.setSize(450, 540);
        pnCard.add(pnUzum, "pnUzum");
        pnCard.add(pnMenu, "pnMenu");
        pnUzum.add(pnSulUzum, BorderLayout.SOUTH);
        pnUzum.add(pnNorteUzum);
        pnSulUzum.add(pnSulNorteUzum, BorderLayout.NORTH);
        pnSulUzum.add(pnSulSulUzum, BorderLayout.SOUTH);
        pnSulNorteUzum.add(btUzum);
        pnSulSulUzum.add(lbSulUzum);
        pnNorteUzum.add(lbUzum);

        pnMenu.setBackground(Color.BLUE);
        btMateria.setBackground(Color.BLUE);
        btProfessor.setBackground(Color.BLUE);
        btTurma.setBackground(Color.BLUE);
        btTurma.setBorderPainted(false);
        btMateria.setBorderPainted(false);
        btProfessor.setBorderPainted(false);

        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 0;
        pnMenu.add(btProfessor, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 0;
        cons.gridx = 1;
        pnMenu.add(btTurma, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 0;
        pnMenu.add(btMateria, cons);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridy = 1;
        cons.gridx = 1;
        pnMenu.add(btMateria_Turma, cons);

        btUzum.setToolTipText("Uzum");
        btMateria.setToolTipText("Matéria");
        btMateria_Turma.setToolTipText("Marcar PA");
        btTurma.setToolTipText("Turma");
        btProfessor.setToolTipText("Professor");

        Versao.setAlignmentX(CENTER_ALIGNMENT);
        Versao.setBackground(Color.CYAN);
        Versao.setOpaque(true);

        /* ############################## BOTÃO UZUM ##################################*/
        btUzum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout.show(pnCard, "pnMenu");
                setSize(400, 400);
                setLocationRelativeTo(null);
            }
        });

        /* ############################## BOTÃO MATÉRIA ##################################*/
        btMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GUI_Materia gui = new GUI_Materia();
                setVisible(true);
            }
        });

         /* ############################## BOTÃO MATÉRIA ##################################*/
        btMateria_Turma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GUI_turmas_materias gui = new GUI_turmas_materias();
                setVisible(true);
            }
        });

        /* ############################## BOTÃO TURMA ##################################*/
        btTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GUI_Turma gui = new GUI_Turma();
                setVisible(true);
            }
        });

        /* ############################## BOTÃO PROFESSOR ##################################*/
        btProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GUI_Professor gui = new GUI_Professor();
                setVisible(true);
            }
        });

        /* ############################## BOTÃO ALUNO TECLADO ##################################*/
        setSize(450, 540);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("UZUM");
        setLocationRelativeTo(null);//centraliza no monitor
        setVisible(true);

    }

}
