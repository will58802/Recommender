package sol;

import src.IAttributeDataset;
import tester.Tester;

import java.util.LinkedList;

public class TestSuite {

    private static ListObjsData<Candidate> candidates;

    public void testFunctions(Tester t){
        LinkedList<String> canAttr = new LinkedList<String>();
        // different attributes to consider similar to the Candidate class
        canAttr.add("gender");
        canAttr.add("leadershipExperience");
        canAttr.add("lastPositionDuration");
        canAttr.add("numWorkExperiences");
        canAttr.add("programmingLanguages");
        canAttr.add("gpa");
        canAttr.add("location");
        canAttr.add("hired");

        String filepath = "data/train_candidates_unequal.csv";

        RecommenderCSVParser<Candidate> parser = new RecommenderCSVParser<Candidate>();

        LinkedList<Candidate> allCandidates = new LinkedList<>();
        // parsing the dataset in the form of a CSV file, CommaSeparatedValues.
        allCandidates = (LinkedList<Candidate>) parser.parse(Candidate.class, filepath, ',', true);
        // if the filename is the correlated variable, we're not looking at the gender
        // variable at all while looking
        // at different Candidate objects while building the tree.
        if (filepath.equals("data/train_candidates_correlated.csv")) {
            canAttr.remove("gender");
        }

        TestSuite.candidates = new ListObjsData<Candidate>(canAttr, allCandidates);

        t.checkExpect(TestSuite.candidates.size(), 52);

        LinkedList<String> attributes = new LinkedList<String>();
        attributes.addLast("gender");
        attributes.addLast("leadershipExperience");
        attributes.addLast("lastPositionDuration");
        attributes.addLast("numWorkExperiences");                                                                           //getAttributes() test
        attributes.addLast("programmingLanguages");
        attributes.addLast("gpa");
        attributes.addLast("location");
        attributes.addLast("hired");
        t.checkExpect(TestSuite.candidates.getAttributes(), attributes);

        t.checkExpect(TestSuite.candidates.mostCommonValue("lastPositionDuration"), "1-2");                 //mostCommonValue() tests
        t.checkExpect(TestSuite.candidates.mostCommonValue("gender"), "Female");



        LinkedList<IAttributeDataset<Candidate>> results = TestSuite.candidates.partition("gender");                //tests both partition() and getSharedValue()
        t.checkExpect(results.get(0).getSharedValue("gender"), "Male");
        t.checkExpect(results.size(), 3);  // 3 partitions


        t.checkExpect(TestSuite.candidates.allSameValue("gender"), false);                                  //tests for allSameValue()
        t.checkExpect(results.get(0).allSameValue("gender"), true);


        t.checkExpect(TestSuite.candidates.rows.get(0).getValueOf("gender"), "Male");                   //tests for getValueOf()
        t.checkExpect(TestSuite.candidates.rows.get(1).getValueOf("gender"), "Female");











        }


    public static void main(String[] args){
        Tester.run(new TestSuite());
        }
}
