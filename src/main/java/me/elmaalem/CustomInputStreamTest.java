/*
package me.elmaalem;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomInputStreamTest extends InputStream implements KeyListener {

    private JTextArea textArea;
    private String str = null;
    private int pos = 0;

    public CustomInputStreamTest(JTextArea textArea) {
        this.textArea = textArea;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER){
            //ignores the special case of an empty line
            //so a test for \n before the Caret or the Caret still being at 0 is necessary
            int endpos = textArea.getCaret().getMark();
            int startpos = textArea.getText().substring(0, endpos-1).lastIndexOf('\n')+1;
            str = textArea.getText() + "\n";
            pos = 0;
            textArea.setText("");
            synchronized (this) {
                    //maybe this should only notify() as multiple threads may
                    //be waiting for input and they would now race for input
                    this.notifyAll();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    };

    @Override
    public int read() throws IOException {
        //test if the available input has reached its end
        //and the EOS should be returned
        if(str != null && pos == str.length()){
            str =null;
            //this is supposed to return -1 on "end of stream"
            //but I'm having a hard time locating the constant
            return java.io.StreamTokenizer.TT_EOF;
        }
        //no input available, block until more is available because that's
        //the behavior specified in the Javadocs
        while (str == null || pos >= str.length()) {
            try {
                //according to the docs read() should block until new input is available
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        //read an additional character, return it and increment the index
        return str.charAt(pos++);
    }

}


*/
