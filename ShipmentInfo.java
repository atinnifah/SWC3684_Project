public class ShipmentInfo
{
    private String shipmentId;
    private String packageType;
    private int ecoPriorityScore; // 1-5 scale
    private String dispatchDate;
    private int estimatedTransitTime;
    private double carbonTaxCost;

    // constructor with parameter
    public ShipmentInfo(String sId, String pType, int score, String dDate, int transit, double tax)
    {
        shipmentId = sId;
        packageType = pType;
        ecoPriorityScore = score;
        dispatchDate = dDate;
        estimatedTransitTime = transit;
        carbonTaxCost = tax;
    }

    // mutator for each attributes
    public void setShipmentId(String sId)
    {
        shipmentId = sId;
    }
    public void setPackageType(String pType)
    {
        packageType = pType;
    }
    public void setEcoPriorityScore(int score)
    {
        ecoPriorityScore = score;
    }
    public void setDispatchDate(String dDate)
    {
        dispatchDate = dDate;
    }
    public void setEstimatedTransitTime(int transit)
    {
        estimatedTransitTime = transit;
    }
    public void setCarbonTaxCost(double tax)
    {
        carbonTaxCost = tax;
    }

    // accessor methods for each attributes
    public String getShipmentId()
    {
        return shipmentId;
    }
    public String getPackageType()
    {
        return packageType;
    }
    public int getEcoPriorityScore()
    {
        return ecoPriorityScore;
    }
    public String getDispatchDate()
    {
        return dispatchDate;
    }
    public int getEstimatedTransitTime()
    {
        return estimatedTransitTime;
    }
    public double getCarbonTaxCost()
    {
        return carbonTaxCost;
    }

    public String toString()
    {
        return ("Shipment ID: " + shipmentId + "\n" +
        "Package Type: " + packageType + "\n" +
        "Eco Priority Score: " + ecoPriorityScore + "\n" +
        "Dispatch Date: " + dispatchDate + "\n" +
        "Estimated Transit Time: " + estimatedTransitTime + "\n" +
        "Carbon Tax Cost: RM " + carbonTaxCost + "\n");
    }
}