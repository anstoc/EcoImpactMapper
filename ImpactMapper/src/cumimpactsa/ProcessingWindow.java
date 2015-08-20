/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ast
 */
public class ProcessingWindow extends javax.swing.JDialog {

    /**
     * Creates new form ProcessingWindow
     */
    public ProcessingWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //set up data layer list
        String[] stressorNames=MappingProject.getStressorNames();
        String[] ecocompNames = MappingProject.getEcocompNames();
        DefaultListModel model = new DefaultListModel();
        for(int i=0; i<stressorNames.length; i++) {model.addElement(stressorNames[i]);}
        for(int i=0; i<ecocompNames.length; i++) {model.addElement(ecocompNames[i]);}
        this.listDataLayers.setModel(model);
        
        //set up processor list
        String[] processorNames=MappingProject.getProcessorNames();
        DefaultListModel model2 = new DefaultListModel();
        for(int i=0; i<processorNames.length; i++) {model2.addElement(processorNames[i]);}
        this.listAvailableProcessors.setModel(model2);
        
    
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDataLayers = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listAvailableProcessors = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listProcessingChain = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listProcessingParameters = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        textFieldParamValue = new javax.swing.JTextField();
        buttonSetParameterValue = new javax.swing.JButton();
        buttonAddProcessor = new javax.swing.JButton();
        buttonRemoveProcessor = new javax.swing.JButton();
        buttonOk = new javax.swing.JButton();
        buttonSetForAllStressors = new javax.swing.JButton();
        buttonSetForAllEcocomps = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Data layers");

        listDataLayers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDataLayersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listDataLayers);

        jLabel2.setText("Available processors");

        jScrollPane2.setViewportView(listAvailableProcessors);

        jLabel3.setText("Processing chain");

        jScrollPane3.setViewportView(listProcessingChain);

        jLabel4.setText("Processing parameters");
        jLabel4.setEnabled(false);

        listProcessingParameters.setEnabled(false);
        jScrollPane4.setViewportView(listProcessingParameters);

        jLabel5.setText("Parameter value");
        jLabel5.setEnabled(false);

        buttonSetParameterValue.setText("Set value");
        buttonSetParameterValue.setEnabled(false);
        buttonSetParameterValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetParameterValueActionPerformed(evt);
            }
        });

        buttonAddProcessor.setText("-->");
        buttonAddProcessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddProcessorActionPerformed(evt);
            }
        });

        buttonRemoveProcessor.setText("<--");
        buttonRemoveProcessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveProcessorActionPerformed(evt);
            }
        });

        buttonOk.setText("OK");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        buttonSetForAllStressors.setText("Set for all stressors");
        buttonSetForAllStressors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetForAllStressorsActionPerformed(evt);
            }
        });

        buttonSetForAllEcocomps.setText("Set for all ecol. components");
        buttonSetForAllEcocomps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetForAllEcocompsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(32, 32, 32)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(buttonAddProcessor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(buttonRemoveProcessor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(12, 12, 12)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel5)
                                    .add(textFieldParamValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(buttonSetParameterValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(1, 1, 1)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(buttonOk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(buttonSetForAllStressors, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                                .add(buttonSetForAllEcocomps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                            .add(layout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel4)
                                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(jLabel2))
                .add(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(91, 91, 91)
                        .add(buttonAddProcessor)
                        .add(12, 12, 12)
                        .add(buttonRemoveProcessor))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(layout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel2)
                                    .add(jLabel3)
                                    .add(jLabel4))))
                        .add(8, 8, 8)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 478, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 478, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(textFieldParamValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(6, 6, 6)
                                .add(buttonSetParameterValue)
                                .add(1, 1, 1)
                                .add(buttonSetForAllStressors)
                                .add(1, 1, 1)
                                .add(buttonSetForAllEcocomps)))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonOk, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listDataLayersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listDataLayersValueChanged
        //get selected data layer
        String selectedValue= (String) this.listDataLayers.getSelectedValue();
        
        //case 1: no data selected
        if(selectedValue==null)
        {
            DefaultListModel model = new DefaultListModel();
            this.listProcessingChain.setModel(model);
        }
        //case 2: one data layer selected
        else
        {
            DefaultListModel model = new DefaultListModel();
            ArrayList<PreProcessor> processingChain = MappingProject.getDataLayerByName(selectedValue).getProcessingChain();
            
            for(int i=0; i<processingChain.size();i++)
            {
                model.addElement(processingChain.get(i).getName());
            }
            
            this.listProcessingChain.setModel(model);
        }
            
        
    }//GEN-LAST:event_listDataLayersValueChanged

    private void buttonAddProcessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddProcessorActionPerformed
        //get selected data layer
        String selected= (String) this.listDataLayers.getSelectedValue();
        String processor = (String) this.listAvailableProcessors.getSelectedValue();
        if(selected==null || processor==null)
        {
            return;
        }
        else
        {
            PreProcessor newProcessor=MappingProject.getNewProcessorByName(processor);
            MappingProject.getDataLayerByName(selected).getProcessingChain().add(newProcessor);
            listDataLayersValueChanged(null); //for updating display
        }
        
        
        
    }//GEN-LAST:event_buttonAddProcessorActionPerformed

    private void buttonRemoveProcessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveProcessorActionPerformed
        String selected= (String) this.listDataLayers.getSelectedValue();
        String processor = (String) this.listProcessingChain.getSelectedValue();
        if(selected==null || processor==null)
        {
            return;
        }
         else
        {
            ArrayList<PreProcessor> chain =MappingProject.getDataLayerByName(selected).getProcessingChain();
            chain.remove(this.listProcessingChain.getSelectedIndex());
            listDataLayersValueChanged(null); //for updating display
        }
    }//GEN-LAST:event_buttonRemoveProcessorActionPerformed

    private void buttonSetParameterValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetParameterValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSetParameterValueActionPerformed

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonOkActionPerformed

    private void buttonSetForAllStressorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetForAllStressorsActionPerformed
       //add to stressors
       for(int i=0; i<MappingProject.stressors.size();i++)
       {
           //create ProcessingChain from list
           ArrayList<PreProcessor> processingChain = new ArrayList<PreProcessor>();
           for(int j=0; j<this.listProcessingChain.getModel().getSize();j++)
            {
                String procName = (String) this.listProcessingChain.getModel().getElementAt(j);
                processingChain.add(MappingProject.getNewProcessorByName(procName));
            }
           
           MappingProject.stressors.get(i).setProcessingChain(processingChain);
       }
        
    }//GEN-LAST:event_buttonSetForAllStressorsActionPerformed

    private void buttonSetForAllEcocompsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetForAllEcocompsActionPerformed
     
       //add to ecological components
       for(int i=0; i<MappingProject.ecocomps.size();i++)
       {
           //create ProcessingChain from list
           ArrayList<PreProcessor> processingChain = new ArrayList<PreProcessor>();
           for(int j=0; j<this.listProcessingChain.getModel().getSize();j++)
            {
                String procName = (String) this.listProcessingChain.getModel().getElementAt(j);
                processingChain.add(MappingProject.getNewProcessorByName(procName));
            }
           
           MappingProject.ecocomps.get(i).setProcessingChain(processingChain);
       }
    }//GEN-LAST:event_buttonSetForAllEcocompsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcessingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProcessingWindow dialog = new ProcessingWindow(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddProcessor;
    private javax.swing.JButton buttonOk;
    private javax.swing.JButton buttonRemoveProcessor;
    private javax.swing.JButton buttonSetForAllEcocomps;
    private javax.swing.JButton buttonSetForAllStressors;
    private javax.swing.JButton buttonSetParameterValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList listAvailableProcessors;
    private javax.swing.JList listDataLayers;
    private javax.swing.JList listProcessingChain;
    private javax.swing.JList listProcessingParameters;
    private javax.swing.JTextField textFieldParamValue;
    // End of variables declaration//GEN-END:variables
}