package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;

    private ContactBusiness mContactBusiness;

    // construtor que inicializa a interface
    public MainForm() {

        // definindo tamanho da janela do JFrame
        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        // centraliza o JFrame em relação ao monitor
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // obtem o tamanho da tela
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2); // centraliza a amplicação na janela, considerando metade do tamanho original

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // encerra o programa ao fechar a janela

        mContactBusiness = new ContactBusiness(); // instancia a variável mContactBusiness

        setListeners();

        loadContacts();
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusiness.getList(); // lista de contatos salvos em ContactRepository
    }

    // método responsável por "ouvir" e atribuir os eventos às ações
    private void setListeners() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ao clicar no botão, o ContactForm será invocado
                new ContactForm();
                dispose(); // fecha o MainForm ao abrir o ContactForm
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
