package com.kchmielewski.sda.ai.classification.decisiontree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(BlockJUnit4ClassRunner.class)
public class EmptyAnswerNodeTest {
    private final DecisionTree<Object, Object> instance = EmptyAnswerNode.instance();

    @Test
    public void yesThrowsException() throws Exception {
        assertThatThrownBy(instance::yes).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void noThrowsException() throws Exception {
        assertThatThrownBy(instance::no).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void passingDecisionTreeToYesThrowsException() throws Exception {
        assertThatThrownBy(() -> instance.yes(instance)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void passingDecisionTreeToNoThrowsException() throws Exception {
        assertThatThrownBy(() -> instance.no(instance)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void askThrowsException() throws Exception {
        assertThatThrownBy(() -> instance.ask(new Object())).isInstanceOf(UnsupportedOperationException.class);
    }

}