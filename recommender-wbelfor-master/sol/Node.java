package sol;

import src.IAttributeDatum;
import src.INode;

import java.util.ArrayList;

public class Node implements INode {

    public String attribute;
    public Object mostCommonValue;
    public ArrayList<Edge> edges;

    public Node(String attribute, Object mostCommonValue) {
        this.attribute = attribute;
        this.mostCommonValue = mostCommonValue;
    }

    @Override
    public Object lookupDecision(IAttributeDatum attrVals) {

        // declare result object
        Object result = null;

        // go through all of the available edges looking for a matching path
        for (Edge edge : edges) {

            // check if match
            if (edge.value.equals(attrVals.getValueOf(this.attribute))) {
                // recursive pass-through
                result = edge.node.lookupDecision(attrVals);
                break;
            }

        }

        // check if still null
        if (result == null) {

            // go through all of the available edges looking for the most common one)
            for (Edge edge : edges) {
                // check if match
                if (edge.value.equals(mostCommonValue)) {
                    // recursive pass-through
                    result = edge.node.lookupDecision(attrVals);
                    break;
                }
            }

        }

        return result;
    }

    @Override
    public void printNode(String leadspace) {

        // print out the current node
        System.out.println(leadspace + this.attribute);

        // go through the nodes children
        for (Edge edge : edges) {
            // check if end
            if (edge.node instanceof Node) {
                edge.node.printNode(leadspace + "   ");
            }
        }

    }

}
