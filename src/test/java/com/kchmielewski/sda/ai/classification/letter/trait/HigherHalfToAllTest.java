package com.kchmielewski.sda.ai.classification.letter.trait;

import com.kchmielewski.sda.ai.classification.letter.Letter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(BlockJUnit4ClassRunner.class)
public class HigherHalfToAllTest {
    private final Letter letter = mock(Letter.class);
    private final Trait trait = new HigherHalfToAll();

    @Test
    public void forFourPixelImageWithHigherHalfBlack1IsReturned() throws Exception {
        boolean[][] image = {
                {true, true},
                {false, false}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(1);
    }

    @Test
    public void forFourPixelImageWholeBlack05IsReturned() throws Exception {
        boolean[][] image = {
                {true, true},
                {true, true}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(0.5);
    }

    @Test
    public void forFourPixelImageWholeWhite0IsReturned() throws Exception {
        boolean[][] image = {
                {false, false},
                {false, false}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(0);
    }
}