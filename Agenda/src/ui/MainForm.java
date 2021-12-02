package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelContactCount;

    private ContactBusiness mContactBusiness;
    private String mName = "";
    private String mPhone = "";

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

        String[] columnNames = {"Nome", "Telefone"}; // cria uma lista de strings com os nomes das colunas que farão a tabela

        /*
            Instância um modelo de tabela vazia, recebendo um objeto que define o número de colunas. Os nomes das colunas
            são definidos por columnNames
         */

        DefaultTableModel tableModel = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity contact : contactList) {
            /*
            Para cada contato do tipo ContactEntity dentro de contact List
             */
            Object[] object = new Object[columnNames.length]; // cria um objeto com o número de colunas iguais ao tamanho de columnNames

            object[contactList.indexOf(contact)] = contact.getName();
            object[contactList.indexOf(contact) + 1] = contact.getPhone();

            tableModel.addRow(object); // adiciona o objeto ao modelo da tabela
        }

        tableContacts.clearSelection(); // inicializa tabela sem nenhuma pré-seleção de contato
        tableContacts.setModel(tableModel); // seta o modelo de tabela criado ao tableContacts

        labelContactCount.setText(mContactBusiness.getContactCountDescription()); // atualiza a label
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

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // "escutar" estado da tabela para saber qual coluna está selecionada
                if (e.getValueIsAdjusting()) {
                    mName = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                    mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
