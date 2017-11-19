package com.kchmielewski.sda.ai.classification.letter.trait;

import com.kchmielewski.sda.ai.classification.letter.Letter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(BlockJUnit4ClassRunner.class)
public class MiddleThirdToAllTest {
    private final Letter letter = mock(Letter.class);
    private final Trait trait = new MiddleThirdToAll();

    @Test
    public void forNinePixelImageWithMiddleThirdBlack1IsReturned() throws Exception {
        boolean[][] image = {
                {false, false, false},
                {true, true, true},
                {false, false, false}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(1);
    }

    @Test
    public void forNinePixelImageWholeBlackOneThirdIsReturned() throws Exception {
        boolean[][] image = {
                {true, true, true},
                {true, true, true},
                {true, true, true}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(1d / 3d);
    }

    @Test
    public void forFourPixelImageWholeWhite0IsReturned() throws Exception {
        boolean[][] image = {
                {false, false, false},
                {false, false, false},
                {false, false, false}};
        given(letter.getImage()).willReturn(image);

        assertThat(trait.calculate(letter)).isEqualTo(0);
    }
}