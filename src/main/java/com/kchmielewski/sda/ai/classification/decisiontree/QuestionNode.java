package com.kchmielewski.sda.ai.classification.decisiontree;

import java.util.function.Predicate;

public class QuestionNode<Input, Output> implements DecisionTree<Input, Output> {
    private final Predicate<Input> question;
    private DecisionTree<Input, Output> yes = EmptyAnswerNode.instance();
    private DecisionTree<Input, Output> no = EmptyAnswerNode.instance();

    public QuestionNode(Predicate<Input> question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null.");
        }
        this.question = question;
    }

    @Override
    public DecisionTree<Input, Output> yes() {
        return yes;
    }

    @Override
    public DecisionTree<Input, Output> no() {
        return no;
    }

    @Override
    public void yes(DecisionTree<Input, Output> answer) {
        yes = answer;
    }

    @Override
    public void no(DecisionTree<Input, Output> answer) {
        no = answer;
    }

    @Override
    public Output ask(Input input) {
        return question.test(input) ? yes.ask(input) : no.ask(input);
    }
}
