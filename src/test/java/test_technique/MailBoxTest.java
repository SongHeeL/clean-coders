package test_technique;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MailBoxTest {
    @Test
    public void newMailBox_should_empty(){
        MailBox mailbox = new MailBox();
        assertThat(mailbox.messageCount(), is(0));
    }
}
