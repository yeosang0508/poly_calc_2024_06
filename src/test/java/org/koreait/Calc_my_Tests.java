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

    @Test
    @DisplayName("1 + 2 - 3 == 0")
    void t3() {
        assertThat(Calc_my.execute("1 + 2 - 3")).isEqualTo(0);
    }

    @Test
    @DisplayName("3 * 1 == 3")
    void t4() {
        assertThat(Calc_my.execute("3 * 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("3 * 1 * 4 == 12")
    void t5() {
        assertThat(Calc_my.execute("3 * 1 * 4")).isEqualTo(12);
    }


}
