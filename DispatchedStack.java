import java.util.Queue;
import java.util.Stack;

public class DispatchedStack {

    private Stack<CarrierInfo> dispatchedStack = new Stack<>();

    public void processPipeline(Queue<CarrierInfo> q1, Queue<CarrierInfo> q2, Queue<CarrierInfo> q3) {

        // Blocks of 5 until all are  completely empty
        while (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()) {
            processBatch(q1, 5, "Regional Queue");
            processBatch(q2, 5, "Cross-Border Queue");
            processBatch(q3, 5, "Industrial Bulk Queue");
        }
    }

    private void processBatch(Queue<CarrierInfo> queue, int batchSize, String laneName) {
        int processed = 0;
        while (!queue.isEmpty() && processed < batchSize) {
            CarrierInfo carrier = queue.poll(); // Dequeue
            System.out.println("CLEARED FOR DEPARTURE -> " + carrier.getCarrierName() + " via " + laneName);
            dispatchedStack.push(carrier); // Push onto Central tracking stack
            processed++;
        }
    }

    public String generateTerminalDepartureLog() {
        StringBuilder sb = new StringBuilder();

        // Pop every element out of the Stack to trace terminal logs
        while (!dispatchedStack.isEmpty()) {
            CarrierInfo clearedCarrier = dispatchedStack.pop();
            sb.append("DEPARTED -> Carrier: ").append(clearedCarrier.getCarrierName())
            .append(" | Fleet: ").append(clearedCarrier.getFleetType())
            .append(" | Shipments: ").append(clearedCarrier.getTotalShipments())
            .append(" | Carbon Cost: RM ").append(String.format("%.2f", clearedCarrier.getTotalCarbonTaxCost()))
            .append("\n");
        }

        return sb.toString();
    }
}