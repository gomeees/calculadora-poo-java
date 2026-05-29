/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Arrays;

/**
 *
 * @author Gabriel Gomes
 */
public class CalculadoraModel {
    private String displayAtual = "0";
    private String displayOperacao = "";
    private String operador = "";
    public String ultimoDigito = "";
    public String ultimoSinal = "";
    private String sinalMemoria = "";
    private int numMemoria;
    private final String[] ops = {"+", "-", "*", "/", "%", "^"};
    private boolean esperandoIgual = false;
    private boolean ultimoDigitoIgual = false;
    private int num1 = 0;
    private String numM = "0";
    
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
    }
}
    
    public void addDigito(String num) {
        if(displayAtual.length() >= 3 && (displayAtual.charAt(0) != '-' || displayAtual.contains("."))) return;
        if(displayAtual.length() > 3) return;
        if(displayAtual.equals("0")) displayAtual = "";
        if(Arrays.asList(ops).contains(ultimoDigito)) displayAtual = "";
        if(ultimoDigitoIgual || ultimoDigito.equals("!")) {
            displayAtual = "";
            displayOperacao = "";
        }
        displayAtual += num;
        ultimoDigito = num;
        ultimoDigitoIgual = false;
    }
    
    public void apagaDigito() {
        if(displayAtual.length() == 1) displayAtual = "0";
        else if(displayAtual.length() > 1) displayAtual = displayAtual.substring(0, displayAtual.length() - 1);
        
    }
    
    public void addOperador(String op) {
        if(esperandoIgual && isNumeric(ultimoDigito)) {
            execOperacao(ultimoSinal);
            esperandoIgual = false;
        }
        displayOperacao = displayAtual + " " + op;
        operador = op;
        num1 = Integer.parseInt(displayAtual);
        ultimoDigito = op;
        esperandoIgual = true;
        ultimoDigitoIgual = false;
        ultimoSinal = op;
        sinalMemoria = "";
        numMemoria = 0;
        
    }
    
    public void execOperacao(String op) {
        if(!sinalMemoria.equals("")) {
            num1 = Integer.parseInt(displayAtual);
            switch(sinalMemoria) {
                case "+":
                    displayAtual = Integer.toString(num1 + numMemoria);
                    break;
                case "-":
                    displayAtual = Integer.toString(num1 - numMemoria);
                    break;
                case "*":
                    displayAtual = Integer.toString(num1 * numMemoria);
                    break;
                case "/":
                    if(numMemoria == 0)displayAtual = "Operação inválida";
                    else displayAtual = Integer.toString(num1 / numMemoria);
                    break;
                case "%":
                    if(numMemoria == 0)displayAtual = "Operação inválida";
                    else displayAtual = Integer.toString(num1 % numMemoria);
                    break;
                case "^":
                    if(num1 == 0) displayAtual = "1";
                    else {
                        int aux = 1;
                        for(int i = 0; i < numMemoria; i++) {
                            aux *= num1;
                        }
                        displayAtual = Integer.toString(aux);
                    }
                    break;
            }
            displayOperacao = num1 + " " + sinalMemoria + " " + numMemoria + " " + " =";
            esperandoIgual = false;
            ultimoDigito = op;
            ultimoDigitoIgual = true;
            if(!displayAtual.equals("Operação inválida"))System.out.println(num1 + " " + sinalMemoria + " " + numMemoria + " " + " = " + displayAtual);
            return;
        }
        if(!op.equals("="))operador = op;
        if(!displayAtual.equals("Operação inválida")) {
            int num2 = Integer.parseInt(displayAtual);
            switch(operador) {
                case "+":
                    displayAtual = Integer.toString(num1 + num2);
                    break;
                case "-":
                    displayAtual = Integer.toString(num1 - num2);
                    break;
                case "*":
                    displayAtual = Integer.toString(num1 * num2);
                    break;
                case "/":
                    if(num2 == 0) displayAtual = "Operação inválida";
                    else displayAtual = Integer.toString(num1 / num2);
                    break;
                case "%":
                    if(num2 == 0) displayAtual = "Operação inválida";
                    else displayAtual = Integer.toString(num1 % num2);
                    break;
                case "^":
                    if(num1 == 0) displayAtual = "1";
                    else {
                        int aux = 1;
                        for(int i = 0; i < num2; i++) {
                            aux *= num1;
                        }
                        displayAtual = Integer.toString(aux);
                    }
                    break;
            }
            if(!displayAtual.equals("Operação inválida"))System.out.println(num1 + " " + operador + " " + num2 + " " + " = " + displayAtual);
            if(op.equals("=") && !operador.isBlank()) {
                displayOperacao = displayOperacao + " " + Integer.toString(num2) + " =";
                numMemoria = num2;
                sinalMemoria = operador;
                
            }
            else displayOperacao = displayAtual + " " + op;
            esperandoIgual = false;
        }
        if(op.equals("=")) ultimoDigitoIgual = true;
    }
    
    public void fat() {
        int num = Integer.parseInt(displayAtual);
        int numInicial = num;
        if (num == 0 || num == 1) num = 1;
        else {
            int aux = num - 1;
            do {
                num *= aux;
                aux--;
            }while(aux > 0);
        }
        displayAtual = Integer.toString(num);
        displayOperacao = Integer.toString(numInicial) + " ! = ";
        ultimoDigito = "!";
        System.out.println(numInicial + "! = " + displayAtual);
    }
    
    public void sqrt() {
        double num = Double.parseDouble(displayAtual);
        displayAtual = Integer.toString((int)(Math.sqrt(num)));
    }
    
    public void addNumM() {
        numM = displayAtual;
    }
    
    public void colaNumM() {
        if(!esperandoIgual) displayOperacao = "";
        displayAtual = numM;
    }
    
    public String getDisplayAtual() {
        return displayAtual;
    }
    
    public void setDisplayAtual(String displayAtual) {
        this.displayAtual = displayAtual;
    }
    
    public String getDisplayOperacao() {
        return displayOperacao;
    }
    
    public String getUltimoDigito() {
        return ultimoDigito;
    }
    
    public boolean getUltimoDigitoIgual() {
        return ultimoDigitoIgual;
    }
    
    public void setDisplayOperacao(String displayOperacao) {
        this.displayOperacao = displayOperacao;
    }
    
    public void setOperador(String operador) {
        this.operador = operador;
    }
    
    public void setEsperandoIgual(boolean esperandoIgual) {
        this.esperandoIgual = esperandoIgual;
    }
    
    public void setNumMemoria(int num) {
        this.numMemoria = num;
    }
    
    public void setSinalMemoria(String sinal) {
        this.sinalMemoria = sinal;
    }
    
    public void limpaNum() {
        num1 = 0;
    }
}
