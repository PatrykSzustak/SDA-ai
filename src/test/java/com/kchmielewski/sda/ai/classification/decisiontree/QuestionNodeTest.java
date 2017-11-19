package com.kchmielewski.sda.ai.classification.decisiontree;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionNodeTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final QuestionNode<Integer, String> alwaysTrueNode = new QuestionNode<>((i) -> true);
    private final QuestionNode<Integer, String> alwaysFalseNode = new QuestionNode<>((i) -> false);

    @Test
    public void forEmptyQuestionThrowsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new QuestionNode<>(null);
    }

    @Test
    public void yesByDefaultDelegatesToEmptyAnswerNode() throws Exception {
        assertThat(alwaysTrueNode.yes()).isSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void noByDefaultDelegatesToEmptyAnswerNode() throws Exception {
        assertThat(alwaysTrueNode.no()).isSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void yesNoLongerDelegatesToEmptyAnswerNodeIfYesWasSet() throws Exception {
        alwaysTrueNode.yes(new AnswerNode<>(""));
        assertThat(alwaysTrueNode.yes()).isNotSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void noNoLongerDelegatesToEmptyAnswerNodeIfNoWasSet() throws Exception {
        alwaysTrueNode.no(new AnswerNode<>(""));
        assertThat(alwaysTrueNode.no()).isNotSameAs(EmptyAnswerNode.instance());
    }

    @Test
    public void ifAnswerIsYesDelegatesToYesNode() throws Exception {
        alwaysTrueNode.yes(new AnswerNode<>("YES YES YES"));
        assertThat(alwaysTrueNode.ask(1)).isSameAs("YES YES YES");
    }

    @Test
    public void ifAnswerIsNoDelegatesToYesNode() throws Exception {
        alwaysFalseNode.no(new AnswerNode<>("NO NO NO"));
        assertThat(alwaysFalseNode.ask(1)).isSameAs("NO NO NO");
    }

    @Test
    public void ifAnswerIsYesAndYesIsSetItIsEvaluated() throws Exception {
        alwaysTrueNode.yes(alwaysFalseNode);
        alwaysFalseNode.no(new AnswerNode<>("Nobody expects the Spanish Inquisition"));
        assertThat(alwaysTrueNode.ask(1)).isSameAs("Nobody expects the Spanish Inquisition");
    }
}