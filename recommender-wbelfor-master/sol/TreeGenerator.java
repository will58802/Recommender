package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.IGenerator;
import src.INode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/*
 * Class for creating and interacting with a decision tree given a dataset.
 *
 * T is the type of object that we are trying to classify.
 * (like src.Vegetable)
 */
public class TreeGenerator<T extends IAttributeDatum> implements IGenerator {

    public IAttributeDataset<T> initTrainingData;

    public INode root = null;

    /**
     * Constructor for this class.
     *
     * @param initTrainingData - IAttributeDataset of the data table
     */
    public TreeGenerator(IAttributeDataset<T> initTrainingData) {
        this.initTrainingData = initTrainingData;
    }


    private INode recursion(IAttributeDataset<T> data, LinkedList<String> attributes, String targetAttr) {

        // Base Case
        if (attributes.size() == 0) {

            return new LeafNode(data.mostCommonValue(targetAttr));

        } else {

            // get the next attribute in line
            String current = attributes.getFirst();
            attributes.removeFirst();

            // make the current top node
            Node top = new Node(current, data.mostCommonValue(current));

            // do the partitioning
            LinkedList<IAttributeDataset<T>> results = data.partition(current);

            // declare node array / edge array
            ArrayList<INode> nodes = new ArrayList<INode>();
            ArrayList<Object> edgeValues = new ArrayList<Object>();

            // go through the subsets
            for (IAttributeDataset<T> result : results) {
                nodes.add(recursion(result, attributes, targetAttr));
                edgeValues.add(result.getSharedValue(current));
            }

            // declare edge array
            ArrayList<Edge> edges = new ArrayList<Edge>();

            // go through all of the nodes (add edges to nodes)
            for (int i = 0; i < nodes.size(); i++) {
                edges.add(new Edge(edgeValues.get(i), nodes.get(i)));
            }

            // append the edges to the top node
            top.edges = edges;

            // return the top node
            return top;

        }

    }

    @Override
    public INode buildClassifier(String targetAttr) {

        // Filter out the targetAttr
        LinkedList<String> attributes = new LinkedList<String>();
        for (String current : this.initTrainingData.getAttributes()) {
            if (!current.equals(targetAttr)) {
                attributes.add(current);
            }
        }

        // shuffle the attributes
        Collections.shuffle(attributes);
        Collections.shuffle(attributes);
        Collections.shuffle(attributes);
        Collections.shuffle(attributes);
        Collections.shuffle(attributes);

        // build the tree
        this.root = recursion(this.initTrainingData, attributes, targetAttr);

        return this.root;
    }

    @Override
    public Object lookupRecommendation(IAttributeDatum forVals) {
        return this.root.lookupDecision(forVals);
    }

    @Override
    public void printTree() {
        this.root.printNode("");
    }

}
