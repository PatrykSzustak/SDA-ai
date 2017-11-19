package com.kchmielewski.sda.ai.classification.decisiontree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerNodeTest {
    private static final String ANSWER = "Some meaningful answer";

    private AnswerNode<Integer, String> answerNode = new AnswerNode<>(ANSWER);

    @Test
    public void forEmptyAnswerThrowsException() throws Exception {
        assertThatThrownBy(() -> new AnswerNode<>(null)).isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> answerNode.yes(answerNode)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void passingDecisionTreeToNoThrowsException() throws Exception {
        assertThatThrownBy(() -> answerNode.no(answerNode)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void askReturnsPredefinedAnswer() throws Exception {
        assertThat(answerNode.ask(123)).isEqualTo(ANSWER);
    }

}