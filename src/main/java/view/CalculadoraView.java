/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package view;

import java.util.Arrays;
import controller.CalculadoraController;
import model.CalculadoraModel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author 13639179943
 */
public class CalculadoraView extends JFrame implements ActionListener{

    public final String[] ops = {"+", "-", "*", "/", "%", "^"};
    public CalculadoraController controller;
    JTextField display = new JTextField("0");
    JTextField displayOp = new JTextField("");
    
    public CalculadoraView() {
        setTitle("Calculadora");
        setSize(320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color corBg = new Color(243, 243, 243);
        getContentPane().setBackground(corBg);
    
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.insets = new Insets(0, 0, 0, 0);
        grid.weightx = 1.0;
        
        displayOp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        displayOp.setForeground(new Color(0, 0, 0, 100));
        displayOp.setHorizontalAlignment(JTextField.RIGHT);
        displayOp.setBackground(corBg);
        displayOp.setBorder(BorderFactory.createEmptyBorder(20, 5, -10, 5));
        displayOp.setMargin(new Insets(0,0,0,0));
        displayOp.setEditable(false);
        grid.gridx = 0;
        grid.gridy = 0;
        grid.weighty = 0.1;
        add(displayOp, grid);
        
        display.setFont(new Font("Segoe UI", Font.BOLD, 46));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(corBg);
        display.setBorder(BorderFactory.createEmptyBorder(-30, 5, 0, 5));
        display.setMargin(new Insets(0,0,0,0));
        display.setEditable(false);
        grid.gridx = 0;
        grid.gridy = 1;
        grid.weighty = 0.25;
        add(display, grid);
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(6,4,5,5));
        panelBotoes.setBackground(corBg);
        
        String[] botoes = {
        "%", "!", "C", "<-",
        "1/x", "x\u02B8", "√x", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "+/-", "0", ",", "="
        };
        
        for(String item: botoes) {
            JButton button = new JButton(item);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            //button.setBorder(BorderFactory.createLineBorder(cinzaEscuro));
            button.addActionListener(this);
            button.setFocusable(false);
            if(item.equals("=")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            }
            panelBotoes.add(button);
        }
        
        grid.gridy = 2;
        grid.weighty = 0.65;
        
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(0,4,4,4));
        add(panelBotoes, grid);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        String digito = evt.getActionCommand();
        if(digito.equals("x\u02B8")) digito = "^";
        if(display.getText().equals("Operação inválida")) controller.limparTudo();
        else if(isNumeric(digito) || digito.equals("=")) controller.cliqueBotao(digito);
        else if(Arrays.asList(ops).contains(digito)) controller.cliqueBotaoOp(digito);
        else {
            switch(digito) {
                case "C":
                    controller.limparTudo();
                    break;
                case "!":
                    controller.chamaFat();
                    break;
                case "√x":
                    controller.chamaSqrt();
                    break;
                case "<-":
                    controller.chamaApagaDigito();
                    break;
                case "+/-":
                    controller.mudarSinal();
                    break; 
            }
        }
    }
    
    private static boolean isNumeric(String str) {
        if(str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void updateDisplay(String num) {
        display.setText(num);
    }
    
    public void updateDisplayOperacao(String op) {
        displayOp.setText(op);
    }
    
    public void setController(CalculadoraController controller) {
        this.controller = controller;
    }
    
    public static void main(String[] args) {
        com.formdev.flatlaf.FlatLightLaf.setup();
        UIManager.put("Button.cornerRadius", 18);
        
        CalculadoraView view = new CalculadoraView();
        CalculadoraModel model = new CalculadoraModel();
        CalculadoraController controller = new CalculadoraController(view, model);
        
        view.setController(controller);
    }
}
