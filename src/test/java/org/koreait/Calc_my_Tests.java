package org.koreait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.PrinterResolution;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Calc_my_Tests {

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {assertThat(Calc_my.execute("1 + 1")).isEqualTo(2);}



}
