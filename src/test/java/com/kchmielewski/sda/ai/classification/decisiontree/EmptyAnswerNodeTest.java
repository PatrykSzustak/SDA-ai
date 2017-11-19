package com.kchmielewski.sda.ai.classification.decisiontree;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class EmptyAnswerNodeTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final DecisionTree<Object, Object> instance = EmptyAnswerNode.instance();

    @Test
    public void yesThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        instance.yes();
    }

    @Test
    public void noThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        instance.no();
    }

    @Test
    public void passingDecisionTreeToYesThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        instance.yes(instance);
    }

    @Test
    public void passingDecisionTreeToNoThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        instance.no(instance);
    }

    @Test
    public void askThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        instance.ask(new Object());
    }

}