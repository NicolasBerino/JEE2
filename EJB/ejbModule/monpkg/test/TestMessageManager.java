package monpkg.test;

import static org.junit.Assert.assertNotNull;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import monpkg.services.MessageManager;
import org.junit.Test;

public class TestMessageManager {

    @EJB
    MessageManager messageManager;

    public TestMessageManager() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
        assertNotNull(messageManager);
    }

    @Test
    public void testAddMessage() throws Exception {
        messageManager.addMessage("Hello");
        messageManager.addMessage("Salut");
    }

}