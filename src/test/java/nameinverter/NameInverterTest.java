package nameinverter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NameInverterTest {
    private NamerInverter namerInverter = new NamerInverter();
    @Test
    public void name(){
        assertThat(namerInverter.invert(null), is(""));
        assertThat(namerInverter.invert(""), is(""));
        assertThat(namerInverter.invert("name"), is("name"));
        assertThat(namerInverter.invert("first last"), is("last, first"));
        assertThat(namerInverter.invert("   name    "), is("name"));
        assertThat(namerInverter.invert("first     last"), is("last, first"));
        assertThat(namerInverter.invert("Mr. first last"), is("last, first"));
        assertThat(namerInverter.invert("Mrs. first last"), is("last, first"));
        assertThat(namerInverter.invert("first last SR."), is("last, first SR."));
        assertThat(namerInverter.invert("first last SR. Phd."), is("last, first SR. Phd."));
        assertThat(namerInverter.invert("  Rober  Martin II esq."), is("Martin, Rober II esq."));
    }
}
