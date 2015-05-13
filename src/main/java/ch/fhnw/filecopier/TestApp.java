/*
 * TestApp.java
 *
 * Created on 22. April 2008, 15:30
 *
 * This file is part of the Java File Copy Library.
 * 
 * The Java File Copy Libraryis free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 * 
 * The Java File Copy Libraryis distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.fhnw.filecopier;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * A small application for testing the Java File Copy Library.
 * @author Ronny Standtke <Ronny.Standtke@gmx.net>
 */
public class TestApp extends javax.swing.JFrame {

    private static final Logger LOGGER =
            Logger.getLogger(TestApp.class.getName());

    /** Creates new form TestApp */
    public TestApp() {
        initComponents();

        // test run
        final FileCopier fileCopier = new FileCopier();
        fileCopierPanel.setFileCopier(fileCopier);
        SwingWorker copier = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() {
                try {
                    // Unix example
                    Source[] sources = new Source[]{
                        // all files from directory /home/user/source1/
                        new Source("/tmp/filecopiertest/source1/"),
                        // all *.java files from directory /home/user/source2/
                        new Source("/tmp/filecopiertest/source2/", ".*\\.java")
                    };
                    String[] destinations = new String[]{
                        "/tmp/filecopiertest/destination1",
                        "/tmp/filecopiertest/destination2"
                    };
                    CopyJob unixCopyJob = new CopyJob(sources, destinations, false);
                    fileCopier.copy(unixCopyJob);

                    // Windows example
//                    CopyJob windowsCopyJob = new CopyJob(
//                            new Source[]{new Source("C:\\Test.txt")},
//                            new String[]{"C:\\Test2.txt"});
//                    fileCopier.copy(windowsCopyJob);

                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Done!");
            }
        };
        copier.execute();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileCopierPanel = new FileCopierPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileCopierPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileCopierPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TestApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FileCopierPanel fileCopierPanel;
    // End of variables declaration//GEN-END:variables
}
