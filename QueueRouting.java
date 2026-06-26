import java.io.*;
import java.util.*;
import java.util.Queue; //import the Queue package
import java.util.LinkedList; //import the LinkedList pacakage

public class QueueRouting 
{
    private LinkedList<CarrierInfo> carrierList = new LinkedList<>();

    //Initialize the 3 required queue objects
    private Queue<CarrierInfo> queue1; //Regional Micro-Distribution Queue
    private Queue<CarrierInfo> queue2; //Cross-Border Express Queue
    private Queue<CarrierInfo> queue3; //Industrial Bulk Logistics Fleet 

    public QueueRouting()
    {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        queue3 = new LinkedList<>();
    }

    //File Load
    public void loadFile(String fileName)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String inData;

            while((inData = br.readLine()) !=null)
            {

                StringTokenizer st = new StringTokenizer(inData, ";");

                String carrierId = st.nextToken();
                String carrierName = st.nextToken();
                String fleetType = st.nextToken();

                String shipmentId = st.nextToken();
                String packageType = st.nextToken();
                int ecoPriorityScore = Integer.parseInt(st.nextToken());
                String dispatchDate = st.nextToken();
                int estimatedTransitTime = Integer.parseInt(st.nextToken());
                double carbonTaxCost = Double.parseDouble(st.nextToken());

                ShipmentInfo shipment = new ShipmentInfo(shipmentId,packageType,ecoPriorityScore,
                dispatchDate,estimatedTransitTime,carbonTaxCost);

                CarrierInfo carrier = findCarrier(carrierId); 

                if(carrier == null)
                {
                    carrier = new CarrierInfo(carrierId,carrierName,fleetType);
                    carrierList.add(carrier);
                }

                carrier.addShipment(shipment);
            }

            br.close();
        }   
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private CarrierInfo findCarrier(String carrierId)
    {
        for(CarrierInfo carrier : carrierList)
        {
            if(carrier.getCarrierId().equals(carrierId))
            {
                return carrier;
            }
        }

        return null;
    }

    public LinkedList<CarrierInfo> getCarrierList()
    {
        return carrierList;
    }

    //Route carriers into queues
    public void distributeCarriers() 
    {
        boolean alternate = true;
        //to alternate carriers between Queue 1 and Queue 2 for balanced distribution

        for (CarrierInfo carrier : carrierList)
        {
            if (carrier.getTotalShipments() <=3)
            {
                if(alternate) 
                {
                    queue1.offer(carrier);

                    System.out.println(carrier.getCarrierName() + " -----> Regional Micro Distribution Queue");
                    System.out.println();
                } 
                else 
                {
                    queue2.offer(carrier);

                    System.out.println(carrier.getCarrierName() + " -----> Cross Border Express Queue");
                    System.out.println();
                }

                alternate = !alternate;
                //switches between true and false to alternate queue
            }
            else
            {
                queue3.offer(carrier);

                System.out.println(carrier.getCarrierName() + " -----> Industrial Bulk Logistics Fleet");
                System.out.println();
            }
        }
    }

    //Display Queue 1
    public void displayQueue1(Queue<CarrierInfo> queue1)
    {
        System.out.println("----- QUEUE 1 -----");
        System.out.println("Regional Micro Distribution Queue");

        displayQueue(queue1);
    }
    //Display Queue 2
    public void displayQueue2(Queue<CarrierInfo> queue2)
    {
        System.out.println("----- QUEUE 2 -----");
        System.out.println("Cross Border Express Queue");

        displayQueue(queue2);
    }

    //Display Queue 3
    public void displayQueue3(Queue<CarrierInfo> queue3)
    {
        System.out.println("----- QUEUE 3 -----");
        System.out.println("Industrial Bulk Logistics Fleet");

        displayQueue(queue3);
    }

    //Common display method
    private void displayQueue(Queue<CarrierInfo> queue)
    {
        for (CarrierInfo carrier : queue)
        {
            System.out.println("--------------------");
            System.out.println("Carrier Name: " + carrier.getCarrierName()); 
            System.out.println("Fleet Classification Type: " + carrier.getFleetType());

            System.out.println("Assigned Shipments: ");
            System.out.println();

            for (ShipmentInfo shipment : carrier.getShipments())
            {
                System.out.println("Shipment ID: " + shipment.getShipmentId());
                System.out.println("Package Type: " + shipment.getPackageType());
                System.out.println("Eco Priority Score: " + shipment.getEcoPriorityScore());
                System.out.println("Dispatch Date: " + shipment.getDispatchDate());
                System.out.println("Estimated Transit Time: " + shipment.getEstimatedTransitTime() + "hours" );
                System.out.printf("Carbon Tax Cost: RM%.2f%n", shipment.getCarbonTaxCost());
                System.out.println();
            }

            System.out.printf("Total Compiled Carbon Tax Cost: RM %.2f%n", carrier.getTotalCarbonTaxCost());

            System.out.println("--------------------");
        }
    }

    public Queue<CarrierInfo> getQueue1() 
    {
        return queue1;
    }

    public Queue<CarrierInfo> getQueue2() 
    {
        return queue2;
    }

    public Queue<CarrierInfo> getQueue3()
    {
        return queue3;
    }
}