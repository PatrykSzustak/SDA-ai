package com.kchmielewski.sda.ai.classification.decisiontree;

public class AnswerNode<Input, Output> implements DecisionTree<Input, Output> {
    private final Output answer;

    public AnswerNode(Output answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null.");
        }
        this.answer = answer;
    }

    @Override
    public DecisionTree<Input, Output> yes() {
        return EmptyAnswerNode.instance();
    }

    @Override
    public DecisionTree<Input, Output> no() {
        return EmptyAnswerNode.instance();
    }

    @Override
    public void yes(DecisionTree<Input, Output> answer) {
        throw new UnsupportedOperationException("AnswerNode can only answer by itself. It cannot have other nodes set.");
    }

    @Override
    public void no(DecisionTree<Input, Output> answer) {
        throw new UnsupportedOperationException("AnswerNode can only answer by itself. It cannot have other nodes set.");
    }

    @Override
    public Output ask(Input input) {
        return answer;
    }
}
