import org.junit.jupiter.api.Test;

class TestThings {

    @Test
    void test() {
        String regex = "[0-9]+";
        String data = "0023343453";
        System.out.println(data.matches(regex));

        data = "23d343453";
        System.out.println(data.matches(regex));

        data = "";
        System.out.println(data.matches(regex));

        data = " 32423423";
        System.out.println(data.matches(regex));
    }
}
