package sol;

import src.IAttributeDatum;
import src.INode;

public class LeafNode implements INode {

    public Object value;

    public LeafNode(Object value) {
        this.value = value;
    }

    @Override
    public Object lookupDecision(IAttributeDatum attrVals) {
        return this.value;
    }

    @Override
    public void printNode(String leadspace) {
        // print out the current node
        System.out.println(leadspace + this.value);
    }

}
