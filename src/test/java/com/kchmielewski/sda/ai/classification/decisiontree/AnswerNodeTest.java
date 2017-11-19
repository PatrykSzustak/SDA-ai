package com.kchmielewski.sda.ai.classification.decisiontree;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerNodeTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final String ANSWER = "Some meaningful answer";

    private AnswerNode<Integer, String> answerNode = new AnswerNode<>(ANSWER);

    @Test
    public void forEmptyAnswerThrowsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new AnswerNode<>(null);
    }

    @Test
    public void yesReturnsEmptyNode() throws Exception {
        assertThat(answerNode.yes()).isSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void noReturnsEmptyNode() throws Exception {
        assertThat(answerNode.no()).isSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void passingDecisionTreeToYesThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        answerNode.yes(answerNode);
    }

    @Test
    public void passingDecisionTreeToNoThrowsException() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        answerNode.no(answerNode);
    }

    @Test
    public void askReturnsPredefinedAnswer() throws Exception {
        assertThat(answerNode.ask(123)).isEqualTo(ANSWER);
    }

}