import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame 
{
    private JTextArea displayArea;

    private JButton btnLoad;
    private JButton btnDistribute;
    private JButton btnQueue1;
    private JButton btnQueue2;
    private JButton btnQueue3;
    private JButton btnProcess;
    private JButton btnExit;

    private QueueRouting routing;
    private DispatchedStack stackSystem;
    
    public MainGUI()
    {
        routing = new QueueRouting();
        stackSystem = new DispatchedStack();
        
        setTitle("Eco-Friendly Freight Routing System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Font
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 13);
        
        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Buttons
        JButton btnLoad = new JButton("Load File");
        JButton btnDistribute = new JButton("Distribute Carriers");
        JButton btnQueue1 = new JButton("Display Queue 1");
        JButton btnQueue2 = new JButton("Display Queue 2");
        JButton btnQueue3 = new JButton("Display Queue 3");
        JButton btnProcess = new JButton("Process Stack"); 
        JButton btnExit = new JButton("Exit");
        
        //Apply font to buttons
        btnLoad.setFont(labelFont);
        btnDistribute.setFont(labelFont);
        btnQueue1.setFont(labelFont);
        btnQueue2.setFont(labelFont);
        btnQueue3.setFont(labelFont);
        btnProcess.setFont(labelFont);
        btnExit.setFont(labelFont);
        
        //Add buttons
        panel.add(btnLoad);
        panel.add(btnDistribute);
        panel.add(btnQueue1);
        panel.add(btnQueue2);
        panel.add(btnQueue3);
        panel.add(btnProcess);
        panel.add(btnExit);

        displayArea = new JTextArea();
        displayArea.setFont(inputFont); 
        displayArea.setEditable(false);

        JScrollPane scrollPane =new JScrollPane(displayArea);

        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
              
        btnLoad.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                routing.loadFile("supply_chain_manifest.txt");
                
                displayArea.setText("");  
                displayArea.append("File loaded successfully.\n");
                displayArea.append("Total Carriers Loaded: " + routing.getCarrierList().size() + "\n\n");
            }
        });
        
        btnDistribute.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                routing.distributeCarriers();
                
                displayArea.append("");
                displayArea.append("Carriers distributed into queue.\n\n");
                
                displayArea.append("Queue 1 Size: " + routing.getQueue1().size() + "\n"); 
                displayArea.append("Queue 2 Size: " + routing.getQueue2().size() + "\n"); 
                displayArea.append("Queue 3 Size: " + routing.getQueue3().size() + "\n");
            }
        });
        
        btnQueue1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                displayQueue(routing.getQueue1(),"QUEUE 1");
            }
        });
        
        btnQueue2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                displayQueue(routing.getQueue2(),"QUEUE 2");
            }
        });
        
        btnQueue3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                displayQueue(routing.getQueue3(),"QUEUE 3");
            }
        });
        
        btnProcess.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                stackSystem.processPipeline(routing.getQueue1(),routing.getQueue2(),routing.getQueue3());
                
                String log = stackSystem.generateTerminalDepartureLog();
                
                displayArea.setText("Manifest Settlement Completed.\n" + "All queues processed.\n");
                displayArea.append(log);
            }
        });
        
        btnExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        
        setVisible(true);
    }

        
    private void displayQueue(java.util.Queue<CarrierInfo> queue, String title) 
    {
        displayArea.setText("");

        displayArea.append("========== " + title +" ==========\n\n");

        for(CarrierInfo carrier : queue)
        {
            displayArea.append("Carrier Name: "+ carrier.getCarrierName() + "\n");

            displayArea.append("Fleet Type: "+ carrier.getFleetType()+ "\n");

            displayArea.append("Total Shipments: "+ carrier.getTotalShipments()+ "\n");

            displayArea.append("Carbon Tax Cost: RM "+ String.format("%.2f",carrier.getTotalCarbonTaxCost()) + "\n\n");
            
        }
    }
    
    
    public static void main(String[] args) {
        new MainGUI();
    }
}
