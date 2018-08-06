package edu.toronto.group0162.view;

import edu.toronto.group0162.dao.EdgeDao;
import edu.toronto.group0162.dao.NodeDao;
import edu.toronto.group0162.entity.Node;
import edu.toronto.group0162.service.Graph;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 涉及计价
 */
public class MapFrame extends JFrame {

//    @Getter
//    @Setter
//    private NodeDao nodeDao;
//    private EdgeDao edgeDao;

    private CityFrame cityFrame;

    private Graph transitGraph;

    private JButton btnSearch;
    private JButton btnConfirm;
    private JButton btnBack;

    JComboBox entranceBox;
    JComboBox exitBox;

    private JLabel lbEntrance;
    private JLabel lbExit;


    JTextArea info;

    @Getter
    @Setter
    private int currentLogInUid;

    private  int[][] path ;
    private double[][] floy ;
    private List<Node> listNodes;


    public MapFrame(String title) {
        super( title );                      // invoke the JFrame constructor
        setSize( 1400, 1000 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLayout( new FlowLayout(FlowLayout.LEFT,20,40) );       // set the layout manager

        btnSearch = new JButton ("search route");
        btnConfirm = new JButton ("confirm");
        btnBack = new JButton("Back");

        entranceBox = new JComboBox();
        exitBox = new JComboBox();

        lbEntrance = new JLabel("Enter");
        lbExit = new JLabel("Exit");

        info = new JTextArea(10, 25);
        info.setLineWrap(true);
        info.setVisible(true);
        info.setEditable(false);

        add(btnSearch);
        add(btnConfirm);
        add(lbEntrance);
        add(entranceBox);
        add(lbExit);
        add(exitBox);
        add(info);
        add(btnBack);


        btnSearch.addActionListener((ActionEvent e) -> this.onClickSearch(e));
        btnConfirm.addActionListener((ActionEvent e) -> this.onClickConfirm(e));
        btnBack.addActionListener((ActionEvent e) -> this.onClickBack(e));
//
//        JTextField tf = new JTextField();
//        tf.setColumns( 20 );
//        base.add(tf);
//        pack();
    }

    private void onClickSearch(ActionEvent e) {




             this.transitGraph.GetShortestPath((String) entranceBox.getSelectedItem(),(String) exitBox.getSelectedItem(),
                        this.floy,this.path,this.listNodes);

        info.setText("Shortest Route: " +this.transitGraph.getTripInfo());


//                cardService.getCardDao().get((Integer)((JComboBox) e.getSource()).getSelectedItem()).getBalance());


        //                        c.getSelectedIndex() + "   ");
                //+ ((JComboBox) e.getSource()).getSelectedItem());


    }

    private void onClickConfirm(ActionEvent e) {
    }

    public void setTransitGraph(Graph graph){
        this.transitGraph = graph;
    }


    public void setAlgorithm(int[][]path, double[][]floy, List<Node>listNodes){
        this.path = path;
        this.floy = floy;
        this.listNodes = listNodes;
    }

    private void onClickBack(ActionEvent e) {
        this.setVisible(false);
        this.cityFrame.setVisible(true);
    }

    public void setCityFrame(CityFrame cityFrame){
        this.cityFrame = cityFrame;
    }


}
