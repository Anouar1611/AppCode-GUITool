package me.elmaalem;

import sun.applet.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomInputStream extends InputStream {

    final JTextArea text;
    final BlockingQueue<String> queue;

    public CustomInputStream(JTextArea text) {
        this.text = text;
        queue = new LinkedBlockingQueue<>();

    }

    private String s;
    int pos;

    @Override
    public int read() throws IOException {
        while (null == s || s.length() <= pos) {
            try {
                s = queue.take();
                pos = 0;
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ret = (int) s.charAt(pos);
        pos++;
        return ret;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytes_copied = 0;
        while (bytes_copied < 1) {
            while (null == s || s.length() <= pos) {
                try {
                    s = queue.take();
                    System.out.println("s = " + s);
                    pos = 0;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int bytes_to_copy = len < s.length()-pos ? len : s.length()-pos;
            System.arraycopy(s.getBytes(), pos, b, off, bytes_to_copy);
            pos += bytes_to_copy;
            bytes_copied += bytes_to_copy;
        }
        return bytes_copied;
    }
    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public void actionPerformed(KeyEvent e) {
        queue.add(text.getText() + "\r\n");
        text.setText("");
    }
}
