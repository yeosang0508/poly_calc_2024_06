package org.koreait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Calc_my_Tests {

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        assertThat(Calc_my.execute("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("1 + 2 + 3 == 6")
    void t2() {
        assertThat(Calc_my.execute("1 + 2 + 3")).isEqualTo(6);
    }


}
