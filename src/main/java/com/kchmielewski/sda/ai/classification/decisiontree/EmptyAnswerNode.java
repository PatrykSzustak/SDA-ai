package com.kchmielewski.sda.ai.classification.decisiontree;

public class EmptyAnswerNode<Input, Output> implements DecisionTree<Input, Output> {
    private static final EmptyAnswerNode<?, ?> INSTANCE = new EmptyAnswerNode<>();

    @SuppressWarnings("unchecked")
    static <Input, Output> EmptyAnswerNode<Input, Output> instance() {
        return (EmptyAnswerNode<Input, Output>) INSTANCE;
    }

    @Override
    public DecisionTree<Input, Output> yes() {
        throw new UnsupportedOperationException("EmptyAnswerNode cannot answer. It must be replaced with actual node.");
    }

    @Override
    public DecisionTree<Input, Output> no() {
        throw new UnsupportedOperationException("EmptyAnswerNode cannot have no branch. It must be replaced with actual node.");
    }

    @Override
    public void yes(DecisionTree<Input, Output> answer) {
        throw new UnsupportedOperationException("EmptyAnswerNode cannot have yes branch. It must be replaced with actual node.");
    }

    @Override
    public void no(DecisionTree<Input, Output> answer) {
        throw new UnsupportedOperationException("EmptyAnswerNode cannot have no branch. It must be replaced with actual node.");
    }

    @Override
    public Output ask(Input input) {
        throw new UnsupportedOperationException("EmptyAnswerNode cannot answer question. It must be replaced with actual node.");
    }
}
