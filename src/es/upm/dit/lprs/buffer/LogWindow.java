package es.upm.dit.lprs.buffer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * @author Jose A. Manas
 * @version 16/3/2012
 */
public class LogWindow {
    private JTextArea AREA;
    @SuppressWarnings("unused")
	private final Thread thread;

    public LogWindow(Thread thread) {
        this.thread = thread;

        JFrame frame = new JFrame(thread.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AREA = new JTextArea(25, 30);
        frame.getContentPane().add(new JScrollPane(AREA));

        JToolBar toolBar = new JToolBar();
        toolBar.add(new ActionStart(thread));
        toolBar.add(new ActionStop(thread));
        toolBar.add(new ActionInterrumpir(thread));
        frame.add(toolBar, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public void println(String msg) {
        AREA.append(String.format("%tT: %s%n", new Date(), msg));
    }

    @SuppressWarnings("serial")
	private static class ActionStart
            extends AbstractAction {
        private final Thread thread;

        private ActionStart(Thread thread) {
            super("arrancar");
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent event) {
            if (thread instanceof Productor) {
                Productor productor = (Productor) thread;
                productor.arrancar();
            }
            if (thread instanceof Consumidor) {
                Consumidor consumidor = (Consumidor) thread;
                consumidor.arrancar();
            }
        }
    }
    @SuppressWarnings("serial")
	private static class ActionStop
            extends AbstractAction {
        private final Thread thread;

        private ActionStop(Thread thread) {
            super("parar");
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent event) {
            if (thread instanceof Productor) {
                Productor productor = (Productor) thread;
                productor.parar();
            }
            if (thread instanceof Consumidor) {
                Consumidor consumidor = (Consumidor) thread;
                consumidor.parar();
            }
        }
    }

    @SuppressWarnings("serial")
	private static class ActionInterrumpir
            extends AbstractAction {
        private final Thread thread;

        private ActionInterrumpir(Thread thread) {
            super("interrumpir");
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent event) {
            thread.interrupt();
        }
    }
}
