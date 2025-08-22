package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.LinkUtil.formatLink;

class LinkUtilTest {

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isLinkFormated(String input, String expected) {
        assertEquals(expected, formatLink(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("Открытка с [Unicon 2025|https://www.unicon.lv/participate] в Латвии",
                        "Открытка с <a href=\"https://www.unicon.lv/participate\" target=\"_blank\">Unicon 2025</a> в Латвии"),
                Arguments.of("[parcsama.es|https://parcsama.es/parc-sama-en/]",
                        "<a href=\"https://parcsama.es/parc-sama-en/\" target=\"_blank\">parcsama.es</a>")
        );
    }
}