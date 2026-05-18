/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Arrays;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import model.CalculadoraModel;
import view.CalculadoraView;
/**
 *
 * @author Gabriel Gomes
 */
public class CalculadoraController {
    private CalculadoraView view;
    private CalculadoraModel model;
    private final String[] ops = {"+", "-", "*", "/"};
    
    public CalculadoraController(CalculadoraView view, CalculadoraModel model) {
        this.view = view;
        this.model = model;
        configTeclado();
    }
    
    private void configTeclado() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent evt) {
                if(evt.getID() == KeyEvent.KEY_PRESSED) {
                    teclaApertada(evt);
                }
                return false;
            }
        });
    }
    
    public void cliqueBotao(String digito) {
        if(digito.equals("=")) {
            model.execOperacao("=");
            view.updateDisplay(model.getDisplayAtual());
            view.updateDisplayOperacao(model.getDisplayOperacao());
            return;
        } else condAddDigito(digito);
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void cliqueBotaoOp(String op) {
        acaoOperador(op);
        view.updateDisplay(model.getDisplayAtual());
        view.updateDisplayOperacao(model.getDisplayOperacao());
        
    }
    
    public void chamaApagaDigito() {
        model.apagaDigito();
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void chamaFuncPorcentagem() {
        model.funcPoercentagem();
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void chamaSqrt() {
        model.sqrt();
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void chamaSqr() {
        model.sqr();
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void limparTudo() {
        view.updateDisplay("0");
        model.setDisplayAtual("0");
        view.updateDisplayOperacao("");
        model.setDisplayOperacao("");
        model.limpaNum();
        model.setOperador("");
        model.setEsperandoIgual(false);
        model.setNumMemoria(0);
        model.setSinalMemoria("");
    }
    
    public void condAddDigito(String digito) {
        if(model.getUltimoDigitoIgual()) {
            view.updateDisplay("");
            model.setDisplayAtual("");
            view.updateDisplayOperacao("");
            model.setDisplayOperacao("");
            model.addDigito(digito);
        } else if(Arrays.asList(ops).contains(model.getUltimoDigito())) {
            view.updateDisplay("");
            model.setDisplayAtual("");
            model.addDigito(digito);
        }  else model.addDigito(digito);
    }
    
    public void acaoOperador(String op) {
        model.addOperador(op);
        view.updateDisplay(model.getDisplayAtual());
    }
    
    public void mudarSinal() {
        String displayAtual = model.getDisplayAtual();
        if(!displayAtual.equals("0")) {
            if(displayAtual.contains("-")) model.setDisplayAtual(displayAtual.substring(1));
            else model.setDisplayAtual("-".concat(displayAtual));
            view.updateDisplay(model.getDisplayAtual());
        }
    }
    
    public void teclaApertada(KeyEvent evt) {
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_1, KeyEvent.VK_NUMPAD1:
                condAddDigito("1");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_2, KeyEvent.VK_NUMPAD2:
                condAddDigito("2");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_3, KeyEvent.VK_NUMPAD3:
                condAddDigito("3");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_4, KeyEvent.VK_NUMPAD4:
                condAddDigito("4");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_5, KeyEvent.VK_NUMPAD5:
                condAddDigito("5");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_6, KeyEvent.VK_NUMPAD6:
                condAddDigito("6");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_7, KeyEvent.VK_NUMPAD7:
                condAddDigito("7");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_8, KeyEvent.VK_NUMPAD8:
                condAddDigito("8");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_9, KeyEvent.VK_NUMPAD9:
                condAddDigito("9");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_0, KeyEvent.VK_NUMPAD0:
                condAddDigito("0");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD, KeyEvent.VK_DECIMAL:
                condAddDigito(".");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_BACK_SPACE:
                chamaApagaDigito();
                break;
            case KeyEvent.VK_ADD:
                acaoOperador("+");
                System.out.println(model.getDisplayOperacao() + " " + model.getDisplayAtual());
                break;
            case KeyEvent.VK_SUBTRACT:
                acaoOperador("-");
                break;
            case KeyEvent.VK_MULTIPLY:
                acaoOperador("*");
                break;
            case KeyEvent.VK_DIVIDE:
                acaoOperador("/");
                break;
            case KeyEvent.VK_ENTER:
                model.execOperacao("=");
                view.updateDisplay(model.getDisplayAtual());
                break;
            case KeyEvent.VK_C:
                limparTudo();
                view.updateDisplay(model.getDisplayAtual());
                break;
        }
        
        
        view.updateDisplayOperacao(model.getDisplayOperacao());
    }
}
