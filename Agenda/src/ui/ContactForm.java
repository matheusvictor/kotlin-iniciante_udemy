package ui;

import business.ContactBusiness;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JTextField textName;
    private JTextField textPhone;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ContactBusiness mContactBusiness;

    // construtor que inicializa a interface
    public ContactForm() {
        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // obtem o tamanho da tela
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2); // centraliza a amplicação na janela, considerando metade do tamanho original

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // encerra o programa ao fechar a janela

        mContactBusiness = new ContactBusiness();
        setListeners();
    }

    // ao clicar no botão cancelar, a janela atual vai "se esconder" e o MainForm aparece novamente
    private void setListeners() {

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pega os valores digitados nos campos de texto da janela ContactForm
                String contactName = textName.getText();
                String contactPhone = textPhone.getText();

                mContactBusiness.save(contactName, contactPhone);

                /*
                Instancia um nome MainForm que será exibido após salvar um contato e fechar a janela de CntactForm
                 */
                new MainForm();
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });
    }
}
