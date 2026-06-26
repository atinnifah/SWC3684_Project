import java.util.LinkedList;
import java.util.List;

public class CarrierInfo
{
    private String carrierId;
    private String carrierName;
    private String fleetType;
    private List<ShipmentInfo> shipments; // Establishes a has-a relationship

    // constructor with parameter
    public CarrierInfo(String cId, String cName, String fType)
    {
        carrierId = cId;
        carrierName = cName;
        fleetType = fType;
        shipments = new LinkedList<ShipmentInfo>(); // initial value is empty list
    }

    // mutator for each attributes
    public void setCarrierId(String cId)
    {
        carrierId = cId;
    }

    public void setCarrierName(String cName)
    {
        carrierName = cName;
    }

    public void setFleetType(String fType)
    {
        fleetType = fType;
    }

    public void setShipments(List<ShipmentInfo> shipList)
    {
        shipments = shipList;
    }

    // accessor methods for each attributes
    public String getCarrierId()
    {
        return carrierId;
    }

    public String getCarrierName()
    {
        return carrierName;
    }

    public String getFleetType()
    {
        return fleetType;
    }

    public List<ShipmentInfo> getShipments()
    {
        return shipments;
    }

    // Helper process to append item to nested list
    public void addShipment(ShipmentInfo s)
    {
        shipments.add(s);
    }

    public int getTotalShipments() 
    {
        return shipments.size();
    }

    public double getTotalCarbonTaxCost() {
        double total = 0.0;
        for (ShipmentInfo s : shipments) {
            total += s.getCarbonTaxCost();
        }
        return total;
    }

    public void displayCarrierDetails() 
    {
        {
            System.out.println("Carrier ID: " + carrierId);
            System.out.println("Carrier Name: " + carrierName);
            System.out.println("Fleet Classification Type: " + fleetType);
            System.out.println("Assigned Shipments:");
            for (ShipmentInfo s : shipments) {
                System.out.println("  - " + s.getShipmentId() + " | " + s.getPackageType() + " | RM " + s.getCarbonTaxCost());
            }
            System.out.println("Total Carbon Tax Cost: RM " + String.format("%.2f", getTotalCarbonTaxCost()));
            System.out.println("--------------------------------------------------");
        }
    }
    
    public String toString()
    {
        return ("Carrier ID: " + carrierId + "\n" +
        "Carrier Name: " + carrierName + "\n" +
        "Fleet Type: " + fleetType + "\n" +
        "Number of Managed Shipments: " + shipments.size() + "\n");
    }
}